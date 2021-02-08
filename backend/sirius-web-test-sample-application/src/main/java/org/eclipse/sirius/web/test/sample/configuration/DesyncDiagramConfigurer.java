/*******************************************************************************
 * Copyright (c) 2021 Obeo.
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
package org.eclipse.sirius.web.test.sample.configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.web.api.configuration.IRepresentationDescriptionRegistry;
import org.eclipse.sirius.web.api.configuration.IRepresentationDescriptionRegistryConfigurer;
import org.eclipse.sirius.web.collaborative.diagrams.api.IDiagramContext;
import org.eclipse.sirius.web.core.api.IObjectService;
import org.eclipse.sirius.web.diagrams.Diagram;
import org.eclipse.sirius.web.diagrams.INodeStyle;
import org.eclipse.sirius.web.diagrams.LineStyle;
import org.eclipse.sirius.web.diagrams.Node;
import org.eclipse.sirius.web.diagrams.NodeType;
import org.eclipse.sirius.web.diagrams.RectangularNodeStyle;
import org.eclipse.sirius.web.diagrams.ViewCreationRequest;
import org.eclipse.sirius.web.diagrams.description.DiagramDescription;
import org.eclipse.sirius.web.diagrams.description.LabelDescription;
import org.eclipse.sirius.web.diagrams.description.LabelStyleDescription;
import org.eclipse.sirius.web.diagrams.description.NodeDescription;
import org.eclipse.sirius.web.diagrams.description.SynchronizationPolicy;
import org.eclipse.sirius.web.diagrams.tools.DropTool;
import org.eclipse.sirius.web.diagrams.tools.ToolSection;
import org.eclipse.sirius.web.representations.Status;
import org.eclipse.sirius.web.representations.VariableManager;
import org.springframework.stereotype.Component;

/**
 * Provides a simple desync diagram to test the drop tool.
 *
 * @author pcdavid
 * @author hmarchadour
 */
@Component
public class DesyncDiagramConfigurer implements IRepresentationDescriptionRegistryConfigurer {

    private final IObjectService objectService;

    public DesyncDiagramConfigurer(IObjectService objectService) {
        this.objectService = Objects.requireNonNull(objectService);
    }

    @Override
    public void addRepresentationDescriptions(IRepresentationDescriptionRegistry registry) {
        registry.add(this.getDomainDiagramDescription());
    }

