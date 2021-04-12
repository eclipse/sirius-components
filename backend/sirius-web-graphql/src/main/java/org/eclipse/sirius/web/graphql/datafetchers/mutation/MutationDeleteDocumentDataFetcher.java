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
import org.eclipse.sirius.web.collaborative.api.dto.DeleteDocumentInput;
import org.eclipse.sirius.web.collaborative.api.dto.DeleteDocumentSuccessPayload;
import org.eclipse.sirius.web.collaborative.api.dto.Document;
import org.eclipse.sirius.web.collaborative.api.services.IEditingContextEventProcessorRegistry;
import org.eclipse.sirius.web.core.api.ErrorPayload;
import org.eclipse.sirius.web.core.api.IPayload;
import org.eclipse.sirius.web.graphql.datafetchers.IDataFetchingEnvironmentService;
import org.eclipse.sirius.web.graphql.messages.IGraphQLMessageService;
import org.eclipse.sirius.web.graphql.schema.MutationTypeProvider;
import org.eclipse.sirius.web.services.api.document.IDocumentService;
import org.eclipse.sirius.web.spring.graphql.api.IDataFetcherWithFieldCoordinates;

import graphql.schema.DataFetchingEnvironment;

/**
 * The data fetcher used to delete a {@link IDocument}.
 * <p>
 * It will be used to handle the following GraphQL field:
 * </p>
 *
 * <pre>
 * type Mutation {
 *   deleteDocument(documentId: String!): DeleteDocumentPayload!
 * }
 * </pre>
 *
 * @author fbarbin
 */
// @formatter:off
@GraphQLMutationTypes(
    input = DeleteDocumentInput.class,
    payloads = {
        DeleteDocumentSuccessPayload.class
    }
)
@MutationDataFetcher(type = MutationTypeProvider.TYPE, field = MutationDeleteDocumentDataFetcher.DELETE_DOCUMENT_FIELD)
// @formatter:on
public class MutationDeleteDocumentDataFetcher implements IDataFetcherWithFieldCoordinates<IPayload> {

    public static final String DELETE_DOCUMENT_FIELD = "deleteDocument"; //$NON-NLS-1$

    private final IDataFetchingEnvironmentService dataFetchingEnvironmentService;

    private final IDocumentService documentService;

    private final IEditingContextEventProcessorRegistry editingContextEventProcessorRegistry;

    private final IGraphQLMessageService messageService;

    public MutationDeleteDocumentDataFetcher(IDataFetchingEnvironmentService dataFetchingEnvironmentService, IEditingContextEventProcessorRegistry editingContextEventProcessorRegistry,
            IDocumentService documentService, IGraphQLMessageService messageService) {
        this.dataFetchingEnvironmentService = Objects.requireNonNull(dataFetchingEnvironmentService);
        this.editingContextEventProcessorRegistry = Objects.requireNonNull(editingContextEventProcessorRegistry);
        this.documentService = Objects.requireNonNull(documentService);
        this.messageService = Objects.requireNonNull(messageService);
    }

    @Override
    public IPayload get(DataFetchingEnvironment environment) throws Exception {
        var input = this.dataFetchingEnvironmentService.getInput(environment, DeleteDocumentInput.class);

        IPayload payload = new ErrorPayload(input.getId(), this.messageService.unexpectedError());

        var optionalDocument = this.documentService.getDocument(input.getDocumentId());
        if (optionalDocument.isPresent()) {
            Document document = optionalDocument.get();

            boolean canEdit = this.dataFetchingEnvironmentService.canEdit(environment, document.getProject().getId());
            if (canEdit) {
                // @formatter:off
                payload = this.editingContextEventProcessorRegistry.dispatchEvent(document.getProject().getId(), input)
                        .orElse(new ErrorPayload(input.getId(), this.messageService.unexpectedError()));
                // @formatter:on
            } else {
                payload = new ErrorPayload(input.getId(), this.messageService.unauthorized());
            }
        }

        return payload;
    }

}
