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

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.eclipse.sirius.web.emf.view.CustomImage;
import org.eclipse.sirius.web.emf.view.ICustomImagesService;
import org.eclipse.sirius.web.persistence.entities.CustomImageEntity;
import org.eclipse.sirius.web.persistence.repositories.ICustomImageRepository;
import org.springframework.stereotype.Service;

/**
 * Service used to manipulate custom images.
 *
 * @author pcdavid
 */
@Service
public class CustomImagesService implements ICustomImagesService {
    private final ICustomImageRepository customImageRepository;

    public CustomImagesService(ICustomImageRepository customImageRepository) {
        this.customImageRepository = Objects.requireNonNull(customImageRepository);
    }

    @Override
    public Optional<byte[]> getImageContentsById(UUID id) {
        return this.customImageRepository.findById(id).map(CustomImageEntity::getContent);
    }

    @Override
    public Optional<String> getImageContentsTypeById(UUID id) {
        return this.customImageRepository.findById(id).map(CustomImageEntity::getContentType);
    }

    @Override
    public List<CustomImage> getAvailableImages() {
        return this.customImageRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<CustomImage> findById(UUID id) {
        return this.customImageRepository.findById(id).map(this::toDTO);
    }

    private CustomImage toDTO(CustomImageEntity customImageEntity) {
        return new CustomImage(customImageEntity.getId(), customImageEntity.getLabel(), customImageEntity.getContentType());
    }

}
