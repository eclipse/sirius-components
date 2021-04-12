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
package org.eclipse.sirius.web.core.api;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.eclipse.sirius.web.representations.IRepresentationDescription;

/**
 * Service used to find the representation descriptions available.
 *
 * @author sbegaudeau
 */
public interface IRepresentationDescriptionSearchService {
    List<IRepresentationDescription> getRepresentationDescriptions(UUID editingContextId, Object clazz);

    List<IRepresentationDescription> getRepresentationDescriptions(UUID editingContextId);

    Optional<IRepresentationDescription> findRepresentationDescriptionById(UUID id);
}
