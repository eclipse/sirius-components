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
package org.eclipse.sirius.web.services.api.projects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.eclipse.sirius.web.annotations.Immutable;
import org.eclipse.sirius.web.collaborative.api.dto.RepresentationDescriptor;
import org.eclipse.sirius.web.core.api.UploadFile;

/**
 * Data class for project that has been unzipped with {@link ProjectUnzipper}.
 *
 * @author gcoutable
 */
@Immutable
public class UnzippedProject {

    private String projectName;

    private List<RepresentationDescriptor> representationDescriptors = new ArrayList<>();

    private Map<String, UploadFile> documentIdToUploadFile = new HashMap<>();

    private ProjectManifest manifest;

    public String getProjectName() {
        return this.projectName;
    }

    public List<RepresentationDescriptor> getRepresentationDescriptors() {
        return this.representationDescriptors;
    }

    public Map<String, UploadFile> getDocumentIdToUploadFile() {
        return this.documentIdToUploadFile;
    }

    public ProjectManifest getManifest() {
        return this.manifest;
    }

    public static Builder newUnzippedProject(String projectName) {
        return new Builder(projectName);
    }

    /**
     * The builder used to create the {@link UnzippedProject}.
     *
     * @author gcoutable
     */
    @SuppressWarnings("checkstyle:HiddenField")
    public static final class Builder {
        private String projectName;

        private List<RepresentationDescriptor> representationDescriptors;

        private Map<String, UploadFile> documentIdToUploadFile;

        private ProjectManifest manifest;

        private Builder(String projectName) {
            this.projectName = Objects.requireNonNull(projectName);
        }

        public Builder representationDescriptors(List<RepresentationDescriptor> representationDescriptors) {
            this.representationDescriptors = Objects.requireNonNull(representationDescriptors);
            return this;
        }

        public Builder documentIdToUploadFile(Map<String, UploadFile> documentIdToUploadFile) {
            this.documentIdToUploadFile = Objects.requireNonNull(documentIdToUploadFile);
            return this;
        }

        public Builder projectManifest(ProjectManifest manifest) {
            this.manifest = Objects.requireNonNull(manifest);
            return this;
        }

        public UnzippedProject build() {
            UnzippedProject unzippedProject = new UnzippedProject();
            unzippedProject.projectName = Objects.requireNonNull(this.projectName);
            unzippedProject.representationDescriptors = Objects.requireNonNull(this.representationDescriptors);
            unzippedProject.documentIdToUploadFile = Objects.requireNonNull(this.documentIdToUploadFile);
            unzippedProject.manifest = Objects.requireNonNull(this.manifest);
            return unzippedProject;
        }
    }

}
