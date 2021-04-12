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
package org.eclipse.sirius.web.spring.collaborative.diagrams.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.Optional;

import org.eclipse.sirius.web.collaborative.api.services.IRepresentationDeserializer;
import org.eclipse.sirius.web.diagrams.Diagram;
import org.eclipse.sirius.web.representations.IRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Used to deserialize a diagram.
 *
 * @author sbegaudeau
 */
@Service
public class DiagramDeserializer implements IRepresentationDeserializer {

    private final Logger logger = LoggerFactory.getLogger(DiagramDeserializer.class);

    @Override
    public boolean canHandle(ObjectNode root) {
        // @formatter:off
        return Optional.ofNullable(root.get("kind")) //$NON-NLS-1$
                .map(JsonNode::asText)
                .filter(Diagram.KIND::equals)
                .isPresent();
        // @formatter:on
    }

    @Override
    public Optional<IRepresentation> handle(ObjectMapper mapper, ObjectNode root) {
        try {
            return Optional.of(mapper.readValue(root.toString(), Diagram.class));
        } catch (JsonProcessingException exception) {
            this.logger.warn(exception.getMessage(), exception);
        }

        return Optional.empty();
    }

}
