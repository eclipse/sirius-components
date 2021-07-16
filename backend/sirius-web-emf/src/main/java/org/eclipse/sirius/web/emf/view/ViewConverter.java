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
package org.eclipse.sirius.web.emf.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.web.components.Element;
import org.eclipse.sirius.web.core.api.IEditService;
import org.eclipse.sirius.web.core.api.IEditingContext;
import org.eclipse.sirius.web.core.api.IObjectService;
import org.eclipse.sirius.web.diagrams.EdgeStyle;
import org.eclipse.sirius.web.diagrams.INodeStyle;
import org.eclipse.sirius.web.diagrams.Size;
import org.eclipse.sirius.web.diagrams.description.DiagramDescription;
import org.eclipse.sirius.web.diagrams.description.EdgeDescription;
import org.eclipse.sirius.web.diagrams.description.LabelDescription;
import org.eclipse.sirius.web.diagrams.description.LabelStyleDescription;
import org.eclipse.sirius.web.diagrams.description.NodeDescription;
import org.eclipse.sirius.web.diagrams.description.SynchronizationPolicy;
import org.eclipse.sirius.web.diagrams.elements.NodeElementProps;
import org.eclipse.sirius.web.diagrams.renderer.DiagramRenderingCache;
import org.eclipse.sirius.web.diagrams.tools.CreateEdgeTool;
import org.eclipse.sirius.web.diagrams.tools.CreateNodeTool;
import org.eclipse.sirius.web.diagrams.tools.EdgeCandidate;
import org.eclipse.sirius.web.diagrams.tools.ITool;
import org.eclipse.sirius.web.diagrams.tools.ToolSection;
import org.eclipse.sirius.web.emf.compatibility.DomainClassPredicate;
import org.eclipse.sirius.web.interpreter.AQLInterpreter;
import org.eclipse.sirius.web.interpreter.Result;
import org.eclipse.sirius.web.representations.IRepresentationDescription;
import org.eclipse.sirius.web.representations.Status;
import org.eclipse.sirius.web.representations.VariableManager;
import org.eclipse.sirius.web.view.DeleteTool;
import org.eclipse.sirius.web.view.DiagramElementDescription;
import org.eclipse.sirius.web.view.EdgeTool;
import org.eclipse.sirius.web.view.LabelEditTool;
import org.eclipse.sirius.web.view.NodeStyle;
import org.eclipse.sirius.web.view.NodeTool;
import org.eclipse.sirius.web.view.View;

/**
 * Converts a View into an equivalent list of {@link DiagramDescription}.
 *
 * @author pcdavid
 */
public class ViewConverter {

    private static final String DEFAULT_DIAGRAM_LABEL = "Diagram"; //$NON-NLS-1$

    private static final String NODE_CREATION_TOOL_SECTION = "Node Creation"; //$NON-NLS-1$

    private static final String NODE_CREATION_TOOL_ICON = "/img/Entity.svg"; //$NON-NLS-1$

    private static final String EDGE_CREATION_TOOL_SECTION = "Edge Creation"; //$NON-NLS-1$

    private static final String EDGE_CREATION_TOOL_ICON = "/img/Relation.svg"; //$NON-NLS-1$

    private final List<IJavaServiceProvider> javaServiceProviders;

    private final IObjectService objectService;

    private final IEditService editService;

    private final CanonicalBehaviors canonicalBehaviors;

    private final StylesFactory stylesFactory;

    private final Function<VariableManager, String> semanticTargetIdProvider;

    private final Function<VariableManager, String> semanticTargetKindProvider;

    private final Function<VariableManager, String> semanticTargetLabelProvider;

    private final Function<DiagramElementDescription, UUID> idProvider = (diagramElementDescription) -> {
        // DiagramElementDescription should have a proper id.
        return UUID.nameUUIDFromBytes(EcoreUtil.getURI(diagramElementDescription).toString().getBytes());
    };

    private final boolean isStudioDefinitionEnabled;

    private Map<org.eclipse.sirius.web.view.NodeDescription, NodeDescription> convertedNodes;

    private Map<org.eclipse.sirius.web.view.EdgeDescription, EdgeDescription> convertedEdges;

