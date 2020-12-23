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
import {
  EditLabelAction,
  UpdateModelAction,
  ModelSource,
  ApplyLabelEditAction,
  SetViewportAction,
  getWindowScroll,
  GetViewportAction,
  SelectAction,
  GetSelectionAction,
  CenterAction,
  getAbsoluteBounds,
  SGraph,
  SNode,
} from 'sprotty';
import { convertDiagram } from 'diagram/sprotty/convertDiagram';
/** Action to delete a sprotty element */
export const SPROTTY_DELETE_ACTION = 'sprottyDeleteElement';
/** Action to select a sprotty element */
export const SPROTTY_SELECT_ACTION = 'sprottySelectElement';
/** Action to select an Sirius element */
export const SIRIUS_LABEL_EDIT_ACTION = 'siriusLabelEditElement';
/** Action to select an Sirius element */
export const SIRIUS_SELECT_ACTION = 'siriusSelectElement';
/** Action to select an Sirius element */
export const SIRIUS_UPDATE_MODEL_ACTION = 'siriusUpdateModel';
/** Action to set a tool active */
export const ACTIVE_TOOL_ACTION = 'activeTool';
/** Action to set the source element */
export const SOURCE_ELEMENT_ACTION = 'sourceElement';
/** Action to show a contextual toolbar */
export const SHOW_CONTEXTUAL_TOOLBAR_ACTION = 'showContextualToolbar';
/** Action to hide a contextual toolbar */
export const HIDE_CONTEXTUAL_TOOLBAR_ACTION = 'hideContextualToolbar';
/** Action to zoom in */
export const ZOOM_IN_ACTION = 'zoomIn';
/** Action to zoom OUT */
export const ZOOM_OUT_ACTION = 'zoomOut';
/** Action to zoom to a specifi level*/
export const ZOOM_TO_ACTION = 'zoomTo';

/** Where to open the contextual palette relative to the mouse position */
const popupOffset = {
  x: 24,
  y: -1,
};

/** Default zoom in factor used internally by Sprotty */
const ZOOM_IN_FACTOR = Math.exp(0.5);
/** Default zoom out factor used internally by Sprotty */
const ZOOM_OUT_FACTOR = Math.exp(-0.5);

const INITIAL_ROOT = {
  type: 'NONE',
  id: 'ROOT',
};

interface Root {
  type: string;
  id: string;
  index?: any;
}

/**
 * The WebSocket diagram server used to communicate with the remote server.
 *
 * @gcoutable
 */
export class SiriusWebWebSocketDiagramServer extends ModelSource {
  logger;
  mousePositionTracker;
  modelFactory;
  activeTool;
  editLabel;
  deleteElements;

  invokeTool;
  setContextualPalette;

  // Used to store the edge source element.
  diagramSourceElement;
  currentRoot: Root = INITIAL_ROOT;

  initialize(registry) {
    super.initialize(registry);
    registry.register(ApplyLabelEditAction.KIND, this);
    registry.register(EditLabelAction.KIND, this);
    registry.register(UpdateModelAction.KIND, this);
    registry.register(SIRIUS_LABEL_EDIT_ACTION, this);
    registry.register(SIRIUS_UPDATE_MODEL_ACTION, this);
    registry.register(SIRIUS_SELECT_ACTION, this);
    registry.register(SPROTTY_SELECT_ACTION, this);
    registry.register(SPROTTY_DELETE_ACTION, this);
    registry.register(ACTIVE_TOOL_ACTION, this);
    registry.register(SOURCE_ELEMENT_ACTION, this);
    registry.register(SHOW_CONTEXTUAL_TOOLBAR_ACTION, this);
    registry.register(HIDE_CONTEXTUAL_TOOLBAR_ACTION, this);
    registry.register(ZOOM_IN_ACTION, this);
    registry.register(ZOOM_OUT_ACTION, this);
    registry.register(ZOOM_TO_ACTION, this);
  }

  commitModel(newRoot) {
    const previousRoot = this.currentRoot;
    this.currentRoot = newRoot;
    return previousRoot;
  }

