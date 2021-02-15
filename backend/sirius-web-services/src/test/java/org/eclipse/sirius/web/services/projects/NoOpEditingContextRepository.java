/*******************************************************************************
 * Copyright (c) 2021 Obeo.
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
package org.eclipse.sirius.web.services.projects;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.eclipse.sirius.web.persistence.entities.AccessLevelEntity;
import org.eclipse.sirius.web.persistence.entities.EditingContextEntity;
import org.eclipse.sirius.web.persistence.repositories.IEditingContextRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * Empty implementation of IEditingContextRepository, to serve a a base for testing.
 *
 * @author pcdavid
 */
public class NoOpEditingContextRepository implements IEditingContextRepository {

    @Override
    public Iterable<EditingContextEntity> findAll(Sort sort) {
        return List.of();
    }

    @Override
    public Page<EditingContextEntity> findAll(Pageable pageable) {
        return Page.empty();
    }

    @Override
    public <S extends EditingContextEntity> Iterable<S> saveAll(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public boolean existsById(UUID id) {
        return false;
    }

    @Override
    public Iterable<EditingContextEntity> findAll() {
        return List.of();
    }

    @Override
    public Iterable<EditingContextEntity> findAllById(Iterable<UUID> ids) {
        return List.of();
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void delete(EditingContextEntity entity) {
    }

    @Override
    public void deleteAll(Iterable<? extends EditingContextEntity> entities) {
    }

    @Override
    public void deleteAll() {
    }

    @Override
    public Optional<EditingContextEntity> findById(UUID id) {
        return Optional.empty();
    }

    @Override
    public <S extends EditingContextEntity> S save(S editingContextEntity) {
        return editingContextEntity;
    }

    @Override
    public void deleteById(UUID id) {
    }

    @Override
    public AccessLevelEntity getUserAccessLevel(UUID editingContextId, String userName) {
        return AccessLevelEntity.ADMIN;
    }

}
