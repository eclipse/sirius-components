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
package org.eclipse.sirius.web.services.images;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.eclipse.sirius.web.emf.view.ICustomImagesContentService;
import org.eclipse.sirius.web.persistence.entities.CustomImageEntity;
import org.eclipse.sirius.web.persistence.repositories.ICustomImageRepository;
import org.springframework.stereotype.Service;

/**
 * Implementation of the service used to retrieve the content of custom images.
 *
 * @author pcdavid
 */
@Service
public class CustomImagesContentService implements ICustomImagesContentService {
    private final ICustomImageRepository customImageRepository;

    public CustomImagesContentService(ICustomImageRepository customImageRepository) {
        this.customImageRepository = Objects.requireNonNull(customImageRepository);
    }

    @Override
    public Optional<byte[]> getImageContentById(UUID id) {
        return this.customImageRepository.findById(id).map(CustomImageEntity::getContent);
    }

    @Override
    public Optional<String> getImageContentTypeById(UUID id) {
        return this.customImageRepository.findById(id).map(CustomImageEntity::getContentType);
    }
}
