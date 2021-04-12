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
import org.eclipse.sirius.web.api.configuration.StereotypeDescription;
import org.eclipse.sirius.web.core.api.IStereotypeDescriptionSearchService;
import org.eclipse.sirius.web.graphql.schema.EditingContextTypeProvider;
import org.eclipse.sirius.web.spring.graphql.api.IDataFetcherWithFieldCoordinates;

import graphql.schema.DataFetchingEnvironment;

/**
 * The data fetcher used to retrieve the stereotypes accessible in an editing context.
 * <p>
 * It will be used to fetch the data for the following GraphQL field:
 * </p>
 *
 * <pre>
 * type EditingContext {
 *   stereotypeDescriptions: [StereotypeDescription!]!
 * }
 * </pre>
 *
 * @author hmarchadour
 */
@QueryDataFetcher(type = EditingContextTypeProvider.TYPE, field = EditingContextTypeProvider.STEREOTYPE_DESCRIPTIONS_FIELD)
public class EditingContextStereotypeDescriptionsDataFetcher implements IDataFetcherWithFieldCoordinates<List<StereotypeDescription>> {

    private final IStereotypeDescriptionSearchService stereotypeDescriptionService;

    public EditingContextStereotypeDescriptionsDataFetcher(IStereotypeDescriptionSearchService stereotypeDescriptionService) {
        this.stereotypeDescriptionService = Objects.requireNonNull(stereotypeDescriptionService);
    }

    @Override
    public List<StereotypeDescription> get(DataFetchingEnvironment environment) throws Exception {
        UUID editingContextId = environment.getSource();
        return this.stereotypeDescriptionService.getStereotypeDescriptions(editingContextId);
    }
}
