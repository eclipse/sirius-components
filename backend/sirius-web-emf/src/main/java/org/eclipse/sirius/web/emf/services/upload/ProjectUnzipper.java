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
package org.eclipse.sirius.web.emf.services.upload;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.eclipse.sirius.web.core.api.UploadFile;
import org.eclipse.sirius.web.services.api.projects.ProjectManifest;
import org.eclipse.sirius.web.services.api.projects.UnzippedProject;
import org.eclipse.sirius.web.services.api.representations.RepresentationDescriptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class used to unzip a project and ease access to models, representations and manifest.json.
 *
 * @author gcoutable
 */
public class ProjectUnzipper {

    private static final String ZIP_FOLDER_SEPARATOR = "/"; //$NON-NLS-1$

    private static final String MANIFEST_JSON_FILE = "manifest.json"; //$NON-NLS-1$

    private static final String REPRESENTATIONS_FOLDER = "representations"; //$NON-NLS-1$

    private static final String DOCUMENTS_FOLDER = "documents"; //$NON-NLS-1$

    private final Logger logger = LoggerFactory.getLogger(ProjectUnzipper.class);

    private final InputStream inputStream;

    private final ObjectMapper objectMapper;

    public ProjectUnzipper(InputStream inputStream, ObjectMapper objectMapper) {
        this.inputStream = inputStream;
        this.objectMapper = objectMapper;
    }

    /**
     * Unzip the project.
     *
     * <p>
     * The zip must have the following structure:
     * <ul>
     * <li>Documents should be in [projectName]/documents/*</li>
     * <li>Representations should be in [projectName]/representations/*</li>
     * <li>The manifest.json should be in [projectName]/*</li>
     * </ul>
     * </p>
     *
     * @return <code>true</code> whether the unzip went well, <code>false</code> otherwise.
     */
    public Optional<UnzippedProject> unzipProject() {
        Optional<UnzippedProject> optionalUnzippedProject = Optional.empty();
        Map<String, ByteArrayOutputStream> zipEntryNameToContent = this.readZipFile();

        Optional<String> optionalProjectName = this.handleProjectName(zipEntryNameToContent);
        if (optionalProjectName.isPresent()) {
            String projectName = optionalProjectName.get();

            String documentsFolderInZip = projectName + ZIP_FOLDER_SEPARATOR + DOCUMENTS_FOLDER + ZIP_FOLDER_SEPARATOR;
            String representationsFolderInZip = projectName + ZIP_FOLDER_SEPARATOR + REPRESENTATIONS_FOLDER + ZIP_FOLDER_SEPARATOR;
            String manifestPathInZip = projectName + ZIP_FOLDER_SEPARATOR + MANIFEST_JSON_FILE;

            Optional<ProjectManifest> optionalManifest = this.getProjectManifest(zipEntryNameToContent, manifestPathInZip);
            Map<String, ByteArrayOutputStream> documentIdToDocumentContent = this.selectAndTransformIntoDocumentIdToDocumentContent(zipEntryNameToContent, documentsFolderInZip);
            List<ByteArrayOutputStream> representationDescritorsContent = this.selectAndTransformIntoRepresentationDescriptorsContent(zipEntryNameToContent, representationsFolderInZip);

            if (!optionalManifest.isEmpty() && this.validateDocuments(documentIdToDocumentContent, optionalManifest.get())) {
                ProjectManifest manifest = optionalManifest.get();

                Map<String, UploadFile> documentIdToUploadFile = this.getUploadFiles(documentIdToDocumentContent, manifest);

                List<RepresentationDescriptor> representationDescriptors;
                try {
                    representationDescriptors = this.getRepresentationDescriptors(representationDescritorsContent);
                    // @formatter:off
                    UnzippedProject unzippedProject = UnzippedProject.newUnzippedProject(projectName)
                            .projectManifest(manifest)
                            .documentIdToUploadFile(documentIdToUploadFile)
                            .representationDescriptors(representationDescriptors)
                            .build();
                    // @formatter:on

                    optionalUnzippedProject = Optional.of(unzippedProject);
                } catch (IOException e) {
                    this.logger.error(e.getMessage(), e);
                }
            }
        }

        return optionalUnzippedProject;
    }

