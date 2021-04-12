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
package org.eclipse.sirius.web.graphql.datafetchers.editingcontext;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import org.eclipse.sirius.web.annotations.spring.graphql.QueryDataFetcher;
import org.eclipse.sirius.web.core.api.IEditService;
import org.eclipse.sirius.web.graphql.schema.EditingContextTypeProvider;
import org.eclipse.sirius.web.representations.IRepresentationDescription;
import org.eclipse.sirius.web.services.api.representations.IRepresentationDescriptionService;
import org.eclipse.sirius.web.spring.graphql.api.IDataFetcherWithFieldCoordinates;

import graphql.relay.Connection;
import graphql.relay.ConnectionCursor;
import graphql.relay.DefaultConnection;
import graphql.relay.DefaultConnectionCursor;
import graphql.relay.DefaultEdge;
import graphql.relay.DefaultPageInfo;
import graphql.relay.Edge;
import graphql.relay.PageInfo;
import graphql.schema.DataFetchingEnvironment;

/**
 * The data fetcher used to retrieve the representation descriptions accessible to a viewer.
 * <p>
 * It will be used to fetch the data for the following GraphQL field:
 * </p>
 *
 * <pre>
 * type EditingContext {
 *   representationDescriptions(classId: ID): EditingContextRepresentationDescriptionConnection!
 * }
 * </pre>
 *
 * @author pcdavid
 * @author sbegaudeau
 */
@QueryDataFetcher(type = EditingContextTypeProvider.TYPE, field = EditingContextTypeProvider.REPRESENTATION_DESCRIPTIONS_FIELD)
public class EditingContextRepresentationDescriptionsDataFetcher implements IDataFetcherWithFieldCoordinates<Connection<IRepresentationDescription>> {

    private final IRepresentationDescriptionService representationDescriptionService;

    private final IEditService editService;

    public EditingContextRepresentationDescriptionsDataFetcher(IRepresentationDescriptionService representationDescriptionService, IEditService editService) {
        this.representationDescriptionService = Objects.requireNonNull(representationDescriptionService);
        this.editService = Objects.requireNonNull(editService);
    }

    @Override
    public Connection<IRepresentationDescription> get(DataFetchingEnvironment environment) throws Exception {
        UUID editingContextId = environment.getSource();
        String classId = environment.getArgument(EditingContextTypeProvider.CLASS_ID_ARGUMENT);

        // @formatter:off
        var representationDescriptions = this.editService.findClass(editingContextId, classId)
                .map(this.representationDescriptionService::getRepresentationDescriptions)
                .orElseGet(ArrayList::new);
        // @formatter:on

        // @formatter:off
        List<Edge<IRepresentationDescription>> representationDescriptionEdges = representationDescriptions.stream()
                .map(representationDescription -> {
                    String value = Base64.getEncoder().encodeToString(representationDescription.getId().toString().getBytes());
                    ConnectionCursor cursor = new DefaultConnectionCursor(value);
                    return new DefaultEdge<>(representationDescription, cursor);
                })
                .collect(Collectors.toList());
        // @formatter:on

        ConnectionCursor startCursor = representationDescriptionEdges.stream().findFirst().map(Edge::getCursor).orElse(null);
        ConnectionCursor endCursor = null;
        if (!representationDescriptionEdges.isEmpty()) {
            endCursor = representationDescriptionEdges.get(representationDescriptionEdges.size() - 1).getCursor();
        }
        PageInfo pageInfo = new DefaultPageInfo(startCursor, endCursor, false, false);
        return new DefaultConnection<>(representationDescriptionEdges, pageInfo);
    }

}