    public ViewConverter(List<IJavaServiceProvider> javaServiceProviders, IObjectService objectService, IEditService editService, boolean isStudioDefinitionEnabled) {
        this.javaServiceProviders = Objects.requireNonNull(javaServiceProviders);
        this.objectService = Objects.requireNonNull(objectService);
        this.editService = Objects.requireNonNull(editService);
        this.semanticTargetIdProvider = variableManager -> this.self(variableManager).map(this.objectService::getId).orElse(null);
        this.semanticTargetKindProvider = variableManager -> this.self(variableManager).map(this.objectService::getKind).orElse(null);
        this.semanticTargetLabelProvider = variableManager -> this.self(variableManager).map(this.objectService::getLabel).orElse(null);
        this.isStudioDefinitionEnabled = isStudioDefinitionEnabled;
        this.canonicalBehaviors = new CanonicalBehaviors(objectService, editService);
        this.stylesFactory = new StylesFactory();
    }

    /**
     * Extract and convert the {@link IRepresentationDescription} from a {@link View} model. Currently only
     * {@link DiagramDescription}s are supported. <b>Warning:</b> this code is not re-entrant.
     */
    public List<IRepresentationDescription> convert(View view) {
        List<IRepresentationDescription> result = List.of();
        if (this.isStudioDefinitionEnabled) {
            // @formatter:off
            List<Class<?>> serviceClasses = this.javaServiceProviders.stream()
                                                .flatMap(provider -> provider.getServiceClasses(view).stream())
                                                .collect(Collectors.toList());
            // @formatter:on
            AQLInterpreter interpreter = new AQLInterpreter(serviceClasses, List.of());
            try {
                // @formatter:off
                result = view.getDescriptions().stream()
                             .filter(org.eclipse.sirius.web.view.DiagramDescription.class::isInstance)
                             .map(org.eclipse.sirius.web.view.DiagramDescription.class::cast)
                             .map(viewDiagramDescription -> this.convert(viewDiagramDescription, interpreter))
                             .collect(Collectors.toList());
                // @formatter:on
            } catch (NullPointerException e) {
                // Can easily happen if the View model is currently invalid/inconsistent, typically because it is
                // currently being created or edited.
            }
        }
        return result;
    }

    private DiagramDescription convert(org.eclipse.sirius.web.view.DiagramDescription viewDiagramDescription, AQLInterpreter interpreter) {
        this.convertedNodes = new HashMap<>();
        this.convertedEdges = new HashMap<>();
        try {
            // Nodes must be fully converted first.
            List<NodeDescription> nodeDescriptions = viewDiagramDescription.getNodeDescriptions().stream().map(node -> this.convert(node, interpreter)).collect(Collectors.toList());
            List<EdgeDescription> edgeDescriptions = viewDiagramDescription.getEdgeDescriptions().stream().map(edge -> this.convert(edge, interpreter)).collect(Collectors.toList());
            // @formatter:off
            return DiagramDescription.newDiagramDescription(UUID.nameUUIDFromBytes(viewDiagramDescription.getName().getBytes()))
                    .label(Optional.ofNullable(viewDiagramDescription.getName()).orElse(DEFAULT_DIAGRAM_LABEL))
                    .labelProvider(variableManager -> this.computeDiagramLabel(viewDiagramDescription, variableManager, interpreter))
                    .canCreatePredicate(variableManager -> this.canCreateDiagram(variableManager, viewDiagramDescription.getDomainType()))
                    .autoLayout(viewDiagramDescription.isAutoLayout())
                    .targetObjectIdProvider(this.semanticTargetIdProvider)
                    .nodeDescriptions(nodeDescriptions)
                    .edgeDescriptions(edgeDescriptions)
                    .toolSections(this.createToolSections(interpreter))
                    .build();
            // @formatter:on
        } finally {
            this.convertedNodes.clear();
            this.convertedEdges.clear();
        }
    }

