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

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.eclipse.sirius.web.annotations.graphql.GraphQLMutationTypes;
import org.eclipse.sirius.web.annotations.spring.graphql.MutationDataFetcher;
import org.eclipse.sirius.web.collaborative.api.dto.UploadDocumentInput;
import org.eclipse.sirius.web.collaborative.api.dto.UploadDocumentSuccessPayload;
import org.eclipse.sirius.web.collaborative.api.services.IEditingContextEventProcessorRegistry;
import org.eclipse.sirius.web.core.api.ErrorPayload;
import org.eclipse.sirius.web.core.api.IPayload;
import org.eclipse.sirius.web.core.api.UploadFile;
import org.eclipse.sirius.web.graphql.datafetchers.IDataFetchingEnvironmentService;
import org.eclipse.sirius.web.graphql.messages.IGraphQLMessageService;
import org.eclipse.sirius.web.graphql.schema.MutationTypeProvider;
import org.eclipse.sirius.web.spring.graphql.api.IDataFetcherWithFieldCoordinates;

import graphql.schema.DataFetchingEnvironment;

/**
 * The data fetcher used to create a new document thanks to an UploadInput.
 * <p>
 * It will be used to handle the following GraphQL field:
 * </p>
 *
 * <pre>
 * type Mutation {
 *   uploadDocument(input: UploadDocumentInput!): UploadDocumentPayload!
 * }
 * </pre>
 *
 * @author smonnier
 */
// @formatter:off
@GraphQLMutationTypes(
    input = UploadDocumentInput.class,
    payloads = {
        UploadDocumentSuccessPayload.class,
    }
)
@MutationDataFetcher(type = MutationTypeProvider.TYPE, field = MutationUploadDocumentDataFetcher.UPLOAD_DOCUMENT_FIELD)
// @formatter:on
public class MutationUploadDocumentDataFetcher implements IDataFetcherWithFieldCoordinates<IPayload> {

    public static final String UPLOAD_DOCUMENT_FIELD = "uploadDocument"; //$NON-NLS-1$

    private static final String PROJECT_ID = "projectId"; //$NON-NLS-1$

    private static final String FILE = "file"; //$NON-NLS-1$

    private static final String ID = "id"; //$NON-NLS-1$

    private final IDataFetchingEnvironmentService dataFetchingEnvironmentService;

    private final IEditingContextEventProcessorRegistry editingContextEventProcessorRegistry;

    private final IGraphQLMessageService messageService;

    public MutationUploadDocumentDataFetcher(IDataFetchingEnvironmentService dataFetchingEnvironmentService, IEditingContextEventProcessorRegistry editingContextEventProcessorRegistry,
            IGraphQLMessageService messageService) {
        this.dataFetchingEnvironmentService = Objects.requireNonNull(dataFetchingEnvironmentService);
        this.editingContextEventProcessorRegistry = Objects.requireNonNull(editingContextEventProcessorRegistry);
        this.messageService = Objects.requireNonNull(messageService);
    }

    @Override
    public IPayload get(DataFetchingEnvironment environment) throws Exception {
        Map<Object, Object> inputArgument = environment.getArgument(MutationTypeProvider.INPUT_ARGUMENT);

        // We cannot use directly UploadDocumentInput, the objectMapper cannot handle the file stream.

        // @formatter:off
        UUID id = Optional.of(inputArgument.get(ID))
                .filter(String.class::isInstance)
                .map(String.class::cast)
                .map(UUID::fromString)
                .orElse(null);

        UUID projectId = Optional.of(inputArgument.get(PROJECT_ID))
                .filter(String.class::isInstance)
                .map(String.class::cast)
                .map(UUID::fromString)
                .orElse(null);

        UploadFile file = Optional.of(inputArgument.get(FILE))
                .filter(UploadFile.class::isInstance)
                .map(UploadFile.class::cast)
                .orElse(null);
        // @formatter:on

        UploadDocumentInput input = new UploadDocumentInput(id, projectId, file);
        IPayload payload = new ErrorPayload(input.getId(), this.messageService.unauthorized());

        boolean canEdit = this.dataFetchingEnvironmentService.canEdit(environment, projectId);
        if (canEdit) {
            // @formatter:off
            payload = this.editingContextEventProcessorRegistry.dispatchEvent(projectId, input)
                    .orElse(new ErrorPayload(input.getId(), this.messageService.unexpectedError()));
            // @formatter:on
        }

        return payload;
    }

}
