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

/**
 * Interface of the collaborative diagram message service.
 *
 * @author sbegaudeau
 */
public interface ICollaborativeDiagramMessageService {

    String invalidInput(String expectedInputTypeName, String receivedInputTypeName);

    String edgeNotFound(String id);

    String nodeNotFound(String id);

    String deleteEdgeFailed(String id);

    String deleteNodeFailed(String id);

    String deleteFailed();

    String semanticObjectNotFound(String id);

    String nodeDescriptionNotFound(String id);

    String edgeDescriptionNotFound(String id);

    String invalidDrop();
}