    private String computeDiagramLabel(org.eclipse.sirius.web.view.DiagramDescription viewDiagramDescription, VariableManager variableManager, AQLInterpreter interpreter) {
        String title = variableManager.get(DiagramDescription.LABEL, String.class).orElseGet(() -> {
            return this.evaluateString(interpreter, variableManager, viewDiagramDescription.getTitleExpression());
        });
        if (title == null || title.isBlank()) {
            return DEFAULT_DIAGRAM_LABEL;
        } else {
            return title;
        }
    }

    private boolean canCreateDiagram(VariableManager variableManager, String domainType) {
        // @formatter:off
        return variableManager.get(IRepresentationDescription.CLASS, EClass.class)
                              .map(EcoreUtil::create)
                              .filter(new DomainClassPredicate(Optional.ofNullable(domainType).orElse(""))) //$NON-NLS-1$
                              .isPresent();
        // @formatter:on
    }

    private NodeDescription convert(org.eclipse.sirius.web.view.NodeDescription viewNodeDescription, AQLInterpreter interpreter) {
        // @formatter:off
        // Convert our children first, we need their converted values to build our NodeDescription
        var childNodeDescriptions = viewNodeDescription.getChildrenDescriptions().stream()
                                                       .map(childNodeDescription -> this.convert(childNodeDescription, interpreter))
                                                       .collect(Collectors.toList());
        // @formatter:on
        SynchronizationPolicy synchronizationPolicy = SynchronizationPolicy.SYNCHRONIZED;
        String nodeType = this.stylesFactory.getNodeType(viewNodeDescription.getStyle());

        Function<VariableManager, INodeStyle> styleProvider = variableManager -> {
            // @formatter:off
            var effectiveStyle = viewNodeDescription.getConditionalStyles().stream()
                    .filter(style -> this.matches(interpreter, style.getCondition(), variableManager))
                    .map(NodeStyle.class::cast)
                    .findFirst()
                    .orElseGet(viewNodeDescription::getStyle);
            Optional<UUID> optionalEditingContextId = variableManager.get(IEditingContext.EDITING_CONTEXT, IEditingContext.class)
                                                                     .map(IEditingContext::getId);
            // @formatter:on
            return this.stylesFactory.createNodeStyle(effectiveStyle, optionalEditingContextId);
        };

        // @formatter:off
        NodeDescription result = NodeDescription.newNodeDescription(this.idProvider.apply(viewNodeDescription))
                .targetObjectIdProvider(this.semanticTargetIdProvider)
                .targetObjectKindProvider(this.semanticTargetKindProvider)
                .targetObjectLabelProvider(this.semanticTargetLabelProvider)
                .semanticElementsProvider(this.getSemanticElementsProvider(viewNodeDescription, interpreter))
                .synchronizationPolicy(synchronizationPolicy)
                .typeProvider(variableManager -> nodeType)
                .labelDescription(this.getLabelDescription(viewNodeDescription, interpreter))
                .styleProvider(styleProvider)
                .childNodeDescriptions(childNodeDescriptions)
                .borderNodeDescriptions(List.of())
                .sizeProvider(variableManager -> Size.UNDEFINED)
                .labelEditHandler(this.createLabelEditHandler(viewNodeDescription, interpreter))
                .deleteHandler(this.createDeleteHandler(viewNodeDescription, interpreter))
                .build();
        // @formatter:on
        this.convertedNodes.put(viewNodeDescription, result);
        return result;
    }

    private boolean matches(AQLInterpreter interpreter, String condition, VariableManager variableManager) {
        return interpreter.evaluateExpression(variableManager.getVariables(), condition).asBoolean().orElse(Boolean.FALSE);
    }