  handle(action) {
    switch (action.kind) {
      case UpdateModelAction.KIND:
        this.handleModelAction(action);
        break;
      case ApplyLabelEditAction.KIND:
        this.handleApplyLabelEditAction(action);
        break;
      case EditLabelAction.KIND:
        this.handleEditLabelAction(action);
        break;
      case SIRIUS_LABEL_EDIT_ACTION:
        this.handleSiriusLabelEditAction(action);
        break;
      case SIRIUS_UPDATE_MODEL_ACTION:
        this.handleSiriusUpdateModelAction(action);
        break;
      case SIRIUS_SELECT_ACTION:
        this.handleSiriusSelectAction(action);
        break;
      case SPROTTY_SELECT_ACTION:
        this.handleSprottySelectAction(action);
        break;
      case SPROTTY_DELETE_ACTION:
        this.handleSiriusDeleteAction(action);
        break;
      case ACTIVE_TOOL_ACTION:
        this.handleActiveToolAction(action);
        break;
      case SOURCE_ELEMENT_ACTION:
        this.handleSourceElementAction(action);
        break;
      case SHOW_CONTEXTUAL_TOOLBAR_ACTION:
        this.handleShowContextualToolbarAction(action);
        break;
      case HIDE_CONTEXTUAL_TOOLBAR_ACTION:
        this.handleHideContextualToolbarAction(action);
        break;
      case ZOOM_IN_ACTION:
        this.handleZoomInAction(action);
        break;
      case ZOOM_OUT_ACTION:
        this.handleZoomOutAction(action);
        break;
      case ZOOM_TO_ACTION:
        this.handleZoomToAction(action);
        break;
      default:
        this.logger.error(this, 'Invalid action', action);
        break;
    }
  }

  handleModelAction(action) {
    const { newRoot } = action;
    if (newRoot) {
      this.currentRoot = newRoot;
    }
    this.actionDispatcher.dispatchAll([{ kind: HIDE_CONTEXTUAL_TOOLBAR_ACTION }]);
  }

  handleApplyLabelEditAction(action) {
    const { labelId, text } = action;
    this.editLabel(labelId, text);
  }

  handleEditLabelAction(action) {
    const { element } = action;
    if (element) {
      const selectedItems = element.index.all().filter((e) => e.selected);
      selectedItems.forEach((item) => {
        const label = item.editableLabel;
        if (label) {
          this.actionDispatcher.dispatchAll([{ kind: HIDE_CONTEXTUAL_TOOLBAR_ACTION }, new EditLabelAction(label.id)]);
        }
      });
    }
  }

  handleSiriusLabelEditAction(action) {
    const { elementId } = action;
    this.actionDispatcher.dispatchAll([
      { kind: HIDE_CONTEXTUAL_TOOLBAR_ACTION },
      new EditLabelAction(elementId + '_label'),
    ]);
  }

  handleSprottySelectAction(action) {
    const { element } = action;
    if (this.activeTool) {
      if (this.activeTool.__typename === 'CreateNodeTool') {
        this.invokeTool(this.activeTool, element.id);
      } else if (this.activeTool.__typename === 'CreateEdgeTool') {
        this.invokeTool(this.activeTool, this.diagramSourceElement.id, element.id);
      }
    } else {
      this.actionDispatcher.dispatch({
        kind: SHOW_CONTEXTUAL_TOOLBAR_ACTION,
        element,
      } as any);
    }
  }

  async handleSiriusUpdateModelAction(action) {
    const { diagram } = action;
    if (diagram) {
      const convertedDiagram = convertDiagram(diagram);
      const sprottyModel = this.modelFactory.createRoot(convertedDiagram);
      const selectionResult = await this.actionDispatcher.request(GetSelectionAction.create());
      sprottyModel.index
        .all()
        .filter((element) => selectionResult.selectedElementsIDs.indexOf(element.id) >= 0)
        .forEach((element) => (element.selected = true));
      this.actionDispatcher.dispatch(new UpdateModelAction(sprottyModel));
    } else {
      this.actionDispatcher.dispatch(new UpdateModelAction(INITIAL_ROOT));
    }
  }

  async handleSiriusSelectAction(action) {
    if (this.currentRoot.index) {
      const { selection } = action;
      const selectedElementsIDs = [];
      const selectionResult = await this.actionDispatcher.request(GetSelectionAction.create());
      const prevSelectedObjectIds = this.currentRoot.index
        .all()
        .filter((element) => selectionResult.selectedElementsIDs.indexOf(element.id) >= 0)
        .map((element) => element.id);
      const deselectedElementsIDs = [...prevSelectedObjectIds];
      if (selection?.id !== this.currentRoot.id) {
        const selectionElement = this.findElement(selection.id);
        if (selectionElement && prevSelectedObjectIds.indexOf(selectionElement.id) < 0) {
          // The React selection and the Sprotty selection does not match. We must update the Sprotty selection
          selectedElementsIDs.push(selectionElement.id);
        }
      }
      const actions = [];
      if (selectedElementsIDs.length > 0 || deselectedElementsIDs.length > 0) {
        actions.push(new SelectAction(selectedElementsIDs, deselectedElementsIDs));
      }
      if (selectedElementsIDs.length > 0) {
        actions.push(new CenterAction(selectedElementsIDs));
        actions.push({ kind: HIDE_CONTEXTUAL_TOOLBAR_ACTION });
      }
      this.actionDispatcher.dispatchAll(actions);
    }
  }

