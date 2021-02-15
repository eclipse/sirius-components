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
import org.eclipse.sirius.web.core.api.IEditingContext;
import org.eclipse.sirius.web.core.api.IInput;
import org.eclipse.sirius.web.services.api.document.Document;
import org.eclipse.sirius.web.services.api.document.RenameDocumentInput;
import org.junit.Test;

import io.micrometer.core.instrument.simple.SimpleMeterRegistry;

/**
 * Unit tests of the rename document event handler.
 *
 * @author fbarbin
 */
public class RenameDocumentEventHandlerTestCases {
    private static final String OLD_NAME = "oldName"; //$NON-NLS-1$

    private static final String NEW_NAME = "newName"; //$NON-NLS-1$

    @Test
    public void testRenameDocument() {
        NoOpDocumentService noOpDocumentService = new NoOpDocumentService() {
            @Override
            public Optional<Document> rename(UUID documentId, String newName) {
                return Optional.of(new Document(documentId, new org.eclipse.sirius.web.services.api.editingcontexts.EditingContext(UUID.randomUUID()), newName, "noContent")); //$NON-NLS-1$
            }
        };
        RenameDocumentEventHandler handler = new RenameDocumentEventHandler(noOpDocumentService, new NoOpEMFMessageService(), new SimpleMeterRegistry());

        UUID documentId = UUID.randomUUID();
        IInput input = new RenameDocumentInput(documentId, NEW_NAME);

        assertThat(handler.canHandle(input)).isTrue();

        AdapterFactoryEditingDomain editingDomain = new EditingDomainFactory().create();

        DocumentMetadataAdapter adapter = new DocumentMetadataAdapter(OLD_NAME);
        Resource resource = new SiriusWebJSONResourceFactoryImpl().createResource(URI.createURI(documentId.toString()));
        resource.eAdapters().add(adapter);
        editingDomain.getResourceSet().getResources().add(resource);

        assertThat(editingDomain.getResourceSet().getResources().size()).isEqualTo(1);
        assertThat(adapter.getName()).isEqualTo(OLD_NAME);

        IEditingContext editingContext = new EditingContext(UUID.randomUUID(), editingDomain);

        handler.handle(editingContext, input);

        assertThat(editingDomain.getResourceSet().getResources().size()).isEqualTo(1);
        assertThat(adapter.getName()).isEqualTo(NEW_NAME);
    }
}
