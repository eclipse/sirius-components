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
package org.eclipse.sirius.web.spring.collaborative.diagrams.handlers;

import java.util.Objects;
import java.util.Optional;

import org.eclipse.sirius.web.collaborative.api.services.EventHandlerResponse;
import org.eclipse.sirius.web.collaborative.api.services.Monitoring;
import org.eclipse.sirius.web.collaborative.diagrams.api.IDiagramContext;
import org.eclipse.sirius.web.collaborative.diagrams.api.IDiagramDescriptionService;
import org.eclipse.sirius.web.collaborative.diagrams.api.IDiagramEventHandler;
import org.eclipse.sirius.web.collaborative.diagrams.api.IDiagramInput;
import org.eclipse.sirius.web.collaborative.diagrams.api.IDiagramService;
import org.eclipse.sirius.web.collaborative.diagrams.api.dto.EditLabelInput;
import org.eclipse.sirius.web.collaborative.diagrams.api.dto.EditLabelSuccessPayload;
import org.eclipse.sirius.web.diagrams.Diagram;
import org.eclipse.sirius.web.diagrams.Edge;
import org.eclipse.sirius.web.diagrams.Node;
import org.eclipse.sirius.web.diagrams.description.DiagramDescription;
import org.eclipse.sirius.web.diagrams.description.EdgeDescription;
import org.eclipse.sirius.web.diagrams.description.NodeDescription;
import org.eclipse.sirius.web.representations.VariableManager;
import org.eclipse.sirius.web.services.api.dto.ErrorPayload;
import org.eclipse.sirius.web.services.api.objects.IEditingContext;
import org.eclipse.sirius.web.services.api.objects.IObjectService;
import org.eclipse.sirius.web.services.api.representations.IRepresentationDescriptionService;
import org.eclipse.sirius.web.spring.collaborative.diagrams.messages.ICollaborativeDiagramMessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

/**
 * Handle "Edit Label" events.
 *
 * @author pcdavid
 */
@Service
public class EditLabelEventHandler implements IDiagramEventHandler {
    private static final String LABEL_SUFFIX = "_label"; //$NON-NLS-1$

    private static final String CENTERLABEL_SUFFIX = "_centerlabel"; //$NON-NLS-1$

    private final IObjectService objectService;

    private final IDiagramService diagramService;

    private final IDiagramDescriptionService diagramDescriptionService;

    private final IRepresentationDescriptionService representationDescriptionService;

    private final ICollaborativeDiagramMessageService messageService;

    private final Logger logger = LoggerFactory.getLogger(EditLabelEventHandler.class);

    private final Counter counter;

    public EditLabelEventHandler(IObjectService objectService, IDiagramService diagramService, IDiagramDescriptionService diagramDescriptionService,
            IRepresentationDescriptionService representationDescriptionService, ICollaborativeDiagramMessageService messageService, MeterRegistry meterRegistry) {
        this.objectService = Objects.requireNonNull(objectService);
        this.diagramService = Objects.requireNonNull(diagramService);
        this.diagramDescriptionService = Objects.requireNonNull(diagramDescriptionService);
        this.representationDescriptionService = Objects.requireNonNull(representationDescriptionService);
        this.messageService = Objects.requireNonNull(messageService);

        // @formatter:off
        this.counter = Counter.builder(Monitoring.EVENT_HANDLER)
                .tag(Monitoring.NAME, this.getClass().getSimpleName())
                .register(meterRegistry);
        // @formatter:on
    }

    @Override
    public boolean canHandle(IDiagramInput diagramInput) {
        return diagramInput instanceof EditLabelInput;
    }

