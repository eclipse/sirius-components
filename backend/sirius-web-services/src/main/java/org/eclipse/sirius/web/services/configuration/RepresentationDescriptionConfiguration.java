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
package org.eclipse.sirius.web.services.configuration;

import java.util.List;
import java.util.Objects;

import org.eclipse.sirius.web.api.configuration.IRepresentationDescriptionRegistryConfigurer;
import org.eclipse.sirius.web.services.api.representations.IDynamicRepresentationDescriptionService;
import org.eclipse.sirius.web.spring.collaborative.representations.RepresentationDescriptionRegistry;
import org.eclipse.sirius.web.spring.collaborative.representations.RepresentationDescriptionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The configuration used to provide the services related to the representation descriptions.
 *
 * @author sbegaudeau
 */
@Configuration
public class RepresentationDescriptionConfiguration {
    private final IDynamicRepresentationDescriptionService dynamicRepresentationDescriptionService;

    public RepresentationDescriptionConfiguration(IDynamicRepresentationDescriptionService dynamicRepresentationDescriptionService) {
        this.dynamicRepresentationDescriptionService = Objects.requireNonNull(dynamicRepresentationDescriptionService);
    }

    @Bean
    public RepresentationDescriptionService representationDescriptionService(List<IRepresentationDescriptionRegistryConfigurer> configurers) {
        RepresentationDescriptionRegistry registry = new RepresentationDescriptionRegistry();
        configurers.forEach(configurer -> configurer.addRepresentationDescriptions(registry));
        return new RepresentationDescriptionService(registry, this.dynamicRepresentationDescriptionService);
    }
}
