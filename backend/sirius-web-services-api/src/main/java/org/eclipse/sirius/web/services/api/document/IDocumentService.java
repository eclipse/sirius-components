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

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.eclipse.sirius.web.collaborative.api.dto.Document;

/**
 * Interface of the service interacting with documents.
 *
 * @author hmarchadour
 */
public interface IDocumentService {

    String RESOURCE_KIND_JSON = "json"; //$NON-NLS-1$

    String RESOURCE_KIND_XMI = "xmi"; //$NON-NLS-1$

    Optional<Document> createDocument(UUID projectId, String name, String content);

    Optional<Document> getDocument(UUID documentId);

    Optional<Document> getDocument(UUID projectId, UUID documentId);

    List<Document> getDocuments(UUID projectId);

    void delete(UUID documentId);

    Optional<byte[]> getBytes(Document document, String resourceKind);

    Optional<Document> rename(UUID documentId, String newName);

}
