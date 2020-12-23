/*******************************************************************************
 * Copyright (c) 2019, 2020 Obeo.
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
import { useLazyQuery, useMutation, useSubscription } from '@apollo/client';
import { Text } from 'core/text/Text';
import {
  COMPLETE__STATE,
  HANDLE_COMPLETE__ACTION,
  HANDLE_DATA__ACTION,
  HANDLE_ERROR__ACTION,
  INITIALIZE__ACTION,
  LOADING__STATE,
  READY__STATE,
  SELECTED_ELEMENT__ACTION,
  SELECTION__ACTION,
  SELECT_ZOOM_LEVEL__ACTION,
  SET_ACTIVE_TOOL__ACTION,
  SET_CONTEXTUAL_PALETTE__ACTION,
  SET_DEFAULT_TOOL__ACTION,
  SET_TOOL_SECTIONS__ACTION,
  SWITCH_REPRESENTATION__ACTION,
} from 'diagram/machine';
import { ContextualPalette } from 'diagram/palette/ContextualPalette';
import { initialState, reducer } from 'diagram/reducer';
import { edgeCreationFeedback } from 'diagram/sprotty/edgeCreationFeedback';
import {
  ACTIVE_TOOL_ACTION,
  HIDE_CONTEXTUAL_TOOLBAR_ACTION,
  SIRIUS_SELECT_ACTION,
  SIRIUS_UPDATE_MODEL_ACTION,
  SOURCE_ELEMENT_ACTION,
  SPROTTY_SELECT_ACTION,
  ZOOM_IN_ACTION,
  ZOOM_OUT_ACTION,
  ZOOM_TO_ACTION,
} from 'diagram/sprotty/WebSocketDiagramServer';
import { Toolbar } from 'diagram/Toolbar';
import PropTypes from 'prop-types';
import React, { useCallback, useEffect, useReducer, useRef } from 'react';
import 'reflect-metadata'; // Required because Sprotty uses Inversify and both frameworks are written in TypeScript with experimental features.
import { SEdge, SNode, FitToScreenAction, EditLabelAction } from 'sprotty';
import styles from './Diagram.module.css';
import {
  deleteFromDiagramMutation,
  diagramEventSubscription,
  editLabelMutation as editLabelMutationOp,
  getToolSectionsQuery,
  invokeEdgeToolOnDiagramMutation,
  invokeNodeToolOnDiagramMutation,
} from './operations';

const propTypes = {
  projectId: PropTypes.string.isRequired,
  representationId: PropTypes.string.isRequired,
  readOnly: PropTypes.bool.isRequired,
  selection: PropTypes.shape({
    id: PropTypes.string.isRequired,
    label: PropTypes.string.isRequired,
    kind: PropTypes.string.isRequired,
  }),
  setSelection: PropTypes.func.isRequired,
  setSubscribers: PropTypes.func.isRequired,
};

/**
 * Here be dragons!
 *
 * If you want to modify this component, you need to understand this documentation first and foremost.
 * Any change to this component which involve a change to the reducer, its machine or one of the useEffect
 * functions will REQUIRE an update to this documentation. The lifecycle of this component is not perfect
 * and it can still be dramatically improved. Yet, we have currently a stable situation which can be
 * documented.
 *
 * This component can be in one of four states:
 * - EMPTY__STATE
 * - LOADING__STATE
 * - READY__STATE
 * - COMPLETE__STATE
 *
 * Almost a dozen variables are involved to determine whether or not we should transition from one state
 * to another. The EMPTY__STATE is used to represent a state where this component is blank and not
 * connected to any GraphQL subscription. In this state, the component is not even connected to a specific
 * representation. This state is temporary and will not persist for long since immediatly after starting
 * from this state, we will transition to the LOADING__STATE. This first state is thus used in order to
 * trigger the initial transition of the state machine in a similar fashion as some future transitions.
 *
 * From the LOADING__STATE, the component will have only one goal, to perform the initialization of the
 * diagram server. Once initialized the component will move to the READY__STATE. In the READY__STATE, we
 * will start the GraphQL subscription and listen to diagram events coming from the server. In this state,
 * the various callbacks defined in this component and used by our Sprotty code can be used safely.
 *
 * If we receive a connection error while starting the GraphQL subscription or if we encounter a GraphQL-WS
 * error or complete message, we will move to the COMPLETE__STATE. In this state, we know that the diagram
 * being displayed cannot be displayed anymore. The diagram has simply been deleted by someone (the current
 * user or another user) or the diagram has never existed (if someone has entered an invalid URL for example).
 * In this case, we will carefully remove the Sprotty diagram from the component (see more about that in a
 * large comment below). Once in the COMPLETE__STATE, we will only be able to perform one transition, switching
 * to another representation. Such transition will make us switch to the LOADING__STATE to wait for the
 * initialization of a new diagram server instance.
 *
 * Here are some use cases showing how the component should react:
 *
 * 1/ Manipulating diagrams
 *
 * In the default use case, a user will open a diagram, view it and iteract with it. For that, we will
 * start in the EMPTY__STATE, once a first empty rendering has been done and the React ref has been
 * initialized, we will move to the initialization of the diagram server. When the diagram server has
 * been initialized, we will start the GraphQL subscription.
 *
 * When we will receive some data, the user will finally be able to see a diagram on screen and interact
 * with it. Most of the work of a user will be done in this READY__STATE. From there, we have a working
 * diagram server ready to interact with Sprotty.
 *
 * +-----------------+                                +-------------------+                                        +-----------------+
 * |                 |                                |                   |                                        |                 |
 * |   EMPTY STATE   +---[SWITCHING REPRESENTATION]-->|   LOADING STATE   +---[INITIALIZING THE DIAGRAM SERVER]--->|   READY STATE   |
 * |                 |                                |                   |                                        |                 |
 * +-----------------+                                +-------------------+                                        +-----------------+
 *
 * 2/ Switching between diagrams
 *
 * When we will switch from one diagram to another using either our tabs, the explorer or by changing the
 * URL. First, we will have a diagram loaded by the user (see the previous section) so here we will start
 * with the READY__STATE. Switching to another diagram will make us initialize everything once again from
 * scratch. This new initialization step is required in order to have the proper representationId in the
 * closure of our callbacks used by Sprotty to perform our mutations. While this could be simplified,
 * keep in mind that switching to another diagram requires initializing the diagram server.
 *
 * +-----------------+                                 +-------------------+                                        +-----------------+
 * |                 |                                 |                   |                                        |                 |
 * |   READY STATE   +---[SWITCHING REPRESENTATION]--->|   LOADING STATE   +---[INITIALIZING THE DIAGRAM SERVER]--->|   READY STATE   |
 * |                 |                                 |                   |                                        |                 |
 * +-----------------+                                 +-------------------+                                        +-----------------+
 *
 * Switching from one diagram to another or switching from no diagram to the first selected diagram are
 * thus performed in a similar way.
 *
 * 3/ Reacting to the diagram deletion
 *
 * When a diagram is deleted, the backend will send us a complete message indicating that we will never
 * ever receive anymore diagram refresh events for the diagram. Once that message has been received, we
 * will switch to the COMPLETE__STATE and remove the diagram from the interface.
 *
 * +-----------------+                                   +--------------------+
 * |                 |                                   |                    |
 * |   READY STATE   +---[RECEIVE A COMPLETE MESSAGE]--->|   COMPLETE STATE   |
 * |                 |                                   |                    |
 * +-----------------+                                   +--------------------+
 *
 * The same behavior would occur in case of an error or a connection error since those are errors we cannot
 * recover from.
 *
 * 4/ Switching to another diagram
 *
 * When we are working on a proper diagram and switching to a diagram which does not exist anymore or
 * when we are trying to open a deleted diagram, we will start from either the READY__STATE in case we
 * were viewing an existing diagram or from the COMPLETE__STATE if we were viewing a deleted or non-existent
 * diagram. In those situations, we will start by going back to the LOADING__STATE to wait for the diagram
 * server initialization.
 *
 * Once initialized, the diagram server will receive a complete message indicating that we will never ever
 * receive any diagram refresh event for the given representationId and thus that the diagram does not exist.
 * The server cannot distinguish a representation which has never existed and a representation which has
 * been deleted.
 *
 * The only way to properly deactivate Sprotty is both to remove the diagram server and to remove the base
 * div used to display the diagram. This div can only be removed by React thanks to a carefully arranged
 * organization. This behavior is documented later in this component.
 *
 * +-----------------+
 * |                 |
 * |   READY STATE   +------+
 * |                 |      |                                +-------------------+                                        +-----------------+
 * +-----------------+      |                                |                   |                                        |                 |
 *                          +--[SWITCHING REPRESENTATION]--->|   LOADING STATE   +---[INITIALIZING THE DIAGRAM SERVER]--->|   READY STATE   |
 * +--------------------+   |                                |                   |                                        |                 |
 * |                    |   |                                +-------------------+                                        +-----------------+
 * |   COMPLETE STATE   +---+
 * |                    |
 * +--------------------+
 *
 * Then after having reach the READY__STATE, we will receive a complete message in case we are trying to
 * view a diagram which does not exist or which has been deleted. Deleting a diagram by ourserlves, trying
 * to view a diagram deleted by another person or trying to view a diagram which has never existed are
 * exactly the same thing.
 *
 * 5/ Switching from a deleted diagram to another diagram
 *
 * If we are viewing a deleted diagram, we will have reach the COMPLETE__STATE. In order to display another
 * diagram, we will need to initialize the Sprotty base div once again. For that, we will need to display
 * it and connect it to React with useRef() once again. In order to perform that, we will need to move back
 * to the LOADING__STATE once again.
 *
 * +--------------------+                                 +-------------------+
 * |                    |                                 |                   |
 * |   COMPLETE STATE   +---[SWITCHING REPRESENTATION]--->|   LOADING STATE   |
 * |                    |                                 |                   |
 * +--------------------+                                 +-------------------+
 *
 * Once the diagram server has been initialized, we will either start receiving proper diagram data and thus
 * switch to the diagram loaded state or we will receive a complete message and switch back to the complete
 * state since we would have switched to another deleted diagram.
 *
 *
 * As you may have understood it, the lifecycle of this component has evolved organically due to the complexity
 * of managing multiple useEffect hooks, user interactions and Websocket messages. The current organization is
 * not perfect but some proper states are starting to emerge from this. We have something like six potentially
 * asynchronous processes to schedule properly with close to a dozen variables which should be in a proper state
 * in each situation. This is complex but it is getting better updates after updates for the moment.
 *
 * @author sbegaudeau
 */
