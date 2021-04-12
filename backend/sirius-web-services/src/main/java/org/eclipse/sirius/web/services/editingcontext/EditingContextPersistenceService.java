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
package org.eclipse.sirius.web.services.editingcontext;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.sirius.emfjson.resource.JsonResource;
import org.eclipse.sirius.web.collaborative.api.dto.Document;
import org.eclipse.sirius.web.core.api.IEditingContext;
import org.eclipse.sirius.web.core.api.IEditingContextPersistenceService;
import org.eclipse.sirius.web.emf.services.EObjectIDManager;
import org.eclipse.sirius.web.emf.services.EditingContext;
import org.eclipse.sirius.web.persistence.entities.DocumentEntity;
import org.eclipse.sirius.web.persistence.repositories.IDocumentRepository;
import org.eclipse.sirius.web.services.api.events.DocumentsModifiedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;

/**
 * Service used to persist the editing context when a change has been performed.
 *
 * @author sbegaudeau
 */
@Service
public class EditingContextPersistenceService implements IEditingContextPersistenceService {

    private static final String TIMER_NAME = "siriusweb_editingcontext_save"; //$NON-NLS-1$

    private final Logger logger = LoggerFactory.getLogger(EditingContextPersistenceService.class);

    private final IDocumentRepository documentRepository;

    private final ApplicationEventPublisher applicationEventPublisher;

    private final Timer timer;

    public EditingContextPersistenceService(IDocumentRepository documentRepository, ApplicationEventPublisher applicationEventPublisher, MeterRegistry meterRegistry) {
        this.documentRepository = Objects.requireNonNull(documentRepository);
        this.applicationEventPublisher = Objects.requireNonNull(applicationEventPublisher);

        this.timer = Timer.builder(TIMER_NAME).register(meterRegistry);
    }

    @Override
    public void persist(IEditingContext editingContext) {
        long start = System.currentTimeMillis();

        if (editingContext instanceof EditingContext) {
            EditingDomain editingDomain = ((EditingContext) editingContext).getDomain();
            List<DocumentEntity> documentEntities = this.persist(editingDomain);
            List<Document> documents = documentEntities.stream().map(new DocumentMapper()::toDTO).collect(Collectors.toList());
            this.applicationEventPublisher.publishEvent(new DocumentsModifiedEvent(editingContext.getId(), documents));
        }

        long end = System.currentTimeMillis();
        this.timer.record(end - start, TimeUnit.MILLISECONDS);
    }

    private List<DocumentEntity> persist(EditingDomain editingDomain) {
        List<DocumentEntity> result = new ArrayList<>();
        List<Resource> resources = editingDomain.getResourceSet().getResources();
        for (Resource resource : resources) {
            this.save(resource).ifPresent(result::add);
        }
        return result;
    }

    private Optional<DocumentEntity> save(Resource resource) {
        Optional<DocumentEntity> result = Optional.empty();
        HashMap<Object, Object> options = new HashMap<>();
        options.put(JsonResource.OPTION_ID_MANAGER, new EObjectIDManager());

        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            resource.save(outputStream, options);

            for (Resource.Diagnostic warning : resource.getWarnings()) {
                this.logger.warn(warning.getMessage());
            }
            for (Resource.Diagnostic error : resource.getErrors()) {
                this.logger.error(error.getMessage());
            }

            byte[] bytes = outputStream.toByteArray();
            String content = new String(bytes);

            UUID id = UUID.fromString(resource.getURI().toString());
            result = this.documentRepository.findById(id).map(entity -> {
                entity.setContent(content);
                return this.documentRepository.save(entity);
            });
        } catch (IllegalArgumentException | IOException exception) {
            this.logger.error(exception.getMessage(), exception);
        }
        return result;
    }
}
