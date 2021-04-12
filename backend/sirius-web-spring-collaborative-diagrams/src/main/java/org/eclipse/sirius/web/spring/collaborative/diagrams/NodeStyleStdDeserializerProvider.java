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
package org.eclipse.sirius.web.spring.collaborative.diagrams;

import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import org.eclipse.sirius.web.collaborative.api.services.IStdDeserializerProvider;
import org.eclipse.sirius.web.diagrams.INodeStyle;
import org.springframework.stereotype.Service;

/**
 * The deserializer provider for {@link INodeStyle}.
 *
 * @author gcoutable
 */
@Service
public class NodeStyleStdDeserializerProvider implements IStdDeserializerProvider<INodeStyle> {

    @Override
    public StdDeserializer<INodeStyle> getDeserializer() {
        return new INodeStyleDeserializer();
    }

    @Override
    public Class<INodeStyle> getType() {
        return INodeStyle.class;
    }

}
