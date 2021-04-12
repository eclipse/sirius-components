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
package org.eclipse.sirius.web.services.representations;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import org.eclipse.sirius.web.collaborative.api.services.IRepresentationDeserializer;
import org.eclipse.sirius.web.representations.IRepresentation;

/**
 * Custom deserializer to customize the ObjectMapper.
 *
 * @author gcoutable
 */
public class RepresentationStdDeserializer extends StdDeserializer<IRepresentation> {

    private static final long serialVersionUID = -1759653601179599083L;

    private final List<IRepresentationDeserializer> representationDeserializers;

    public RepresentationStdDeserializer(List<IRepresentationDeserializer> representationDeserializers) {
        this(null, representationDeserializers);
    }

    public RepresentationStdDeserializer(Class<?> valueClass, List<IRepresentationDeserializer> representationDeserializers) {
        super(valueClass);
        this.representationDeserializers = Objects.requireNonNull(representationDeserializers);
    }

    @Override
    public IRepresentation deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException {
        IRepresentation representation = null;
        ObjectCodec objectCodec = jsonParser.getCodec();
        if (objectCodec instanceof ObjectMapper) {
            ObjectMapper mapper = (ObjectMapper) objectCodec;
            ObjectNode root = mapper.readTree(jsonParser);

            // @formatter:off
            representation = this.representationDeserializers.stream()
                    .filter(representationDeserializer -> representationDeserializer.canHandle(root))
                    .findFirst()
                    .flatMap(representationDeserializer -> representationDeserializer.handle(mapper, root))
                    .orElse(null);
            // @formatter:on
        }
        return representation;
    }
}
