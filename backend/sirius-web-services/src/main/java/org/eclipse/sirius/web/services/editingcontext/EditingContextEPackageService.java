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
package org.eclipse.sirius.web.services.editingcontext;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EPackageRegistryImpl;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.sirius.emfjson.resource.JsonResource;
import org.eclipse.sirius.web.domain.Domain;
import org.eclipse.sirius.web.emf.domain.DomainConverter;
import org.eclipse.sirius.web.emf.services.DocumentMetadataAdapter;
import org.eclipse.sirius.web.emf.services.IEditingContextEPackageService;
import org.eclipse.sirius.web.emf.services.SiriusWebJSONResourceFactoryImpl;
import org.eclipse.sirius.web.persistence.entities.DocumentEntity;
import org.eclipse.sirius.web.persistence.repositories.IDocumentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * A service used to retrieve all the EPackages accessible for given editing context.
 *
 * @author sbegaudeau
 */
@Service
public class EditingContextEPackageService implements IEditingContextEPackageService {

    private final Logger logger = LoggerFactory.getLogger(EditingContextEPackageService.class);

    private final EPackage.Registry globalEPackageRegistry;

    private final IDocumentRepository documentRepository;

    public EditingContextEPackageService(EPackage.Registry globalEPackageRegistry, IDocumentRepository documentRepository) {
        this.globalEPackageRegistry = Objects.requireNonNull(globalEPackageRegistry);
        this.documentRepository = Objects.requireNonNull(documentRepository);
    }

    @Override
    public List<EPackage> getEPackages(UUID editingContextId) {
        // @formatter:off
        List<DocumentEntity> entities = StreamSupport.stream(this.documentRepository.findAll().spliterator(), false)
            .collect(Collectors.toList());
        // @formatter:on

        EPackageRegistryImpl ePackageRegistry = new EPackageRegistryImpl();
        this.globalEPackageRegistry.forEach(ePackageRegistry::put);
        ResourceSet resourceSet = new ResourceSetImpl();
        resourceSet.setPackageRegistry(ePackageRegistry);

        for (DocumentEntity documentEntity : entities) {
            URI uri = URI.createURI(documentEntity.getId().toString());
            JsonResource resource = new SiriusWebJSONResourceFactoryImpl().createResource(uri);
            try (var inputStream = new ByteArrayInputStream(documentEntity.getContent().getBytes())) {
                resourceSet.getResources().add(resource);
                resource.load(inputStream, null);

                resource.eAdapters().add(new DocumentMetadataAdapter(documentEntity.getName()));
            } catch (IOException | IllegalArgumentException exception) {
                this.logger.warn(exception.getMessage(), exception);
            }
        }

        // @formatter:off
        return resourceSet.getResources().stream()
                .flatMap(resource -> {
                    return resource.getContents().stream()
                            .filter(Domain.class::isInstance)
                            .map(Domain.class::cast)
                            .findFirst()
                            .map(new DomainConverter()::convert)
                            .stream();
                })
                .collect(Collectors.toList());
        // @formatter:on
    }
}
