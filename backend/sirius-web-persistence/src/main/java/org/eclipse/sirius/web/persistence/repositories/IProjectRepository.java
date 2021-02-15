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
package org.eclipse.sirius.web.persistence.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.eclipse.sirius.web.annotations.Audited;
import org.eclipse.sirius.web.persistence.entities.AccessLevelEntity;
import org.eclipse.sirius.web.persistence.entities.ProjectEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Persistence layer used to manipulate projects.
 * <p>
 * The actual definitions for the named queries referenced here must be available at runtime in a file at
 * <code>META-INF/jpa-named-queries.properties</code>. For the Sirius Web example application it is in Typically it is
 * defined in <code>sirius-web-sample-application/src/main/resources/META-INF/jpa-named-queries.properties</code>.
 *
 * @author sbegaudeau
 */
@Repository
public interface IProjectRepository extends PagingAndSortingRepository<ProjectEntity, UUID> {

    @Audited
    @Query(name = "Project.getUserAccessLevel", nativeQuery = true)
    AccessLevelEntity getUserAccessLevel(UUID projectId, String userName);

    @Audited
    @Query(name = "Project.existsByIdAndIsVisibleBy", nativeQuery = true)
    boolean existsByIdAndIsVisibleBy(UUID id, String username);

    @Audited
    @Query(name = "Project.findAllVisibleBy", nativeQuery = true)
    List<ProjectEntity> findAllVisibleBy(String username);

    @Audited
    @Query(name = "Project.findByIdIfVisibleBy", nativeQuery = true)
    Optional<ProjectEntity> findByIdIfVisibleBy(UUID projectId, String currentUsername);

    @Audited
    @Query(name = "Project.findByCurrentEditingContextIdIfVisibleBy", nativeQuery = true)
    Optional<ProjectEntity> findByCurrentEditingContextIfVisibleBy(UUID editingContextId, String currentUsername);

    @Audited
    @Override
    Optional<ProjectEntity> findById(UUID id);

    @Audited
    @Query(name = "Project.isOwner")
    boolean isOwner(String username, UUID projectId);

    @Audited
    @Override
    <S extends ProjectEntity> S save(S projectEntity);

    @Audited
    @Override
    void deleteById(UUID id);

}
