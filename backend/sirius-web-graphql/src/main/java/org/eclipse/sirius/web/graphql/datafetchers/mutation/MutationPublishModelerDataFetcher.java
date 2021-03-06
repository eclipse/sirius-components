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

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Objects;
import java.util.Optional;

import org.eclipse.sirius.web.annotations.graphql.GraphQLMutationTypes;
import org.eclipse.sirius.web.annotations.spring.graphql.MutationDataFetcher;
import org.eclipse.sirius.web.core.api.ErrorPayload;
import org.eclipse.sirius.web.core.api.IPayload;
import org.eclipse.sirius.web.graphql.messages.IGraphQLMessageService;
import org.eclipse.sirius.web.graphql.schema.MutationTypeProvider;
import org.eclipse.sirius.web.services.api.modelers.IModelerService;
import org.eclipse.sirius.web.services.api.modelers.Modeler;
import org.eclipse.sirius.web.services.api.modelers.PublishModelerInput;
import org.eclipse.sirius.web.services.api.modelers.PublishModelerSuccessPayload;
import org.eclipse.sirius.web.spring.graphql.api.IDataFetcherWithFieldCoordinates;

import graphql.schema.DataFetchingEnvironment;

/**
 * The data fetcher used to publish a modeler.
 * <p>
 * It will be used to handle the following GraphQL field:
 * </p>
 *
 * <pre>
 * type Mutation {
 *   publishModeler(input: PublishModelerInput!): PublishModelerPayload!
 * }
 * </pre>
 *
 * @author pcdavid
 */
// @formatter:off
@GraphQLMutationTypes(
    input = PublishModelerInput.class,
    payloads = {
        PublishModelerSuccessPayload.class
    }
)
@MutationDataFetcher(type = MutationTypeProvider.TYPE, field = MutationPublishModelerDataFetcher.PUBLISH_MODELER_FIELD)
// @formatter:on
public class MutationPublishModelerDataFetcher implements IDataFetcherWithFieldCoordinates<IPayload> {

    public static final String PUBLISH_MODELER_FIELD = "publishModeler"; //$NON-NLS-1$

    private final ObjectMapper objectMapper;

    private final IModelerService modelerService;

    private final IGraphQLMessageService messageService;

    public MutationPublishModelerDataFetcher(ObjectMapper objectMapper, IModelerService modelerService, IGraphQLMessageService messageService) {
        this.objectMapper = Objects.requireNonNull(objectMapper);
        this.modelerService = Objects.requireNonNull(modelerService);
        this.messageService = Objects.requireNonNull(messageService);
    }

    @Override
    public IPayload get(DataFetchingEnvironment environment) throws Exception {
        Object argument = environment.getArgument(MutationTypeProvider.INPUT_ARGUMENT);
        var input = this.objectMapper.convertValue(argument, PublishModelerInput.class);

        IPayload payload = new ErrorPayload(input.getId(), this.messageService.unexpectedError());

        Optional<Modeler> optionalModeler = this.modelerService.getModeler(input.getModelerId());
        if (optionalModeler.isPresent()) {
            payload = this.modelerService.publishModeler(input);
        }
        return payload;
    }
}
