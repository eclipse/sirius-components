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
package org.eclipse.sirius.web.collaborative.selection.api;

import java.util.Objects;
import java.util.UUID;

import org.eclipse.sirius.web.collaborative.api.services.IRepresentationConfiguration;

/**
 * The configuration used to create a selection event processor.
 *
 * @author arichard
 */
public class SelectionConfiguration implements IRepresentationConfiguration {

    private final UUID id;

    private final UUID selectionId;

    private final String targetObjectId;

    public SelectionConfiguration(UUID id, UUID selectionId, String targetObjectId) {
        this.id = Objects.requireNonNull(id);
        this.selectionId = Objects.requireNonNull(selectionId);
        this.targetObjectId = Objects.requireNonNull(targetObjectId);
    }

    @Override
    public UUID getId() {
        return this.id;
    }

    public UUID getSelectionId() {
        return this.selectionId;
    }

    public String getTargetObjectId() {
        return this.targetObjectId;
    }

}
