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
package org.eclipse.sirius.web.graphql.datafetchers.query;

import org.eclipse.sirius.web.diagrams.tools.ToolSection;
import org.eclipse.sirius.web.graphql.utils.schema.ITypeCustomizer;
import org.springframework.stereotype.Service;

import graphql.Scalars;
import graphql.schema.GraphQLArgument;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLInterfaceType;
import graphql.schema.GraphQLList;
import graphql.schema.GraphQLNamedOutputType;
import graphql.schema.GraphQLNonNull;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLType;
import graphql.schema.GraphQLTypeReference;

/**
 * Used to add additional fields to the query type.
 *
 * @author sbegaudeau
 */
@Service
public class QueryTypeCustomizer implements ITypeCustomizer {

    public static final String TOOL_SECTIONS_FIELD = "toolSections"; //$NON-NLS-1$

    public static final String DIAGRAM_ID_ARGUMENT = "diagramId"; //$NON-NLS-1$

    private static final String VIEWER = "Viewer"; //$NON-NLS-1$

    @Override
    public GraphQLType customize(GraphQLType graphQLType) {
        GraphQLType customizedGraphQLType = graphQLType;
        if (graphQLType instanceof GraphQLInterfaceType) {
            GraphQLInterfaceType graphQLInterfaceType = (GraphQLInterfaceType) graphQLType;
            boolean isViewer = graphQLInterfaceType.getName().equals(VIEWER);

            if (isViewer) {
                // @formatter:off
                customizedGraphQLType = GraphQLInterfaceType.newInterface(graphQLInterfaceType)
                        .field(this.getToolSectionsField())
                        .build();
                // @formatter:on
            }
        } else if (graphQLType instanceof GraphQLObjectType) {
            GraphQLObjectType graphQLObjectType = (GraphQLObjectType) graphQLType;
            // @formatter:off
            boolean isViewer = graphQLObjectType.getInterfaces().stream()
                    .map(GraphQLNamedOutputType::getName)
                    .anyMatch(VIEWER::equals);
            if (isViewer) {
                customizedGraphQLType = GraphQLObjectType.newObject(graphQLObjectType)
                        .field(this.getToolSectionsField())
                        .build();
            }
            // @formatter:on
        }
        return customizedGraphQLType;
    }

    private GraphQLArgument getDiagramIdArgument() {
        // @formatter:off
        return GraphQLArgument.newArgument()
                .name(DIAGRAM_ID_ARGUMENT)
                .type(new GraphQLNonNull(Scalars.GraphQLID))
                .build();
        // @formatter:on
    }

    private GraphQLFieldDefinition getToolSectionsField() {
        // @formatter:off
        return GraphQLFieldDefinition.newFieldDefinition()
                .name(TOOL_SECTIONS_FIELD)
                .type(new GraphQLNonNull(new GraphQLList(new GraphQLNonNull(new GraphQLTypeReference(ToolSection.class.getSimpleName())))))
                .argument(this.getDiagramIdArgument())
                .build();
        // @formatter:on
    }

}
