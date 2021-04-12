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

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import java.util.UUID;

import org.eclipse.sirius.web.collaborative.api.dto.Document;
import org.eclipse.sirius.web.services.api.accounts.Profile;
import org.eclipse.sirius.web.services.api.document.IDocumentService;
import org.eclipse.sirius.web.services.api.projects.Project;
import org.eclipse.sirius.web.services.api.projects.Visibility;
import org.junit.Test;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

/**
 * Tests of the document controller.
 *
 * @author sbegaudeau
 */
public class DocumentControllerTestCases {

    @Test
    public void testDocumentDoesNotExist() {
        IDocumentService noOpDocumentService = new NoOpDocumentService();
        DocumentController documentController = new DocumentController(noOpDocumentService);

        UUID projectId = UUID.randomUUID();
        UUID documentId = UUID.randomUUID();
        ResponseEntity<Resource> responseEntity = documentController.getDocument(projectId.toString(), documentId.toString());
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void testDocumentExist() {
        String name = "Document"; //$NON-NLS-1$

        IDocumentService documentService = new NoOpDocumentService() {
            @Override
            public Optional<Document> getDocument(UUID projectId, UUID documentId) {
                return Optional.of(new Document(documentId, new Project(projectId, "", new Profile(UUID.randomUUID(), "username"), Visibility.PUBLIC), name, null)); //$NON-NLS-1$ //$NON-NLS-2$
            }

            @Override
            public Optional<byte[]> getBytes(Document document, String resourceKind) {
                return Optional.of(new byte[] {});
            }
        };
        DocumentController documentController = new DocumentController(documentService);

        UUID projectId = UUID.randomUUID();
        UUID documentId = UUID.randomUUID();
        ResponseEntity<Resource> responseEntity = documentController.getDocument(projectId.toString(), documentId.toString());
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        HttpHeaders headers = responseEntity.getHeaders();
        assertThat(headers.getContentType()).isEqualTo(MediaType.APPLICATION_XML);
        assertThat(headers.getContentLength()).isEqualTo(0);
        assertThat(headers.getContentDisposition().getFilename()).isEqualTo(name);
    }
}
