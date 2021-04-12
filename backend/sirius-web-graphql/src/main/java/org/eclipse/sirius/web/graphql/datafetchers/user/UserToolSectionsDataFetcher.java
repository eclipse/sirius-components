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
package org.eclipse.sirius.web.graphql.datafetchers.user;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.eclipse.sirius.web.annotations.spring.graphql.QueryDataFetcher;
import org.eclipse.sirius.web.collaborative.diagrams.api.IToolService;
import org.eclipse.sirius.web.diagrams.services.api.IDiagramService;
import org.eclipse.sirius.web.diagrams.tools.ToolSection;
import org.eclipse.sirius.web.graphql.datafetchers.query.QueryTypeCustomizer;
import org.eclipse.sirius.web.graphql.schema.ViewerTypeProvider;
import org.eclipse.sirius.web.spring.graphql.api.IDataFetcherWithFieldCoordinates;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import graphql.schema.DataFetchingEnvironment;

/**
 * The data fetcher used to retrieve tool sections relevant to a given diagramId.
 * <p>
 * It will be used to fetch the data for the following GraphQL field:
 * </p>
 *
 * <pre>
 * type Tool {
 *   toolSections(diagramId: ID!): [ToolSection!]!
 * }
 * </pre>
 *
 * @author hmarchadour
 */
@QueryDataFetcher(type = ViewerTypeProvider.USER_TYPE, field = QueryTypeCustomizer.TOOL_SECTIONS_FIELD)
public class UserToolSectionsDataFetcher implements IDataFetcherWithFieldCoordinates<List<ToolSection>> {

    private final IToolService toolService;

    private final IDiagramService diagramService;

    private final Logger logger = LoggerFactory.getLogger(UserToolSectionsDataFetcher.class);

    public UserToolSectionsDataFetcher(IToolService toolService, IDiagramService diagramService) {
        this.toolService = Objects.requireNonNull(toolService);
        this.diagramService = Objects.requireNonNull(diagramService);
    }

    @Override
    public List<ToolSection> get(DataFetchingEnvironment environment) throws Exception {
        List<ToolSection> result = List.of();
        try {
            UUID diagramId = UUID.fromString(environment.getArgument(QueryTypeCustomizer.DIAGRAM_ID_ARGUMENT));
            // @formatter:off
            result = this.diagramService.findById(diagramId)
                    .map(this.toolService::getToolSections)
                    .orElse(List.of());
            // @formatter:on
        } catch (IllegalArgumentException exception) {
            this.logger.error(exception.getMessage(), exception);
        }
        return result;
    }
}
