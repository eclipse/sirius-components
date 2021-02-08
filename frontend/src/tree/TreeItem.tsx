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
import { useMutation } from '@apollo/client';
import { httpOrigin } from 'common/URL';
import { IconButton } from 'core/button/Button';
import { Text } from 'core/text/Text';
import { Textfield } from 'core/textfield/Textfield';
import gql from 'graphql-tag';
import { ArrowCollapsed, ArrowExpanded, More, NoIcon } from 'icons';
import { DeleteDocumentModal } from 'modals/delete-document/DeleteDocumentModal';
import { NewObjectModal } from 'modals/new-object/NewObjectModal';
import { NewRepresentationModal } from 'modals/new-representation/NewRepresentationModal';
import { NewRootObjectModal } from 'modals/new-root-object/NewRootObjectModal';
import PropTypes from 'prop-types';
import React, { useEffect, useRef, useState } from 'react';
import { TreeItemDiagramContextMenu } from 'tree/TreeItemDiagramContextMenu';
import { TreeItemDocumentContextMenu } from 'tree/TreeItemDocumentContextMenu';
import { TreeItemObjectContextMenu } from 'tree/TreeItemObjectContextMenu';
import styles from './TreeItem.module.css';

const deleteObjectMutation = gql`
  mutation deleteObject($input: DeleteObjectInput!) {
    deleteObject(input: $input) {
      __typename
      ... on DeleteObjectSuccessPayload {
        document {
          id
        }
      }
      ... on ErrorPayload {
        message
      }
    }
  }
`;

const deleteRepresentationMutation = gql`
  mutation deleteRepresentation($input: DeleteRepresentationInput!) {
    deleteRepresentation(input: $input) {
      __typename
      ... on DeleteRepresentationSuccessPayload {
        representationId
      }
      ... on ErrorPayload {
        message
      }
    }
  }
`;

const renameDocumentMutation = gql`
  mutation renameDocument($input: RenameDocumentInput!) {
    renameDocument(input: $input) {
      __typename
      ... on RenameDocumentSuccessPayload {
        document {
          name
        }
      }
      ... on ErrorPayload {
        message
      }
    }
  }
`;

const renameObjectMutation = gql`
  mutation renameObject($input: RenameObjectInput!) {
    renameObject(input: $input) {
      __typename
      ... on RenameObjectSuccessPayload {
        objectId
        newName
      }
      ... on ErrorPayload {
        message
      }
    }
  }
`;

const renameRepresentationMutation = gql`
  mutation renameRepresentation($input: RenameRepresentationInput!) {
    renameRepresentation(input: $input) {
      __typename
      ... on RenameRepresentationSuccessPayload {
        representation {
          label
        }
      }
      ... on ErrorPayload {
        message
      }
    }
  }
`;

