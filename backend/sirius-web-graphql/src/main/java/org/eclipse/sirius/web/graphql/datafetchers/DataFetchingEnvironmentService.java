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
package org.eclipse.sirius.web.graphql.datafetchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.security.Principal;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.eclipse.sirius.web.graphql.schema.MutationTypeProvider;
import org.eclipse.sirius.web.services.api.editingcontexts.IEditingContextAccessPolicy;
import org.eclipse.sirius.web.services.api.projects.IProjectAccessPolicy;
import org.eclipse.sirius.web.spring.graphql.api.GraphQLConstants;
import org.springframework.stereotype.Service;

import graphql.GraphQLContext;
import graphql.schema.DataFetchingEnvironment;

/**
 * Implementation of the data fetching environment service.
 *
 * @author pcdavid
 */
@Service
public class DataFetchingEnvironmentService implements IDataFetchingEnvironmentService {

    private final ObjectMapper objectMapper;

    private final IProjectAccessPolicy projectAccessPolicy;

    private final IEditingContextAccessPolicy editingContextAccessPolicy;

    public DataFetchingEnvironmentService(ObjectMapper objectMapper, IProjectAccessPolicy projectAccessPolicy, IEditingContextAccessPolicy editingContextAccessPolicy) {
        this.objectMapper = Objects.requireNonNull(objectMapper);
        this.projectAccessPolicy = Objects.requireNonNull(projectAccessPolicy);
        this.editingContextAccessPolicy = Objects.requireNonNull(editingContextAccessPolicy);
    }

    @Override
    public <T> T getInput(DataFetchingEnvironment environment, Class<T> inputType) {
        Object argument = environment.getArgument(MutationTypeProvider.INPUT_ARGUMENT);
        return this.objectMapper.convertValue(argument, inputType);
    }

    @Override
    public Optional<Principal> getPrincipal(DataFetchingEnvironment environment) {
        GraphQLContext graphQLContext = environment.getContext();
        // @formatter:off
        return graphQLContext.getOrEmpty(GraphQLConstants.PRINCIPAL)
                .filter(Principal.class::isInstance)
                .map(Principal.class::cast);
        // @formatter:on
    }

    @Override
    public String getSubscriptionId(DataFetchingEnvironment environment) {
        GraphQLContext graphQLContext = environment.getContext();
        return graphQLContext.getOrDefault(GraphQLConstants.SUBSCRIPTION_ID, ""); //$NON-NLS-1$
    }

    @Override
    public boolean canEditProject(DataFetchingEnvironment environment, UUID projectId) {
        // @formatter:off
        return this.getPrincipal(environment)
                .map(Principal::getName)
                .map(username -> this.projectAccessPolicy.canEdit(username, projectId))
                .orElse(Boolean.FALSE)
                .booleanValue();
        // @formatter:on
    }

    @Override
    public boolean canAdminProject(DataFetchingEnvironment environment, UUID projectId) {
        // @formatter:off
        return this.getPrincipal(environment)
                .map(Principal::getName)
                .map(username -> this.projectAccessPolicy.canAdmin(username, projectId))
                .orElse(Boolean.FALSE)
                .booleanValue();
        // @formatter:on
    }

    @Override
    public boolean canEditEditingContext(DataFetchingEnvironment environment, UUID editingContextId) {
        // @formatter:off
        return this.getPrincipal(environment)
                .map(Principal::getName)
                .map(username -> this.editingContextAccessPolicy.canEdit(username, editingContextId))
                .orElse(Boolean.FALSE)
                .booleanValue();
        // @formatter:on
    }

    @Override
    public boolean canAdminEditingContext(DataFetchingEnvironment environment, UUID editingContextId) {
        // @formatter:off
        return this.getPrincipal(environment)
                .map(Principal::getName)
                .map(username -> this.editingContextAccessPolicy.canAdmin(username, editingContextId))
                .orElse(Boolean.FALSE)
                .booleanValue();
        // @formatter:on
    }

}
