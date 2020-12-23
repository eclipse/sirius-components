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
package org.eclipse.sirius.web.compat.diagrams;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.viewpoint.description.style.BasicLabelStyleDescription;
import org.eclipse.sirius.web.compat.services.representations.IdentifierProvider;
import org.eclipse.sirius.web.compat.utils.SemanticCandidatesProvider;
import org.eclipse.sirius.web.compat.utils.StringValueProvider;
import org.eclipse.sirius.web.components.Element;
import org.eclipse.sirius.web.diagrams.EdgeStyle;
import org.eclipse.sirius.web.diagrams.Label;
import org.eclipse.sirius.web.diagrams.LabelStyle;
import org.eclipse.sirius.web.diagrams.Position;
import org.eclipse.sirius.web.diagrams.Size;
import org.eclipse.sirius.web.diagrams.description.EdgeDescription;
import org.eclipse.sirius.web.diagrams.description.LabelDescription;
import org.eclipse.sirius.web.diagrams.description.LabelStyleDescription;
import org.eclipse.sirius.web.diagrams.description.NodeDescription;
import org.eclipse.sirius.web.diagrams.elements.NodeElementProps;
import org.eclipse.sirius.web.interpreter.AQLInterpreter;
import org.eclipse.sirius.web.representations.VariableManager;
import org.eclipse.sirius.web.services.api.objects.IEditService;
import org.eclipse.sirius.web.services.api.objects.IObjectService;

/**
 * This class is used to convert a Sirius EdgeMapping to an Sirius Web EdgeDescription.
 *
 * @author sbegaudeau
 */
public class EdgeMappingConverter {

    private final IObjectService objectService;

    private final IEditService editService;

    private final AQLInterpreter interpreter;

    private final IdentifierProvider identifierProvider;

    private final Map<UUID, NodeDescription> id2NodeDescriptions;

    public EdgeMappingConverter(IObjectService objectService, IEditService editService, AQLInterpreter interpreter, IdentifierProvider identifierProvider,
            Map<UUID, NodeDescription> id2NodeDescriptions) {
        this.objectService = Objects.requireNonNull(objectService);
        this.editService = Objects.requireNonNull(editService);
        this.interpreter = Objects.requireNonNull(interpreter);
        this.identifierProvider = Objects.requireNonNull(identifierProvider);
        this.id2NodeDescriptions = Objects.requireNonNull(id2NodeDescriptions);
    }

