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
package org.eclipse.sirius.web.diagrams.layout;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import org.eclipse.elk.alg.layered.options.LayeredOptions;
import org.eclipse.elk.core.IGraphLayoutEngine;
import org.eclipse.elk.core.LayoutConfigurator;
import org.eclipse.elk.core.RecursiveGraphLayoutEngine;
import org.eclipse.elk.core.data.LayoutMetaDataService;
import org.eclipse.elk.core.util.BasicProgressMonitor;
import org.eclipse.elk.core.util.ElkUtil;
import org.eclipse.elk.graph.ElkGraphElement;
import org.eclipse.elk.graph.ElkNode;
import org.eclipse.sirius.web.core.api.IRepresentationDescriptionSearchService;
import org.eclipse.sirius.web.diagrams.Diagram;
import org.eclipse.sirius.web.diagrams.MoveEvent;
import org.eclipse.sirius.web.diagrams.Position;
import org.eclipse.sirius.web.diagrams.description.DiagramDescription;
import org.eclipse.sirius.web.diagrams.layout.api.ILayoutService;
import org.eclipse.sirius.web.diagrams.layout.incremental.IncrementalLayoutConvertedDiagram;
import org.eclipse.sirius.web.diagrams.layout.incremental.IncrementalLayoutDiagramConverter;
import org.eclipse.sirius.web.diagrams.layout.incremental.IncrementalLayoutEngine;
import org.eclipse.sirius.web.diagrams.layout.incremental.IncrementalLayoutedDiagramProvider;
import org.eclipse.sirius.web.diagrams.layout.incremental.data.DiagramLayoutData;
import org.eclipse.sirius.web.diagrams.layout.incremental.data.ILayoutData;
import org.springframework.stereotype.Service;

/**
 * Perform the layouting of the given diagram.
 *
 * @author sbegaudeau
 */
@Service
public class LayoutService implements ILayoutService {

    private final ELKDiagramConverter elkDiagramConverter;

    private final LayoutConfiguratorRegistry layoutConfiguratorRegistry;

    private final ELKLayoutedDiagramProvider elkLayoutedDiagramProvider;

    private final IncrementalLayoutedDiagramProvider incrementalLayoutedDiagramProvider;

    private final IRepresentationDescriptionSearchService representationDescriptionSearchService;

    private final IncrementalLayoutDiagramConverter incrementalLayoutDiagramConverter;

    public LayoutService(ELKDiagramConverter elkDiagramConverter, IncrementalLayoutDiagramConverter incrementalLayoutDiagramConverter, LayoutConfiguratorRegistry layoutConfiguratorRegistry,
            ELKLayoutedDiagramProvider layoutedDiagramProvider, IncrementalLayoutedDiagramProvider incrementalLayoutedDiagramProvider,
            IRepresentationDescriptionSearchService representationDescriptionSearchService) {
        this.elkDiagramConverter = Objects.requireNonNull(elkDiagramConverter);
        this.incrementalLayoutDiagramConverter = Objects.requireNonNull(incrementalLayoutDiagramConverter);
        this.layoutConfiguratorRegistry = Objects.requireNonNull(layoutConfiguratorRegistry);
        this.elkLayoutedDiagramProvider = Objects.requireNonNull(layoutedDiagramProvider);
        this.incrementalLayoutedDiagramProvider = Objects.requireNonNull(incrementalLayoutedDiagramProvider);
        this.representationDescriptionSearchService = Objects.requireNonNull(representationDescriptionSearchService);
    }

    @Override
    public Diagram layout(Diagram diagram) {
        ELKConvertedDiagram convertedDiagram = this.elkDiagramConverter.convert(diagram);

        ElkNode elkDiagram = convertedDiagram.getElkDiagram();
        var representationDescription = this.representationDescriptionSearchService.findRepresentationDescriptionById(diagram.getDescriptionId());
        LayoutConfigurator layoutConfigurator;
        if (representationDescription.isPresent() && representationDescription.get() instanceof DiagramDescription) {
            layoutConfigurator = this.layoutConfiguratorRegistry.getLayoutConfigurator(diagram, (DiagramDescription) representationDescription.get());
        } else {
            layoutConfigurator = this.layoutConfiguratorRegistry.getDefaultLayoutConfigurator();
        }

        LayoutMetaDataService.getInstance().registerLayoutMetaDataProviders(new LayeredOptions());

        ElkUtil.applyVisitors(elkDiagram, layoutConfigurator);
        IGraphLayoutEngine engine = new RecursiveGraphLayoutEngine();
        engine.layout(elkDiagram, new BasicProgressMonitor());

        Map<String, ElkGraphElement> id2ElkGraphElements = convertedDiagram.getId2ElkGraphElements();
        Diagram layoutedDiagram = this.elkLayoutedDiagramProvider.getLayoutedDiagram(diagram, elkDiagram, id2ElkGraphElements);

        return layoutedDiagram;
    }

    @Override
    public Diagram incrementalLayout(Diagram newDiagram, MoveEvent moveEvent, Position startingPosition) {
        IncrementalLayoutConvertedDiagram convertedDiagram = this.incrementalLayoutDiagramConverter.convert(newDiagram);
        DiagramLayoutData diagramLayoutData = convertedDiagram.getDiagramLayoutData();
        new IncrementalLayoutEngine(moveEvent, startingPosition).layout(diagramLayoutData);
        Map<UUID, ILayoutData> id2LayoutData = convertedDiagram.getId2LayoutData();
        return this.incrementalLayoutedDiagramProvider.getLayoutedDiagram(newDiagram, diagramLayoutData, id2LayoutData);
    }

}