    private List<ToolSection> createToolSections(AQLInterpreter interpreter) {
        List<ITool> nodeCreationTools = new ArrayList<>();
        for (var nodeDescription : this.convertedNodes.keySet()) {
            // Add custom tools
            int i = 0;
            for (NodeTool nodeTool : nodeDescription.getNodeTools()) {
                // @formatter:off
                CreateNodeTool customTool = CreateNodeTool.newCreateNodeTool(this.idProvider.apply(nodeDescription) + "_tool" + i++) //$NON-NLS-1$
                        .label(nodeTool.getName())
                        .imageURL(NODE_CREATION_TOOL_ICON)
                        .handler(variableManager -> new ToolInterpreter(interpreter, this.editService).executeTool(nodeTool, variableManager))
                        .targetDescriptions(Optional.ofNullable(nodeDescription.eContainer()).map(this.convertedNodes::get).stream().collect(Collectors.toList()))
                        .appliesToDiagramRoot(nodeDescription.eContainer() instanceof org.eclipse.sirius.web.view.DiagramDescription)
                        .build();
                // @formatter:on
                nodeCreationTools.add(customTool);
            }
            // If there are no custom tools defined, add a canonical creation tool
            if (i == 0) {
                // @formatter:off
                CreateNodeTool tool = CreateNodeTool.newCreateNodeTool(this.idProvider.apply(nodeDescription) + "_creationTool") //$NON-NLS-1$
                        .label("New node " + nodeDescription.getDomainType()) //$NON-NLS-1$
                        .imageURL(NODE_CREATION_TOOL_ICON)
                        .handler(variableManager -> this.canonicalBehaviors.createNewNode(nodeDescription, variableManager))
                        .targetDescriptions(Optional.ofNullable(nodeDescription.eContainer()).map(this.convertedNodes::get).stream().collect(Collectors.toList()))
                        .appliesToDiagramRoot(nodeDescription.eContainer() instanceof org.eclipse.sirius.web.view.DiagramDescription)
                        .build();
                // @formatter:on
                nodeCreationTools.add(tool);
            }
        }

        List<ITool> edgeCreationTools = new ArrayList<>();
        for (var edgeDescription : this.convertedEdges.keySet()) {
            // Add custom tools
            int i = 0;
            for (EdgeTool edgeTool : edgeDescription.getEdgeTools()) {
                // @formatter:off
                CreateEdgeTool customTool = CreateEdgeTool.newCreateEdgeTool(this.idProvider.apply(edgeDescription) + "_tool" + i++) //$NON-NLS-1$
                        .label(edgeTool.getName())
                        .imageURL(EDGE_CREATION_TOOL_ICON)
                        .edgeCandidates(List.of(EdgeCandidate.newEdgeCandidate()
                                .sources(List.of(this.convert(edgeDescription.getSourceNodeDescription(), interpreter)))
                                .targets(List.of(this.convert(edgeDescription.getTargetNodeDescription(), interpreter)))
                                .build()))
                        .handler(variableManager -> new ToolInterpreter(interpreter, this.editService).executeTool(edgeTool, variableManager))
                        .build();
                // @formatter:on
                nodeCreationTools.add(customTool);
            }
            // If there are no custom tools defined, add a canonical creation tool
            // @formatter:off
            CreateEdgeTool tool = CreateEdgeTool.newCreateEdgeTool(this.idProvider.apply(edgeDescription) + "_creationTool") //$NON-NLS-1$
                    .label("New edge" + edgeDescription.getDomainType()) //$NON-NLS-1$
                    .imageURL(EDGE_CREATION_TOOL_ICON)
                    .edgeCandidates(List.of(EdgeCandidate.newEdgeCandidate()
                            .sources(List.of(this.convert(edgeDescription.getSourceNodeDescription(), interpreter)))
                            .targets(List.of(this.convert(edgeDescription.getTargetNodeDescription(), interpreter)))
                            .build()))
                    .handler(variableManager -> this.canonicalBehaviors.createNewEdge(variableManager, edgeDescription))
                    .build();
            // @formatter:on
            edgeCreationTools.add(tool);
        }

        // @formatter:off
        return List.of(ToolSection.newToolSection(UUID.randomUUID().toString()).label(NODE_CREATION_TOOL_SECTION).tools(nodeCreationTools).imageURL("").build(), //$NON-NLS-1$
                       ToolSection.newToolSection(UUID.randomUUID().toString()).label(EDGE_CREATION_TOOL_SECTION).tools(edgeCreationTools).imageURL("").build()); //$NON-NLS-1$
        // @formatter:on
    }

