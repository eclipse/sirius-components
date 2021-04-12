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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.eclipse.sirius.web.collaborative.api.services.ChangeDescription;
import org.eclipse.sirius.web.collaborative.api.services.ChangeKind;
import org.eclipse.sirius.web.collaborative.api.services.EventHandlerResponse;
import org.eclipse.sirius.web.collaborative.api.services.Monitoring;
import org.eclipse.sirius.web.collaborative.diagrams.api.IDiagramContext;
import org.eclipse.sirius.web.collaborative.diagrams.api.IDiagramDescriptionService;
import org.eclipse.sirius.web.collaborative.diagrams.api.IDiagramEventHandler;
import org.eclipse.sirius.web.collaborative.diagrams.api.IDiagramInput;
import org.eclipse.sirius.web.collaborative.diagrams.api.dto.DeleteFromDiagramInput;
import org.eclipse.sirius.web.collaborative.diagrams.api.dto.DeleteFromDiagramSuccessPayload;
import org.eclipse.sirius.web.core.api.ErrorPayload;
import org.eclipse.sirius.web.core.api.IEditingContext;
import org.eclipse.sirius.web.core.api.IObjectService;
import org.eclipse.sirius.web.core.api.IRepresentationDescriptionSearchService;
import org.eclipse.sirius.web.diagrams.Diagram;
import org.eclipse.sirius.web.diagrams.Edge;
import org.eclipse.sirius.web.diagrams.Node;
import org.eclipse.sirius.web.diagrams.description.DiagramDescription;
import org.eclipse.sirius.web.diagrams.description.EdgeDescription;
import org.eclipse.sirius.web.diagrams.description.NodeDescription;
import org.eclipse.sirius.web.diagrams.services.api.IDiagramService;
import org.eclipse.sirius.web.representations.Status;
import org.eclipse.sirius.web.representations.VariableManager;
import org.eclipse.sirius.web.spring.collaborative.diagrams.messages.ICollaborativeDiagramMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

/**
 * Handle "Delete from Diagram" events.
 *
 * @author pcdavid
 */
@Service
public class DeleteFromDiagramEventHandler implements IDiagramEventHandler {

    private final IObjectService objectService;

    private final IDiagramService diagramService;

    private final IDiagramDescriptionService diagramDescriptionService;

    private final IRepresentationDescriptionSearchService representationDescriptionSearchService;

    private final ICollaborativeDiagramMessageService messageService;

    private final Logger logger = LoggerFactory.getLogger(DeleteFromDiagramEventHandler.class);

    private final Counter counter;

    public DeleteFromDiagramEventHandler(IObjectService objectService, IDiagramService diagramService, IDiagramDescriptionService diagramDescriptionService,
            IRepresentationDescriptionSearchService representationDescriptionSearchService, ICollaborativeDiagramMessageService messageService, MeterRegistry meterRegistry) {
        this.objectService = Objects.requireNonNull(objectService);
        this.diagramService = Objects.requireNonNull(diagramService);
        this.diagramDescriptionService = Objects.requireNonNull(diagramDescriptionService);
        this.representationDescriptionSearchService = Objects.requireNonNull(representationDescriptionSearchService);
        this.messageService = Objects.requireNonNull(messageService);

        // @formatter:off
        this.counter = Counter.builder(Monitoring.EVENT_HANDLER)
                .tag(Monitoring.NAME, this.getClass().getSimpleName())
                .register(meterRegistry);
        // @formatter:on
    }

    @Override
    public boolean canHandle(IDiagramInput diagramInput) {
        return diagramInput instanceof DeleteFromDiagramInput;
    }

    @Override
    public EventHandlerResponse handle(IEditingContext editingContext, IDiagramContext diagramContext, IDiagramInput diagramInput) {
        this.counter.increment();

        EventHandlerResponse result;
        if (diagramInput instanceof DeleteFromDiagramInput) {
            result = this.handleDelete(editingContext, diagramContext, (DeleteFromDiagramInput) diagramInput);
        } else {
            String message = this.messageService.invalidInput(diagramInput.getClass().getSimpleName(), DeleteFromDiagramInput.class.getSimpleName());
            result = new EventHandlerResponse(new ChangeDescription(ChangeKind.NOTHING, diagramInput.getRepresentationId()), new ErrorPayload(diagramInput.getId(), message));
        }
        return result;
    }

    private EventHandlerResponse handleDelete(IEditingContext editingContext, IDiagramContext diagramContext, DeleteFromDiagramInput diagramInput) {
        List<String> errors = new ArrayList<>();
        boolean atLeastOneOk = false;
        Diagram diagram = diagramContext.getDiagram();
        for (UUID edgeId : diagramInput.getEdgeIds()) {
            var optionalElement = this.diagramService.findEdgeById(diagram, edgeId);
            if (optionalElement.isPresent()) {
                Status status = this.invokeDeleteEdgeTool(optionalElement.get(), editingContext, diagramContext);
                if (Status.OK == status) {
                    atLeastOneOk = true;
                }
            } else {
                String message = this.messageService.edgeNotFound(edgeId.toString());
                errors.add(message);
            }
        }
        for (UUID nodeId : diagramInput.getNodeIds()) {
            var optionalElement = this.diagramService.findNodeById(diagram, nodeId);
            if (optionalElement.isPresent()) {
                Status status = this.invokeDeleteNodeTool(optionalElement.get(), editingContext, diagramContext);
                if (Status.OK == status) {
                    atLeastOneOk = true;
                }
            } else {
                String message = this.messageService.nodeNotFound(nodeId.toString());
                errors.add(message);
            }
        }

        return this.computeResponse(errors, atLeastOneOk, diagramContext, diagramInput);
    }

