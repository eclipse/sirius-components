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
package org.eclipse.sirius.web.emf.services;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import java.util.UUID;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.sirius.web.services.api.document.DeleteDocumentInput;
import org.eclipse.sirius.web.services.api.document.Document;
import org.eclipse.sirius.web.services.api.document.IDocumentService;
import org.junit.Test;

import io.micrometer.core.instrument.simple.SimpleMeterRegistry;

/**
 * Unit tests of the delete document event handler.
 *
 * @author sbegaudeau
 */
public class DeleteDocumentEventHandlerTestCases {
    @Test
    public void testDeleteDocument() {
        Document document = new Document(UUID.randomUUID(), new org.eclipse.sirius.web.services.api.editingcontexts.EditingContext(UUID.randomUUID()), "name", "content"); //$NON-NLS-1$ //$NON-NLS-2$

        IDocumentService documentService = new NoOpDocumentService() {
            @Override
            public Optional<Document> getDocument(UUID documentId) {
                return Optional.of(document);
            }
        };
        DeleteDocumentEventHandler handler = new DeleteDocumentEventHandler(documentService, new NoOpProjectService(), new NoOpEMFMessageService(), new SimpleMeterRegistry());

        var input = new DeleteDocumentInput(document.getId());

        assertThat(handler.canHandle(input)).isTrue();

        AdapterFactoryEditingDomain editingDomain = new EditingDomainFactory().create();

        Resource resource = new SiriusWebJSONResourceFactoryImpl().createResource(URI.createURI(document.getId().toString()));
        editingDomain.getResourceSet().getResources().add(resource);
        EditingContext editingContext = new EditingContext(UUID.randomUUID(), editingDomain);

        handler.handle(editingContext, input);
        assertThat(editingDomain.getResourceSet().getResources().size()).isEqualTo(0);
    }
}
