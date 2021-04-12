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
package org.eclipse.sirius.web.core.api;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Interface for the edition of the domain objects.
 *
 * @author sbegaudeau
 */
public interface IEditService {

    Optional<Object> findClass(UUID editingContextId, String classId);

    List<Namespace> getNamespaces(UUID editingContextId);

    List<ChildCreationDescription> getRootCreationDescriptions(UUID editingContextId, String namespaceId, boolean suggested);

    List<ChildCreationDescription> getChildCreationDescriptions(UUID editingContextId, String classId);

    Optional<Object> createChild(IEditingContext editingContext, Object object, String childCreationDescriptionId);

    Optional<Object> createRootObject(IEditingContext editingContext, UUID documentId, String namespaceId, String rootObjectCreationDescriptionId);

    void delete(Object object);

    void editLabel(Object object, String labelField, String newValue);
}
