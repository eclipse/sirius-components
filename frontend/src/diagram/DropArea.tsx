/*******************************************************************************
 * Copyright (c) 2019, 2021 Obeo.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
import { useMutation } from '@apollo/client';
import IconButton from '@material-ui/core/IconButton';
import Snackbar from '@material-ui/core/Snackbar';
import CloseIcon from '@material-ui/icons/Close';
import { useMachine } from '@xstate/react';
import { canDrop } from 'diagram/toolServices';
import PropTypes from 'prop-types';
import React, { useEffect } from 'react';
import styles from './DropArea.module.css';
import {
  DropAreaContext,
  dropAreaMachine,
  DropEvent,
  HandleDropEvent,
  HandleResponseEvent,
  HideToastEvent,
  SchemaValue,
  ShowToastEvent,
} from './DropAreaMachine';
import { invokeDropToolOnDiagramMutation } from './operations';

const DATA_DIAGRAM_ID = 'data-diagramid';

const DATA_DESCRIPTION_ID = 'data-descriptionid';

const DATA_NODE_ID = 'data-nodeid';

const SVG_NAMESPACE = 'http://www.w3.org/2000/svg';

const toolType = PropTypes.shape({
  id: PropTypes.string.isRequired,
  label: PropTypes.string.isRequired,
  imageURL: PropTypes.string,
});
const propTypes = {
  projectId: PropTypes.string.isRequired,
  representationId: PropTypes.string.isRequired,
  toolSections: PropTypes.arrayOf(
    PropTypes.shape({
      label: PropTypes.string.isRequired,
      tools: PropTypes.arrayOf(toolType).isRequired,
      defaultTool: toolType.isRequired,
    })
  ).isRequired,
  invokeHover: PropTypes.func.isRequired,
  children: PropTypes.node.isRequired,
};

export const DropArea = ({ projectId, representationId, toolSections, invokeHover, children }) => {
  const [{ value, context }, dispatch] = useMachine<DropAreaContext, DropEvent>(dropAreaMachine);
  const { toast } = value as SchemaValue;
  const { message } = context;
  const [invokeDropTool, { loading, error, data }] = useMutation(invokeDropToolOnDiagramMutation);

  useEffect(() => {
    if (!loading) {
      if (error) {
        const showToastEvent: ShowToastEvent = {
          type: 'SHOW_TOAST',
          message: 'An unexpected error has occurred, please refresh the page',
        };
        dispatch(showToastEvent);
      }
      if (data) {
        const handleResponseEvent: HandleResponseEvent = { type: 'HANDLE_RESPONSE', data };
        dispatch(handleResponseEvent);

        const typename = data.invokeDropToolOnDiagram.__typename;
        if (typename === 'ErrorPayload') {
          const { message } = data.invokeDropToolOnDiagram;
          const showToastEvent: ShowToastEvent = { type: 'SHOW_TOAST', message };
          dispatch(showToastEvent);
        }
      }
    }
  }, [loading, error, data, dispatch]);

  const dropElement = (tool: any, objectId: string, diagramElementId?: string) => {
    const { id: toolId } = tool;
    const input = {
      projectId,
      representationId,
      objectId,
      toolId,
    };
    if (diagramElementId) {
      input['diagramElementId'] = diagramElementId;
    }

    const variables = {
      input,
    };
    const handleDropEvent: HandleDropEvent = { type: 'HANDLE_DROP' };
    dispatch(handleDropEvent);
    invokeDropTool({ variables });
  };

  const searchId = (dom) => {
    if (dom.hasAttribute(DATA_DIAGRAM_ID)) {
      return dom.getAttribute(DATA_DIAGRAM_ID);
    } else if (dom.hasAttribute(DATA_NODE_ID)) {
      return dom.getAttribute(DATA_NODE_ID);
    } else if (dom.parentElement) {
      const parentDom = dom.parentElement;
      if (parentDom.namespaceURI === SVG_NAMESPACE) {
        return searchId(parentDom);
      }
    }
    return null;
  };

  const searchDescriptionId = (dom) => {
    if (dom.hasAttribute(DATA_DESCRIPTION_ID)) {
      return dom.getAttribute(DATA_DESCRIPTION_ID);
    } else if (dom.parentElement) {
      const parentDom = dom.parentElement;
      if (parentDom.namespaceURI === SVG_NAMESPACE) {
        return searchDescriptionId(parentDom);
      }
    }
    return null;
  };

  const searchDropTool = (targetId, targetDescriptionId) => {
    let firstValidDropTool;
    for (let toolSection of toolSections) {
      firstValidDropTool = toolSection.tools.find((tool) =>
        canDrop(tool, targetId, targetDescriptionId, representationId)
      );
      if (firstValidDropTool) {
        break;
      }
    }
    return firstValidDropTool;
  };

  const handleDragOver = (e) => {
    e.preventDefault();
    const id = searchId(e.target);
    invokeHover(id, true);
    const descriptionId = searchDescriptionId(e.target);
    const tool = searchDropTool(id, descriptionId);
    if (tool) {
      e.dataTransfer.dropEffect = 'link';
    }
  };

  const handleDragLeave = (e) => {
    e.preventDefault();
    const id = searchId(e.target);
    invokeHover(id, false);
  };

  const handleDrop = (e) => {
    e.preventDefault();
    const objectId = e.dataTransfer.getData('id');

    const id = searchId(e.target);
    const descriptionId = searchDescriptionId(e.target);
    const tool = searchDropTool(id, descriptionId);

    if (tool && objectId) {
      const diagramElementId = searchId(e.target);
      if (diagramElementId) {
        dropElement(tool, objectId, diagramElementId);
      } else {
        dropElement(tool, objectId);
      }
    }
  };

  return (
    <div
      className={styles.dropArea}
      onDrop={(e) => handleDrop(e)}
      onDragOver={(e) => handleDragOver(e)}
      onDragLeave={(e) => handleDragLeave(e)}>
      <Snackbar
        anchorOrigin={{
          vertical: 'bottom',
          horizontal: 'right',
        }}
        open={toast === 'visible'}
        autoHideDuration={3000}
        onClose={() => dispatch({ type: 'HIDE_TOAST' } as HideToastEvent)}
        message={message}
        action={
          <IconButton
            size="small"
            aria-label="close"
            color="inherit"
            onClick={() => dispatch({ type: 'HIDE_TOAST' } as HideToastEvent)}>
            <CloseIcon fontSize="small" />
          </IconButton>
        }
        data-testid="error"
      />
      {children}
    </div>
  );
};
DropArea.propTypes = propTypes;
