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
 * The input object of the create document mutation.
 *
 * @author sbegaudeau
 */
@GraphQLInputObjectType
public final class CreateDocumentInput implements IInput {
    private UUID id;

    private UUID editingContextId;

    private String name;

    private UUID stereotypeDescriptionId;

    public CreateDocumentInput() {
        // Used by Jackson
    }

    public CreateDocumentInput(UUID id, UUID editingContextId, String name, UUID stereotypeDescriptionId) {
        this.id = Objects.requireNonNull(id);
        this.editingContextId = Objects.requireNonNull(editingContextId);
        this.name = Objects.requireNonNull(name);
        this.stereotypeDescriptionId = Objects.requireNonNull(stereotypeDescriptionId);
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

    @GraphQLField
    @GraphQLNonNull
    public String getName() {
        return this.name;
    }

    @GraphQLID
    @GraphQLField
    @GraphQLNonNull
    public UUID getStereotypeDescriptionId() {
        return this.stereotypeDescriptionId;
    }

    @Override
    public String toString() {
        String pattern = "{0} '{'id: {1}, editingContextId: {2}, name: {3}, stereotypeDescriptionId: {4}'}'"; //$NON-NLS-1$
        return MessageFormat.format(pattern, this.getClass().getSimpleName(), this.id, this.editingContextId, this.name, this.stereotypeDescriptionId);
    }
}