  handleSiriusDeleteAction(action) {
    const { element } = action;
    if (element) {
      const selectedItems = element.index.all().filter((e) => e.selected);
      this.deleteElements([...selectedItems]);
    } else {
      this.logger.log(this, 'Invalid delete action', action);
    }
  }

  handleActiveToolAction(action) {
    const { tool } = action;
    this.activeTool = tool;
  }

  handleSourceElementAction(action) {
    const { sourceElement } = action;
    this.diagramSourceElement = sourceElement;
  }

  async handleShowContextualToolbarAction(action) {
    const { element } = action;
    if (element && (element.kind === 'Diagram' || element.parent)) {
      const root = await this.actionDispatcher.request(GetViewportAction.create());
      const { viewport, canvasBounds } = root;
      const { scroll, zoom } = viewport;
      const lastPositionOnDiagram = this.mousePositionTracker.lastPositionOnDiagram;
      if (lastPositionOnDiagram) {
        const bounds = {
          x: (lastPositionOnDiagram.x - scroll.x) * zoom + canvasBounds.x + popupOffset.x,
          y: (lastPositionOnDiagram.y - scroll.y) * zoom + canvasBounds.y + popupOffset.y,
          width: -1,
          height: -1,
        };

        const absoluteBounds = getAbsoluteBounds(element);
        let origin = { x: 0, y: 0 };
        if (element instanceof SNode) {
          origin = {
            x: absoluteBounds.x + (element.size.width / 2) * zoom,
            y: absoluteBounds.y + (element.size.height / 2) * zoom,
          };
        }

        const contextualPalette = {
          canvasBounds: bounds,
          origin,
          element: element,
          renameable: !(element instanceof SGraph),
          deletable: !(element instanceof SGraph),
        };
        this.setContextualPalette(contextualPalette);
      }
    } else {
      const contextualPalette = undefined;
      this.setContextualPalette(contextualPalette);
    }
  }

  handleHideContextualToolbarAction(action) {
    const contextualPalette = undefined;
    this.setContextualPalette(contextualPalette);
  }
  async handleZoomInAction(action) {
    await this.doZoom(ZOOM_IN_FACTOR);
  }

  async handleZoomOutAction(action) {
    await this.doZoom(ZOOM_OUT_FACTOR);
  }

  async handleZoomToAction(action) {
    await this.doZoomLevel(action.level);
  }

  async doZoom(zoomFactor) {
    const root = await this.actionDispatcher.request(GetViewportAction.create());
    const { viewport } = root;
    await this.doZoomLevel(viewport.zoom * zoomFactor);
  }

  async doZoomLevel(zoomLevel) {
    const root = await this.actionDispatcher.request(GetViewportAction.create());
    const { viewport, canvasBounds } = root;
    const windowScroll = getWindowScroll();
    const clientX = canvasBounds.x + canvasBounds.width / 2;
    const clientY = canvasBounds.y + canvasBounds.height / 2;

    const viewportOffset = {
      x: clientX + windowScroll.x - canvasBounds.x,
      y: clientY + windowScroll.y - canvasBounds.y,
    };
    const offsetFactor = 1.0 / zoomLevel - 1.0 / viewport.zoom;
    const newViewport = {
      zoom: zoomLevel,
      scroll: {
        x: viewport.scroll.x - offsetFactor * viewportOffset.x,
        y: viewport.scroll.y - offsetFactor * viewportOffset.y,
      },
    };
    await this.actionDispatcher.dispatch(new SetViewportAction(this.currentRoot.id, newViewport, true));
  }

  findElementWithTarget(element) {
    if (element?.targetObjectId) {
      return element;
    } else if (element.targetObjectId) {
      return this.findElementWithTarget(element.parent);
    }
    // Otherwise, use the diagram as element with target.
    return this.currentRoot;
  }

  findElement(id) {
    const [element] = this.currentRoot.index
      .all()
      .filter((element) => id === element.targetObjectId || id === element.id);
    return element;
  }

  setLogger(logger) {
    this.logger = logger;
  }
  setMousePositionTracker(mousePositionTracker) {
    this.mousePositionTracker = mousePositionTracker;
  }
  setModelFactory(modelFactory) {
    this.modelFactory = modelFactory;
  }

  setEditLabelListener(editLabel) {
    this.editLabel = editLabel;
  }

  setDeleteElementsListener(deleteElements) {
    this.deleteElements = deleteElements;
  }

  setInvokeToolListener(invokeTool) {
    this.invokeTool = invokeTool;
  }

  setContextualPaletteListener(setContextualPalette) {
    this.setContextualPalette = setContextualPalette;
  }
}