    public EdgeDescription convert(EdgeMapping edgeMapping) {
        Function<VariableManager, String> idProvider = variableManager -> {
            // @formatter:off
            var sourceId = Optional.of(variableManager.getVariables().get(EdgeDescription.SOURCE_NODE))
                    .filter(Element.class::isInstance)
                    .map(Element.class::cast)
                    .map(Element::getProps)
                    .filter(NodeElementProps.class::isInstance)
                    .map(NodeElementProps.class::cast)
                    .map(NodeElementProps::getId)
                    .orElse(""); //$NON-NLS-1$

            var targetId = Optional.of(variableManager.getVariables().get(EdgeDescription.TARGET_NODE))
                    .filter(Element.class::isInstance)
                    .map(Element.class::cast)
                    .map(Element::getProps)
                    .filter(NodeElementProps.class::isInstance)
                    .map(NodeElementProps.class::cast)
                    .map(NodeElementProps::getId)
                    .orElse(""); //$NON-NLS-1$

            var count = Optional.of(variableManager.getVariables().get(EdgeDescription.COUNT))
                    .filter(Integer.class::isInstance).orElse(0);
            // @formatter:on

            return sourceId + " --> " + targetId + " - " + count; //$NON-NLS-1$ //$NON-NLS-2$
        };

        Function<VariableManager, String> targetIdProvider = variableManager -> {
            return variableManager.get(VariableManager.SELF, Object.class).map(this.objectService::getId).orElse(null);
        };
        Function<VariableManager, String> targetKindProvider = variableManager -> {
            return variableManager.get(VariableManager.SELF, Object.class).map(this.objectService::getKind).orElse(null);
        };
        Function<VariableManager, String> targetLabelProvider = variableManager -> {
            return variableManager.get(VariableManager.SELF, Object.class).map(this.objectService::getLabel).orElse(null);
        };

        List<NodeDescription> sourceNodeDescriptions = this.getNodeDescriptions(edgeMapping.getSourceMapping());
        List<NodeDescription> targetNodeDescriptions = this.getNodeDescriptions(edgeMapping.getTargetMapping());

        Function<VariableManager, List<Object>> semanticElementsProvider = this.getSemanticElementsProvider(edgeMapping, sourceNodeDescriptions);

        Function<VariableManager, List<Element>> sourceNodesProvider = null;
        if (edgeMapping.isUseDomainElement()) {
            sourceNodesProvider = new DomainBasedSourceNodesProvider(edgeMapping, this.interpreter, this.identifierProvider);
        } else {
            sourceNodesProvider = new RelationBasedSourceNodesProvider(edgeMapping, this.identifierProvider);
        }

        Function<VariableManager, List<Element>> targetNodesProvider = new TargetNodesProvider(edgeMapping, this.interpreter, this.identifierProvider);
        Function<VariableManager, EdgeStyle> styleProvider = new EdgeMappingStyleProvider(this.interpreter, edgeMapping);

        LabelStyleDescriptionConverter labelStyleDescriptionConverter = new LabelStyleDescriptionConverter(this.interpreter, this.objectService);

        // @formatter:off
        Function<VariableManager, Optional<Label>> beginLabelProvider = Optional.ofNullable(edgeMapping.getStyle())
                .map(edgeStyle -> this.createLabelProvider(labelStyleDescriptionConverter, edgeStyle.getBeginLabelStyleDescription(), "_beginlabel")) //$NON-NLS-1$
                .orElse(variableManager -> Optional.empty());

        Function<VariableManager, Optional<Label>> centerLabelProvider = Optional.ofNullable(edgeMapping.getStyle())
                .map(edgeStyle -> this.createLabelProvider(labelStyleDescriptionConverter, edgeStyle.getCenterLabelStyleDescription(), "_centerlabel")) //$NON-NLS-1$
                .orElse(variableManager -> Optional.empty());

        Function<VariableManager, Optional<Label>> endLabelProvider = Optional.ofNullable(edgeMapping.getStyle())
                .map(edgeStyle -> this.createLabelProvider(labelStyleDescriptionConverter, edgeStyle.getEndLabelStyleDescription(), "_endlabel")) //$NON-NLS-1$
                .orElse(variableManager -> Optional.empty());

        ToolConverter toolConverter = new ToolConverter(this.interpreter, this.editService);
        var deleteHandler = toolConverter.createDeleteToolHandler(edgeMapping.getDeletionDescription());
        var labelEditHandler = toolConverter.createDirectEditToolHandler(edgeMapping.getLabelDirectEdit());

        return EdgeDescription.newEdgeDescription(UUID.fromString(this.identifierProvider.getIdentifier(edgeMapping)))
                .idProvider(idProvider)
                .targetObjectIdProvider(targetIdProvider)
                .targetObjectKindProvider(targetKindProvider)
                .targetObjectLabelProvider(targetLabelProvider)
                .semanticElementsProvider(semanticElementsProvider)
                .beginLabelProvider(beginLabelProvider)
                .centerLabelProvider(centerLabelProvider)
                .endLabelProvider(endLabelProvider)
                .sourceNodeDescriptions(sourceNodeDescriptions)
                .targetNodeDescriptions(targetNodeDescriptions)
                .sourceNodesProvider(sourceNodesProvider)
                .targetNodesProvider(targetNodesProvider)
                .styleProvider(styleProvider)
                .deleteHandler(deleteHandler)
                .labelEditHandler(labelEditHandler)
                .build();
        // @formatter:on
    }

