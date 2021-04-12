/*******************************************************************************
 * Copyright (c) 2019, 2021 Obeo.
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
package org.eclipse.sirius.web.services.representations;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Objects;

import org.eclipse.sirius.web.collaborative.api.dto.RepresentationDescriptor;
import org.eclipse.sirius.web.persistence.entities.ProjectEntity;
import org.eclipse.sirius.web.persistence.entities.RepresentationEntity;
import org.eclipse.sirius.web.representations.IRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Class used to convert representation entities to representation descriptor data transfer objects and vice versa.
 *
 * @author sbegaudeau
 */
public class RepresentationMapper {

    private final Logger logger = LoggerFactory.getLogger(RepresentationMapper.class);

    private final ObjectMapper objectMapper;

    public RepresentationMapper(ObjectMapper objectMapper) {
        this.objectMapper = Objects.requireNonNull(objectMapper);
    }

    public RepresentationDescriptor toDTO(RepresentationEntity representationEntity) {
        try {
            IRepresentation representation = this.objectMapper.readValue(representationEntity.getContent(), IRepresentation.class);
            // @formatter:off
            return RepresentationDescriptor.newRepresentationDescriptor(representationEntity.getId())
                    .label(representationEntity.getLabel())
                    .projectId(representationEntity.getProject().getId())
                    .descriptionId(representation.getDescriptionId())
                    .targetObjectId(representationEntity.getTargetObjectId())
                    .representation(representation)
                    .build();
            // @formatter:on
        } catch (JsonProcessingException exception) {
            this.logger.warn(exception.getMessage(), exception);
        }
        return null;
    }

    public RepresentationEntity toEntity(RepresentationDescriptor representationDescriptor, ProjectEntity projectEntity) {
        RepresentationEntity representationEntity = new RepresentationEntity();

        representationEntity.setId(representationDescriptor.getId());
        representationEntity.setProject(projectEntity);
        representationEntity.setLabel(representationDescriptor.getLabel());
        representationEntity.setTargetObjectId(representationDescriptor.getTargetObjectId());
        representationEntity.setContentType(representationDescriptor.getRepresentation().getClass().getSimpleName());
        try {
            String content = this.objectMapper.writeValueAsString(representationDescriptor.getRepresentation());
            representationEntity.setContent(content);
        } catch (JsonProcessingException exception) {
            this.logger.error(exception.getMessage(), exception);
        }

        return representationEntity;
    }

}
