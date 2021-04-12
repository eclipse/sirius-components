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

import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.util.List;
import java.util.Objects;

import org.eclipse.sirius.web.collaborative.api.services.IRepresentationDeserializer;
import org.eclipse.sirius.web.collaborative.api.services.IStdDeserializerProvider;
import org.eclipse.sirius.web.representations.IRepresentation;
import org.springframework.stereotype.Service;

/**
 * The deserializer provider for {@link IRepresentation}.
 *
 * @author gcoutable
 */
@Service
public class RepresentationStdDeserializerProvider implements IStdDeserializerProvider<IRepresentation> {

    private final List<IRepresentationDeserializer> representationDeserializers;

    public RepresentationStdDeserializerProvider(List<IRepresentationDeserializer> representationDeserializers) {
        this.representationDeserializers = Objects.requireNonNull(representationDeserializers);
    }

    @Override
    public StdDeserializer<IRepresentation> getDeserializer() {
        return new RepresentationStdDeserializer(this.representationDeserializers);
    }

    @Override
    public Class<IRepresentation> getType() {
        return IRepresentation.class;
    }
}
