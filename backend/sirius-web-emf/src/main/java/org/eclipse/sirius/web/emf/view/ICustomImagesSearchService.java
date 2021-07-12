/*******************************************************************************
 * Copyright (c) 2021 Obeo.
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
package org.eclipse.sirius.web.emf.view;

import java.util.List;

/**
 * Service used to find existing custom images.
 *
 * @author pcdavid
 */
public interface ICustomImagesSearchService {
    List<CustomImage> getAvailableImages();

    /**
     * Implementation which does nothing, used for mocks in unit tests.
     *
     * @author pcdavid
     */
    class NoOp implements ICustomImagesSearchService {

        @Override
        public List<CustomImage> getAvailableImages() {
            return List.of();
        }

    }
}