// The list of characters that will enable the direct edit mechanism.
const directEditActivationValidCharacters = /[\w&é§èàùçÔØÁÛÊË"«»’”„´$¥€£\\¿?!=+-,;:%/{}[\]–#@*.]/;

/**
 * Determines where the context menu should open relative to the actual mouse position.
 * These are relative to the bottom-left corner of the "more" icon, and to the size of the
 * caret, so that the caret at the left of the menu points to the middle of the "more" icon.
 */
const menuPositionDelta = {
  dx: 20,
  dy: -6,
};

const propTypes = {
  editingContextId: PropTypes.string.isRequired,
  item: PropTypes.object.isRequired,
  depth: PropTypes.number.isRequired,
  onExpand: PropTypes.func.isRequired,
  selection: PropTypes.object,
  setSelection: PropTypes.func.isRequired,
};

export const TreeItem = ({ editingContextId, item, depth, onExpand, selection, setSelection }) => {
  const initialState = {
    modalDisplayed: null,
    x: 0,
    y: 0,
    showContextMenu: false,
    editingMode: false,
    label: item.label,
    prevSelectionId: null,
  };
  const [state, setState] = useState(initialState);
  const [deleteObject] = useMutation(deleteObjectMutation);
  const [deleteRepresentation] = useMutation(deleteRepresentationMutation);
  const refDom = useRef() as any;

  const [
    renameDocument,
    { loading: renameDocumentLoading, data: renameDocumentData, error: renameDocumentError },
  ] = useMutation(renameDocumentMutation);
  useEffect(() => {
    if (!renameDocumentLoading && !renameDocumentError && renameDocumentData?.renameDocument) {
      const { renameDocument } = renameDocumentData;
      if (renameDocument.__typename === 'RenameDocumentSuccessPayload') {
        setState((prevState) => {
          return { ...prevState, editingMode: false, label: renameDocument.name };
        });
      }
    }
  }, [renameDocumentData, renameDocumentError, renameDocumentLoading]);

  const [
    renameRepresentation,
    { loading: renameRepresentationLoading, data: renameRepresentationData, error: renameRepresentationError },
  ] = useMutation(renameRepresentationMutation);
  useEffect(() => {
    if (!renameRepresentationLoading && !renameRepresentationError && renameRepresentationData?.renameRepresentation) {
      const { renameRepresentation } = renameRepresentationData;
      if (renameRepresentation.__typename === 'RenameRepresentationSuccessPayload') {
        setState((prevState) => {
          return { ...prevState, editingMode: false, label: renameRepresentation.label };
        });
      }
    }
  }, [renameRepresentationData, renameRepresentationError, renameRepresentationLoading]);

  const [
    renameObject,
    { loading: renameObjectLoading, data: renameObjectData, error: renameObjectError },
  ] = useMutation(renameObjectMutation);
  useEffect(() => {
    if (!renameObjectLoading && !renameObjectError && renameObjectData?.renameObject) {
      const { renameObject } = renameObjectData;
      if (renameObject.__typename === 'RenameObjectSuccessPayload') {
        setState((prevState) => {
          return { ...prevState, editingMode: false, label: renameObject.newName };
        });
      }
    }
  }, [renameObjectData, renameObjectError, renameObjectLoading]);

  // custom hook for getting previous value
  const usePrevious = (value) => {
    const ref = useRef();
    useEffect(() => {
      ref.current = value;
    });
    return ref.current;
  };

  const onMore = (event) => {
    const { x, y } = event.currentTarget.getBoundingClientRect();
    if (!state.showContextMenu) {
      setState((prevState) => {
        return {
          modalDisplayed: prevState.modalDisplayed,
          x: x + menuPositionDelta.dx,
          y: y + menuPositionDelta.dy,
          showContextMenu: true,
          editingMode: false,
          label: item.label,
          prevSelectionId: prevState.prevSelectionId,
        };
      });
    }
  };

  const { x, y, showContextMenu, modalDisplayed, editingMode, label } = state;

  const prevEditingMode = usePrevious(editingMode);
  useEffect(() => {
    if (prevEditingMode && !editingMode) {
      refDom.current.focus();
    }
  }, [editingMode, prevEditingMode]);

  let contextMenu = null;
  if (showContextMenu) {
    const onCloseContextMenu = () => {
      setState((prevState) => {
        return {
          modalDisplayed: null,
          x: 0,
          y: 0,
          showContextMenu: false,
          editingMode: false,
          label: item.label,
          prevSelectionId: prevState.prevSelectionId,
        };
      });
    };

    if (item.kind === 'Document') {
      const onNewObject = () =>
        setState((prevState) => {
          return {
            modalDisplayed: 'CreateNewRootObject',
            x: 0,
            y: 0,
            showContextMenu: false,
            editingMode: false,
            label: item.label,
            prevSelectionId: prevState.prevSelectionId,
          };
        });
      const onRenameDocument = () =>
        setState((prevState) => {
          return {
            modalDisplayed: null,
            x: 0,
            y: 0,
            showContextMenu: false,
            editingMode: true,
            label: item.label,
            prevSelectionId: prevState.prevSelectionId,
          };
        });
      const onDownload = () =>
        setState((prevState) => {
          return {
            modalDisplayed: null,
            x: 0,
            y: 0,
            showContextMenu: false,
            editingMode: false,
            label: item.label,
            prevSelectionId: prevState.prevSelectionId,
          };
        });
      const onDeleteDocument = () =>
        setState((prevState) => {
          return {
            modalDisplayed: 'DeleteDocument',
            x: 0,
            y: 0,
            showContextMenu: false,
            editingMode: false,
            label: item.label,
            prevSelectionId: prevState.prevSelectionId,
          };
        });
      contextMenu = (
        <TreeItemDocumentContextMenu
          projectId={editingContextId}
          documentId={item.id}
          x={x}
          y={y}
          onNewObject={onNewObject}
          onRenameDocument={onRenameDocument}
          onDownload={onDownload}
          onDeleteDocument={onDeleteDocument}
          onClose={onCloseContextMenu}
        />
      );
    } else if (item.kind === 'Diagram' || item.kind === 'Form') {
      const onDeleteRepresentation = () => {
        const variables = {
          input: {
            representationId: item.id,
          },
        };
        deleteRepresentation({ variables });
        onCloseContextMenu();
      };
      const onRenameRepresentation = () =>
        setState((prevState) => {
          return {
            modalDisplayed: null,
            x: 0,
            y: 0,
            showContextMenu: false,
            editingMode: true,
            label: item.label,
            prevSelectionId: prevState.prevSelectionId,
          };
        });
      contextMenu = (
        <TreeItemDiagramContextMenu
          onDeleteRepresentation={onDeleteRepresentation}
          onRenameRepresentation={onRenameRepresentation}
          x={x}
          y={y}
          onClose={onCloseContextMenu}
        />
      );
    } else {
      const onCreateNewObject = () =>
        setState((prevState) => {
          return {
            modalDisplayed: 'CreateNewObject',
            x: 0,
            y: 0,
            showContextMenu: false,
            editingMode: false,
            label: item.label,
            prevSelectionId: prevState.prevSelectionId,
          };
        });
      const onCreateRepresentation = () =>
        setState((prevState) => {
          return {
            modalDisplayed: 'CreateRepresentation',
            x: 0,
            y: 0,
            showContextMenu: false,
            editingMode: false,
            label: item.label,
            prevSelectionId: prevState.prevSelectionId,
          };
        });
      const onRenameObject = () =>
        setState((prevState) => {
          return {
            modalDisplayed: null,
            x: 0,
            y: 0,
            showContextMenu: false,
            editingMode: true,
            label: item.label,
            prevSelectionId: prevState.prevSelectionId,
          };
        });
      const onDeleteObject = () => {
        const variables = {
          input: {
            projectId: editingContextId,
            objectId: item.id,
          },
        };
        deleteObject({ variables });
        onCloseContextMenu();
      };
      contextMenu = (
        <TreeItemObjectContextMenu
          x={x}
          y={y}
          onCreateNewObject={onCreateNewObject}
          onCreateRepresentation={onCreateRepresentation}
          editable={item.editable}
          onRenameObject={onRenameObject}
          onDeleteObject={onDeleteObject}
          onClose={onCloseContextMenu}
        />
      );
    }
  }
  const onCloseModal = () =>
    setState((prevState) => {
      return {
        modalDisplayed: null,
        x: 0,
        y: 0,
        showContextMenu: false,
        editingMode: false,
        label: item.label,
        prevSelectionId: prevState.prevSelectionId,
      };
    });

  let itemLabel = null;
  if (item.kind === 'Document' || item.kind === 'Diagram' || item.kind === 'Form') {
    itemLabel = item.label;
  } else {
    itemLabel = item.kind.split('::').pop();
    if (item.label) {
      itemLabel += ' ' + item.label;
    }
  }

  let text;
  if (editingMode) {
    const handleChange = (event) => {
      const newLabel = event.target.value;
      setState((prevState) => {
        return { ...prevState, editingMode: true, label: newLabel };
      });
    };

    const doRename = () => {
      const isNameValid = label.length >= 1;
      if (isNameValid && item) {
        if (item.kind === 'Document') {
          renameDocument({ variables: { input: { documentId: item.id, newName: label } } });
        } else if (item?.kind === 'Diagram') {
          renameRepresentation({
            variables: { input: { projectId: editingContextId, representationId: item.id, newLabel: label } },
          });
        } else {
          renameObject({ variables: { input: { projectId: editingContextId, objectId: item.id, newName: label } } });
        }
      } else {
        setState((prevState) => {
          return { ...prevState, editingMode: false, label: item.label };
        });
      }
    };
    const onFinishEditing = (event) => {
      const { key } = event;
      if (key === 'Enter') {
        doRename();
      } else if (key === 'Escape') {
        setState((prevState) => {
          return { ...prevState, editingMode: false, label: item.label };
        });
      }
    };
    const onFocusIn = (event) => {
      event.target.select();
    };
    const onFocusOut = (event) => {
      doRename();
    };
    text = (
      <Textfield
        kind={'small'}
        name="name"
        placeholder={'Enter the new name'}
        value={label}
        onChange={handleChange}
        onKeyDown={onFinishEditing}
        onFocus={onFocusIn}
        onBlur={onFocusOut}
        autoFocus
        data-testid="name-edit"
      />
    );
  } else {
    text = <Text className={styles.label}>{itemLabel}</Text>;
  }

  let modal = null;
  if (modalDisplayed === 'DeleteDocument') {
    modal = (
      <DeleteDocumentModal
        documentName={item.label}
        documentId={item.id}
        onDocumentDeleted={onCloseModal}
        onClose={onCloseModal}
      />
    );
  } else if (modalDisplayed === 'CreateNewRootObject') {
    const onRootObjectCreated = (object) => {
      if (!item.expanded && item.hasChildren) {
        onExpand(item.id, depth);
      }

      const { id, label, kind } = object;
      setSelection({ id, label, kind });
      setState((prevState) => {
        return {
          modalDisplayed: null,
          x: 0,
          y: 0,
          showContextMenu: false,
          editingMode: false,
          label: label,
          prevSelectionId: prevState.prevSelectionId,
        };
      });
    };
    modal = (
      <NewRootObjectModal
        projectId={editingContextId}
        documentId={item.id}
        onObjectCreated={onRootObjectCreated}
        onClose={onCloseModal}
      />
    );
  } else if (modalDisplayed === 'CreateNewObject') {
    const onObjectCreated = (object) => {
      if (!item.expanded && item.hasChildren) {
        onExpand(item.id, depth);
      }

      const { id, label, kind } = object;
      setSelection({ id, label, kind });
      setState((prevState) => {
        return {
          modalDisplayed: null,
          x: 0,
          y: 0,
          showContextMenu: false,
          editingMode: false,
          label: label,
          prevSelectionId: prevState.prevSelectionId,
        };
      });
    };
    modal = (
      <NewObjectModal
        projectId={editingContextId}
        classId={item.kind}
        objectId={item.id}
        onObjectCreated={onObjectCreated}
        onClose={onCloseModal}
      />
    );
  } else if (modalDisplayed === 'CreateRepresentation') {
    const onRepresentationCreated = (representation) => {
      if (!item.expanded && item.hasChildren) {
        onExpand(item.id, depth);
      }

      const { id, label, kind } = representation;
      setSelection({ id, label, kind });
      setState((prevState) => {
        return {
          modalDisplayed: null,
          x: 0,
          y: 0,
          showContextMenu: false,
          editingMode: false,
          label: label,
          prevSelectionId: prevState.prevSelectionId,
        };
      });
    };
    modal = (
      <NewRepresentationModal
        projectId={editingContextId}
        classId={item.kind}
        objectId={item.id}
        onRepresentationCreated={onRepresentationCreated}
        onClose={onCloseModal}
      />
    );
  }

  let children = null;
  if (item.expanded) {
    children = (
      <ul className={styles.ul}>
        {item.children.map((childItem) => {
          return (
            <li key={childItem.id}>
              <TreeItem
                editingContextId={editingContextId}
                item={childItem}
                depth={depth + 1}
                onExpand={onExpand}
                selection={selection}
                setSelection={setSelection}
              />
            </li>
          );
        })}
      </ul>
    );
  }

  let className = styles.treeItem;
  let dataTestid = undefined;

  if (selection?.id === item.id) {
    className = `${className} ${styles.selected}`;
    dataTestid = 'selected';
  }

  let arrow = null;
  if (item.hasChildren) {
    if (item.expanded) {
      arrow = (
        <ArrowExpanded
          title="Collapse"
          className={styles.arrow}
          width="20"
          height="20"
          onClick={() => onExpand(item.id, depth)}
          data-testid="expand"
        />
      );
    } else {
      arrow = (
        <ArrowCollapsed
          title="Expand"
          className={styles.arrow}
          width="20"
          height="20"
          onClick={() => onExpand(item.id, depth)}
          data-testid="expand"
        />
      );
    }
  }

  let image = <NoIcon title={item.kind} />;
  if (item.imageURL) {
    image = <img height="16" width="16" alt={item.kind} src={httpOrigin + item.imageURL}></img>;
  }

  const onFocus = () => {
    const { id, label, kind } = item;
    setSelection({ id, label, kind });
  };

  const onClick = () => {
    if (!editingMode) {
      refDom.current.focus();
    }
  };

  const onBeginEditing = (event) => {
    if (!item.editable || editingMode) {
      return;
    }
    const { key } = event;
    /*If a modifier key is hit alone, do nothing*/
    if ((event.altKey || event.shiftKey) && event.getModifierState(key)) {
      return;
    }
    const validFirstInputChar =
      !event.metaKey && !event.ctrlKey && key.length === 1 && directEditActivationValidCharacters.test(key);
    if (validFirstInputChar) {
      setState((prevState) => {
        return { ...prevState, editingMode: true, label: key };
      });
    }
  };

  const { kind } = item;
  const draggable = kind !== 'Document' && kind !== 'Diagram';
  const dragStart = (e) => {
    if (selection?.kind !== 'Document' && selection?.kind !== 'Diagram') {
      e.dataTransfer.setData('id', selection.id);
    }
  };
  const dragOver = (e) => {
    e.stopPropagation();
  };

  /* ref, tabindex and onFocus are used to set the React component focusabled and to set the focus to the corresponding DOM part */
  return (
    <>
      <div
        className={className}
        ref={refDom}
        tabIndex={0}
        onFocus={onFocus}
        onKeyDown={onBeginEditing}
        draggable={draggable}
        onDragStart={dragStart}
        onDragOver={dragOver}
        data-treeitemid={item.id}
        data-haschildren={item.hasChildren.toString()}
        data-depth={depth}
        data-expanded={item.expanded.toString()}
        data-testid={dataTestid}>
        {arrow}
        <div className={styles.content}>
          <div
            className={styles.imageAndLabel}
            onClick={onClick}
            onDoubleClick={() => item.hasChildren && onExpand(item.id, depth)}
            data-testid={itemLabel}>
            {image}
            {text}
          </div>
          <IconButton className={styles.more} onClick={onMore} data-testid={`${itemLabel}-more`}>
            <More title="More" />
          </IconButton>
        </div>
      </div>
      {children}
      {contextMenu}
      {modal}
    </>
  );
};
TreeItem.propTypes = propTypes;