    private LabelDescription getLabelDescription(org.eclipse.sirius.web.view.NodeDescription viewNodeDescription, AQLInterpreter interpreter) {
        Function<VariableManager, String> labelIdProvider = variableManager -> {
            Object parentId = variableManager.get(LabelDescription.OWNER_ID, Object.class).orElse(null);
            return String.valueOf(parentId) + LabelDescription.LABEL_SUFFIX;
        };

        Function<VariableManager, LabelStyleDescription> styleDescriptionProvider = variableManager -> {
            // @formatter:off
            var effectiveStyle = viewNodeDescription.getConditionalStyles().stream()
                    .filter(style -> this.matches(interpreter, style.getCondition(), variableManager))
                    .map(NodeStyle.class::cast)
                    .findFirst()
                    .orElseGet(viewNodeDescription::getStyle);
            // @formatter:on

            return this.stylesFactory.createLabelStyleDescription(effectiveStyle);
        };

        // @formatter:off
        return LabelDescription.newLabelDescription(EcoreUtil.getURI(viewNodeDescription).toString() + LabelDescription.LABEL_SUFFIX)
                .idProvider(labelIdProvider)
                .textProvider(variableManager -> this.evaluateString(interpreter, variableManager, viewNodeDescription.getLabelExpression()))
                .styleDescriptionProvider(styleDescriptionProvider)
                .build();
        // @formatter:on
    }

    private Function<VariableManager, List<Object>> getSemanticElementsProvider(org.eclipse.sirius.web.view.DiagramElementDescription elementDescription, AQLInterpreter interpreter) {
        return variableManager -> {
            Result result = interpreter.evaluateExpression(variableManager.getVariables(), elementDescription.getSemanticCandidatesExpression());
            List<Object> candidates = result.asObjects().orElse(List.of());
            // @formatter:off
            return candidates.stream()
                    .filter(EObject.class::isInstance)
                    .map(EObject.class::cast)
                    .filter(candidate -> new DomainClassPredicate(Optional.ofNullable(elementDescription.getDomainType()).orElse("")).test(candidate)) //$NON-NLS-1$
                    .collect(Collectors.toList());
            // @formatter:on
        };
    }

    private EdgeDescription convert(org.eclipse.sirius.web.view.EdgeDescription viewEdgeDescription, AQLInterpreter interpreter) {
        Function<VariableManager, List<Object>> semanticElementsProvider;
        if (viewEdgeDescription.isIsDomainBasedEdge()) {
            // Same logic as for nodes.
            semanticElementsProvider = this.getSemanticElementsProvider(viewEdgeDescription, interpreter);
        } else {
            //
            var sourceNodeDescription = this.convert(viewEdgeDescription.getSourceNodeDescription(), interpreter);
            semanticElementsProvider = new RelationBasedSemanticElementsProvider(List.of(sourceNodeDescription.getId()));
        }

        Function<VariableManager, List<Element>> sourceNodesProvider = null;
        if (viewEdgeDescription.isIsDomainBasedEdge()) {
            sourceNodesProvider = variableManager -> {
                var optionalCache = variableManager.get(DiagramDescription.CACHE, DiagramRenderingCache.class);
                if (optionalCache.isEmpty()) {
                    return List.of();
                }
                DiagramRenderingCache cache = optionalCache.get();
                String sourceFinderExpression = viewEdgeDescription.getSourceNodesExpression();

                Result result = interpreter.evaluateExpression(variableManager.getVariables(), sourceFinderExpression);
                List<Object> semanticCandidates = result.asObjects().orElse(List.of());
                var nodeCandidates = semanticCandidates.stream().flatMap(semanticObject -> cache.getElementsRepresenting(semanticObject).stream());

                // @formatter:off
                return nodeCandidates
                        .filter(nodeElement -> this.isFromDescription(nodeElement, viewEdgeDescription.getSourceNodeDescription()))
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList());
                // @formatter:on
            };
        } else {
            sourceNodesProvider = variableManager -> {
                var optionalObject = variableManager.get(VariableManager.SELF, Object.class);
                var optionalCache = variableManager.get(DiagramDescription.CACHE, DiagramRenderingCache.class);
                if (optionalObject.isEmpty() || optionalCache.isEmpty()) {
                    return List.of();
                }

                DiagramRenderingCache cache = optionalCache.get();
                Object object = optionalObject.get();

             // @formatter:off
                return cache.getElementsRepresenting(object).stream()
                        .filter(this.isFromCompatibleSourceMapping(viewEdgeDescription))
                        .filter(Objects::nonNull)
                        .collect(Collectors.toList());
                // @formatter:on
            };
        }

