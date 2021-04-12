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
package org.eclipse.sirius.web.collaborative.api.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.Optional;

import org.eclipse.sirius.web.representations.IRepresentation;

/**
 * Used to deserialize a representation with Jackson.
 *
 * @author sbegaudeau
 */
public interface IRepresentationDeserializer {

    boolean canHandle(ObjectNode root);

    Optional<IRepresentation> handle(ObjectMapper mapper, ObjectNode root);

}
