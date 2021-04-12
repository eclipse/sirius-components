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
package org.eclipse.sirius.web.collaborative.api.services;

/**
 * Description of the changes performed by the event handler.
 *
 * @author sbegaudeau
 */
public final class ChangeKind {

    public static final String NOTHING = "NOTHING"; //$NON-NLS-1$

    public static final String REPRESENTATION_CREATION = "REPRESENTATION_CREATION"; //$NON-NLS-1$

    public static final String REPRESENTATION_DELETION = "REPRESENTATION_DELETION"; //$NON-NLS-1$

    public static final String REPRESENTATION_RENAMING = "REPRESENTATION_RENAMING"; //$NON-NLS-1$

    public static final String SEMANTIC_CHANGE = "SEMANTIC_CHANGE"; //$NON-NLS-1$

    public static final String FOCUS_CHANGE = "FOCUS_CHANGE"; //$NON-NLS-1$

    private ChangeKind() {
        // Prevent instantiation
    }
}