        Function<VariableManager, List<Element>> targetNodesProvider = new TargetNodesProvider(this.idProvider, viewEdgeDescription, interpreter);

        Function<VariableManager, EdgeStyle> styleProvider = variableManager -> {
            // @formatter:off
            var effectiveStyle = viewEdgeDescription.getConditionalStyles().stream()
                    .filter(style -> this.matches(interpreter, style.getCondition(), variableManager))
                    .map(org.eclipse.sirius.web.view.EdgeStyle.class::cast)
                    .findFirst()
                    .orElseGet(viewEdgeDescription::getStyle);
            // @formatter:on
            return this.stylesFactory.createEdgeStyle(effectiveStyle);
        };

        // @formatter:off
        EdgeDescription result = EdgeDescription.newEdgeDescription(this.idProvider.apply(viewEdgeDescription))
                                     .targetObjectIdProvider(this.semanticTargetIdProvider)
                                     .targetObjectKindProvider(this.semanticTargetKindProvider)
                                     .targetObjectLabelProvider(this.semanticTargetLabelProvider)
                                     .sourceNodeDescriptions(List.of(this.convertedNodes.get(viewEdgeDescription.getSourceNodeDescription())))
                                     .targetNodeDescriptions(List.of(this.convertedNodes.get(viewEdgeDescription.getTargetNodeDescription())))
                                     .semanticElementsProvider(semanticElementsProvider)
                                     .sourceNodesProvider(sourceNodesProvider)
                                     .targetNodesProvider(targetNodesProvider)
                                     .styleProvider(styleProvider)
                                     .deleteHandler(this.createDeleteHandler(viewEdgeDescription, interpreter))
                                     .build();
        this.convertedEdges.put(viewEdgeDescription, result);
        return result;
        // @formatter:on
    }

    private Function<VariableManager, Status> createDeleteHandler(DiagramElementDescription diagramElementDescription, AQLInterpreter interpreter) {
        DeleteTool tool = diagramElementDescription.getDeleteTool();
        if (tool != null) {
            return variableManager -> {
                return new ToolInterpreter(interpreter, this.editService).executeTool(tool, variableManager);
            };
        } else {
            return this.canonicalBehaviors::deleteElement;
        }
    }

    private BiFunction<VariableManager, String, Status> createLabelEditHandler(DiagramElementDescription diagramElementDescription, AQLInterpreter interpreter) {
        LabelEditTool tool = diagramElementDescription.getLabelEditTool();
        if (tool != null) {
            return (variableManager, newLabel) -> {
                VariableManager childVariableManager = variableManager.createChild();
                childVariableManager.put("arg0", newLabel); //$NON-NLS-1$
                return new ToolInterpreter(interpreter, this.editService).executeTool(tool, childVariableManager);
            };
        } else {
            return this.canonicalBehaviors::editLabel;
        }
    }

    private Predicate<Element> isFromCompatibleSourceMapping(org.eclipse.sirius.web.view.EdgeDescription edgeDescription) {
        return nodeElement -> this.isFromDescription(nodeElement, edgeDescription.getSourceNodeDescription());
    }

    private boolean isFromDescription(Element nodeElement, DiagramElementDescription diagramElementDescription) {
        if (nodeElement.getProps() instanceof NodeElementProps) {
            NodeElementProps props = (NodeElementProps) nodeElement.getProps();
            return Objects.equals(this.idProvider.apply(diagramElementDescription), props.getDescriptionId());
        }
        return false;
    }

    private Optional<Object> self(VariableManager variableManager) {
        return variableManager.get(VariableManager.SELF, Object.class);
    }

    private String evaluateString(AQLInterpreter interpreter, VariableManager variableManager, String expression) {
        return interpreter.evaluateExpression(variableManager.getVariables(), expression).asString().orElse(""); //$NON-NLS-1$
    }

}
