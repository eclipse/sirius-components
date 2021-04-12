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
package org.eclipse.sirius.web.graphql.schema;

import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.sirius.web.core.api.Namespace;
import org.eclipse.sirius.web.graphql.utils.providers.GraphQLObjectTypeProvider;
import org.eclipse.sirius.web.graphql.utils.schema.ITypeProvider;
import org.springframework.stereotype.Service;

import graphql.schema.GraphQLType;

/**
 * This class is used to create the definition of the Namespace type.
 * <p>
 * The types created will match the following GraphQL textual definition:
 * </p>
 *
 * <pre>
 * type Namespace {
 *   id: ID!
 *   label: String!
 * }
 * </pre>
 *
 * @author lfasani
 */
@Service
public class NamespaceTypeProvider implements ITypeProvider {

    private final GraphQLObjectTypeProvider graphQLObjectTypeProvider = new GraphQLObjectTypeProvider();

    @Override
    public Set<GraphQLType> getTypes() {
        LinkedHashSet<GraphQLType> types = new LinkedHashSet<>();
        types.add(this.graphQLObjectTypeProvider.getType(Namespace.class));
        return types;
    }

}
