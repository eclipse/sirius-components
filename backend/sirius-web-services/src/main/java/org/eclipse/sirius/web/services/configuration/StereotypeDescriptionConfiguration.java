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
package org.eclipse.sirius.web.services.configuration;

import java.util.List;

import org.eclipse.sirius.web.api.configuration.IStereotypeDescriptionRegistryConfigurer;
import org.eclipse.sirius.web.services.stereotypes.StereotypeDescriptionRegistry;
import org.eclipse.sirius.web.services.stereotypes.StereotypeDescriptionSearchService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The configuration used to provide the services related to the stereotype descriptions.
 *
 * @author sbegaudeau
 */
@Configuration
public class StereotypeDescriptionConfiguration {
    @Bean
    public StereotypeDescriptionSearchService stereotypeDescriptionSearchService(List<IStereotypeDescriptionRegistryConfigurer> configurers) {
        StereotypeDescriptionRegistry registry = new StereotypeDescriptionRegistry();
        configurers.forEach(configurer -> configurer.addStereotypeDescriptions(registry));
        return new StereotypeDescriptionSearchService(registry);
    }
}