    private Function<VariableManager, Optional<Label>> createLabelProvider(LabelStyleDescriptionConverter labelStyleDescriptionConverter, BasicLabelStyleDescription siriusBasicLabelStyleDescription,
            String idSuffix) {
        return variableManager -> {
            return Optional.ofNullable(siriusBasicLabelStyleDescription).map(siriusLabelStyleDescription -> {
                String ownerId = variableManager.get(LabelDescription.OWNER_ID, String.class).orElse(""); //$NON-NLS-1$

                String labelId = ownerId + idSuffix;
                String labelExpression = Optional.ofNullable(siriusLabelStyleDescription).map(BasicLabelStyleDescription::getLabelExpression).orElse(""); //$NON-NLS-1$
                LabelStyleDescription labelStyleDescription = labelStyleDescriptionConverter.convert(siriusLabelStyleDescription);

                // @formatter:off
                LabelStyle style = LabelStyle.newLabelStyle()
                        .color(labelStyleDescription.getColorProvider().apply(variableManager))
                        .fontSize(labelStyleDescription.getFontSizeProvider().apply(variableManager))
                        .bold(labelStyleDescription.getBoldProvider().apply(variableManager))
                        .italic(labelStyleDescription.getItalicProvider().apply(variableManager))
                        .strikeThrough(labelStyleDescription.getStrikeThroughProvider().apply(variableManager))
                        .underline(labelStyleDescription.getUnderlineProvider().apply(variableManager))
                        .iconURL(labelStyleDescription.getIconURLProvider().apply(variableManager))
                        .build();

                return Label.newLabel(labelId)
                        .type("label:inside-center") //$NON-NLS-1$
                        .text(new StringValueProvider(this.interpreter, labelExpression).apply(variableManager))
                        .position(Position.UNDEFINED)
                        .size(Size.UNDEFINED)
                        .alignment(Position.UNDEFINED)
                        .style(style)
                        .build();
                // @formatter:on
            });
        };
    }

    /**
     * Returns the node descriptions matching the given mappings.
     *
     * @param mappings
     *            The mappings referenced by the edge description (source or target)
     * @return The relevant node descriptions created by the node and container description converters
     */
    private List<NodeDescription> getNodeDescriptions(List<DiagramElementMapping> mappings) {
        // @formatter:off
        return mappings.stream()
                .filter(AbstractNodeMapping.class::isInstance)
                .map(AbstractNodeMapping.class::cast)
                .map(mapping->UUID.fromString(this.identifierProvider.getIdentifier(mapping)))
                .map(this.id2NodeDescriptions::get)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        // @formatter:on
    }

    private Function<VariableManager, List<Object>> getSemanticElementsProvider(EdgeMapping edgeMapping, List<NodeDescription> sourceNodeDescriptions) {
        Function<VariableManager, List<Object>> semanticElementsProvider = variableManager -> List.of();

        if (edgeMapping.isUseDomainElement()) {
            String semanticCandidatesExpression = edgeMapping.getSemanticCandidatesExpression();
            String preconditionExpression = edgeMapping.getPreconditionExpression();
            String domainClass = Optional.ofNullable(edgeMapping.getDomainClass()).orElse(""); //$NON-NLS-1$
            semanticElementsProvider = new SemanticCandidatesProvider(this.interpreter, domainClass, semanticCandidatesExpression, preconditionExpression);
        } else {
            // @formatter:off
            List<UUID> sourceNodeDescriptionIds = sourceNodeDescriptions.stream()
                    .map(NodeDescription::getId)
                    .collect(Collectors.toList());
            // @formatter:on

            semanticElementsProvider = new RelationBasedSemanticElementsProvider(sourceNodeDescriptionIds);
        }
        return semanticElementsProvider;
    }
}
