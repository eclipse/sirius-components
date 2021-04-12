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
package org.eclipse.sirius.web.services.api.events;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.eclipse.sirius.web.collaborative.api.dto.Document;

/**
 * An internal event indicating that a new version of a set of documents has been persisted.
 *
 * @author pcdavid
 */
public class DocumentsModifiedEvent {
    private final UUID projectId;

    private final List<Document> documents;

    public DocumentsModifiedEvent(UUID projectId, List<Document> documents) {
        this.projectId = Objects.requireNonNull(projectId);
        this.documents = List.copyOf(Objects.requireNonNull(documents));
    }

    public UUID getProjectId() {
        return this.projectId;
    }

    public List<Document> getDocuments() {
        return this.documents;
    }

}
