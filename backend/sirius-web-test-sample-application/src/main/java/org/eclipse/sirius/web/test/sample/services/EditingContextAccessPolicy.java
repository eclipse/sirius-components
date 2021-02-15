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
package org.eclipse.sirius.web.test.sample.services;

import java.util.UUID;

import org.eclipse.sirius.web.services.api.editingcontexts.IEditingContextAccessPolicy;
import org.eclipse.sirius.web.services.api.projects.AccessLevel;
import org.springframework.stereotype.Service;

/**
 * The editing context access policy for Sirius Web.
 *
 * @author pcdavid
 */
@Service
public class EditingContextAccessPolicy implements IEditingContextAccessPolicy {
    @Override
    public AccessLevel getAccessLevel(String username, UUID editingContextId) {
        return AccessLevel.ADMIN;
    }
}
