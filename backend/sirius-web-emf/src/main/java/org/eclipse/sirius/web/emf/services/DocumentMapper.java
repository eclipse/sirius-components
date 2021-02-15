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
package org.eclipse.sirius.web.emf.services;

import org.eclipse.sirius.web.persistence.entities.DocumentEntity;
import org.eclipse.sirius.web.persistence.entities.EditingContextEntity;
import org.eclipse.sirius.web.services.api.document.Document;
import org.eclipse.sirius.web.services.api.editingcontexts.EditingContext;

/**
 * Class used to convert document entities to document data transfer objects and vice versa.
 *
 * @author sbegaudeau
 */
public class DocumentMapper {
    public Document toDTO(DocumentEntity documentEntity) {
        EditingContextEntity editingContextEntity = documentEntity.getEditingContext();

        EditingContext editingContext = new EditingContext(editingContextEntity.getId());
        return new Document(documentEntity.getId(), editingContext, documentEntity.getName(), documentEntity.getContent());
    }
}
