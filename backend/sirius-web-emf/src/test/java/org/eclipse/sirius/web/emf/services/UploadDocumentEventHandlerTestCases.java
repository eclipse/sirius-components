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
package org.eclipse.sirius.web.emf.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.sirius.emfjson.resource.JsonResource;
import org.eclipse.sirius.emfjson.resource.JsonResourceImpl;
import org.eclipse.sirius.web.core.api.IEditingContext;
import org.eclipse.sirius.web.core.api.UploadFile;
import org.eclipse.sirius.web.emf.services.messages.IEMFMessageService;
import org.eclipse.sirius.web.services.api.accounts.Profile;
import org.eclipse.sirius.web.services.api.document.Document;
import org.eclipse.sirius.web.services.api.document.IDocumentService;
import org.eclipse.sirius.web.services.api.document.UploadDocumentInput;
import org.eclipse.sirius.web.services.api.projects.Project;
import org.eclipse.sirius.web.services.api.projects.Visibility;
import org.junit.Test;

import io.micrometer.core.instrument.simple.SimpleMeterRegistry;

/**
 * Unit tests of the create document from upload event handler.
 *
 * @author sbegaudeau
 */
public class UploadDocumentEventHandlerTestCases {

    private static final String FILE_NAME = "name"; //$NON-NLS-1$

    private static final String PATH_TO_XMI_DOCUMENT = "test_import/document.xmi"; //$NON-NLS-1$

    // @formatter:off
    private static final String JSON_CONTENT = "{" + System.lineSeparator() //$NON-NLS-1$
    + "    \"json\": {" + System.lineSeparator() //$NON-NLS-1$
    + "      \"version\": \"1.0\"," + System.lineSeparator() //$NON-NLS-1$
    + "    \"encoding\": \"utf-8\"" + System.lineSeparator() //$NON-NLS-1$
    + "  }," + System.lineSeparator() //$NON-NLS-1$
    + "  \"ns\": {" + System.lineSeparator() //$NON-NLS-1$
    + "      \"ecore\": \"http://www.eclipse.org/emf/2002/Ecore\"" + System.lineSeparator() //$NON-NLS-1$
    + "  }," + System.lineSeparator() //$NON-NLS-1$
    + "  \"content\": [" + System.lineSeparator() //$NON-NLS-1$
    + "      {" + System.lineSeparator() //$NON-NLS-1$
    + "        \"id\": \"c7fb6833-1452-49bb-aa54-5d148925c2cb\"," + System.lineSeparator() //$NON-NLS-1$
    + "        \"eClass\": \"ecore:EPackage\"," + System.lineSeparator() //$NON-NLS-1$
    + "        \"data\": {" + System.lineSeparator() //$NON-NLS-1$
    + "          \"name\": \"ecore\"," + System.lineSeparator() //$NON-NLS-1$
    + "        \"nsURI\": \"http://www.eclipse.org/emf/2002/Ecore\"," + System.lineSeparator() //$NON-NLS-1$
    + "        \"nsPrefix\": \"ecore\"," + System.lineSeparator() //$NON-NLS-1$
    + "        \"eClassifiers\": [" + System.lineSeparator() //$NON-NLS-1$
    + "            {" + System.lineSeparator() //$NON-NLS-1$
    + "              \"id\": \"031e998a-26b8-4eb0-9f62-d31ad2f96ca3\"," + System.lineSeparator() //$NON-NLS-1$
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
    // @formatter:off

    private static final String XMI_CONTENT = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + System.lineSeparator() //$NON-NLS-1$
    + "<ecore:EPackage xmi:version=\"2.0\" xmlns:xmi=\"http://www.omg.org/XMI\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"" + System.lineSeparator() //$NON-NLS-1$
    + "    xmlns:ecore=\"http://www.eclipse.org/emf/2002/Ecore\" name=\"ecore\" nsURI=\"http://www.eclipse.org/emf/2002/Ecore\" nsPrefix=\"ecore\">" + System.lineSeparator() //$NON-NLS-1$
    + "  <eClassifiers xsi:type=\"ecore:EClass\" name=\"AClass\"/>" + System.lineSeparator() //$NON-NLS-1$
    + "</ecore:EPackage>"; //$NON-NLS-1$
    // @formatter:on

