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
package org.eclipse.sirius.web.compat.services.diagrams;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import org.eclipse.sirius.diagram.business.internal.metamodel.helper.LayerHelper;
import org.eclipse.sirius.diagram.description.AdditionalLayer;
import org.eclipse.sirius.diagram.description.ContainerMappingImport;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.web.compat.api.IIdentifierProvider;
import org.eclipse.sirius.web.compat.api.IModelOperationHandlerSwitchProvider;
import org.eclipse.sirius.web.compat.api.ISemanticCandidatesProviderFactory;
import org.eclipse.sirius.web.compat.diagrams.ContainerMappingConverter;
import org.eclipse.sirius.web.compat.diagrams.EdgeMappingConverter;
import org.eclipse.sirius.web.compat.diagrams.NodeMappingConverter;
import org.eclipse.sirius.web.core.api.IEditService;
import org.eclipse.sirius.web.core.api.IObjectService;
import org.eclipse.sirius.web.diagrams.description.DiagramDescription.Builder;
import org.eclipse.sirius.web.diagrams.description.EdgeDescription;
import org.eclipse.sirius.web.diagrams.description.NodeDescription;
import org.eclipse.sirius.web.interpreter.AQLInterpreter;
import org.springframework.stereotype.Service;

/**
 * Used to populate both the nodeDescriptions, the edgeDescriptions and the tool sections of the diagram description
 * builder.
 *
 * @author sbegaudeau
 */
@Service
public class DiagramDescriptionNodeAndEdgeDescriptionsPopulator implements IDiagramDescriptionPopulator {

    private final IObjectService objectService;

    private final IEditService editService;

    private final IIdentifierProvider identifierProvider;

    private final ISemanticCandidatesProviderFactory semanticCandidatesProviderFactory;

    private final IModelOperationHandlerSwitchProvider modelOperationHandlerSwitchProvider;

    private final IToolProvider toolProvider;

    public DiagramDescriptionNodeAndEdgeDescriptionsPopulator(IObjectService objectService, IEditService editService, IIdentifierProvider identifierProvider,
            ISemanticCandidatesProviderFactory semanticCandidatesProviderFactory, IModelOperationHandlerSwitchProvider modelOperationHandlerSwitchProvider, IToolProvider toolProvider) {
        this.objectService = Objects.requireNonNull(objectService);
        this.editService = Objects.requireNonNull(editService);
        this.identifierProvider = Objects.requireNonNull(identifierProvider);
        this.semanticCandidatesProviderFactory = Objects.requireNonNull(semanticCandidatesProviderFactory);
        this.modelOperationHandlerSwitchProvider = Objects.requireNonNull(modelOperationHandlerSwitchProvider);
        this.toolProvider = Objects.requireNonNull(toolProvider);
    }

    @Override
    public Builder populate(Builder builder, DiagramDescription siriusDiagramDescription, AQLInterpreter interpreter) {
        Map<UUID, NodeDescription> id2NodeDescriptions = new HashMap<>();

        // @formatter:off
        ContainerMappingConverter containerMappingConverter = new ContainerMappingConverter(this.objectService, this.editService, interpreter, this.identifierProvider, this.semanticCandidatesProviderFactory, this.modelOperationHandlerSwitchProvider);
        List<Layer> layers = LayerHelper.getAllLayers(siriusDiagramDescription).stream()
                .filter(this::isEnabledByDefault)
                .collect(Collectors.toList());

        List<NodeDescription> containerDescriptions = layers.stream()
                .flatMap(layer -> layer.getContainerMappings().stream().filter(containerMapping -> !(containerMapping instanceof ContainerMappingImport)))
                .map(containerMapping -> containerMappingConverter.convert(containerMapping, id2NodeDescriptions))
                .collect(Collectors.toList());
        // @formatter:on

        // @formatter:off
        NodeMappingConverter nodeMappingConverter = new NodeMappingConverter(this.objectService, this.editService, interpreter, this.identifierProvider, this.semanticCandidatesProviderFactory, this.modelOperationHandlerSwitchProvider);
        List<NodeDescription> nodeDescriptions = layers.stream()
                .flatMap(layer -> layer.getNodeMappings().stream())
                .map(nodeMapping -> nodeMappingConverter.convert(nodeMapping, id2NodeDescriptions))
                .collect(Collectors.toList());
        // @formatter:on
        nodeDescriptions.addAll(containerDescriptions);

        // @formatter:off
        EdgeMappingConverter edgeMappingConverter = new EdgeMappingConverter(this.objectService, this.editService, interpreter, this.identifierProvider, this.semanticCandidatesProviderFactory, this.modelOperationHandlerSwitchProvider, id2NodeDescriptions);
        List<EdgeDescription> edgeDescriptions = layers.stream()
                .flatMap(layer -> layer.getEdgeMappings().stream())
                .map(edgeMappingConverter::convert)
                .collect(Collectors.toList());
        // @formatter:on
        return builder.nodeDescriptions(nodeDescriptions).edgeDescriptions(edgeDescriptions)
                .toolSections(this.toolProvider.getToolSections(id2NodeDescriptions, edgeDescriptions, siriusDiagramDescription, layers));
    }

    private boolean isEnabledByDefault(Layer layer) {
        if (layer instanceof AdditionalLayer) {
            AdditionalLayer additionalLayer = (AdditionalLayer) layer;
            return !additionalLayer.isOptional() || additionalLayer.isActiveByDefault();
        }
        return true;
    }

}
