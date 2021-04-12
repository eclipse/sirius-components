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
package org.eclipse.sirius.web.spring.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.eclipse.sirius.web.collaborative.api.dto.Document;
import org.eclipse.sirius.web.services.api.document.IDocumentService;

/**
 * Implementation of the document service which does nothing at all.
 *
 * @author sbegaudeau
 */
public class NoOpDocumentService implements IDocumentService {

    @Override
    public Optional<Document> createDocument(UUID projectId, String name, String content) {
        return Optional.empty();
    }

    @Override
    public Optional<Document> getDocument(UUID documentId) {
        return Optional.empty();
    }

    @Override
    public Optional<Document> getDocument(UUID projectId, UUID documentId) {
        return Optional.empty();
    }

    @Override
    public List<Document> getDocuments(UUID projectId) {
        return new ArrayList<>();
    }

    @Override
    public void delete(UUID documentId) {
    }

    @Override
    public Optional<byte[]> getBytes(Document document, String resourceKind) {
        return Optional.empty();
    }

    @Override
    public Optional<Document> rename(UUID documentId, String newName) {
        return Optional.empty();
    }

}
