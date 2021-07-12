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
package org.eclipse.sirius.web.spring.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.eclipse.sirius.web.api.services.IImagePathService;
import org.eclipse.sirius.web.emf.view.ICustomImagesContentService;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.util.ClassUtils;

import io.micrometer.core.instrument.simple.SimpleMeterRegistry;

/**
 * Unit test for the images controller.
 *
 * @author sbegaudeau
 */
public class ImagesControllerTests {
    @Test
    public void testInvalidFolder() {
        ImagesController imagesController = new ImagesController(new ArrayList<>(), new ICustomImagesContentService.NoOp(), new SimpleMeterRegistry());
        HttpServletRequest request = new MockHttpServletRequest(HttpMethod.GET.name(), "/api/images/invalidFolder/doesNotExist.png"); //$NON-NLS-1$
        ResponseEntity<Resource> responseEntity = imagesController.getImage(request);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void testValidFolderWithImageWhichDoesNotExist() {
        IImagePathService imagePathService = new IImagePathService() {
            @Override
            public List<String> getPaths() {
                return List.of("/validFolder"); //$NON-NLS-1$
            }
        };
        ImagesController imagesController = new ImagesController(List.of(imagePathService), new ICustomImagesContentService.NoOp(), new SimpleMeterRegistry());
        HttpServletRequest request = new MockHttpServletRequest(HttpMethod.GET.name(), "/api/images/validFolder/doesNotExist.png"); //$NON-NLS-1$
        ResponseEntity<Resource> responseEntity = imagesController.getImage(request);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void testValidImage() {
        IImagePathService imagePathService = new IImagePathService() {
            @Override
            public List<String> getPaths() {
                return List.of("/icons"); //$NON-NLS-1$
            }
        };
        ImagesController imagesController = new ImagesController(List.of(imagePathService), new ICustomImagesContentService.NoOp(), new SimpleMeterRegistry());
        HttpServletRequest request = new MockHttpServletRequest(HttpMethod.GET.name(), "/api/images/icons/full/obj16/EClass.gif"); //$NON-NLS-1$

        // We need to replace the current class loader to trick Spring into thinking that the resource exists
        ClassLoader testClassLoader = new URLClassLoader(new URL[] {}) {
            @Override
            public URL getResource(String name) {
                try {
                    return new URL("jar:file:somejar!/icons/full/obj16/EClass.gif"); //$NON-NLS-1$
                } catch (MalformedURLException exception) {
                    fail(exception.getMessage(), exception);
                }
                return null;
            }
        };
        ClassLoader threadContextClassLoader = ClassUtils.overrideThreadContextClassLoader(testClassLoader);
        ResponseEntity<Resource> responseEntity = imagesController.getImage(request);
        ClassUtils.overrideThreadContextClassLoader(threadContextClassLoader);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        HttpHeaders headers = responseEntity.getHeaders();
        assertThat(headers.getContentType()).isEqualTo(MediaType.IMAGE_GIF);
    }
}