    /**
     * Selects and transforms the given map of zip entry name to {@link RepresentationDescriptor} content.
     *
     * <p>
     * Select entries in the given map where the key represented by the zip entry name are in the representation folder
     * (start with representationsFolderInZip). For each remaining entries collect the values into a list.
     * </p>
     *
     * @param zipEntryNameToContent
     *            The map of all zip entries to their content
     * @param representationsFolderInZip
     *            The path of representations folder in zip
     * @return The list of {@link RepresentationDescriptor}
     */
    private List<ByteArrayOutputStream> selectAndTransformIntoRepresentationDescriptorsContent(Map<String, ByteArrayOutputStream> zipEntryNameToContent, String representationsFolderInZip) {
        // @formatter:off
        List<ByteArrayOutputStream> outputStreamOfRepresentationDescritors = zipEntryNameToContent.entrySet().stream()
                .filter(entry -> entry.getKey().startsWith(representationsFolderInZip))
                .map(Entry::getValue)
                .collect(Collectors.toList());
        // @formatter:on
        return outputStreamOfRepresentationDescritors;
    }

    /**
     * Selects and transforms the given map of zip entry name to zip entry content into a map of document id to document
     * content.
     *
     * <p>
     * Select entries in the given map where the key represented by the zip entry name are in the document folder (start
     * with documentsFolderInZip). For each remaining entries extract the document id by removing documentsFolderInZip
     * from the zip entry name.
     * </p>
     *
     * @param zipEntryNameToContent
     *            The map of all zip entries to their content
     * @param documentsFolderInZip
     *            The path of documents folder in zip
     * @return The map of document id to document content
     */
    private Map<String, ByteArrayOutputStream> selectAndTransformIntoDocumentIdToDocumentContent(Map<String, ByteArrayOutputStream> zipEntryNameToContent, String documentsFolderInZip) {
        Function<Entry<String, ByteArrayOutputStream>, String> mapZipEntryNameToDocumentId = e -> {
            String fullPath = e.getKey();
            String fileName = fullPath.substring(documentsFolderInZip.length());
            int extensionIndex = fileName.lastIndexOf('.');
            if (extensionIndex >= 0) {
                return fileName.substring(0, extensionIndex);
            } else {
                return fileName;
            }
        };

        // @formatter:off
        Map<String, ByteArrayOutputStream> documentIdToDocumentContent = zipEntryNameToContent.entrySet().stream()
                .filter(entry -> entry.getKey().startsWith(documentsFolderInZip))
                .collect(Collectors.toMap(mapZipEntryNameToDocumentId::apply, Entry::getValue));
        // @formatter:on
        return documentIdToDocumentContent;
    }

    /**
     * Validates all document id of the given map have a document name associated in the {@link ProjectManifest}.
     *
     * @param documentIdToDocumentContent
     *            The map of document id to their content
     * @param projectManifest
     *            The {@link ProjectManifest} holding a map of document id to document name
     * @return <code>true</code> whether all document id are associated with a document name thanks to the
     *         {@link ProjectManifest}, <code>false</code> otherwise
     */
    private boolean validateDocuments(Map<String, ByteArrayOutputStream> documentIdToDocumentContent, ProjectManifest projectManifest) {
        // @formatter:off
        return documentIdToDocumentContent.entrySet().stream()
                .map(Entry::getKey)
                .allMatch(projectManifest.getDocumentIdsToName()::containsKey);
        // @formatter:on
    }

    /**
     * Reads the zip file and returns the a map of zip entry name to zip entry content.
     *
     * @return The zip entry names mapped to its zip entry contents
     */
    private Map<String, ByteArrayOutputStream> readZipFile() {
        Map<String, ByteArrayOutputStream> entryToHandle = new HashMap<>();
        try (var zipperProjectInputStream = new ZipInputStream(this.inputStream)) {
            ZipEntry zipEntry = zipperProjectInputStream.getNextEntry();
            while (zipEntry != null) {
                if (!zipEntry.isDirectory()) {
                    String name = zipEntry.getName();
                    ByteArrayOutputStream entryBaos = new ByteArrayOutputStream();
                    zipperProjectInputStream.transferTo(entryBaos);
                    entryToHandle.put(name, entryBaos);
                }
                zipEntry = zipperProjectInputStream.getNextEntry();
            }
        } catch (IOException e) {
            this.logger.error(e.getMessage(), e);
        }

        return entryToHandle;
    }

