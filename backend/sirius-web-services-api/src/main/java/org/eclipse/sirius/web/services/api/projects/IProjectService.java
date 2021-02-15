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

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.eclipse.sirius.web.core.api.IPayload;

/**
 * Interface of the service used to manipulate projects.
 *
 * @author sbegaudeau
 */
public interface IProjectService {

    Optional<Project> getProject(UUID projectId);

    Optional<Project> getProjectByCurrentEditingContextId(UUID editingContextId);

    List<Project> getProjects();

    IPayload createProject(CreateProjectInput input);

    void delete(UUID projectId);

    Optional<Project> renameProject(UUID projectId, String newName);

}