    @Test
    public void testUploadXMIDocument() {
        EditingDomain editingDomain = this.uploadDocument(XMI_CONTENT.getBytes());
        this.testUploadXMIDocument(editingDomain);
    }

    @Test
    public void testUploadXMIDocumentFromFile() throws UnsupportedEncodingException {
        String filePath = UploadDocumentEventHandlerTestCases.class.getClassLoader().getResource(PATH_TO_XMI_DOCUMENT).getFile();
        String decodedfilePath = URLDecoder.decode(filePath, StandardCharsets.UTF_8.name());
        File file = new File(decodedfilePath);
        assertThat(file.exists()).isTrue();
        EditingDomain editingDomain = this.uploadDocument(file);
        this.testUploadXMIDocument(editingDomain);
    }

    private void testUploadXMIDocument(EditingDomain editingDomain) {
        assertThat(editingDomain.getResourceSet().getResources().size()).isEqualTo(1);
        Resource res = editingDomain.getResourceSet().getResources().get(0);
        assertThat(res).isInstanceOf(JsonResourceImpl.class);
        assertThat(res.getContents()).hasSize(1);
        EObject root = res.getContents().get(0);
        assertThat(root).isInstanceOf(EPackage.class);
    }

    @Test
    public void testUploadEmptyDocument() {
        EditingDomain editingDomain = this.uploadDocument(new byte[0]);
        assertThat(editingDomain.getResourceSet().getResources().size()).isEqualTo(1);
        Resource res = editingDomain.getResourceSet().getResources().get(0);
        assertThat(res).isInstanceOf(JsonResourceImpl.class);
        assertThat(res.getContents()).hasSize(0);
    }

    private EditingDomain uploadDocument(byte[] contents) {
        return this.uploadDocument(new ByteArrayInputStream(contents));
    }

    private EditingDomain uploadDocument(File file) {
        try {
            return this.uploadDocument(new FileInputStream(file));
        } catch (FileNotFoundException exception) {
            fail(exception.getMessage());
        }
        return null;
    }

    private EditingDomain uploadDocument(InputStream inputstream) {
        IDocumentService documentService = new NoOpDocumentService() {
            @Override
            public Optional<Document> createDocument(UUID projectId, String name, String content) {
                return Optional.of(new Document(UUID.randomUUID(), new Project(projectId, "", new Profile(UUID.randomUUID(), "username"), Visibility.PUBLIC), name, content)); //$NON-NLS-1$ //$NON-NLS-2$
            }
        };
        IEMFMessageService messageService = new NoOpEMFMessageService();

        UploadDocumentEventHandler handler = new UploadDocumentEventHandler(documentService, messageService, new SimpleMeterRegistry());

        UploadFile file = new UploadFile(FILE_NAME, inputstream);
        var input = new UploadDocumentInput(UUID.randomUUID(), UUID.randomUUID(), file);

        assertThat(handler.canHandle(input)).isTrue();

        AdapterFactoryEditingDomain editingDomain = new EditingDomainFactory().create();

        IEditingContext editingContext = new EditingContext(UUID.randomUUID(), editingDomain);

        handler.handle(editingContext, input);
        return editingDomain;
    }

