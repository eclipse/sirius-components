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

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.eclipse.sirius.web.annotations.spring.graphql.QueryDataFetcher;
import org.eclipse.sirius.web.collaborative.api.dto.RepresentationDescriptor;
import org.eclipse.sirius.web.graphql.schema.ProjectTypeProvider;
import org.eclipse.sirius.web.representations.IRepresentation;
import org.eclipse.sirius.web.services.api.projects.Project;
import org.eclipse.sirius.web.services.api.representations.IRepresentationService;
import org.eclipse.sirius.web.spring.graphql.api.IDataFetcherWithFieldCoordinates;

import graphql.schema.DataFetchingEnvironment;

/**
 * The data fetcher used to retrieve all the representations of a project.
 * <p>
 * It will be used to fetch the data for the following GraphQL field:
 * </p>
 *
 * <pre>
 * type Project {
 *   representations: [Representation!]!
 * }
 * </pre>
 *
 * @author wpiers
 */
@QueryDataFetcher(type = ProjectTypeProvider.TYPE, field = ProjectTypeProvider.REPRESENTATIONS_FIELD)
public class ProjectRepresentationsDataFetcher implements IDataFetcherWithFieldCoordinates<List<IRepresentation>> {

    private final IRepresentationService representationService;

    public ProjectRepresentationsDataFetcher(IRepresentationService representationService) {
        this.representationService = Objects.requireNonNull(representationService);
    }

    @Override
    public List<IRepresentation> get(DataFetchingEnvironment environment) throws Exception {
        Project project = environment.getSource();
        // @formatter:off
        return this.representationService.getRepresentationDescriptorsForProjectId(project.getId()).stream()
                .map(RepresentationDescriptor::getRepresentation).collect(Collectors.toList());
        // @formatter:on
    }

}
