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
package org.eclipse.sirius.web.services.representations;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.sirius.emfjson.resource.JsonResource;
import org.eclipse.sirius.web.core.api.IEditService;
import org.eclipse.sirius.web.core.api.IObjectService;
import org.eclipse.sirius.web.emf.services.SiriusWebJSONResourceFactoryImpl;
import org.eclipse.sirius.web.emf.view.IJavaServiceProvider;
import org.eclipse.sirius.web.emf.view.ViewConverter;
import org.eclipse.sirius.web.persistence.entities.DocumentEntity;
import org.eclipse.sirius.web.persistence.repositories.IDocumentRepository;
import org.eclipse.sirius.web.representations.IRepresentationDescription;
import org.eclipse.sirius.web.services.api.representations.IDynamicRepresentationDescriptionService;
import org.eclipse.sirius.web.view.View;
import org.eclipse.sirius.web.view.ViewPackage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Service to discover diagram descriptions dynamically from the existing user-defined documents.
 *
 * @author pcdavid
 */
@Service
public class DynamicRepresentationDescriptionService implements IDynamicRepresentationDescriptionService {
    private final Logger logger = LoggerFactory.getLogger(DynamicRepresentationDescriptionService.class);

    private final IDocumentRepository documentRepository;

    private final EPackage.Registry ePackageRegistry;

    private final ViewConverter viewConverter;

    public DynamicRepresentationDescriptionService(IDocumentRepository documentRepository, EPackage.Registry ePackageRegistry, IObjectService objectService, IEditService editService,
            List<IJavaServiceProvider> javaServiceProviders, @Value("${org.eclipse.sirius.web.features.studioDefinition:false}") boolean isStudioDefinitionEnabled) {
        this.documentRepository = Objects.requireNonNull(documentRepository);
        this.ePackageRegistry = Objects.requireNonNull(ePackageRegistry);
        // @formatter:off
        this.viewConverter = new ViewConverter(Objects.requireNonNull(javaServiceProviders),
                                               Objects.requireNonNull(objectService),
                                               Objects.requireNonNull(editService),
                                               isStudioDefinitionEnabled);
        // @formatter:on
    }

    @Override
    public List<IRepresentationDescription> findDynamicRepresentationDescriptions(UUID editingContextId) {
        List<IRepresentationDescription> dynamicRepresentationDescriptions = new ArrayList<>();
        this.documentRepository.findAllByType(ViewPackage.eNAME, ViewPackage.eNS_URI).forEach(documentEntity -> {
            Resource res = this.loadDocumentAsEMF(documentEntity);
            this.getViewDefinition(res).ifPresent(view -> this.viewConverter.convert(view).stream().filter(Objects::nonNull).forEach(dynamicRepresentationDescriptions::add));
        });
        return dynamicRepresentationDescriptions;
    }

    private Optional<View> getViewDefinition(Resource res) {
        if (!res.getContents().isEmpty() && res.getContents().get(0) instanceof View) {
            return Optional.of((View) res.getContents().get(0));
        } else {
            return Optional.empty();
        }
    }

    private Resource loadDocumentAsEMF(DocumentEntity documentEntity) {
        ResourceSet resourceSet = new ResourceSetImpl();
        resourceSet.setPackageRegistry(this.ePackageRegistry);
        URI uri = URI.createURI(documentEntity.getId().toString());
        JsonResource resource = new SiriusWebJSONResourceFactoryImpl().createResource(uri);
        resourceSet.getResources().add(resource);
        try (var inputStream = new ByteArrayInputStream(documentEntity.getContent().getBytes())) {
            resource.load(inputStream, null);
        } catch (IOException | IllegalArgumentException exception) {
            this.logger.error(exception.getMessage(), exception);
        }
        return resource;
    }
}
