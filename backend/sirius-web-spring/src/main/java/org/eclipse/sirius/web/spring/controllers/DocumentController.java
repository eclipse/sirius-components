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

import java.io.ByteArrayInputStream;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.eclipse.sirius.web.collaborative.api.dto.Document;
import org.eclipse.sirius.web.services.api.document.IDocumentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * The entry point of the HTTP API to download documents.
 * <p>
 * This endpoint will be available on the API base path prefix with download segment and followed by the document Id
 * used as a suffix. As such, users will be able to send document download request to the following URL:
 * </p>
 *
 * <pre>
 * PROTOCOL://DOMAIN.TLD(:PORT)/API_BASE_PATH/projects/PROJECT_ID/documents/DOCUMENT_ID
 * </pre>
 *
 * <p>
 * In a development environment, the URL will most likely be:
 * </p>
 *
 * <pre>
 * http://localhost:8080/api/projects/PROJECT_ID/documents/DOCUMENT_ID
 * </pre>
 *
 * <p>
 * Only documents of type xmi are supported.
 * </p>
 *
 * @author smonnier
 */
@Controller
@RequestMapping(URLConstants.DOCUMENT_BASE_PATH)
public class DocumentController {

    private final IDocumentService documentService;

    private final Logger logger = LoggerFactory.getLogger(DocumentController.class);

    public DocumentController(IDocumentService documentService) {
        this.documentService = Objects.requireNonNull(documentService);
    }

    @GetMapping(path = "/{documentId}")
    @ResponseBody
    public ResponseEntity<Resource> getDocument(@PathVariable String projectId, @PathVariable String documentId) {
        var optionalProjectId = this.convertToUUID(projectId);
        var optionalDocumentId = this.convertToUUID(documentId);
        Optional<Document> optionalDocument = optionalProjectId.flatMap(pId -> {
            return optionalDocumentId.flatMap(dId -> this.documentService.getDocument(pId, dId));
        });

        if (optionalDocument.isPresent()) {
            Document document = optionalDocument.get();
            Optional<byte[]> optionalBytes = this.documentService.getBytes(document, IDocumentService.RESOURCE_KIND_XMI);
            if (optionalBytes.isPresent()) {
                byte[] bytes = optionalBytes.get();

                // @formatter:off
                ContentDisposition contentDisposition = ContentDisposition.builder("attachment")  //$NON-NLS-1$
                        .filename(document.getName())
                        .build();
                // @formatter:on

                HttpHeaders headers = new HttpHeaders();
                headers.setContentDisposition(contentDisposition);
                headers.setContentType(MediaType.APPLICATION_XML);
                headers.setContentLength(bytes.length);
                InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(bytes));
                return new ResponseEntity<>(resource, headers, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    private Optional<UUID> convertToUUID(String id) {
        Optional<UUID> optionalId = Optional.empty();
        try {
            optionalId = Optional.of(UUID.fromString(id));
        } catch (IllegalArgumentException exception) {
            this.logger.error(exception.getMessage(), exception);
        }
        return optionalId;
    }

}
