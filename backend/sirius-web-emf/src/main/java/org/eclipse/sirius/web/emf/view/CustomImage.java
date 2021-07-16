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
package org.eclipse.sirius.web.emf.view;

import java.text.MessageFormat;
import java.util.Objects;
import java.util.UUID;

/**
 * Lightweight DTO representing a custom image (without the content).
 *
 * @author pcdavid
 */
public class CustomImage {
    private UUID id;

    private String label;

    private String contentType;

    public CustomImage(UUID id, String label, String contentType) {
        this.id = Objects.requireNonNull(id);
        this.label = Objects.requireNonNull(label);
        this.contentType = Objects.requireNonNull(contentType);
    }

    public UUID getId() {
        return this.id;
    }

    public String getLabel() {
        return this.label;
    }

    public String getContentType() {
        return this.contentType;
    }

    @Override
    public String toString() {
        String pattern = "{0} '{' id: {1}, label: {2}, contentType: {3} '}'"; //$NON-NLS-1$
        return MessageFormat.format(pattern, this.getClass().getSimpleName(), this.id, this.label, this.contentType);
    }
}
