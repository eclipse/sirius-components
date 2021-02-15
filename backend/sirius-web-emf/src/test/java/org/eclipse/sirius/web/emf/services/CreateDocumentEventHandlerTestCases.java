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

import org.assertj.core.api.Condition;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.sirius.web.api.configuration.StereotypeDescription;
import org.eclipse.sirius.web.emf.services.messages.IEMFMessageService;
import org.eclipse.sirius.web.services.api.document.CreateDocumentInput;
import org.eclipse.sirius.web.services.api.document.CreateDocumentSuccessPayload;
import org.eclipse.sirius.web.services.api.document.Document;
import org.eclipse.sirius.web.services.api.document.IDocumentService;
import org.eclipse.sirius.web.services.api.stereotypes.IStereotypeDescriptionService;
import org.junit.Test;

import io.micrometer.core.instrument.simple.SimpleMeterRegistry;

/**
 * Unit tests of the create document from stereotype event handler.
 *
 * @author sbegaudeau
 */
public class CreateDocumentEventHandlerTestCases {

    // @formatter:off
    private static final String CONTENT = "{" + System.lineSeparator() //$NON-NLS-1$
    + "    \"json\": {" + System.lineSeparator() //$NON-NLS-1$
    + "      \"version\": \"1.0\"," + System.lineSeparator() //$NON-NLS-1$
    + "    \"encoding\": \"utf-8\"" + System.lineSeparator() //$NON-NLS-1$
    + "  }," + System.lineSeparator() //$NON-NLS-1$
    + "  \"ns\": {" + System.lineSeparator() //$NON-NLS-1$
    + "      \"ecore\": \"http://www.eclipse.org/emf/2002/Ecore\"" + System.lineSeparator() //$NON-NLS-1$
    + "  }," + System.lineSeparator() //$NON-NLS-1$
    + "  \"content\": [" + System.lineSeparator() //$NON-NLS-1$
    + "      {" + System.lineSeparator() //$NON-NLS-1$
    + "        \"eClass\": \"ecore:EPackage\"," + System.lineSeparator() //$NON-NLS-1$
    + "      \"data\": {" + System.lineSeparator() //$NON-NLS-1$
    + "          \"name\": \"ecore\"," + System.lineSeparator() //$NON-NLS-1$
    + "        \"nsURI\": \"http://www.eclipse.org/emf/2002/Ecore\"," + System.lineSeparator() //$NON-NLS-1$
    + "        \"nsPrefix\": \"ecore\"," + System.lineSeparator() //$NON-NLS-1$
    + "        \"eClassifiers\": [" + System.lineSeparator() //$NON-NLS-1$
    + "            {" + System.lineSeparator() //$NON-NLS-1$
    + "              \"eClass\": \"ecore:EClass\"," + System.lineSeparator() //$NON-NLS-1$
    + "            \"data\": {" + System.lineSeparator() //$NON-NLS-1$
    + "                \"name\": \"AClass\"" + System.lineSeparator() //$NON-NLS-1$
    + "            }" + System.lineSeparator() //$NON-NLS-1$
    + "          }" + System.lineSeparator() //$NON-NLS-1$
    + "        ]" + System.lineSeparator() //$NON-NLS-1$
    + "      }" + System.lineSeparator() //$NON-NLS-1$
    + "    }" + System.lineSeparator() //$NON-NLS-1$
    + "  ]" + System.lineSeparator() //$NON-NLS-1$
    + "}" + System.lineSeparator(); //$NON-NLS-1$
    // @formatter:on

    private static final String DOCUMENT_NAME = "name"; //$NON-NLS-1$

    private static final String STEREOTYPE_DESCRIPTION_ID = "stereotypeDescriptionId"; //$NON-NLS-1$

