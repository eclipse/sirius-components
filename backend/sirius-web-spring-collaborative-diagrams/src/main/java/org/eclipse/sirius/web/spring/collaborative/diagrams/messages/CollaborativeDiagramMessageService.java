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
package org.eclipse.sirius.web.spring.collaborative.diagrams.messages;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Service;

/**
 * Implementation of the collaborative diagram message service.
 *
 * @author sbegaudeau
 */
@Service
public class CollaborativeDiagramMessageService implements ICollaborativeDiagramMessageService {
    private final MessageSourceAccessor messageSourceAccessor;

    public CollaborativeDiagramMessageService(@Qualifier("collaborativeDiagramMessageSourceAccessor") MessageSourceAccessor messageSourceAccessor) {
        this.messageSourceAccessor = Objects.requireNonNull(messageSourceAccessor);
    }

    @Override
    public String invalidInput(String expectedInputTypeName, String receivedInputTypeName) {
        return this.messageSourceAccessor.getMessage(MessageConstants.INVALID_INPUT, new Object[] { expectedInputTypeName, receivedInputTypeName });
    }

    @Override
    public String edgeNotFound(String id) {
        return this.messageSourceAccessor.getMessage(MessageConstants.EDGE_NOT_FOUND, new Object[] { id });
    }

    @Override
    public String nodeNotFound(String id) {
        return this.messageSourceAccessor.getMessage(MessageConstants.NODE_NOT_FOUND, new Object[] { id });
    }

    @Override
    public String semanticObjectNotFound(String id) {
        return this.messageSourceAccessor.getMessage(MessageConstants.SEMANTIC_OBJECT_NOT_FOUND, new Object[] { id });
    }

    @Override
    public String nodeDescriptionNotFound(String id) {
        return this.messageSourceAccessor.getMessage(MessageConstants.NODE_DESCRIPTION_NOT_FOUND, new Object[] { id });
    }

    @Override
    public String edgeDescriptionNotFound(String id) {
        return this.messageSourceAccessor.getMessage(MessageConstants.EDGE_DESCRIPTION_NOT_FOUND, new Object[] { id });
    }

    @Override
    public String deleteEdgeFailed(String id) {
        return this.messageSourceAccessor.getMessage(MessageConstants.DELETE_EGDE_FAILED, new Object[] { id });
    }

    @Override
    public String deleteNodeFailed(String id) {
        return this.messageSourceAccessor.getMessage(MessageConstants.DELETE_NODE_FAILED, new Object[] { id });
    }

    @Override
    public String deleteFailed() {
        return this.messageSourceAccessor.getMessage(MessageConstants.DELETE_FAILED);
    }

    @Override
    public String invalidDrop() {
        return this.messageSourceAccessor.getMessage(MessageConstants.INVALID_DROP);
    }
}