export const DiagramWebSocketContainer = ({
  projectId,
  representationId,
  readOnly,
  selection,
  setSelection,
  setSubscribers,
}) => {
  const diagramDomElement = useRef(null);

  const [state, dispatch] = useReducer(reducer, initialState);

  const {
    viewState,
    displayedRepresentationId,
    diagramServer,
    diagram,
    toolSections,
    contextualPalette,
    activeTool,
    newSelection,
    zoomLevel,
    subscribers,
    message,
  } = state;

  const [deleteElementsMutation] = useMutation(deleteFromDiagramMutation);
  const [invokeNodeToolMutation] = useMutation(invokeNodeToolOnDiagramMutation);
  const [invokeEdgeToolMutation] = useMutation(invokeEdgeToolOnDiagramMutation);
  const [editLabelMutation] = useMutation(editLabelMutationOp);
  const [getToolSectionData, { loading: toolSectionLoading, data: toolSectionData }] = useLazyQuery(
    getToolSectionsQuery
  );
  /**
   * We have choose to make only one query by diagram to get tools to avoid network flooding.
   * In consequence, a tool must contains all necessary properties to be filtered on a specific context (In the contextual palette for example).
   * The query to get tool sections depends on the representationId and we use a React useEffect to match this workflow.
   * For each update of the representationId value, we will redo a query and update tools.
   */
  useEffect(() => {
    getToolSectionData({ variables: { diagramId: representationId } });
  }, [representationId, getToolSectionData]);
  /**
   * Dispatch the diagram to the diagramServer if our state indicate that diagram has changed.
   */
  useEffect(() => {
    if (diagramServer) {
      diagramServer.actionDispatcher.dispatch({ kind: SIRIUS_UPDATE_MODEL_ACTION, diagram });
    }
  }, [diagram, diagramServer]);

  /**
   * Dispatch the activeTool to the diagramServer if our state indicate that activeTool has changed.
   */
  useEffect(() => {
    if (diagramServer) {
      diagramServer.actionDispatcher.dispatch({ kind: ACTIVE_TOOL_ACTION, tool: activeTool });
    }
  }, [activeTool, diagramServer]);

  /**
   * Dispatch the selection if our props indicate that selection has changed.
   */
  useEffect(() => {
    if (viewState === READY__STATE) {
      dispatch({ type: SELECTION__ACTION, selection });
    }
  }, [selection, diagramServer, viewState]);

  /**
   * Dispatch the new selection to the diagramServer if our state indicate that new selection has changed.
   */
  useEffect(() => {
    if (diagramServer && newSelection) {
      diagramServer.actionDispatcher.dispatch({ kind: SIRIUS_SELECT_ACTION, selection: newSelection });
    }
  }, [newSelection, diagramServer]);

  /**
   * Switch to another diagram if our props indicate that we should display a different diagram
   * then the one currently displayed. This will be used to start displaying a diagram from nothing
   * and to reset everything while switching from one diagram to another.
   *
   * This will bring us to the loading state in which the diagram server will have to be reinitialized.
   */
  useEffect(() => {
    if (displayedRepresentationId !== representationId) {
      dispatch({ type: SWITCH_REPRESENTATION__ACTION, representationId });
    }
  }, [displayedRepresentationId, representationId]);

  const deleteElements = useCallback(
    (diagramElements) => {
      const edgeIds = diagramElements.filter((diagramElement) => diagramElement instanceof SEdge).map((elt) => elt.id);
      const nodeIds = diagramElements.filter((diagramElement) => diagramElement instanceof SNode).map((elt) => elt.id);

      const input = {
        projectId,
        representationId,
        nodeIds,
        edgeIds,
      };
      deleteElementsMutation({ variables: { input } });
      dispatch({ type: SET_CONTEXTUAL_PALETTE__ACTION, contextualPalette: undefined });
    },
    [projectId, representationId, deleteElementsMutation]
  );

  const invokeTool = useCallback(
    (tool, ...params) => {
      if (tool) {
        const { id: toolId } = tool;
        if (tool.__typename === 'CreateEdgeTool') {
          const [diagramSourceElementId, diagramTargetElementId] = params;

          const input = {
            projectId,
            representationId,
            diagramSourceElementId,
            diagramTargetElementId,
            toolId,
          };
          invokeEdgeToolMutation({ variables: { input } });
          edgeCreationFeedback.reset();
        } else {
          const [diagramElementId] = params;

          const input = {
            projectId,
            representationId,
            diagramElementId,
            toolId,
          };
          invokeNodeToolMutation({ variables: { input } });
        }
        dispatch({ type: SET_ACTIVE_TOOL__ACTION });
      }
    },
    [projectId, representationId, invokeNodeToolMutation, invokeEdgeToolMutation]
  );

  /**
   * Initialize the diagram server used by Sprotty in order to perform the diagram edition. This
   * initialization will be done each time we are in the loading state.
   */
  useEffect(() => {
    const onSelectElement = (newSelectedElement, diagServer) => {
      let newSelection;
      if (newSelectedElement.root.id === newSelectedElement.id) {
        const { id, label, kind } = newSelectedElement;
        newSelection = { id, label, kind };
      } else {
        const { targetObjectId, targetObjectKind, targetObjectLabel } = newSelectedElement;
        newSelection = {
          id: targetObjectId,
          label: targetObjectLabel,
          kind: targetObjectKind,
        };
      }
      setSelection(newSelection);
      dispatch({ type: SELECTED_ELEMENT__ACTION, selection: newSelection });
      /**
       * Dispatch the selected element to the diagramServer if our state indicate that selected element has changed.
       * We can't use useEffet hook here, because SPROTTY_SELECT_ACTION must be send to SiriusWebWebSocketDiagramServer even
       * if the same element is selected several times (useEffect hook only reacts if the selected element is not the same).
       * We can also not use diagramServer from the reducer state here, because it is undefined when onSelectElement() is called.
       */
      diagServer.actionDispatcher.dispatch({ kind: SPROTTY_SELECT_ACTION, element: newSelectedElement });
    };
    const editLabel = (labelId, newText) => {
      const input = {
        projectId,
        representationId,
        labelId,
        newText,
      };
      editLabelMutation({ variables: { input } });
    };
    const setContextualPalette = (contextualPalette) => {
      if (!readOnly) {
        dispatch({ type: SET_CONTEXTUAL_PALETTE__ACTION, contextualPalette });
      }
    };

    if (viewState === LOADING__STATE && diagramDomElement.current) {
      dispatch({
        type: INITIALIZE__ACTION,
        diagramDomElement,
        deleteElements,
        invokeTool,
        editLabel,
        onSelectElement,
        toolSections,
        setContextualPalette,
      });
    }
  }, [
    viewState,
    displayedRepresentationId,
    diagramServer,
    setSelection,
    deleteElements,
    invokeTool,
    editLabelMutation,
    toolSections,
    selection,
    projectId,
    representationId,
    readOnly,
  ]);

  useEffect(() => {
    if (!toolSectionLoading && viewState === READY__STATE) {
      if (toolSectionData?.viewer?.toolSections) {
        dispatch({
          type: SET_TOOL_SECTIONS__ACTION,
          toolSections: toolSectionData.viewer.toolSections,
        });
      }
    }
  }, [toolSectionLoading, toolSectionData, viewState]);

  const { error } = useSubscription(diagramEventSubscription, {
    variables: {
      input: {
        projectId,
        diagramId: representationId,
      },
    },
    fetchPolicy: 'no-cache',
    skip: viewState !== READY__STATE,
    onSubscriptionData: ({ subscriptionData }) => {
      dispatch({ type: HANDLE_DATA__ACTION, message: subscriptionData });
    },
    onSubscriptionComplete: () => dispatch({ type: HANDLE_COMPLETE__ACTION }),
    shouldResubscribe: ({ variables: { input } }) =>
      input.projectId !== projectId || input.diagramId !== representationId,
  });
  if (error) {
    dispatch({ type: HANDLE_ERROR__ACTION, message: error });
  }

  /**
   * Each time the list of subscribers is updated, this will trigger the listener used to display the
   * subscribers outside of this component.
   */
  useEffect(() => {
    setSubscribers(subscribers);
  }, [setSubscribers, subscribers]);

  const onZoomIn = () => {
    if (diagramServer) {
      diagramServer.actionDispatcher.dispatch({ kind: ZOOM_IN_ACTION });
    }
  };

  const onZoomOut = () => {
    if (diagramServer) {
      diagramServer.actionDispatcher.dispatch({ kind: ZOOM_OUT_ACTION });
    }
  };

  const onFitToScreen = () => {
    if (diagramServer) {
      diagramServer.actionDispatcher.dispatch(new FitToScreenAction([], 20));
    }
  };

  const setZoomLevel = (level) => {
    if (diagramServer) {
      diagramServer.actionDispatcher.dispatch({ kind: ZOOM_TO_ACTION, level: level });
      dispatch({ type: SELECT_ZOOM_LEVEL__ACTION, level: level });
    }
  };

  /**
   * Gather up, it's time for a story.
   *
   * Once upon a time, the following code was victim of a painful bug. The first version of the bug caused
   * some content to remain even after we told React to remove it. The issue was coming from the fact we
   * were giving control of one of our divs to Sprotty, the div with "ref={diagramElement}". Sprotty seems
   * to heavily modify this div, including it seems destroying it and recreating it. This is not unsurprising
   * since we told it that it is under its control.
   *
   * Now when we need to remove a diagram, because it does not exist anymore, we need to remove this content.
   * An issue arise because we told React to remove the div in question but React has kept a reference to
   * the original version of the div that it has rendered. Now Sprotty has replaced that div with a new one
   * and thus when React tries to delete the content it tries to remove a div from a parent div but the div
   * in question has been long gone from its parent. As a result, a DOMException occurred, warning us that
   * "the node to be removed is not a child of this node".
   *
   * In order to fix this issue, two intermediary divs have been introduced with the id "diagram-container"
   * and "diagram-wrapper". The goal of diagram container is to act as the stable React parent div from which
   * we will remove some content. The diagram wapper will act as the stable child div which will be removed
   * when the content is not available anymore. React does not seems to care that under this div there are
   * some content which is not under its control anymore. Telling React to delete the diagram wrapper will
   * make it drop the whole DOM subtree without looking precisely at its content. It's faster for React and
   * better for us since it allows us to fix our issue.
   *
   * And thus, those various divs lived long and happily ever after...
   *
   * TLDR: Do not touch the div structure below without a deep understanding of the React reconciliation algorithm!
   */
  let contextualPaletteContent;
  if (!readOnly && contextualPalette) {
    const { element, canvasBounds, origin, renameable, deletable } = contextualPalette;
    const { x, y } = origin;
    const invokeCloseFromContextualPalette = () => dispatch({ type: SET_CONTEXTUAL_PALETTE__ACTION });
    const style = {
      left: canvasBounds.x + 'px',
      top: canvasBounds.y + 'px',
    };
    let invokeLabelEditFromContextualPalette;
    if (renameable) {
      let elementIdSuffix = element instanceof SEdge ? '_centerlabel' : '_label';
      invokeLabelEditFromContextualPalette = () =>
        diagramServer.actionDispatcher.dispatchAll([
          { kind: HIDE_CONTEXTUAL_TOOLBAR_ACTION },
          new EditLabelAction(element.id + elementIdSuffix),
        ]);
    }
    let invokeDeleteFromContextualPalette;
    if (deletable) {
      invokeDeleteFromContextualPalette = () => deleteElements([element]);
    }
    const invokeToolFromContextualPalette = (tool) => {
      if (tool.__typename === 'CreateEdgeTool') {
        dispatch({ type: SET_ACTIVE_TOOL__ACTION, activeTool: tool });
        dispatch({ type: SET_CONTEXTUAL_PALETTE__ACTION });
        edgeCreationFeedback.init(x, y);
        diagramServer.actionDispatcher.dispatch({ kind: SOURCE_ELEMENT_ACTION, sourceElement: element });
      } else if (tool.__typename === 'CreateNodeTool') {
        invokeTool(tool, element.id);
        diagramServer.actionDispatcher.dispatch({ kind: SOURCE_ELEMENT_ACTION });
      }
      dispatch({ type: SET_DEFAULT_TOOL__ACTION, defaultTool: tool });
    };
    contextualPaletteContent = (
      <div className={styles.contextualPalette} style={style}>
        <ContextualPalette
          toolSections={toolSections}
          targetElement={element}
          invokeTool={invokeToolFromContextualPalette}
          invokeLabelEdit={invokeLabelEditFromContextualPalette}
          invokeDelete={invokeDeleteFromContextualPalette}
          invokeClose={invokeCloseFromContextualPalette}></ContextualPalette>
      </div>
    );
  }
  let content = (
    <div id="diagram-container" className={styles.diagramContainer}>
      <div id="diagram-wrapper" className={styles.diagramWrapper}>
        <div ref={diagramDomElement} id="diagram" className={styles.diagram} />
        {contextualPaletteContent}
      </div>
    </div>
  );

  if (viewState === COMPLETE__STATE) {
    content = (
      <div id="diagram-container" className={styles.diagramContainer + ' ' + styles.noDiagram}>
        <Text className={styles.noDiagramLabel} data-testid="diagram-complete-message">
          {message}
        </Text>
      </div>
    );
  }

  return (
    <div className={styles.container}>
      <Toolbar
        onZoomIn={onZoomIn}
        onZoomOut={onZoomOut}
        onFitToScreen={onFitToScreen}
        setZoomLevel={setZoomLevel}
        zoomLevel={zoomLevel}
      />
      {content}
    </div>
  );
};
DiagramWebSocketContainer.propTypes = propTypes;