    /**
     * This method test the {@link UploadDocumentEventHandler} generates new IDs for objects loaded from
     * {@link UploadFile} to ensure there will be no ID conflict with existing elements.
     */
    @Test
    public void testEObjectIDGenerationForUpload() {
        AdapterFactoryEditingDomain editingDomain = new EditingDomainFactory().create();
        byte[] resourceBytes = JSON_CONTENT.getBytes();

        Map<String, String> eObjectUriToId = this.getEObjectUriToId(editingDomain, resourceBytes);

        final UUID documentId = UUID.randomUUID();
        this.simulatesDocumentUpload(editingDomain, documentId, resourceBytes);

        Resource resource = editingDomain.getResourceSet().getResource(URI.createURI(documentId.toString()), false);
        // Use an output stream to prevent the resource to be written on file system.
        try (var outputStream = new ByteArrayOutputStream()) {
            resource.save(outputStream, Collections.singletonMap(JsonResource.OPTION_ID_MANAGER, new EObjectIDManager() {
                @Override
                public String getOrCreateId(EObject eObject) {
                    // Should get the Id produced by the EObjectRandomIDSupplier during upload
                    String id = super.getOrCreateId(eObject);
                    String uriFragment = resource.getURIFragment(eObject);
                    // Use the map to get and check if, the id generate from the first load and the id generated during
                    // upload are different
                    String idBeforeUpload = eObjectUriToId.get(uriFragment);
                    assertThat(idBeforeUpload).isNotEqualTo(id);
                    return id;
                }
            }));
        } catch (IOException exception) {
            fail(exception.getMessage());
        }
    }

    /**
     * Simulates a document upload with fake services. The uploaded resource will be put in the given editing domain
     * with the documentId as URI.
     *
     * @param editingDomain
     *            The editing domain where the uploaded resource will be put
     * @param documentId
     *            The id of the document to upload
     * @param resourceBytes
     *            The content of the document to upload
     */
    private void simulatesDocumentUpload(AdapterFactoryEditingDomain editingDomain, UUID documentId, byte[] resourceBytes) {
        IDocumentService documentService = new NoOpDocumentService() {

            @Override
            public Optional<Document> createDocument(UUID projectId, String name, String content) {
                return Optional.of(new Document(documentId, new Project(projectId, "", new Profile(UUID.randomUUID(), "username"), Visibility.PUBLIC), name, content)); //$NON-NLS-1$ //$NON-NLS-2$
            }
        };
        IEMFMessageService messageService = new NoOpEMFMessageService();
        UploadDocumentEventHandler handler = new UploadDocumentEventHandler(documentService, messageService, new SimpleMeterRegistry());
        UploadFile file = new UploadFile(FILE_NAME, new ByteArrayInputStream(resourceBytes));

        var input = new UploadDocumentInput(UUID.randomUUID(), UUID.randomUUID(), file);

        assertThat(handler.canHandle(input)).isTrue();
        IEditingContext editingContext = new EditingContext(UUID.randomUUID(), editingDomain);

        handler.handle(editingContext, input);
    }

    /**
     * Loads a json resource with the {@link #JSON_CONTENT} to map the uriFragment of eObjects to the id supplied by
     * {@link EObjectIDManager}, then returns the map.
     */
    private Map<String, String> getEObjectUriToId(EditingDomain editingDomain, byte[] resourceBytes) {
        Map<String, String> eObjectUriToId = new HashMap<>();
        JsonResource jsonResource = new SiriusWebJSONResourceFactoryImpl().createResource(URI.createURI("json.flow")); //$NON-NLS-1$
        editingDomain.getResourceSet().getResources().add(jsonResource);

        Map<String, Object> options = new HashMap<>();
        options.put(JsonResource.OPTION_ID_MANAGER, new EObjectIDManager());
        try {
            // Load the resource to attach the contained element to the resource.
            ByteArrayInputStream inputStream = new ByteArrayInputStream(resourceBytes);
            jsonResource.load(inputStream, options);
            /*
             * Once the resource is loaded we cannot load it anymore, so we use the save to associate, in a map, the uri
             * fragment of eObject to theirs IDs that have been computed by the IDSupplier during the previous load.
             */
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            jsonResource.save(outputStream, Collections.singletonMap(JsonResource.OPTION_ID_MANAGER, new EObjectIDManager() {
                @Override
                public String getOrCreateId(EObject eObject) {
                    String id = super.getOrCreateId(eObject);
                    String uriFragment = jsonResource.getURIFragment(eObject);
                    eObjectUriToId.put(uriFragment, id);
                    return id;
                }
            }));
        } catch (IOException exception) {
            fail(exception.getMessage());
        }
        return eObjectUriToId;
    }
}
