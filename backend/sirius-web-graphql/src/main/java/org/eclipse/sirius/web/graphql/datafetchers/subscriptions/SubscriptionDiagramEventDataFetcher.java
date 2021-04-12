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
package org.eclipse.sirius.web.graphql.datafetchers.subscriptions;

import java.util.Objects;

import org.eclipse.sirius.web.annotations.graphql.GraphQLSubscriptionTypes;
import org.eclipse.sirius.web.annotations.spring.graphql.SubscriptionDataFetcher;
import org.eclipse.sirius.web.collaborative.api.dto.SubscribersUpdatedEventPayload;
import org.eclipse.sirius.web.collaborative.api.services.IEditingContextEventProcessorRegistry;
import org.eclipse.sirius.web.collaborative.diagrams.api.DiagramConfiguration;
import org.eclipse.sirius.web.collaborative.diagrams.api.IDiagramEventProcessor;
import org.eclipse.sirius.web.collaborative.diagrams.api.dto.DiagramEventInput;
import org.eclipse.sirius.web.collaborative.diagrams.api.dto.DiagramRefreshedEventPayload;
import org.eclipse.sirius.web.core.api.IPayload;
import org.eclipse.sirius.web.graphql.datafetchers.IDataFetchingEnvironmentService;
import org.eclipse.sirius.web.graphql.schema.SubscriptionTypeProvider;
import org.eclipse.sirius.web.spring.graphql.api.IDataFetcherWithFieldCoordinates;
import org.reactivestreams.Publisher;

import graphql.schema.DataFetchingEnvironment;
import reactor.core.publisher.Flux;

/**
 * The data fetcher used to send the refreshed diagram to a subscription.
 * <p>
 * It will be used to fetch the data for the following GraphQL field:
 * </p>
 *
 * <pre>
 * type Subscription {
 *   diagramEvent(input: DiagramEventInput): DiagramEventPayload
 * }
 * </pre>
 *
 * @author sbegaudeau
 * @author pcdavid
 */
// @formatter:off
@GraphQLSubscriptionTypes(
    input = DiagramEventInput.class,
    payloads = {
        DiagramRefreshedEventPayload.class,
        SubscribersUpdatedEventPayload.class,
    }
)
@SubscriptionDataFetcher(type = SubscriptionTypeProvider.TYPE, field = SubscriptionDiagramEventDataFetcher.DIAGRAM_EVENT_FIELD)
// @formatter:on
public class SubscriptionDiagramEventDataFetcher implements IDataFetcherWithFieldCoordinates<Publisher<IPayload>> {

    public static final String DIAGRAM_EVENT_FIELD = "diagramEvent"; //$NON-NLS-1$

    private final IDataFetchingEnvironmentService dataFetchingEnvironmentService;

    private final IEditingContextEventProcessorRegistry editingContextEventProcessorRegistry;

    public SubscriptionDiagramEventDataFetcher(IDataFetchingEnvironmentService dataFetchingEnvironmentService, IEditingContextEventProcessorRegistry editingContextEventProcessorRegistry) {
        this.dataFetchingEnvironmentService = Objects.requireNonNull(dataFetchingEnvironmentService);
        this.editingContextEventProcessorRegistry = Objects.requireNonNull(editingContextEventProcessorRegistry);
    }

    @Override
    public Publisher<IPayload> get(DataFetchingEnvironment environment) throws Exception {
        var input = this.dataFetchingEnvironmentService.getInput(environment, DiagramEventInput.class);
        var diagramConfiguration = new DiagramConfiguration(input.getDiagramId());

        // @formatter:off
        return this.editingContextEventProcessorRegistry.getOrCreateEditingContextEventProcessor(input.getProjectId())
                .flatMap(processor -> processor.acquireRepresentationEventProcessor(IDiagramEventProcessor.class, diagramConfiguration, input))
                .map(representationEventProcessor -> representationEventProcessor.getOutputEvents(input))
                .orElse(Flux.empty());
        // @formatter:on
    }
}
