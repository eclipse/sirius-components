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
package org.eclipse.sirius.web.compat.diagrams;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.style.ContainerStyleDescription;
import org.eclipse.sirius.diagram.description.style.WorkspaceImageDescription;
import org.eclipse.sirius.viewpoint.description.style.BasicLabelStyleDescription;
import org.eclipse.sirius.viewpoint.description.style.StyleFactory;
import org.eclipse.sirius.web.compat.api.IIdentifierProvider;
import org.eclipse.sirius.web.compat.api.IModelOperationHandlerSwitchProvider;
import org.eclipse.sirius.web.compat.api.ISemanticCandidatesProviderFactory;
import org.eclipse.sirius.web.compat.utils.StringValueProvider;
import org.eclipse.sirius.web.core.api.IEditService;
import org.eclipse.sirius.web.core.api.IObjectService;
import org.eclipse.sirius.web.diagrams.INodeStyle;
import org.eclipse.sirius.web.diagrams.NodeType;
import org.eclipse.sirius.web.diagrams.description.LabelDescription;
import org.eclipse.sirius.web.diagrams.description.LabelStyleDescription;
import org.eclipse.sirius.web.diagrams.description.NodeDescription;
import org.eclipse.sirius.web.interpreter.AQLInterpreter;
import org.eclipse.sirius.web.representations.VariableManager;

/**
 * This class is used to convert a Sirius {@link ContainerMapping} to an Sirius Web NodeDescription.
 *
 * @author smonnier
 */
public class ContainerMappingConverter {

    private final IObjectService objectService;

    private final IEditService editService;

    private final AQLInterpreter interpreter;

    private final IIdentifierProvider identifierProvider;

    private final ISemanticCandidatesProviderFactory semanticCandidatesProviderFactory;

    private final IModelOperationHandlerSwitchProvider modelOperationHandlerSwitchProvider;

    private final LabelStyleDescriptionConverter labelStyleDescriptionConverter;

    public ContainerMappingConverter(IObjectService objectService, IEditService editService, AQLInterpreter interpreter, IIdentifierProvider identifierProvider,
            ISemanticCandidatesProviderFactory semanticCandidatesProviderFactory, IModelOperationHandlerSwitchProvider modelOperationHandlerSwitchProvider) {
        this.objectService = Objects.requireNonNull(objectService);
        this.editService = Objects.requireNonNull(editService);
        this.interpreter = Objects.requireNonNull(interpreter);
        this.identifierProvider = Objects.requireNonNull(identifierProvider);
        this.semanticCandidatesProviderFactory = Objects.requireNonNull(semanticCandidatesProviderFactory);
        this.modelOperationHandlerSwitchProvider = Objects.requireNonNull(modelOperationHandlerSwitchProvider);
        this.labelStyleDescriptionConverter = new LabelStyleDescriptionConverter(this.interpreter, this.objectService);
    }

