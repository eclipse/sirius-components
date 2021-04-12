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
package org.eclipse.sirius.web.services.api.projects;

import java.text.MessageFormat;
import java.util.UUID;

import org.eclipse.sirius.web.annotations.graphql.GraphQLField;
import org.eclipse.sirius.web.annotations.graphql.GraphQLID;
import org.eclipse.sirius.web.annotations.graphql.GraphQLInputObjectType;
import org.eclipse.sirius.web.annotations.graphql.GraphQLNonNull;
import org.eclipse.sirius.web.annotations.graphql.GraphQLUpload;
import org.eclipse.sirius.web.core.api.IInput;
import org.eclipse.sirius.web.core.api.UploadFile;

/**
 * The input object for the project upload mutation.
 *
 * @author gcoutable
 */
@GraphQLInputObjectType
public final class UploadProjectInput implements IInput {
    private UUID id;

    private UploadFile file;

    @Override
    @GraphQLID
    @GraphQLField
    @GraphQLNonNull
    public UUID getId() {
        return this.id;
    }

    @GraphQLUpload
    @GraphQLField
    @GraphQLNonNull
    public UploadFile getFile() {
        return this.file;
    }

    @Override
    public String toString() {
        String pattern = "{0}"; //$NON-NLS-1$
        return MessageFormat.format(pattern, this.getClass().getSimpleName());
    }
}