    /**
     * Returns the project name if all zip entries represented by couple (zipEntry name -> OutputStream) in the given
     * {@link Map}, have their zip entry name starting by the project name, which should be the first segment of the
     * path of each zip entries.
     *
     * @param zipEntryToOutputStreams
     *            The map of zip entry name to the zip entry content
     * @return The name of the project
     */
    private Optional<String> handleProjectName(Map<String, ByteArrayOutputStream> zipEntryToOutputStreams) {
        Iterator<String> iterator = zipEntryToOutputStreams.keySet().iterator();
        if (!iterator.hasNext()) {
            // zip was empty
            return Optional.empty();
        }

        Optional<String> optionalProjectName = Optional.empty();
        String possibleProjectName = iterator.next().split(ZIP_FOLDER_SEPARATOR)[0];
        if (!possibleProjectName.isBlank() && zipEntryToOutputStreams.keySet().stream().allMatch(key -> key.split(ZIP_FOLDER_SEPARATOR)[0].equals(possibleProjectName))) {
            optionalProjectName = Optional.of(possibleProjectName);
        }

        return optionalProjectName;
    }

    /**
     * Transforms the map of documentId to document content, to a map of document id to {@link UploadFile}.
     *
     * @param documentIdToDocumentContent
     *            The map of document id to their content
     * @param projectManifest
     *            The project manifest holding the map of document id to document name
     * @return the transformed map of zip entry to document content, to the map of document id to {@link UploadFile}
     */
    private Map<String, UploadFile> getUploadFiles(Map<String, ByteArrayOutputStream> documentIdToDocumentContent, ProjectManifest projectManifest) {
        Map<String, UploadFile> documentIdToUploadFile = new HashMap<>();
        for (Entry<String, ByteArrayOutputStream> entry : documentIdToDocumentContent.entrySet()) {
            String documentId = entry.getKey();
            ByteArrayOutputStream outputStream = entry.getValue();
            String documentName = projectManifest.getDocumentIdsToName().get(documentId);
            UploadFile uploadFile = new UploadFile(documentName, new ByteArrayInputStream(outputStream.toByteArray()));
            documentIdToUploadFile.put(documentId, uploadFile);
        }
        return documentIdToUploadFile;
    }

    /**
     * Transforms the list of output stream that have been filtered previously to contains only output streams that can
     * be deserialized to {@link RepresentationDescriptor}.
     *
     * @param outputStreamToTransformToRepresentationDescriptor
     *            The list of output stream that can be deserialized into RepresentationDescriptors
     * @return The list of {@link RepresentationDescriptor}
     * @throws IOException
     *             If the deserialization has failed
     */
    private List<RepresentationDescriptor> getRepresentationDescriptors(List<ByteArrayOutputStream> outputStreamToTransformToRepresentationDescriptor) throws IOException {
        List<RepresentationDescriptor> representations = new ArrayList<>();
        for (ByteArrayOutputStream outputStream : outputStreamToTransformToRepresentationDescriptor) {
            byte[] representationDescriptorBytes = outputStream.toByteArray();
            RepresentationDescriptor representationDescriptor = this.objectMapper.readValue(representationDescriptorBytes, RepresentationDescriptor.class);
            representations.add(representationDescriptor);
        }
        return representations;
    }

    /**
     * Selects and deserializes the {@link ProjectManifest} from the given map thanks to the given manifest path in zip.
     *
     * @param zipEntryNameToContent
     *            The map of zipEntry name to their content
     * @param manifestPathInZip
     *            The path of the manifest file in zip
     * @return The {@link ProjectManifest} whether it is present in the given map and it has been deserialized
     *         successfully, {@link Optional#empty()} otherwise
     */
    private Optional<ProjectManifest> getProjectManifest(Map<String, ByteArrayOutputStream> zipEntryNameToContent, String manifestPathInZip) {
        Optional<ProjectManifest> optionalProjectManifest = Optional.empty();

        // @formatter:off
        byte[] manifestBytes = zipEntryNameToContent.entrySet().stream()
                .filter(e -> e.getKey().equals(manifestPathInZip))
                .map(Entry::getValue)
                .map(ByteArrayOutputStream::toByteArray)
                .findFirst()
                .orElse(new byte[0]);
        // @formatter:on
        if (manifestBytes.length <= 0) {
            return Optional.empty();
        }

        try {
            ProjectManifest projectManifest = this.objectMapper.readValue(manifestBytes, ProjectManifest.class);
            optionalProjectManifest = Optional.of(projectManifest);
        } catch (IOException e) {
            this.logger.error(e.getMessage(), e);
        }

        return optionalProjectManifest;
    }
}
