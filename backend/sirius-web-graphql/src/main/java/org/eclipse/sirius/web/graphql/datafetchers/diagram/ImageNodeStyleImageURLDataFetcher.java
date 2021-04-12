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
package org.eclipse.sirius.web.graphql.datafetchers.diagram;

import org.eclipse.sirius.web.annotations.spring.graphql.QueryDataFetcher;
import org.eclipse.sirius.web.diagrams.ImageNodeStyle;
import org.eclipse.sirius.web.graphql.schema.ImageURLFieldProvider;
import org.eclipse.sirius.web.spring.graphql.api.IDataFetcherWithFieldCoordinates;
import org.eclipse.sirius.web.spring.graphql.api.URLConstants;

import graphql.schema.DataFetchingEnvironment;

/**
 * Data fetcher for ImageNodeStyle.imageURL, to rewrite the relative path of the image into an absolute path on the
 * server.
 * <p>
 * If the <code>ImageNodeStyle.imageURL</code> is of the form <code>path/to/image.svg</code>, the rewritten value which
 * will be seen by the frontend will be <code>/api/images/path/to/image.svg</code>.
 *
 * @author pcdavid
 */
@QueryDataFetcher(type = "ImageNodeStyle", field = ImageURLFieldProvider.IMAGE_URL_FIELD)
public class ImageNodeStyleImageURLDataFetcher implements IDataFetcherWithFieldCoordinates<String> {

    @Override
    public String get(DataFetchingEnvironment environment) throws Exception {
        ImageNodeStyle style = environment.getSource();
        return URLConstants.IMAGE_BASE_PATH + style.getImageURL();
    }
}
