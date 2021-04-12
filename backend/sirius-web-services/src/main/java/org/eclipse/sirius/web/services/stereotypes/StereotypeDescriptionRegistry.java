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
package org.eclipse.sirius.web.services.stereotypes;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.eclipse.sirius.web.api.configuration.IStereotypeDescriptionRegistry;
import org.eclipse.sirius.web.api.configuration.StereotypeDescription;

/**
 * Registry containing all the stereotype descriptions.
 *
 * @author sbegaudeau
 */
public class StereotypeDescriptionRegistry implements IStereotypeDescriptionRegistry {
    private final Map<UUID, StereotypeDescription> id2stereotypeDescriptions = new LinkedHashMap<>();

    @Override
    public void add(StereotypeDescription stereotypeDescription) {
        this.id2stereotypeDescriptions.put(stereotypeDescription.getId(), stereotypeDescription);
    }

    public Optional<StereotypeDescription> getStereotypeDescription(UUID id) {
        return Optional.ofNullable(this.id2stereotypeDescriptions.get(id));
    }

    public List<StereotypeDescription> getStereotypeDescriptions() {
        return this.id2stereotypeDescriptions.values().stream().collect(Collectors.toUnmodifiableList());
    }

}
