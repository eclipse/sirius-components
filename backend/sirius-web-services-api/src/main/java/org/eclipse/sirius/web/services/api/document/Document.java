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
import java.util.UUID;

import org.eclipse.sirius.web.services.api.editingcontexts.EditingContext;

/**
 * Interface used by all documents.
 *
 * @author sbegaudeau
 */
public class Document {

    private UUID id;

    private EditingContext editingContext;

    private String name;

    private String content;

    public Document(UUID id, EditingContext editingContext, String name, String content) {
        this.id = Objects.requireNonNull(id);
        this.editingContext = Objects.requireNonNull(editingContext);
        this.name = Objects.requireNonNull(name);
        this.content = Objects.requireNonNull(content);
    }

    public UUID getId() {
        return this.id;
    }

    public EditingContext getEditingContext() {
        return this.editingContext;
    }

    public String getName() {
        return this.name;
    }

    public String getContent() {
        return this.content;
    }

    @Override
    public String toString() {
        String pattern = "{0} '{'id: {1}, editingContextId: {2}, name: {3}'}'"; //$NON-NLS-1$
        return MessageFormat.format(pattern, this.getClass().getSimpleName(), this.id, this.editingContext.getId(), this.name);
    }
}
