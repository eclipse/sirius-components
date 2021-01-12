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
package org.eclipse.sirius.web.services.api.document;

import java.text.MessageFormat;
import java.util.Objects;

import org.eclipse.sirius.web.annotations.graphql.GraphQLField;
import org.eclipse.sirius.web.annotations.graphql.GraphQLNonNull;
import org.eclipse.sirius.web.annotations.graphql.GraphQLObjectType;
import org.eclipse.sirius.web.core.api.IPayload;

/**
 * The payload of the create root object mutation.
 *
 * @author lfasani
 */
@GraphQLObjectType
public final class CreateRootObjectSuccessPayload implements IPayload {

    private final Object object;

    public CreateRootObjectSuccessPayload(Object object) {
        this.object = Objects.requireNonNull(object);
    }

    @GraphQLField
    @GraphQLNonNull
    public Object getObject() {
        return this.object;
    }

    @Override
    public String toString() {
        String pattern = "{0} '{''}'"; //$NON-NLS-1$
        return MessageFormat.format(pattern, this.getClass().getSimpleName());
    }
}