    @Test
    public void testCreateDocument() {
        IDocumentService documentService = new NoOpDocumentService() {
            @Override
            public Optional<Document> createDocument(UUID projectId, String name, String content) {
                return Optional.of(new Document(UUID.randomUUID(), new org.eclipse.sirius.web.services.api.editingcontexts.EditingContext(UUID.randomUUID()), name, content));
            }
        };
        IStereotypeDescriptionService stereotypeDescriptionService = new NoOpStereotypeDescriptionService() {
            @Override
            public Optional<StereotypeDescription> getStereotypeDescriptionById(String stereotypeId) {
                StereotypeDescription stereotypeDescription = new StereotypeDescription(stereotypeId, "label", () -> CONTENT); //$NON-NLS-1$
                return Optional.of(stereotypeDescription);
            }
        };
        IEMFMessageService messageService = new NoOpEMFMessageService();

        CreateDocumentEventHandler handler = new CreateDocumentEventHandler(documentService, stereotypeDescriptionService, messageService, new SimpleMeterRegistry());
        var input = new CreateDocumentInput(UUID.randomUUID(), DOCUMENT_NAME, STEREOTYPE_DESCRIPTION_ID);

        assertThat(handler.canHandle(input)).isTrue();

        AdapterFactoryEditingDomain editingDomain = new EditingDomainFactory().create();
        EditingContext editingContext = new EditingContext(UUID.randomUUID(), editingDomain);

        handler.handle(editingContext, input);

        assertThat(editingDomain.getResourceSet().getResources().size()).isEqualTo(1);
        Condition<Object> condition = new Condition<>(adapter -> adapter instanceof DocumentMetadataAdapter, "has an DocumentMetadataAdapter"); //$NON-NLS-1$
        assertThat(editingDomain.getResourceSet().getResources().get(0).eAdapters()).areAtLeastOne(condition);
    }

    @Test
    public void testCreateTwoDocumentWithSameName() {
        IDocumentService documentService = new NoOpDocumentService() {
            @Override
            public Optional<Document> createDocument(UUID projectId, String name, String content) {
                return Optional.of(new Document(UUID.randomUUID(), new org.eclipse.sirius.web.services.api.editingcontexts.EditingContext(UUID.randomUUID()), name, content));
            }
        };
        IStereotypeDescriptionService stereotypeDescriptionService = new NoOpStereotypeDescriptionService() {
            @Override
            public Optional<StereotypeDescription> getStereotypeDescriptionById(String stereotypeId) {
                StereotypeDescription stereotypeDescription = new StereotypeDescription(stereotypeId, "label", () -> CONTENT); //$NON-NLS-1$
                return Optional.of(stereotypeDescription);
            }
        };
        IEMFMessageService messageService = new NoOpEMFMessageService();
        AdapterFactoryEditingDomain editingDomain = new EditingDomainFactory().create();
        EditingContext editingContext = new EditingContext(UUID.randomUUID(), editingDomain);

        CreateDocumentEventHandler handler = new CreateDocumentEventHandler(documentService, stereotypeDescriptionService, messageService, new SimpleMeterRegistry());

        var firstCreateInput = new CreateDocumentInput(editingContext.getId(), DOCUMENT_NAME, STEREOTYPE_DESCRIPTION_ID);
        assertThat(handler.canHandle(firstCreateInput)).isTrue();
        Object firstPayload = handler.handle(editingContext, firstCreateInput).getPayload();
        assertThat(firstPayload).isInstanceOf(CreateDocumentSuccessPayload.class);
        Document firstDocument = ((CreateDocumentSuccessPayload) firstPayload).getDocument();
        assertThat(firstDocument.getName()).isEqualTo(DOCUMENT_NAME);

        var secondCreatedInput = new CreateDocumentInput(editingContext.getId(), DOCUMENT_NAME, STEREOTYPE_DESCRIPTION_ID);
        assertThat(handler.canHandle(secondCreatedInput)).isTrue();
        Object secondPayload = handler.handle(editingContext, secondCreatedInput).getPayload();
        assertThat(secondPayload).isInstanceOf(CreateDocumentSuccessPayload.class);
        Document secondDocument = ((CreateDocumentSuccessPayload) secondPayload).getDocument();
        assertThat(secondDocument.getName()).isEqualTo(DOCUMENT_NAME);

        UUID firstDocumentId = firstDocument.getId();
        UUID secondDocumentId = secondDocument.getId();
        assertThat(firstDocumentId).isNotEqualTo(secondDocumentId);
    }
}
