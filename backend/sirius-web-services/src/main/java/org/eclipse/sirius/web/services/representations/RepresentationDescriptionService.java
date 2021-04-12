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

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.eclipse.sirius.web.representations.IRepresentationDescription;
import org.eclipse.sirius.web.representations.VariableManager;
import org.eclipse.sirius.web.services.api.representations.IDynamicRepresentationDescriptionService;
import org.eclipse.sirius.web.services.api.representations.IRepresentationDescriptionService;

/**
 * Service used to query the representation descriptions available.
 *
 * @author sbegaudeau
 * @author hmarchadour
 */
public class RepresentationDescriptionService implements IRepresentationDescriptionService {

    private final RepresentationDescriptionRegistry registry;

    private final IDynamicRepresentationDescriptionService dynamicRepresentationDescriptionService;

    public RepresentationDescriptionService(RepresentationDescriptionRegistry registry, IDynamicRepresentationDescriptionService dynamicRepresentationDescriptionService) {
        this.registry = Objects.requireNonNull(registry);
        this.dynamicRepresentationDescriptionService = Objects.requireNonNull(dynamicRepresentationDescriptionService);
    }

    @Override
    public List<IRepresentationDescription> getRepresentationDescriptions(Object clazz) {
        List<IRepresentationDescription> result = new ArrayList<>();
        Map<UUID, IRepresentationDescription> allRepresentationDescriptions = this.getAllRepresentationDescriptions();
        for (IRepresentationDescription description : allRepresentationDescriptions.values()) {
            VariableManager variableManager = new VariableManager();
            variableManager.put(IRepresentationDescription.CLASS, clazz);
            Predicate<VariableManager> canCreatePredicate = description.getCanCreatePredicate();
            boolean canCreate = canCreatePredicate.test(variableManager);
            if (canCreate) {
                result.add(description);
            }
        }
        return result;
    }

    private Map<UUID, IRepresentationDescription> getAllRepresentationDescriptions() {
        Map<UUID, IRepresentationDescription> allRepresentationDescriptions = new LinkedHashMap<>();
        this.registry.getRepresentationDescriptions().forEach(representationDescription -> {
            allRepresentationDescriptions.put(representationDescription.getId(), representationDescription);
        });
        this.dynamicRepresentationDescriptionService.findDynamicRepresentationDescriptions(UUID.randomUUID()).forEach(representationDescription -> {
            // The dynamically discovered version wins over the version discovered on startup.
            allRepresentationDescriptions.put(representationDescription.getId(), representationDescription);
        });
        return allRepresentationDescriptions;
    }

    @Override
    public List<IRepresentationDescription> getRepresentationDescriptions() {
        return this.getAllRepresentationDescriptions().values().stream().collect(Collectors.toList());
    }

    @Override
    public Optional<IRepresentationDescription> findRepresentationDescriptionById(UUID id) {
        return Optional.ofNullable(this.getAllRepresentationDescriptions().get(id));
    }

}
