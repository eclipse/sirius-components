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
package org.eclipse.sirius.web.spring.collaborative.handlers;

import java.util.Objects;
import java.util.UUID;

import org.eclipse.sirius.web.collaborative.api.dto.CreateRootObjectInput;
import org.eclipse.sirius.web.collaborative.api.dto.CreateRootObjectSuccessPayload;
import org.eclipse.sirius.web.collaborative.api.services.ChangeDescription;
import org.eclipse.sirius.web.collaborative.api.services.ChangeKind;
import org.eclipse.sirius.web.collaborative.api.services.EventHandlerResponse;
import org.eclipse.sirius.web.collaborative.api.services.IEditingContextEventHandler;
import org.eclipse.sirius.web.collaborative.api.services.Monitoring;
import org.eclipse.sirius.web.core.api.ErrorPayload;
import org.eclipse.sirius.web.core.api.IEditService;
import org.eclipse.sirius.web.core.api.IEditingContext;
import org.eclipse.sirius.web.core.api.IInput;
import org.eclipse.sirius.web.spring.collaborative.messages.ICollaborativeMessageService;
import org.springframework.stereotype.Service;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

/**
 * Handler used to create a new root object.
 *
 * @author lfasani
 */
@Service
public class CreateRootObjectEventHandler implements IEditingContextEventHandler {

    private final IEditService editService;

    private final ICollaborativeMessageService messageService;

    private final Counter counter;

    public CreateRootObjectEventHandler(IEditService editService, ICollaborativeMessageService messageService, MeterRegistry meterRegistry) {
        this.editService = Objects.requireNonNull(editService);
        this.messageService = Objects.requireNonNull(messageService);

        // @formatter:off
        this.counter = Counter.builder(Monitoring.EVENT_HANDLER)
                .tag(Monitoring.NAME, this.getClass().getSimpleName())
                .register(meterRegistry);
        // @formatter:on
    }

    @Override
    public boolean canHandle(IInput input) {
        return input instanceof CreateRootObjectInput;
    }

    @Override
    public EventHandlerResponse handle(IEditingContext editingContext, IInput input) {
        this.counter.increment();

        if (input instanceof CreateRootObjectInput) {
            CreateRootObjectInput createRootObjectInput = (CreateRootObjectInput) input;
            UUID documentId = createRootObjectInput.getDocumentId();
            String rootObjectCreationDescriptionId = createRootObjectInput.getRootObjectCreationDescriptionId();
            String namespaceId = createRootObjectInput.getNamespaceId();

            var optionalObject = this.editService.createRootObject(editingContext, documentId, namespaceId, rootObjectCreationDescriptionId);

            if (optionalObject.isPresent()) {
                return new EventHandlerResponse(new ChangeDescription(ChangeKind.SEMANTIC_CHANGE, editingContext.getId()), new CreateRootObjectSuccessPayload(input.getId(), optionalObject.get()));
            }
        }

        String message = this.messageService.invalidInput(input.getClass().getSimpleName(), CreateRootObjectInput.class.getSimpleName());
        return new EventHandlerResponse(new ChangeDescription(ChangeKind.NOTHING, editingContext.getId()), new ErrorPayload(input.getId(), message));
    }

}