    @Override
    public EventHandlerResponse handle(IEditingContext editingContext, IDiagramContext diagramContext, IDiagramInput diagramInput) {
        this.counter.increment();

        EventHandlerResponse response = null;
        if (diagramInput instanceof EditLabelInput) {
            EditLabelInput input = (EditLabelInput) diagramInput;
            Diagram diagram = diagramContext.getDiagram();
            if (input.getLabelId().endsWith(LABEL_SUFFIX)) {
                String nodeId = this.extractNodeId(input.getLabelId());
                var node = this.diagramService.findNodeById(diagram, nodeId);
                if (node.isPresent()) {
                    this.invokeDirectEditTool(node.get(), editingContext, diagram, input.getNewText());
                    response = new EventHandlerResponse(true, representation -> true, new EditLabelSuccessPayload(diagram));
                }
            } else if (input.getLabelId().endsWith(CENTERLABEL_SUFFIX)) {
                String inputId = this.extractEdgeId(input.getLabelId());
                var edge = this.diagramService.findEdgeById(diagram, inputId);
                if (edge.isPresent()) {
                    this.invokeDirectEditTool(edge.get(), editingContext, diagram, input.getNewText());
                    response = new EventHandlerResponse(true, representation -> true, new EditLabelSuccessPayload(diagram));
                }
            }
        }
        if (response != null) {
            return response;
        } else {
            String message = this.messageService.invalidInput(diagramInput.getClass().getSimpleName(), EditLabelInput.class.getSimpleName());
            return new EventHandlerResponse(false, representation -> false, new ErrorPayload(message));
        }
    }

    private String extractNodeId(String labelId) {
        return labelId.substring(0, labelId.length() - LABEL_SUFFIX.length());
    }

    private String extractEdgeId(String labelId) {
        return labelId.substring(0, labelId.length() - CENTERLABEL_SUFFIX.length());
    }

    private void invokeDirectEditTool(Node node, IEditingContext editingContext, Diagram diagram, String newText) {
        var optionalNodeDescription = this.findNodeDescription(node, diagram);
        if (optionalNodeDescription.isPresent()) {
            NodeDescription nodeDescription = optionalNodeDescription.get();

            var optionalSelf = this.objectService.getObject(editingContext, node.getTargetObjectId());
            if (optionalSelf.isPresent()) {
                Object self = optionalSelf.get();

                VariableManager variableManager = new VariableManager();
                variableManager.put(VariableManager.SELF, self);
                nodeDescription.getLabelEditHandler().apply(variableManager, newText);
                this.logger.debug("Edited label of diagram element {} to {}", node.getId(), newText); //$NON-NLS-1$
            }
        }
    }

    private void invokeDirectEditTool(Edge edge, IEditingContext editingContext, Diagram diagram, String newText) {
        var optionalEdgeDescription = this.findEdgeDescription(edge, diagram);
        if (optionalEdgeDescription.isPresent()) {
            EdgeDescription edgeDescription = optionalEdgeDescription.get();

            var optionalSelf = this.objectService.getObject(editingContext, edge.getTargetObjectId());
            if (optionalSelf.isPresent()) {
                Object self = optionalSelf.get();

                VariableManager variableManager = new VariableManager();
                variableManager.put(VariableManager.SELF, self);
                edgeDescription.getLabelEditHandler().apply(variableManager, newText);
                this.logger.debug("Edited label of diagram element {} to {}", edge.getId(), newText); //$NON-NLS-1$
            }
        }
    }

    private Optional<NodeDescription> findNodeDescription(Node node, Diagram diagram) {
        // @formatter:off
        return this.representationDescriptionService
                .findRepresentationDescriptionById(diagram.getDescriptionId())
                .filter(DiagramDescription.class::isInstance)
                .map(DiagramDescription.class::cast)
                .flatMap(diagramDescription -> this.diagramDescriptionService.findNodeDescriptionById(diagramDescription, node.getDescriptionId()));
        // @formatter:on
    }

    private Optional<EdgeDescription> findEdgeDescription(Edge edge, Diagram diagram) {
        // @formatter:off
        return this.representationDescriptionService
                   .findRepresentationDescriptionById(diagram.getDescriptionId())
                   .filter(DiagramDescription.class::isInstance)
                   .map(DiagramDescription.class::cast)
                   .flatMap(diagramDescription -> this.diagramDescriptionService.findEdgeDescriptionById(diagramDescription, edge.getDescriptionId()));
        // @formatter:on
    }
}
