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
package org.eclipse.sirius.web.emf.compatibility.diagrams;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.eclipse.sirius.web.core.api.ChildCreationDescription;
import org.eclipse.sirius.web.core.api.IEditService;
import org.eclipse.sirius.web.core.api.IEditingContext;
import org.eclipse.sirius.web.core.api.Namespace;

/**
 * Implementation of the edit service which does nothing.
 *
 * @author sbegaudeau
 */
public class NoOpEditService implements IEditService {

    @Override
    public Optional<Object> findClass(UUID editingContextId, String classId) {
        return Optional.empty();
    }

    @Override
    public List<ChildCreationDescription> getChildCreationDescriptions(UUID editingContextId, String classId) {
        return new ArrayList<>();
    }

    @Override
    public Optional<Object> createChild(IEditingContext editingContext, Object object, String childCreationDescriptionId) {
        return Optional.empty();
    }

    @Override
    public void delete(Object object) {
        // Do nothing
    }

    @Override
    public List<Namespace> getNamespaces(UUID editingContextId) {
        return new ArrayList<>();
    }

    @Override
    public List<ChildCreationDescription> getRootCreationDescriptions(UUID editingContextId, String namespaceId, boolean suggested) {
        return new ArrayList<>();
    }

    @Override
    public Optional<Object> createRootObject(IEditingContext editingContext, UUID documentId, String namespaceId, String rootObjectCreationDescriptionId) {
        return Optional.empty();
    }

    @Override
    public void editLabel(Object object, String labelField, String newValue) {
        // Do nothing
    }
}
