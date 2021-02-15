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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.eclipse.sirius.web.core.api.IPayload;
import org.eclipse.sirius.web.services.api.projects.CreateProjectInput;
import org.eclipse.sirius.web.services.api.projects.IProjectService;
import org.eclipse.sirius.web.services.api.projects.Project;

/**
 * Implementation of the project service which does nothing.
 *
 * @author sbegaudeau
 */
public class NoOpProjectService implements IProjectService {

    @Override
    public Optional<Project> getProject(UUID projectId) {
        return Optional.empty();
    }

    @Override
    public Optional<Project> getProjectByCurrentEditingContextId(UUID editingContextId) {
        return Optional.empty();
    }

    @Override
    public List<Project> getProjects() {
        return new ArrayList<>();
    }

    @Override
    public IPayload createProject(CreateProjectInput input) {
        return null;
    }

    @Override
    public void delete(UUID projectId) {
        // do nothing
    }

    @Override
    public Optional<Project> renameProject(UUID projectId, String newName) {
        return Optional.empty();
    }

}