    private DiagramDescription getDomainDiagramDescription() {
        // @formatter:off
        Function<VariableManager, String> semanticTargetIdProvider = variableManager -> {
            return variableManager.get(VariableManager.SELF, Object.class).map(this.objectService::getId).orElse(null);
        };
        Function<VariableManager, String> semanticTargetKindProvider = variableManager -> {
            return variableManager.get(VariableManager.SELF, Object.class).map(this.objectService::getKind).orElse(null);
        };
        Function<VariableManager, String> semanticTargetLabelProvider = variableManager -> {
            return variableManager.get(VariableManager.SELF, Object.class).map(this.objectService::getLabel).orElse(null);
        };
        Function<VariableManager, INodeStyle> styleProvider = variableManager -> {
            return RectangularNodeStyle.newRectangularNodeStyle()
                    .color("#4e90d2") //$NON-NLS-1$
                    .borderColor("") //$NON-NLS-1$
                    .borderSize(0)
                    .borderStyle(LineStyle.Solid)
                    .build();
        };

        NodeDescription secondDesyncDescription = NodeDescription.newNodeDescription(UUID.nameUUIDFromBytes("desync-type2".getBytes())) //$NON-NLS-1$
                .semanticElementsProvider(vm -> vm.get(VariableManager.SELF, EObject.class).map(this::getAllCandidates).orElse(List.of()))
                .targetObjectIdProvider(semanticTargetIdProvider)
                .targetObjectKindProvider(semanticTargetKindProvider)
                .targetObjectLabelProvider(semanticTargetLabelProvider)
                .synchronizationPolicy(SynchronizationPolicy.UNSYNCHRONIZED)
                .typeProvider(variableManager -> NodeType.NODE_RECTANGLE)
                .styleProvider(styleProvider)
                .labelDescription(this.createLabelDescription())
                .labelEditHandler((variableManager, newValue) -> Status.OK)
                .deleteHandler(variableManager -> Status.OK)
                .borderNodeDescriptions(List.of())
                .childNodeDescriptions(List.of())
                .build();

        NodeDescription firstDesyncDescription = NodeDescription.newNodeDescription(UUID.nameUUIDFromBytes("desync-type1".getBytes())) //$NON-NLS-1$
                                                        .semanticElementsProvider(vm -> vm.get(VariableManager.SELF, EObject.class).map(this::getAllCandidates).orElse(List.of()))
                                                        .targetObjectIdProvider(semanticTargetIdProvider)
                                                        .targetObjectKindProvider(semanticTargetKindProvider)
                                                        .targetObjectLabelProvider(semanticTargetLabelProvider)
                                                        .synchronizationPolicy(SynchronizationPolicy.UNSYNCHRONIZED)
                                                        .typeProvider(variableManager -> NodeType.NODE_RECTANGLE)
                                                        .styleProvider(styleProvider)
                                                        .labelDescription(this.createLabelDescription())
                                                        .labelEditHandler((variableManager, newValue) -> Status.OK)
                                                        .deleteHandler(variableManager -> Status.OK)
                                                        .borderNodeDescriptions(List.of())
                                                        .childNodeDescriptions(List.of(secondDesyncDescription))
                                                        .build();
       // @formatter:on

        // @formatter:off
        Function<VariableManager, Status> handler = vm -> {
            Optional<String> optionalSelfId = vm.get(VariableManager.SELF, Object.class).map(this.objectService::getId);
            Optional<IDiagramContext> optionalDiagramContext = vm.get(IDiagramContext.DIAGRAM_CONTEXT, IDiagramContext.class);
            if (optionalSelfId.isPresent() && optionalDiagramContext.isPresent()) {
                IDiagramContext diagramContext = optionalDiagramContext.get();
                Diagram diagram = diagramContext.getDiagram();
                UUID parentElementId = diagram.getId();
                UUID descriptionId = firstDesyncDescription.getId();
                Optional<Node> optionalContainer = vm.get(NodeDescription.NODE_CONTAINER, Node.class);
                if(optionalContainer.isPresent()) {
                    parentElementId = optionalContainer.get().getId();
                    descriptionId = secondDesyncDescription.getId();
                }
                ViewCreationRequest viewCreationRequest = ViewCreationRequest.newViewCreationRequest()
                        .descriptionId(descriptionId)
                        .parentElementId(parentElementId)
                        .targetObjectId(optionalSelfId.get())
                        .build();
                diagramContext.getViewCreationRequests().add(viewCreationRequest);
                return Status.OK;
            }
           return Status.ERROR;
        };
        DropTool dropTool = DropTool.newDropTool(UUID.randomUUID().toString())
            .label("dropTool")//$NON-NLS-1$
            .imageURL("") //$NON-NLS-1$
            .handler(handler)
            .targetDescriptions(List.of(firstDesyncDescription))
            .appliesToDiagramRoot(true)
            .build();
        return DiagramDescription.newDiagramDescription(UUID.nameUUIDFromBytes("desync-diagram".getBytes())) //$NON-NLS-1$
                                 .label("Desync Diagram") //$NON-NLS-1$
                                 .labelProvider(variableManager -> variableManager.get(DiagramDescription.LABEL, String.class).orElse("Anonymous Domain")) //$NON-NLS-1$
                                 .canCreatePredicate(variableManager -> true)
                                 .targetObjectIdProvider(semanticTargetIdProvider)
                                 .nodeDescriptions(List.of(firstDesyncDescription))
                                 .edgeDescriptions(List.of())
                                 .toolSections(List.of(ToolSection.newToolSection("DropTools") //$NON-NLS-1$
                                         .tools(
                                                 List.of(dropTool)
                                         )
                                         .label("Drop Tools") //$NON-NLS-1$
                                         .imageURL("") //$NON-NLS-1$
                                         .build()))
                                 .build();
        // @formatter:on
    }

    private List<Object> getAllCandidates(EObject eObject) {
        List<Object> result = new ArrayList<>();
        eObject.eResource().getAllContents().forEachRemaining(result::add);
        return result;
    }

    private LabelDescription createLabelDescription() {
        // @formatter:off
        var styleDescription = LabelStyleDescription.newLabelStyleDescription()
                                                    .colorProvider(variableManager -> "#051e37") //$NON-NLS-1$
                                                    .fontSizeProvider(variableManager -> 16)
                                                    .boldProvider(variableManager -> false)
                                                    .italicProvider(variableManager -> false)
                                                    .underlineProvider(variableManager -> false)
                                                    .strikeThroughProvider(variableManager -> false)
                                                    .iconURLProvider(variableManager -> "") //$NON-NLS-1$
                                                    .build();

        Function<VariableManager, String> labelIdProvider = variableManager -> {
            Object parentId = variableManager.getVariables().get(LabelDescription.OWNER_ID);
            return String.valueOf(parentId) + LabelDescription.LABEL_SUFFIX;
        };

        return LabelDescription.newLabelDescription("domain-type-label") //$NON-NLS-1$
                .idProvider(labelIdProvider)
                .textProvider(variableManager -> variableManager.get(VariableManager.SELF, EObject.class).map(EObject::eClass).map(EClass::getName).orElse("")) //$NON-NLS-1$
                .styleDescription(styleDescription)
                .build();
        // @formatter:on
    }

}
