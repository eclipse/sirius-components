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
import org.eclipse.sirius.web.annotations.graphql.GraphQLUpload;
import org.eclipse.sirius.web.core.api.IInput;
import org.eclipse.sirius.web.core.api.UploadFile;

/**
 * The input object for the upload document mutation.
 *
 * @author hmarchadour
 */
@GraphQLInputObjectType
public final class UploadDocumentInput implements IInput {

    private final UUID id;

    private final UUID projectId;

    private final UploadFile file;

    public UploadDocumentInput(UUID id, UUID projectId, UploadFile file) {
        this.id = Objects.requireNonNull(id);
        this.projectId = Objects.requireNonNull(projectId);
        this.file = Objects.requireNonNull(file);
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
    public UUID getProjectId() {
        return this.projectId;
    }

    @GraphQLUpload
    @GraphQLField
    @GraphQLNonNull
    public UploadFile getFile() {
        return this.file;
    }

    @Override
    public String toString() {
        String pattern = "{0} '{'id: {1}, projectId: {2}, file: '{'name: {3}'}''}'"; //$NON-NLS-1$
        return MessageFormat.format(pattern, this.getClass().getSimpleName(), this.id, this.projectId, this.file.getName());
    }

}
