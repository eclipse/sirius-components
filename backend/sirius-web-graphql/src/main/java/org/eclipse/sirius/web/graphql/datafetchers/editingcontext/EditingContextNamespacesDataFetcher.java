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
package org.eclipse.sirius.web.graphql.datafetchers.editingcontext;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.eclipse.sirius.web.annotations.spring.graphql.QueryDataFetcher;
import org.eclipse.sirius.web.core.api.IEditService;
import org.eclipse.sirius.web.core.api.Namespace;
import org.eclipse.sirius.web.graphql.schema.EditingContextTypeProvider;
import org.eclipse.sirius.web.spring.graphql.api.IDataFetcherWithFieldCoordinates;

import graphql.schema.DataFetchingEnvironment;

/**
 * The data fetcher used to retrieve the namespaces accessible in an editing context.
 * <p>
 * It will be used to fetch the data for the following GraphQL field:
 * </p>
 *
 * <pre>
 * type EditingContext {
 *   namespaces: [Namespace!]!
 * }
 * </pre>
 *
 * @author lfasani
 */
@QueryDataFetcher(type = EditingContextTypeProvider.TYPE, field = EditingContextTypeProvider.NAMESPACES_FIELD)
public class EditingContextNamespacesDataFetcher implements IDataFetcherWithFieldCoordinates<List<Namespace>> {
    private final IEditService editService;

    public EditingContextNamespacesDataFetcher(IEditService editService) {
        this.editService = Objects.requireNonNull(editService);
    }

    @Override
    public List<Namespace> get(DataFetchingEnvironment environment) throws Exception {
        UUID editingContextId = environment.getSource();
        return this.editService.getNamespaces(editingContextId);
    }
}
