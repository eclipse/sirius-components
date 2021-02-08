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
package org.eclipse.sirius.web.spring.collaborative.diagrams.handlers;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.eclipse.sirius.web.collaborative.api.services.EventHandlerResponse;
import org.eclipse.sirius.web.collaborative.diagrams.api.IDiagramContext;
import org.eclipse.sirius.web.collaborative.diagrams.api.IDiagramEventHandler;
import org.eclipse.sirius.web.collaborative.diagrams.api.IDiagramInput;
import org.eclipse.sirius.web.collaborative.diagrams.api.IDiagramService;
import org.eclipse.sirius.web.collaborative.diagrams.api.IToolService;
import org.eclipse.sirius.web.collaborative.diagrams.api.dto.InvokeDropToolOnDiagramInput;
import org.eclipse.sirius.web.collaborative.diagrams.api.dto.InvokeDropToolOnDiagramSuccessPayload;
import org.eclipse.sirius.web.core.api.ErrorPayload;
import org.eclipse.sirius.web.core.api.IEditingContext;
import org.eclipse.sirius.web.core.api.IObjectService;
import org.eclipse.sirius.web.diagrams.Diagram;
import org.eclipse.sirius.web.diagrams.Node;
import org.eclipse.sirius.web.diagrams.description.NodeDescription;
import org.eclipse.sirius.web.diagrams.tools.DropTool;
import org.eclipse.sirius.web.representations.Status;
import org.eclipse.sirius.web.representations.VariableManager;
import org.eclipse.sirius.web.spring.collaborative.diagrams.messages.ICollaborativeDiagramMessageService;
import org.springframework.stereotype.Service;

/**
 * Handle "Drop in Diagram" events.
 *
 * @author hmarchadour
 */
@Service
public class InvokeDropToolsOnDiagramEventHandler implements IDiagramEventHandler {

    private final IObjectService objectService;

    private final IDiagramService diagramService;

    private final IToolService toolService;

    private final ICollaborativeDiagramMessageService messageService;

    public InvokeDropToolsOnDiagramEventHandler(IObjectService objectService, IDiagramService diagramService, IToolService toolService, ICollaborativeDiagramMessageService messageService) {
        this.objectService = Objects.requireNonNull(objectService);
        this.diagramService = Objects.requireNonNull(diagramService);
        this.toolService = Objects.requireNonNull(toolService);
        this.messageService = Objects.requireNonNull(messageService);
    }

    @Override
    public boolean canHandle(IDiagramInput diagramInput) {
        return diagramInput instanceof InvokeDropToolOnDiagramInput;
    }

    @Override
    public EventHandlerResponse handle(IEditingContext editingContext, IDiagramContext diagramContext, IDiagramInput diagramInput) {
        String message = this.messageService.invalidInput(diagramInput.getClass().getSimpleName(), InvokeDropToolOnDiagramInput.class.getSimpleName());
        EventHandlerResponse result = new EventHandlerResponse(false, representation -> false, new ErrorPayload(message));
        if (diagramInput instanceof InvokeDropToolOnDiagramInput) {
            InvokeDropToolOnDiagramInput input = (InvokeDropToolOnDiagramInput) diagramInput;
            Optional<Object> optionalObject = this.objectService.getObject(editingContext, input.getObjectId());
            Diagram diagram = diagramContext.getDiagram();
            // @formatter:off
            var optionalTool = this.toolService.findToolById(diagram, input.getToolId())
                    .filter(DropTool.class::isInstance)
                    .map(DropTool.class::cast);
            // @formatter:on
            if (optionalObject.isPresent() && optionalTool.isPresent()) {
                Object self = optionalObject.get();
                DropTool tool = optionalTool.get();
                Status status = this.executeTool(editingContext, diagramContext, self, input.getDiagramElementId(), tool);
                if (Objects.equals(Status.OK, status)) {
                    return new EventHandlerResponse(true, representation -> true, new InvokeDropToolOnDiagramSuccessPayload(diagram));
                } else {
                    result = new EventHandlerResponse(false, representation -> false, new ErrorPayload(this.messageService.invalidDrop()));
                }
            }
        }
        return result;
    }

    private Status executeTool(IEditingContext editingContext, IDiagramContext diagramContext, Object self, UUID diagramElementId, DropTool tool) {
        Diagram diagram = diagramContext.getDiagram();
        Optional<Node> node = this.diagramService.findNodeById(diagram, diagramElementId);
        VariableManager variableManager = new VariableManager();
        if (node.isPresent()) {
            variableManager.put(NodeDescription.NODE_CONTAINER, node.get());
        }
        variableManager.put(IEditingContext.EDITING_CONTEXT, editingContext);
        variableManager.put(IDiagramContext.DIAGRAM_CONTEXT, diagramContext);
        variableManager.put(VariableManager.SELF, self);
        return tool.getHandler().apply(variableManager);
    }
}
