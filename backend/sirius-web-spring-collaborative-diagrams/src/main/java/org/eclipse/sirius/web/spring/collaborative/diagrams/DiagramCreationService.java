/*******************************************************************************
 * Copyright (c) 2019, 2021 Obeo and others.
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
package org.eclipse.sirius.web.spring.collaborative.diagrams;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.eclipse.sirius.web.collaborative.api.dto.RepresentationDescriptor;
import org.eclipse.sirius.web.collaborative.api.services.IRepresentationPersistenceService;
import org.eclipse.sirius.web.collaborative.api.services.Monitoring;
import org.eclipse.sirius.web.collaborative.diagrams.api.IDiagramContext;
import org.eclipse.sirius.web.collaborative.diagrams.api.IDiagramCreationService;
import org.eclipse.sirius.web.components.Element;
import org.eclipse.sirius.web.core.api.IEditingContext;
import org.eclipse.sirius.web.core.api.IObjectService;
import org.eclipse.sirius.web.core.api.IRepresentationDescriptionSearchService;
import org.eclipse.sirius.web.diagrams.Diagram;
import org.eclipse.sirius.web.diagrams.MoveEvent;
import org.eclipse.sirius.web.diagrams.Position;
import org.eclipse.sirius.web.diagrams.ViewCreationRequest;
import org.eclipse.sirius.web.diagrams.components.DiagramComponent;
import org.eclipse.sirius.web.diagrams.components.DiagramComponentProps;
import org.eclipse.sirius.web.diagrams.components.DiagramComponentProps.Builder;
import org.eclipse.sirius.web.diagrams.description.DiagramDescription;
import org.eclipse.sirius.web.diagrams.layout.api.ILayoutService;
import org.eclipse.sirius.web.diagrams.renderer.DiagramRenderer;
import org.eclipse.sirius.web.representations.VariableManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;

/**
 * Service used to create diagrams.
 *
 * @author sbegaudeau
 */
@Service
public class DiagramCreationService implements IDiagramCreationService {

    private final IRepresentationDescriptionSearchService representationDescriptionSearchService;

    private final IRepresentationPersistenceService representationPersistenceService;

    private final IObjectService objectService;

    private final ILayoutService layoutService;

    private boolean activateAutoLayout;

    private final Timer timer;

    private final Logger logger = LoggerFactory.getLogger(DiagramCreationService.class);

    public DiagramCreationService(IRepresentationDescriptionSearchService representationDescriptionSearchService, IRepresentationPersistenceService representationPersistenceService,
            IObjectService objectService, ILayoutService layoutService, @Value("${sirius.web.diagrams.autolayout.activate:false}") boolean activateAutoLayout, MeterRegistry meterRegistry) {
        this.representationDescriptionSearchService = Objects.requireNonNull(representationDescriptionSearchService);
        this.representationPersistenceService = Objects.requireNonNull(representationPersistenceService);
        this.objectService = Objects.requireNonNull(objectService);
        this.layoutService = Objects.requireNonNull(layoutService);
        this.activateAutoLayout = activateAutoLayout;
        // @formatter:off
        this.timer = Timer.builder(Monitoring.REPRESENTATION_EVENT_PROCESSOR_REFRESH)
                .tag(Monitoring.NAME, "diagram") //$NON-NLS-1$
                .register(meterRegistry);
        // @formatter:on
    }

    @Override
    public Diagram create(String label, Object targetObject, DiagramDescription diagramDescription, IEditingContext editingContext) {
        Diagram newDiagram = this.doRender(label, targetObject, editingContext, diagramDescription, Optional.empty());
        return this.layoutService.layout(newDiagram);
    }

    @Override
    public Optional<Diagram> refresh(IEditingContext editingContext, IDiagramContext diagramContext) {
        Diagram previousDiagram = diagramContext.getDiagram();
        var optionalObject = this.objectService.getObject(editingContext, previousDiagram.getTargetObjectId());
        // @formatter:off
        var optionalDiagramDescription = this.representationDescriptionSearchService.findRepresentationDescriptionById(previousDiagram.getDescriptionId())
                .filter(DiagramDescription.class::isInstance)
                .map(DiagramDescription.class::cast);
        // @formatter:on

        if (optionalObject.isPresent() && optionalDiagramDescription.isPresent()) {
            Object object = optionalObject.get();
            DiagramDescription diagramDescription = optionalDiagramDescription.get();
            Diagram diagram = this.doRender(previousDiagram.getLabel(), object, editingContext, diagramDescription, Optional.of(diagramContext));
            return Optional.of(diagram);
        }
        return Optional.empty();
    }

    private Diagram doRender(String label, Object targetObject, IEditingContext editingContext, DiagramDescription diagramDescription, Optional<IDiagramContext> optionalDiagramContext) {
        long start = System.currentTimeMillis();

        VariableManager variableManager = new VariableManager();
        variableManager.put(DiagramDescription.LABEL, label);
        variableManager.put(VariableManager.SELF, targetObject);
        variableManager.put(IEditingContext.EDITING_CONTEXT, editingContext);

        MoveEvent moveEvent = optionalDiagramContext.map(IDiagramContext::getMoveEvent).orElse(null);
        Optional<Diagram> optionalPreviousDiagram = optionalDiagramContext.map(IDiagramContext::getDiagram);
        Position startingPosition = optionalDiagramContext.map(IDiagramContext::getStartingPosition).orElse(null);
        List<ViewCreationRequest> viewCreationRequests = optionalDiagramContext.map(IDiagramContext::getViewCreationRequests).orElse(List.of());
        //@formatter:off
        Builder builder = DiagramComponentProps.newDiagramComponentProps()
                .variableManager(variableManager)
                .diagramDescription(diagramDescription)
                .viewCreationRequests(viewCreationRequests)
                .previousDiagram(optionalPreviousDiagram);
        //@formatter:on
        DiagramComponentProps props = builder.build();
        Element element = new Element(DiagramComponent.class, props);

        Diagram newDiagram = new DiagramRenderer(this.logger).render(element);

        // The auto layout is used for the first rendering and after that if it is activated
        if (optionalDiagramContext.isEmpty() || this.activateAutoLayout) {
            newDiagram = this.layoutService.layout(newDiagram);
        } else if (optionalDiagramContext.isPresent()) {
            newDiagram = this.layoutService.incrementalLayout(newDiagram, moveEvent, startingPosition);
        }
        RepresentationDescriptor representationDescriptor = this.getRepresentationDescriptor(editingContext.getId(), newDiagram);
        this.representationPersistenceService.persist(representationDescriptor);
        long end = System.currentTimeMillis();
        this.timer.record(end - start, TimeUnit.MILLISECONDS);
        return newDiagram;
    }

    private RepresentationDescriptor getRepresentationDescriptor(UUID projectId, Diagram diagram) {
        // @formatter:off
        return RepresentationDescriptor.newRepresentationDescriptor(diagram.getId())
                .projectId(projectId)
                .descriptionId(diagram.getDescriptionId())
                .targetObjectId(diagram.getTargetObjectId())
                .label(diagram.getLabel())
                .representation(diagram)
                .build();
        // @formatter:on
    }

}
