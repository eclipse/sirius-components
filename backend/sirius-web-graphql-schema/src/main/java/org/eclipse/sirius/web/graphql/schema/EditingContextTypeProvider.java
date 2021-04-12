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
package org.eclipse.sirius.web.graphql.schema;

import static graphql.schema.GraphQLArgument.newArgument;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLList.list;
import static graphql.schema.GraphQLNonNull.nonNull;
import static graphql.schema.GraphQLTypeReference.typeRef;

import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.sirius.web.core.api.ChildCreationDescription;
import org.eclipse.sirius.web.core.api.Namespace;
import org.eclipse.sirius.web.graphql.utils.schema.ITypeProvider;
import org.springframework.stereotype.Service;

import graphql.Scalars;
import graphql.schema.GraphQLArgument;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLType;

/**
 * This class is used to create the definition of the EditingContext type and its related types.
 * <p>
 * The types created will match the following GraphQL textual definition:
 * </p>
 *
 * <pre>
 * type EditingContext {
 *   id: ID!
 *   stereotypeDescriptions: [StereotypeDescription!]!
 *   childCreationDescriptions(classId: ID!): [ChildCreationDescription!]!
 *   rootObjectCreationDescriptions(namespaceId: ID!, suggested: Boolean!): [ChildCreationDescription!]!
 *   namespaces: [Namespace!]!
 *   representationDescriptions(classId: ID): EditingContextRepresentationDescriptionConnection!
 * }
 * </pre>
 *
 * @author sbegaudeau
 */
@Service
public class EditingContextTypeProvider implements ITypeProvider {

    public static final String TYPE = "EditingContext"; //$NON-NLS-1$

    public static final String STEREOTYPE_DESCRIPTIONS_FIELD = "stereotypeDescriptions"; //$NON-NLS-1$

    public static final String CHILD_CREATION_DESCRIPTIONS_FIELD = "childCreationDescriptions"; //$NON-NLS-1$

    public static final String CLASS_ID_ARGUMENT = "classId"; //$NON-NLS-1$

    public static final String ROOT_OBJECT_CREATION_DESCRIPTIONS_FIELD = "rootObjectCreationDescriptions"; //$NON-NLS-1$

    public static final String NAMESPACE_ID_ARGUMENT = "namespaceId"; //$NON-NLS-1$

    public static final String SUGGESTED_ARGUMENT = "suggested"; //$NON-NLS-1$

    public static final String NAMESPACES_FIELD = "namespaces"; //$NON-NLS-1$

    public static final String REPRESENTATION_DESCRIPTIONS_FIELD = "representationDescriptions"; //$NON-NLS-1$

    public static final String EDITING_CONTEXT_REPRESENTATION_DESCRIPTIONS_CONNECTION = TYPE + RepresentationDescriptionTypeProvider.TYPE + GraphQLConstants.CONNECTION;

    public static final String EDITING_CONTEXT_REPRESENTATION_DESCRIPTIONS_EDGE = TYPE + RepresentationDescriptionTypeProvider.TYPE + GraphQLConstants.EDGE;

    @Override
    public Set<GraphQLType> getTypes() {
        // @formatter:off
        GraphQLObjectType editingContextType = GraphQLObjectType.newObject()
                .name(TYPE)
                .field(new IdFieldProvider().getField())
                .field(this.getStereotypeDescriptionsField())
                .field(this.getChildCreationDescriptionsField())
                .field(this.getRootObjectCreationDescriptionsField())
                .field(this.getNamespaceField())
                .field(this.getRepresentationDescriptionField())
                .build();
        // @formatter:on

        GraphQLObjectType viewerRepresentationDescriptionEdge = new PaginationEdgeTypeProvider(EDITING_CONTEXT_REPRESENTATION_DESCRIPTIONS_EDGE, RepresentationDescriptionTypeProvider.TYPE).getType();
        GraphQLObjectType viewerRepresentationDescriptionConnection = new PaginationConnectionTypeProvider(EDITING_CONTEXT_REPRESENTATION_DESCRIPTIONS_CONNECTION,
                EDITING_CONTEXT_REPRESENTATION_DESCRIPTIONS_EDGE).getType();

        Set<GraphQLType> types = new LinkedHashSet<>();
        types.add(editingContextType);
        types.add(viewerRepresentationDescriptionEdge);
        types.add(viewerRepresentationDescriptionConnection);
        return types;
    }

    private GraphQLFieldDefinition getStereotypeDescriptionsField() {
        // @formatter:off
        return GraphQLFieldDefinition.newFieldDefinition()
                .name(STEREOTYPE_DESCRIPTIONS_FIELD)
                .type(nonNull(list(nonNull(typeRef(StereotypeDescriptionTypeProvider.TYPE)))))
                .build();
        // @formatter:on
    }

    private GraphQLFieldDefinition getChildCreationDescriptionsField() {
        // @formatter:off
        return newFieldDefinition()
                .name(CHILD_CREATION_DESCRIPTIONS_FIELD)
                .argument(this.getClassIdArgument())
                .type(nonNull(list(nonNull(typeRef(ChildCreationDescription.class.getSimpleName())))))
                .build();
        // @formatter:on
    }

    private GraphQLArgument getClassIdArgument() {
        // @formatter:off
        return newArgument()
                .name(CLASS_ID_ARGUMENT)
                .type(nonNull(Scalars.GraphQLID))
                .build();
        // @formatter:on
    }

    private GraphQLFieldDefinition getRootObjectCreationDescriptionsField() {
        // @formatter:off
        return newFieldDefinition()
                .name(ROOT_OBJECT_CREATION_DESCRIPTIONS_FIELD)
                .type(nonNull(list(nonNull(typeRef(ChildCreationDescription.class.getSimpleName())))))
                .argument(this.getNamespaceIdArgument())
                .argument(this.getSuggestedArgument())
                .build();
        // @formatter:on
    }

    private GraphQLArgument getNamespaceIdArgument() {
        // @formatter:off
        return newArgument()
                .name(NAMESPACE_ID_ARGUMENT)
                .type(nonNull(Scalars.GraphQLID))
                .build();
        // @formatter:on
    }

    private GraphQLArgument getSuggestedArgument() {
        // @formatter:off
        return newArgument()
                .name(SUGGESTED_ARGUMENT)
                .type(nonNull(Scalars.GraphQLBoolean))
                .build();
        // @formatter:on
    }

    private GraphQLFieldDefinition getNamespaceField() {
        // @formatter:off
        return newFieldDefinition()
                .name(NAMESPACES_FIELD)
                .type(nonNull(list(nonNull(typeRef(Namespace.class.getSimpleName())))))
                .build();
        // @formatter:on
    }

    private GraphQLFieldDefinition getRepresentationDescriptionField() {
        // @formatter:off
        return newFieldDefinition()
                .name(REPRESENTATION_DESCRIPTIONS_FIELD)
                .argument(this.getClassIdArgument())
                .type(nonNull(typeRef(EDITING_CONTEXT_REPRESENTATION_DESCRIPTIONS_CONNECTION)))
                .build();
        // @formatter:on
    }
}
