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
package org.eclipse.sirius.web.graphql.datafetchers.mutation;

import java.util.Objects;

import org.eclipse.sirius.web.annotations.graphql.GraphQLMutationTypes;
import org.eclipse.sirius.web.annotations.spring.graphql.MutationDataFetcher;
import org.eclipse.sirius.web.collaborative.api.services.IEditingContextEventProcessorRegistry;
import org.eclipse.sirius.web.core.api.ErrorPayload;
import org.eclipse.sirius.web.core.api.IPayload;
import org.eclipse.sirius.web.graphql.datafetchers.IDataFetchingEnvironmentService;
import org.eclipse.sirius.web.graphql.datafetchers.IViewerProvider;
import org.eclipse.sirius.web.graphql.messages.IGraphQLMessageService;
import org.eclipse.sirius.web.graphql.schema.MutationTypeProvider;
import org.eclipse.sirius.web.services.api.projects.DeleteProjectInput;
import org.eclipse.sirius.web.services.api.projects.DeleteProjectSuccessPayload;
import org.eclipse.sirius.web.services.api.projects.IProjectService;
import org.eclipse.sirius.web.services.api.viewer.IViewer;
import org.eclipse.sirius.web.spring.graphql.api.IDataFetcherWithFieldCoordinates;

import graphql.schema.DataFetchingEnvironment;

/**
 * The data fetcher used to delete a project.
 * <p>
 * It will be used to handle the following GraphQL field:
 * </p>
 *
 * <pre>
 * type Mutation {
 *   deleteProject(input: DeleteProjectInput!): DeleteProjectPayload!
 * }
 * </pre>
 *
 * @author fbarbin
 */
// @formatter:off
@GraphQLMutationTypes(
    input = DeleteProjectInput.class,
    payloads = {
        DeleteProjectSuccessPayload.class
    }
)
@MutationDataFetcher(type = MutationTypeProvider.TYPE, field = MutationDeleteProjectDataFetcher.DELETE_PROJECT_FIELD)
// @formatter:on
public class MutationDeleteProjectDataFetcher implements IDataFetcherWithFieldCoordinates<IPayload> {

    public static final String DELETE_PROJECT_FIELD = "deleteProject"; //$NON-NLS-1$

    private final IDataFetchingEnvironmentService dataFetchingEnvironmentService;

    private final IViewerProvider viewerProvider;

    private final IEditingContextEventProcessorRegistry editingContextEventProcessorRegistry;

    private final IProjectService projectService;

    private final IGraphQLMessageService messageService;

    public MutationDeleteProjectDataFetcher(IDataFetchingEnvironmentService dataFetchingEnvironmentService, IViewerProvider viewerProvider, IProjectService projectService,
            IEditingContextEventProcessorRegistry editingContextEventProcessorRegistry, IGraphQLMessageService messageService) {
        this.dataFetchingEnvironmentService = Objects.requireNonNull(dataFetchingEnvironmentService);
        this.viewerProvider = Objects.requireNonNull(viewerProvider);
        this.projectService = Objects.requireNonNull(projectService);
        this.editingContextEventProcessorRegistry = Objects.requireNonNull(editingContextEventProcessorRegistry);
        this.messageService = Objects.requireNonNull(messageService);
    }

    @Override
    public IPayload get(DataFetchingEnvironment environment) throws Exception {
        var input = this.dataFetchingEnvironmentService.getInput(environment, DeleteProjectInput.class);
        var optionalViewer = this.viewerProvider.getViewer(environment);
        IPayload payload = new ErrorPayload(this.messageService.unexpectedError());
        if (optionalViewer.isPresent()) {
            IViewer viewer = optionalViewer.get();
            boolean canAdmin = this.dataFetchingEnvironmentService.canAdminProject(environment, input.getProjectId());
            if (canAdmin) {
                this.editingContextEventProcessorRegistry.dispose(input.getProjectId());
                this.projectService.delete(input.getProjectId());
                payload = new DeleteProjectSuccessPayload(viewer);
            } else {
                payload = new ErrorPayload(this.messageService.unauthorized());
            }
        }

        return payload;
    }
}
