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
import java.util.Optional;
import java.util.UUID;

import org.eclipse.sirius.web.annotations.graphql.GraphQLMutationTypes;
import org.eclipse.sirius.web.annotations.spring.graphql.MutationDataFetcher;
import org.eclipse.sirius.web.collaborative.api.services.IEditingContextEventProcessorRegistry;
import org.eclipse.sirius.web.core.api.ErrorPayload;
import org.eclipse.sirius.web.core.api.IPayload;
import org.eclipse.sirius.web.graphql.datafetchers.IDataFetchingEnvironmentService;
import org.eclipse.sirius.web.graphql.messages.IGraphQLMessageService;
import org.eclipse.sirius.web.graphql.schema.MutationTypeProvider;
import org.eclipse.sirius.web.services.api.document.Document;
import org.eclipse.sirius.web.services.api.document.IDocumentService;
import org.eclipse.sirius.web.services.api.document.RenameDocumentInput;
import org.eclipse.sirius.web.services.api.document.RenameDocumentSuccessPayload;
import org.eclipse.sirius.web.services.api.projects.IProjectService;
import org.eclipse.sirius.web.services.api.projects.Project;
import org.eclipse.sirius.web.spring.graphql.api.IDataFetcherWithFieldCoordinates;

import graphql.schema.DataFetchingEnvironment;

/**
 * The data fetcher used to rename a {@link Document}.
 * <p>
 * It will be used to handle the following GraphQL field:
 * </p>
 *
 * <pre>
 * type Mutation {
 *   renameDocument(input : RenameDocumentInput!): RenameDocumentPayload!
 * }
 * </pre>
 *
 * @author fbarbin
 */
// @formatter:off
@GraphQLMutationTypes(
    input = RenameDocumentInput.class,
    payloads = {
        RenameDocumentSuccessPayload.class
    }
)
@MutationDataFetcher(type = MutationTypeProvider.TYPE, field = MutationRenameDocumentDataFetcher.RENAME_DOCUMENT_FIELD)
// @formatter:on
public class MutationRenameDocumentDataFetcher implements IDataFetcherWithFieldCoordinates<IPayload> {

    public static final String RENAME_DOCUMENT_FIELD = "renameDocument"; //$NON-NLS-1$

    private final IDataFetchingEnvironmentService dataFetchingEnvironmentService;

    private final IDocumentService documentService;

    private final IProjectService projectService;

    private final IEditingContextEventProcessorRegistry editingContextEventProcessorRegistry;

    private final IGraphQLMessageService messageService;

    public MutationRenameDocumentDataFetcher(IDataFetchingEnvironmentService dataFetchingEnvironmentService, IEditingContextEventProcessorRegistry editingContextEventProcessorRegistry,
            IDocumentService documentService, IProjectService projectService, IGraphQLMessageService messageService) {
        this.dataFetchingEnvironmentService = Objects.requireNonNull(dataFetchingEnvironmentService);
        this.editingContextEventProcessorRegistry = Objects.requireNonNull(editingContextEventProcessorRegistry);
        this.documentService = Objects.requireNonNull(documentService);
        this.projectService = Objects.requireNonNull(projectService);
        this.messageService = Objects.requireNonNull(messageService);
    }

    @Override
    public IPayload get(DataFetchingEnvironment environment) throws Exception {
        var input = this.dataFetchingEnvironmentService.getInput(environment, RenameDocumentInput.class);

        IPayload payload = new ErrorPayload(this.messageService.unauthorized());

        Optional<Document> optionalDocument = this.documentService.getDocument(input.getDocumentId());
        if (optionalDocument.isPresent()) {
            Document document = optionalDocument.get();

            boolean canEdit = this.dataFetchingEnvironmentService.canEditEditingContext(environment, document.getEditingContext().getId());
            if (canEdit) {
                UUID editingContextId = document.getEditingContext().getId();
                Optional<UUID> optionalProjectId = this.projectService.getProjectByCurrentEditingContextId(editingContextId).map(Project::getId);
                if (optionalProjectId.isPresent()) {
                    // @formatter:off
                    payload = this.editingContextEventProcessorRegistry.dispatchEvent(optionalProjectId.get(), input)
                            .orElse(new ErrorPayload(this.messageService.unexpectedError()));
                    // @formatter:on
                } else {
                    payload = new ErrorPayload(this.messageService.unexpectedError());
                }
            } else {
                payload = new ErrorPayload(this.messageService.unexpectedError());
            }

        }
        return payload;
    }

}
