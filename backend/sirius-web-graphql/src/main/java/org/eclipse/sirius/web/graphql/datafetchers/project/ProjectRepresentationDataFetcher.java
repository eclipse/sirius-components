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
package org.eclipse.sirius.web.graphql.datafetchers.project;

import java.util.Objects;
import java.util.UUID;

import org.eclipse.sirius.web.annotations.spring.graphql.QueryDataFetcher;
import org.eclipse.sirius.web.collaborative.api.dto.RepresentationDescriptor;
import org.eclipse.sirius.web.graphql.schema.ProjectTypeProvider;
import org.eclipse.sirius.web.representations.IRepresentation;
import org.eclipse.sirius.web.services.api.projects.Project;
import org.eclipse.sirius.web.services.api.representations.IRepresentationService;
import org.eclipse.sirius.web.spring.graphql.api.IDataFetcherWithFieldCoordinates;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import graphql.schema.DataFetchingEnvironment;

/**
 * The data fetcher used to retrieve a representation of a project.
 * <p>
 * It will be used to fetch the data for the following GraphQL field:
 * </p>
 *
 * <pre>
 * type Project {
 *   representation(representationId: ID!): Representation
 * }
 * </pre>
 *
 * @author sbegaudeau
 */
@QueryDataFetcher(type = ProjectTypeProvider.TYPE, field = ProjectTypeProvider.REPRESENTATION_FIELD)
public class ProjectRepresentationDataFetcher implements IDataFetcherWithFieldCoordinates<IRepresentation> {

    private final IRepresentationService representationService;

    private final Logger logger = LoggerFactory.getLogger(ProjectRepresentationDataFetcher.class);

    public ProjectRepresentationDataFetcher(IRepresentationService representationService) {
        this.representationService = Objects.requireNonNull(representationService);
    }

    @Override
    public IRepresentation get(DataFetchingEnvironment environment) throws Exception {
        String representationIdArgument = environment.getArgument(ProjectTypeProvider.REPRESENTATION_ID_ARGUMENT);
        try {
            UUID representationId = UUID.fromString(representationIdArgument);

            Project project = environment.getSource();
            // @formatter:off
            return this.representationService.getRepresentationDescriptorForProjectId(project.getId(), representationId)
                    .map(RepresentationDescriptor::getRepresentation)
                    .orElse(null);
            // @formatter:on
        } catch (IllegalArgumentException exception) {
            this.logger.error(exception.getMessage(), exception);
        }
        return null;
    }

}
