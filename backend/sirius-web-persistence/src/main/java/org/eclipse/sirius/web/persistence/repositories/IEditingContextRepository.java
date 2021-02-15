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
package org.eclipse.sirius.web.persistence.repositories;

import java.util.Optional;
import java.util.UUID;

import org.eclipse.sirius.web.annotations.Audited;
import org.eclipse.sirius.web.persistence.entities.AccessLevelEntity;
import org.eclipse.sirius.web.persistence.entities.EditingContextEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Persistence layer to manipulate editing contexts.
 *
 * @author pcdavid
 */
@Repository
public interface IEditingContextRepository extends PagingAndSortingRepository<EditingContextEntity, UUID> {
    @Audited
    @Query(name = "EditingContext.getUserAccessLevel", nativeQuery = true)
    AccessLevelEntity getUserAccessLevel(UUID editingContextId, String userName);

    @Audited
    @Override
    Optional<EditingContextEntity> findById(UUID id);

    @Audited
    @Override
    <S extends EditingContextEntity> S save(S editingContextEntity);

    @Audited
    @Override
    void deleteById(UUID id);
}
