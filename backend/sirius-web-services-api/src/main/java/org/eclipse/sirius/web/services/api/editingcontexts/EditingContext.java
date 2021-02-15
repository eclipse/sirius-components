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
package org.eclipse.sirius.web.services.api.editingcontexts;

import java.text.MessageFormat;
import java.util.Objects;
import java.util.UUID;

/**
 * DTO representing an Editing Context.
 *
 * @author pcdavid
 */
public class EditingContext {
    private final UUID id;

    public EditingContext(UUID id) {
        this.id = Objects.requireNonNull(id);
    }

    public UUID getId() {
        return this.id;
    }

    @Override
    public String toString() {
        String pattern = "{0} '{'id: {1} '}'"; //$NON-NLS-1$
        return MessageFormat.format(pattern, this.getClass().getSimpleName(), this.id);

    }
}
