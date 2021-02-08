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
package org.eclipse.sirius.web.spring.collaborative.diagrams.graphql;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.sirius.web.diagrams.ArrowStyle;
import org.eclipse.sirius.web.diagrams.Diagram;
import org.eclipse.sirius.web.diagrams.Edge;
import org.eclipse.sirius.web.diagrams.EdgeStyle;
import org.eclipse.sirius.web.diagrams.INodeStyle;
import org.eclipse.sirius.web.diagrams.ImageNodeStyle;
import org.eclipse.sirius.web.diagrams.Label;
import org.eclipse.sirius.web.diagrams.LabelStyle;
import org.eclipse.sirius.web.diagrams.LineStyle;
import org.eclipse.sirius.web.diagrams.Node;
import org.eclipse.sirius.web.diagrams.Position;
import org.eclipse.sirius.web.diagrams.RectangularNodeStyle;
import org.eclipse.sirius.web.diagrams.Size;
import org.eclipse.sirius.web.diagrams.description.DiagramDescription;
import org.eclipse.sirius.web.diagrams.description.NodeDescription;
import org.eclipse.sirius.web.diagrams.tools.CreateEdgeTool;
import org.eclipse.sirius.web.diagrams.tools.CreateNodeTool;
import org.eclipse.sirius.web.diagrams.tools.DropTool;
import org.eclipse.sirius.web.diagrams.tools.EdgeCandidate;
import org.eclipse.sirius.web.diagrams.tools.ToolSection;
import org.eclipse.sirius.web.graphql.utils.providers.GraphQLEnumTypeProvider;
import org.eclipse.sirius.web.graphql.utils.providers.GraphQLObjectTypeProvider;
import org.eclipse.sirius.web.graphql.utils.schema.ITypeProvider;
import org.springframework.stereotype.Service;

import graphql.schema.GraphQLType;
import graphql.schema.GraphQLTypeReference;
import graphql.schema.GraphQLUnionType;

/**
 * This class regroup all diagram (Input & output) types.
 *
 * @author hmarchadour
 *
 */
@Service
public class DiagramTypesProvider implements ITypeProvider {

    public static final String DIAGRAM_TYPE = "Diagram"; //$NON-NLS-1$

    public static final String CREATE_EDGE_TOOL_TYPE = "CreateEdgeTool"; //$NON-NLS-1$

    public static final String CREATE_NODE_TOOL_TYPE = "CreateNodeTool"; //$NON-NLS-1$

    public static final String TOOL_SECTION_TYPE = "ToolSection"; //$NON-NLS-1$

    private final GraphQLObjectTypeProvider graphQLObjectTypeProvider = new GraphQLObjectTypeProvider();

    private final GraphQLEnumTypeProvider graphQLEnumTypeProvider = new GraphQLEnumTypeProvider();

    @Override
    public Set<GraphQLType> getTypes() {
        // @formatter:off
        List<Class<?>> objectClasses = List.of(
            Diagram.class,
            Node.class,
            Label.class,
            Position.class,
            Size.class,
            LabelStyle.class,
            RectangularNodeStyle.class,
            ImageNodeStyle.class,
            Edge.class,
            EdgeStyle.class,
            DiagramDescription.class,
            NodeDescription.class,
            ToolSection.class,
            CreateEdgeTool.class,
            CreateNodeTool.class,
            DropTool.class,
            EdgeCandidate.class
        );
        var graphQLObjectTypes = objectClasses.stream()
                .map(this.graphQLObjectTypeProvider::getType)
                .collect(Collectors.toUnmodifiableList());

        List<Class<?>> enumClasses = List.of(
            LineStyle.class,
            ArrowStyle.class
        );
        var graphQLEnumTypes = enumClasses.stream()
                .map(this.graphQLEnumTypeProvider::getType)
                .collect(Collectors.toUnmodifiableList());
        // @formatter:on

        Set<GraphQLType> types = new LinkedHashSet<>();
        types.addAll(graphQLObjectTypes);
        types.addAll(graphQLEnumTypes);
        types.add(this.getNodeStyleUnionType());
        return types;
    }

    private GraphQLUnionType getNodeStyleUnionType() {
        // @formatter:off
        return GraphQLUnionType.newUnionType()
                .name(INodeStyle.class.getSimpleName())
                .possibleType(new GraphQLTypeReference(RectangularNodeStyle.class.getSimpleName()))
                .possibleType(new GraphQLTypeReference(ImageNodeStyle.class.getSimpleName()))
                .build();
        // @formatter:on
    }

}
