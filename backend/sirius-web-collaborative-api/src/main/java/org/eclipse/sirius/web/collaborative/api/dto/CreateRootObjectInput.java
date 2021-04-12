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
package org.eclipse.sirius.web.collaborative.api.dto;

import java.text.MessageFormat;
import java.util.Objects;
import java.util.UUID;

import org.eclipse.sirius.web.annotations.graphql.GraphQLField;
import org.eclipse.sirius.web.annotations.graphql.GraphQLID;
import org.eclipse.sirius.web.annotations.graphql.GraphQLInputObjectType;
import org.eclipse.sirius.web.annotations.graphql.GraphQLNonNull;
import org.eclipse.sirius.web.core.api.IInput;

/**
 * The input object of the create root object mutation.
 *
 * @author lfasani
 */
@GraphQLInputObjectType
public final class CreateRootObjectInput implements IInput {
    private UUID id;

    private UUID editingContextId;

    private UUID documentId;

    private String namespaceId;

    private String rootObjectCreationDescriptionId;

    public CreateRootObjectInput() {
        // Used by Jackson
    }

    public CreateRootObjectInput(UUID id, UUID editingContextId, UUID documentId, String namespaceId, String rootObjectCreationDescriptionId) {
        this.id = Objects.requireNonNull(id);
        this.editingContextId = Objects.requireNonNull(editingContextId);
        this.documentId = Objects.requireNonNull(documentId);
        this.namespaceId = Objects.requireNonNull(namespaceId);
        this.rootObjectCreationDescriptionId = Objects.requireNonNull(rootObjectCreationDescriptionId);
    }

    @Override
    @GraphQLID
    @GraphQLField
    @GraphQLNonNull
    public UUID getId() {
        return this.id;
    }

    @GraphQLID
    @GraphQLField
    @GraphQLNonNull
    public UUID getEditingContextId() {
        return this.editingContextId;
    }

    @GraphQLID
    @GraphQLField
    @GraphQLNonNull
    public UUID getDocumentId() {
        return this.documentId;
    }

    @GraphQLID
    @GraphQLField
    @GraphQLNonNull
    public String getNamespaceId() {
        return this.namespaceId;
    }

    @GraphQLID
    @GraphQLField
    @GraphQLNonNull
    public String getRootObjectCreationDescriptionId() {
        return this.rootObjectCreationDescriptionId;
    }

    @Override
    public String toString() {
        String pattern = "{0} '{'id: {1}, editingContextId: {2}, documentId: {3}, namespaceId: {4}, rootObjectCreationDescriptionId: {5}'}'"; //$NON-NLS-1$
        return MessageFormat.format(pattern, this.getClass().getSimpleName(), this.id, this.editingContextId, this.documentId, this.namespaceId, this.rootObjectCreationDescriptionId);
    }
}
