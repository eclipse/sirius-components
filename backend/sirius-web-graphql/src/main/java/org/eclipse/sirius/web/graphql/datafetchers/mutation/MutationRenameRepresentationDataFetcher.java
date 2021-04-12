/*******************************************************************************
 * Copyright (c) 2019, 2021 Obeo.
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
import java.util.Optional;
import java.util.UUID;

import org.eclipse.sirius.web.annotations.graphql.GraphQLMutationTypes;
import org.eclipse.sirius.web.annotations.spring.graphql.MutationDataFetcher;
import org.eclipse.sirius.web.collaborative.api.dto.RenameRepresentationInput;
import org.eclipse.sirius.web.collaborative.api.dto.RenameRepresentationSuccessPayload;
import org.eclipse.sirius.web.collaborative.api.dto.RepresentationDescriptor;
import org.eclipse.sirius.web.collaborative.api.services.IEditingContextEventProcessorRegistry;
import org.eclipse.sirius.web.core.api.ErrorPayload;
import org.eclipse.sirius.web.core.api.IPayload;
import org.eclipse.sirius.web.graphql.datafetchers.IDataFetchingEnvironmentService;
import org.eclipse.sirius.web.graphql.messages.IGraphQLMessageService;
import org.eclipse.sirius.web.graphql.schema.MutationTypeProvider;
import org.eclipse.sirius.web.services.api.representations.IRepresentationService;
import org.eclipse.sirius.web.spring.graphql.api.IDataFetcherWithFieldCoordinates;

import graphql.schema.DataFetchingEnvironment;

/**
 * The data fetcher used to rename a representation.
 * <p>
 * It will be used to handle the following GraphQL field:
 * </p>
 *
 * <pre>
 * type Mutation {
 *   renameRepresentation(input : RenameRepresentationInput!): RenameRepresentationPayload!
 * }
 * </pre>
 *
 * @author arichard
 */
// @formatter:off
@GraphQLMutationTypes(
    input = RenameRepresentationInput.class,
    payloads = {
        RenameRepresentationSuccessPayload.class
    }
)
@MutationDataFetcher(type = MutationTypeProvider.TYPE, field = MutationRenameRepresentationDataFetcher.RENAME_REPRESENTATION_FIELD)
// @formatter:on
public class MutationRenameRepresentationDataFetcher implements IDataFetcherWithFieldCoordinates<IPayload> {

    public static final String RENAME_REPRESENTATION_FIELD = "renameRepresentation"; //$NON-NLS-1$

    private final IDataFetchingEnvironmentService dataFetchingEnvironmentService;

    private final IRepresentationService representationService;

    private final IEditingContextEventProcessorRegistry editingContextEventProcessorRegistry;

    private final IGraphQLMessageService messageService;

    public MutationRenameRepresentationDataFetcher(IDataFetchingEnvironmentService dataFetchingEnvironmentService, IEditingContextEventProcessorRegistry editingContextEventProcessorRegistry,
            IRepresentationService representationService, IGraphQLMessageService messageService) {
        this.dataFetchingEnvironmentService = Objects.requireNonNull(dataFetchingEnvironmentService);
        this.editingContextEventProcessorRegistry = Objects.requireNonNull(editingContextEventProcessorRegistry);
        this.representationService = Objects.requireNonNull(representationService);
        this.messageService = Objects.requireNonNull(messageService);
    }

    @Override
    public IPayload get(DataFetchingEnvironment environment) throws Exception {
        var input = this.dataFetchingEnvironmentService.getInput(environment, RenameRepresentationInput.class);

        IPayload payload = new ErrorPayload(input.getId(), this.messageService.unexpectedError());

        UUID projectId = input.getProjectId();
        if (projectId != null) {
            boolean canEditProject = this.dataFetchingEnvironmentService.canEdit(environment, projectId);
            if (!canEditProject) {
                payload = new ErrorPayload(input.getId(), this.messageService.unauthorized());
            } else {
                Optional<RepresentationDescriptor> optionalRepresentationDescriptor = this.representationService.getRepresentation(input.getRepresentationId());
                if (optionalRepresentationDescriptor.isPresent()) {
                    RepresentationDescriptor representationDescriptor = optionalRepresentationDescriptor.get();

                    boolean canEdit = this.dataFetchingEnvironmentService.canEdit(environment, representationDescriptor.getProjectId());
                    if (canEdit) {
                        // @formatter:off
                        payload = this.editingContextEventProcessorRegistry.dispatchEvent(representationDescriptor.getProjectId(), input)
                                .orElse(new ErrorPayload(input.getId(), this.messageService.unexpectedError()));
                        // @formatter:on
                    } else {
                        payload = new ErrorPayload(input.getId(), this.messageService.unauthorized());
                    }

                }
            }
        }
        return payload;
    }

}