    public NodeDescription convert(ContainerMapping containerMapping, Map<UUID, NodeDescription> id2NodeDescriptions) {
        ContainerStyleDescriptionProvider containerStyleDescriptionProvider = new ContainerStyleDescriptionProvider(this.interpreter, containerMapping);

        Function<VariableManager, LabelStyleDescription> labelStyleDescriptionProvider = variableManager -> {
            ContainerStyleDescription styleDescription = containerStyleDescriptionProvider.getContainerStyleDescription(variableManager);
            BasicLabelStyleDescription basicLabelStyleDescription = Optional.ofNullable(styleDescription).map(BasicLabelStyleDescription.class::cast).orElse(this.getDefaultLabelStyle());
            return this.labelStyleDescriptionConverter.convert(basicLabelStyleDescription);
        };

        Function<VariableManager, String> labelExpressionProvider = variableManager -> {
            ContainerStyleDescription styleDescription = containerStyleDescriptionProvider.getContainerStyleDescription(variableManager);
            String labelExpression = Optional.ofNullable(styleDescription).map(ContainerStyleDescription::getLabelExpression).orElse(""); //$NON-NLS-1$
            return new StringValueProvider(this.interpreter, labelExpression).apply(variableManager);
        };

        Function<VariableManager, String> labelIdProvider = variableManager -> {
            Object parentId = variableManager.getVariables().get(LabelDescription.OWNER_ID);
            return String.valueOf(parentId) + LabelDescription.LABEL_SUFFIX;
        };

        LabelDescription labelDescription = LabelDescription.newLabelDescription(this.identifierProvider.getIdentifier(containerMapping) + LabelDescription.LABEL_SUFFIX).idProvider(labelIdProvider)
                .textProvider(labelExpressionProvider).styleDescriptionProvider(labelStyleDescriptionProvider).build();
        // @formatter:on

        Function<VariableManager, String> semanticTargetIdProvider = variableManager -> {
            return variableManager.get(VariableManager.SELF, Object.class).map(this.objectService::getId).orElse(null);
        };
        Function<VariableManager, String> semanticTargetKindProvider = variableManager -> {
            return variableManager.get(VariableManager.SELF, Object.class).map(this.objectService::getKind).orElse(null);
        };
        Function<VariableManager, String> semanticTargetLabelProvider = variableManager -> {
            return variableManager.get(VariableManager.SELF, Object.class).map(this.objectService::getLabel).orElse(null);
        };
        Function<VariableManager, String> typeProvider = variableManager -> {
            ContainerStyleDescription containerStyle = containerStyleDescriptionProvider.getContainerStyleDescription(variableManager);
            if (containerStyle instanceof WorkspaceImageDescription) {
                return NodeType.NODE_IMAGE;
            }
            return NodeType.NODE_RECTANGLE;
        };

        List<NodeDescription> childNodeDescriptions = new ArrayList<>();
        // @formatter:off
        NodeMappingConverter nodeMappingConverter = new NodeMappingConverter(this.objectService, this.editService, this.interpreter, this.identifierProvider, this.semanticCandidatesProviderFactory, this.modelOperationHandlerSwitchProvider);
        List<NodeDescription> childNodeMappingDescriptions = containerMapping.getSubNodeMappings().stream()
                .map(childNodeMapping -> nodeMappingConverter.convert(childNodeMapping, id2NodeDescriptions))
                .collect(Collectors.toList());

        ContainerMappingConverter containerMappingConverter = new ContainerMappingConverter(this.objectService, this.editService, this.interpreter, this.identifierProvider, this.semanticCandidatesProviderFactory, this.modelOperationHandlerSwitchProvider);
        List<NodeDescription> childContainerMappingDescriptions = containerMapping.getSubContainerMappings().stream()
                .map(childContainerMapping -> containerMappingConverter.convert(childContainerMapping, id2NodeDescriptions))
                .collect(Collectors.toList());

        childNodeDescriptions.addAll(childNodeMappingDescriptions);
        childNodeDescriptions.addAll(childContainerMappingDescriptions);

        List<NodeDescription> borderNodeDescriptions = containerMapping.getBorderedNodeMappings().stream()
                .map(borderNodeMapping -> new NodeMappingConverter(this.objectService, this.editService, this.interpreter, this.identifierProvider, this.semanticCandidatesProviderFactory, this.modelOperationHandlerSwitchProvider).convert(borderNodeMapping, id2NodeDescriptions))
                .collect(Collectors.toList());
        // @formatter:on

        String domainClass = containerMapping.getDomainClass();
        String semanticCandidatesExpression = containerMapping.getSemanticCandidatesExpression();
        String preconditionExpression = containerMapping.getPreconditionExpression();
        Function<VariableManager, List<Object>> semanticElementsProvider = this.semanticCandidatesProviderFactory.getSemanticCandidatesProvider(this.interpreter, domainClass,
                semanticCandidatesExpression, preconditionExpression);
        Function<VariableManager, INodeStyle> styleProvider = new ContainerMappingStyleProvider(this.interpreter, containerMapping);

        ToolConverter toolConverter = new ToolConverter(this.interpreter, this.editService, this.modelOperationHandlerSwitchProvider);
        var deleteHandler = toolConverter.createDeleteToolHandler(containerMapping.getDeletionDescription());
        var labelEditHandler = toolConverter.createDirectEditToolHandler(containerMapping.getLabelDirectEdit());

        // @formatter:off
        NodeDescription description = NodeDescription.newNodeDescription(UUID.fromString(this.identifierProvider.getIdentifier(containerMapping)))
                .typeProvider(typeProvider)
                .targetObjectIdProvider(semanticTargetIdProvider)
                .targetObjectKindProvider(semanticTargetKindProvider)
                .targetObjectLabelProvider(semanticTargetLabelProvider)
                .semanticElementsProvider(semanticElementsProvider)
                .labelDescription(labelDescription)
                .styleProvider(styleProvider)
                .childNodeDescriptions(childNodeDescriptions)
                .borderNodeDescriptions(borderNodeDescriptions)
                .labelEditHandler(labelEditHandler)
                .deleteHandler(deleteHandler)
                .build();
        // @formatter:on

        id2NodeDescriptions.put(description.getId(), description);

        return description;
    }

    private BasicLabelStyleDescription getDefaultLabelStyle() {
        var labelStyle = StyleFactory.eINSTANCE.createBasicLabelStyleDescription();
        labelStyle.setLabelExpression(""); //$NON-NLS-1$
        labelStyle.setShowIcon(true);
        labelStyle.setLabelSize(16);
        return labelStyle;
    }
}
