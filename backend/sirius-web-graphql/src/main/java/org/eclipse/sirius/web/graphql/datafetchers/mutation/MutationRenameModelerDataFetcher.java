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
package org.eclipse.sirius.web.graphql.datafetchers.mutation;

import java.util.Objects;
import java.util.Optional;

import org.eclipse.sirius.web.annotations.graphql.GraphQLMutationTypes;
import org.eclipse.sirius.web.annotations.spring.graphql.MutationDataFetcher;
import org.eclipse.sirius.web.core.api.ErrorPayload;
import org.eclipse.sirius.web.core.api.IPayload;
import org.eclipse.sirius.web.graphql.datafetchers.IDataFetchingEnvironmentService;
import org.eclipse.sirius.web.graphql.messages.IGraphQLMessageService;
import org.eclipse.sirius.web.graphql.schema.MutationTypeProvider;
import org.eclipse.sirius.web.services.api.modelers.IModelerService;
import org.eclipse.sirius.web.services.api.modelers.Modeler;
import org.eclipse.sirius.web.services.api.modelers.RenameModelerInput;
import org.eclipse.sirius.web.services.api.modelers.RenameModelerSuccessPayload;
import org.eclipse.sirius.web.spring.graphql.api.IDataFetcherWithFieldCoordinates;

import graphql.schema.DataFetchingEnvironment;

/**
 * The data fetcher used to rename a modeler.
 * <p>
 * It will be used to handle the following GraphQL field:
 * </p>
 *
 * <pre>
 * type Mutation {
 *   renameModeler(input: RenameModelerInput!): RenameModelerPayload!
 * }
 * </pre>
 *
 * @author pcdavid
 */
// @formatter:off
@GraphQLMutationTypes(
    input = RenameModelerInput.class,
    payloads = {
        RenameModelerSuccessPayload.class
    }
)
@MutationDataFetcher(type = MutationTypeProvider.TYPE, field = MutationRenameModelerDataFetcher.RENAME_MODELER_FIELD)
// @formatter:on
public class MutationRenameModelerDataFetcher implements IDataFetcherWithFieldCoordinates<IPayload> {

    public static final String RENAME_MODELER_FIELD = "renameModeler"; //$NON-NLS-1$

    private final IDataFetchingEnvironmentService dataFetchingEnvironmentService;

    private final IModelerService modelerService;

    private final IGraphQLMessageService messageService;

    public MutationRenameModelerDataFetcher(IDataFetchingEnvironmentService dataFetchingEnvironmentService, IModelerService modelerService, IGraphQLMessageService messageService) {
        this.dataFetchingEnvironmentService = Objects.requireNonNull(dataFetchingEnvironmentService);
        this.modelerService = Objects.requireNonNull(modelerService);
        this.messageService = Objects.requireNonNull(messageService);
    }

    @Override
    public IPayload get(DataFetchingEnvironment environment) throws Exception {
        var input = this.dataFetchingEnvironmentService.getInput(environment, RenameModelerInput.class);

        IPayload payload = new ErrorPayload(this.messageService.unauthorized());

        Optional<Modeler> optionalModeler = this.modelerService.getModeler(input.getModelerId());
        if (optionalModeler.isPresent()) {
            Modeler modeler = optionalModeler.get();

            boolean canEdit = this.dataFetchingEnvironmentService.canEditProject(environment, modeler.getProject().getId());
            if (canEdit) {
                payload = this.modelerService.renameModeler(input);
            } else {
                payload = new ErrorPayload(this.messageService.unexpectedError());
            }

        }
        return payload;
    }
}
