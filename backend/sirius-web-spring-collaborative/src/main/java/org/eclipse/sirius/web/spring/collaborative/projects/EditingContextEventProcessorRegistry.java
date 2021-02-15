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
package org.eclipse.sirius.web.spring.collaborative.projects;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

import javax.annotation.PreDestroy;

import org.eclipse.sirius.web.collaborative.api.services.IEditingContextEventHandler;
import org.eclipse.sirius.web.collaborative.api.services.IEditingContextEventProcessor;
import org.eclipse.sirius.web.collaborative.api.services.IEditingContextEventProcessorRegistry;
import org.eclipse.sirius.web.collaborative.api.services.IRepresentationEventProcessorComposedFactory;
import org.eclipse.sirius.web.core.api.IEditingContext;
import org.eclipse.sirius.web.core.api.IEditingContextPersistenceService;
import org.eclipse.sirius.web.core.api.IEditingContextSearchService;
import org.eclipse.sirius.web.core.api.IInput;
import org.eclipse.sirius.web.core.api.IObjectService;
import org.eclipse.sirius.web.core.api.IPayload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * Registry of the editing context event processors.
 *
 * @author sbegaudeau
 */
@Service
public class EditingContextEventProcessorRegistry implements IEditingContextEventProcessorRegistry {

    private final Logger logger = LoggerFactory.getLogger(EditingContextEventProcessorRegistry.class);

    private final IEditingContextSearchService editingContextSearchService;

    private final IEditingContextPersistenceService editingContextPersistenceService;

    private final ApplicationEventPublisher applicationEventPublisher;

    private final IObjectService objectService;

    private final List<IEditingContextEventHandler> editingContextEventHandlers;

    private final IRepresentationEventProcessorComposedFactory representationEventProcessorComposedFactory;

    private final ConcurrentMap<UUID, EditingContextEventProcessor> editingContextEventProcessors = new ConcurrentHashMap<>();

    public EditingContextEventProcessorRegistry(IEditingContextSearchService editingContextSearchService, IEditingContextPersistenceService editingContextPersistenceService,
            IObjectService objectService, ApplicationEventPublisher applicationEventPublisher, List<IEditingContextEventHandler> editingContextEventHandlers,
            IRepresentationEventProcessorComposedFactory representationEventProcessorComposedFactory) {
        this.editingContextSearchService = Objects.requireNonNull(editingContextSearchService);
        this.editingContextPersistenceService = Objects.requireNonNull(editingContextPersistenceService);
        this.applicationEventPublisher = Objects.requireNonNull(applicationEventPublisher);
        this.objectService = Objects.requireNonNull(objectService);
        this.editingContextEventHandlers = Objects.requireNonNull(editingContextEventHandlers);
        this.representationEventProcessorComposedFactory = Objects.requireNonNull(representationEventProcessorComposedFactory);
    }

    @Override
    public List<IEditingContextEventProcessor> getEditingContextEventProcessors() {
        return this.editingContextEventProcessors.values().stream().collect(Collectors.toUnmodifiableList());
    }

    @Override
    public Optional<IPayload> dispatchEvent(UUID editingContextId, IInput input) {
        return this.getOrCreateEditingContextEventProcessor(editingContextId).flatMap(processor -> processor.handle(input));
    }

    @Override
    public Optional<IEditingContextEventProcessor> getOrCreateEditingContextEventProcessor(UUID editingContextId) {
        Optional<IEditingContextEventProcessor> optionalEditingContextEventProcessor = Optional.empty();
        if (this.editingContextSearchService.existsById(editingContextId)) {
            EditingContextEventProcessor editingContextEventProcessor = this.editingContextEventProcessors.get(editingContextId);
            if (editingContextEventProcessor == null) {
                Optional<IEditingContext> optionalEditingContext = this.editingContextSearchService.findById(editingContextId);
                if (optionalEditingContext.isPresent()) {
                    IEditingContext editingContext = optionalEditingContext.get();
                    editingContextEventProcessor = new EditingContextEventProcessor(editingContext, this.editingContextPersistenceService, this.applicationEventPublisher, this.objectService,
                            this.editingContextEventHandlers, this.representationEventProcessorComposedFactory);
                    this.editingContextEventProcessors.put(editingContextId, editingContextEventProcessor);
                }
            }
            optionalEditingContextEventProcessor = Optional.of(editingContextEventProcessor);
        }

        return optionalEditingContextEventProcessor;
    }

    @Override
    public void dispose(UUID editingContextId) {
        // @formatter:off
        Optional.ofNullable(this.editingContextEventProcessors.remove(editingContextId))
                .ifPresent(EditingContextEventProcessor::dispose);
        // @formatter:on
    }

    @PreDestroy
    public void preDestroy() {
        this.logger.debug("Shutting down all the editing context event processors"); //$NON-NLS-1$
        this.editingContextEventProcessors.values().forEach(EditingContextEventProcessor::preDestroy);
    }
}
