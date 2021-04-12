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

import org.eclipse.sirius.web.annotations.graphql.GraphQLMutationTypes;
import org.eclipse.sirius.web.annotations.spring.graphql.MutationDataFetcher;
import org.eclipse.sirius.web.collaborative.api.dto.DeleteRepresentationInput;
import org.eclipse.sirius.web.collaborative.api.dto.DeleteRepresentationSuccessPayload;
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
 * The data fetcher used to delete a representation.
 * <p>
 * *It will be used to handle the following GraphQL field:*
 * </p>
 * **
 *
 * <pre>
 * type Mutation{
 *    deleteRepresentation(input:DeleteRepresentationInput!):DeleteRepresentationPayload!
 * }
 * </pre>
 *
 * @author lfasani
 */
// @formatter:off
@GraphQLMutationTypes(
    input = DeleteRepresentationInput.class,
    payloads = {
        DeleteRepresentationSuccessPayload.class
    }
)
@MutationDataFetcher(type = MutationTypeProvider.TYPE, field = MutationDeleteRepresentationDataFetcher.DELETE_REPRESENTATION_FIELD)
// @formatter:on
public class MutationDeleteRepresentationDataFetcher implements IDataFetcherWithFieldCoordinates<IPayload> {

    public static final String DELETE_REPRESENTATION_FIELD = "deleteRepresentation"; //$NON-NLS-1$

    private final IDataFetchingEnvironmentService dataFetchingEnvironmentService;

    private final IRepresentationService representationService;

    private final IEditingContextEventProcessorRegistry editingContextEventProcessorRegistry;

    private final IGraphQLMessageService messageService;

    public MutationDeleteRepresentationDataFetcher(IDataFetchingEnvironmentService dataFetchingEnvironmentService, IEditingContextEventProcessorRegistry editingContextEventProcessorRegistry,
            IRepresentationService representationService, IGraphQLMessageService messageService) {
        this.dataFetchingEnvironmentService = Objects.requireNonNull(dataFetchingEnvironmentService);
        this.representationService = Objects.requireNonNull(representationService);
        this.editingContextEventProcessorRegistry = Objects.requireNonNull(editingContextEventProcessorRegistry);
        this.messageService = Objects.requireNonNull(messageService);
    }

    @Override
    public IPayload get(DataFetchingEnvironment environment) throws Exception {
        var input = this.dataFetchingEnvironmentService.getInput(environment, DeleteRepresentationInput.class);

        IPayload payload = new ErrorPayload(input.getId(), this.messageService.unexpectedError());

        var optionalRepresentation = this.representationService.getRepresentation(input.getRepresentationId());
        if (optionalRepresentation.isPresent()) {
            RepresentationDescriptor representation = optionalRepresentation.get();

            boolean canEdit = this.dataFetchingEnvironmentService.canEdit(environment, representation.getProjectId());
            if (canEdit) {
                // @formatter:off
                payload = this.editingContextEventProcessorRegistry.dispatchEvent(representation.getProjectId(), input)
                        .orElse(new ErrorPayload(input.getId(), this.messageService.unexpectedError()));
                // @formatter:on
            } else {
                payload = new ErrorPayload(input.getId(), this.messageService.unauthorized());
            }
        }

        return payload;
    }
}
