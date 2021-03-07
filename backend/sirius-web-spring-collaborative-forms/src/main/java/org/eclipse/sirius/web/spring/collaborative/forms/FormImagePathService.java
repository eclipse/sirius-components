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
package org.eclipse.sirius.web.spring.collaborative.forms;

import java.util.List;

import org.eclipse.sirius.web.api.services.IImagePathService;

/**
 * Add the images folder.
 *
 * @author sbegaudeau
 */
public class FormImagePathService implements IImagePathService {
    @Override
    public List<String> getPaths() {
        return List.of("/form-images/"); //$NON-NLS-1$
    }
}
