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

import org.eclipse.sirius.web.persistence.entities.AccessLevelEntity;
import org.eclipse.sirius.web.persistence.entities.ProjectEntity;
import org.eclipse.sirius.web.persistence.repositories.IProjectRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * Implementation of the project repository which does nothing.
 *
 * @author sbegaudeau
 */
public class NoOpProjectRepository implements IProjectRepository {

    @Override
    public Iterable<ProjectEntity> findAll(Sort sort) {
        return new ArrayList<>();
    }

    @Override
    public Page<ProjectEntity> findAll(Pageable pageable) {
        return Page.empty();
    }

    @Override
    public <S extends ProjectEntity> S save(S entity) {
        return entity;
    }

    @Override
    public <S extends ProjectEntity> Iterable<S> saveAll(Iterable<S> entities) {
        return entities;
    }

    @Override
    public Optional<ProjectEntity> findById(UUID id) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(UUID id) {
        return false;
    }

    @Override
    public Iterable<ProjectEntity> findAllById(Iterable<UUID> ids) {
        return new ArrayList<>();
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(UUID id) {
    }

    @Override
    public void delete(ProjectEntity entity) {
    }

    @Override
    public void deleteAll(Iterable<? extends ProjectEntity> entities) {
    }

    @Override
    public void deleteAll() {
    }

    @Override
    public List<ProjectEntity> findAll() {
        return new ArrayList<>();
    }

    @Override
    public boolean existsByIdAndIsVisibleBy(UUID id, String userName) {
        return false;
    }

    @Override
    public List<ProjectEntity> findAllVisibleBy(String userName) {
        return new ArrayList<>();
    }

    @Override
    public Optional<ProjectEntity> findByIdIfVisibleBy(UUID projectId, String currentUserName) {
        return Optional.empty();
    }

    @Override
    public boolean isOwner(String username, UUID projectId) {
        return false;
    }

    @Override
    public AccessLevelEntity getUserAccessLevel(UUID projectId, String userName) {
        return AccessLevelEntity.READ;
    }

    @Override
    public Optional<ProjectEntity> findByCurrentEditingContextIfVisibleBy(UUID editingContextId, String currentUsername) {
        return Optional.empty();
    }
}
