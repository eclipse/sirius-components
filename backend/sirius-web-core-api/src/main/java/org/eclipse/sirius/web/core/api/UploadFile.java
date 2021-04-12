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

import java.io.InputStream;
import java.text.MessageFormat;

/**
 * A file.
 *
 * @author hmarchadour
 */
public class UploadFile {

    private final String name;

    private final InputStream inputStream;

    public UploadFile(String name, InputStream inputStream) {
        this.name = name;
        this.inputStream = inputStream;
    }

    public String getName() {
        return this.name;
    }

    public InputStream getInputStream() {
        return this.inputStream;
    }

    @Override
    public String toString() {
        String pattern = "{0} '{' name: {1} '}'"; //$NON-NLS-1$
        return MessageFormat.format(pattern, this.getClass().getSimpleName(), this.name);
    }
}