    private EventHandlerResponse computeResponse(List<String> errors, boolean atLeastOneSuccess, IDiagramContext diagramContext, DeleteFromDiagramInput diagramInput) {
        EventHandlerResponse result;
        if (errors.isEmpty()) {
            result = new EventHandlerResponse(new ChangeDescription(ChangeKind.SEMANTIC_CHANGE, diagramInput.getRepresentationId()),
                    new DeleteFromDiagramSuccessPayload(diagramInput.getId(), diagramContext.getDiagram()));
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.messageService.deleteFailed());
            for (String error : errors) {
                stringBuilder.append(error);
            }

            var changeDescription = new ChangeDescription(ChangeKind.NOTHING, diagramInput.getRepresentationId());
            if (atLeastOneSuccess) {
                changeDescription = new ChangeDescription(ChangeKind.SEMANTIC_CHANGE, diagramInput.getRepresentationId());
            }
            result = new EventHandlerResponse(changeDescription, new ErrorPayload(diagramInput.getId(), stringBuilder.toString()));
        }
        return result;
    }

    private Status invokeDeleteNodeTool(Node node, IEditingContext editingContext, IDiagramContext diagramContext) {
        Status result = Status.ERROR;
        var optionalNodeDescription = this.findNodeDescription(node, diagramContext.getDiagram());
        if (optionalNodeDescription.isPresent()) {
            var optionalSelf = this.objectService.getObject(editingContext, node.getTargetObjectId());
            if (optionalSelf.isPresent()) {
                VariableManager variableManager = new VariableManager();
                variableManager.put(VariableManager.SELF, optionalSelf.get());
                variableManager.put(IDiagramContext.DIAGRAM_CONTEXT, diagramContext);
                NodeDescription nodeDescription = optionalNodeDescription.get();
                this.logger.debug("Deleted diagram element {}", node.getId()); //$NON-NLS-1$
                result = nodeDescription.getDeleteHandler().apply(variableManager);
            } else {
                String message = this.messageService.semanticObjectNotFound(node.getTargetObjectId());
                this.logger.debug(message);
            }
        } else {
            String message = this.messageService.nodeDescriptionNotFound(node.getId().toString());
            this.logger.debug(message);
        }
        return result;
    }

    private Status invokeDeleteEdgeTool(Edge edge, IEditingContext editingContext, IDiagramContext diagramContext) {
        Status result = Status.ERROR;
        var optionalEdgeDescription = this.findEdgeDescription(edge, diagramContext.getDiagram());
        if (optionalEdgeDescription.isPresent()) {
            var optionalSelf = this.objectService.getObject(editingContext, edge.getTargetObjectId());
            if (optionalSelf.isPresent()) {
                VariableManager variableManager = new VariableManager();
                variableManager.put(VariableManager.SELF, optionalSelf.get());
                variableManager.put(IDiagramContext.DIAGRAM_CONTEXT, diagramContext);
                // @formatter:off
                this.diagramService.findNodeById(diagramContext.getDiagram(), edge.getSourceId())
                                   .flatMap(node -> this.objectService.getObject(editingContext, node.getTargetObjectId()))
                                   .ifPresent(semanticElement -> variableManager.put(EdgeDescription.SEMANTIC_EDGE_SOURCE, semanticElement));
                this.diagramService.findNodeById(diagramContext.getDiagram(), edge.getTargetId())
                                   .flatMap(node -> this.objectService.getObject(editingContext, node.getTargetObjectId()))
                                   .ifPresent(semanticElement -> variableManager.put(EdgeDescription.SEMANTIC_EDGE_TARGET, semanticElement));
                // @formatter:on
                EdgeDescription edgeDescription = optionalEdgeDescription.get();
                this.logger.debug("Deleted diagram edge {}", edge.getId()); //$NON-NLS-1$
                result = edgeDescription.getDeleteHandler().apply(variableManager);
            } else {
                String message = this.messageService.semanticObjectNotFound(edge.getTargetObjectId());
                this.logger.debug(message);
            }
        } else {
            String message = this.messageService.edgeDescriptionNotFound(edge.getId().toString());
            this.logger.debug(message);
        }
        return result;
    }

    private Optional<NodeDescription> findNodeDescription(Node node, Diagram diagram) {
        // @formatter:off
        return this.representationDescriptionSearchService
                .findRepresentationDescriptionById(diagram.getDescriptionId())
                .filter(DiagramDescription.class::isInstance)
                .map(DiagramDescription.class::cast)
                .flatMap(diagramDescription -> this.diagramDescriptionService.findNodeDescriptionById(diagramDescription, node.getDescriptionId()));
        // @formatter:on
    }

    private Optional<EdgeDescription> findEdgeDescription(Edge edge, Diagram diagram) {
        // @formatter:off
        return this.representationDescriptionSearchService
                .findRepresentationDescriptionById(diagram.getDescriptionId())
                .filter(DiagramDescription.class::isInstance)
                .map(DiagramDescription.class::cast)
                .flatMap(diagramDescription -> this.diagramDescriptionService.findEdgeDescriptionById(diagramDescription, edge.getDescriptionId()));
        // @formatter:on
    }

}
