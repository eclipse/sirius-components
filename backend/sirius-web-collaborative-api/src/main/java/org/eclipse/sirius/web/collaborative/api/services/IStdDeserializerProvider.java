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

import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

/**
 * Interface used to register deserializer.
 *
 * @param <T>
 *            The type returned by the deserializer
 * @author gcoutable
 */
public interface IStdDeserializerProvider<T> {

    StdDeserializer<T> getDeserializer();

    Class<T> getType();

}
