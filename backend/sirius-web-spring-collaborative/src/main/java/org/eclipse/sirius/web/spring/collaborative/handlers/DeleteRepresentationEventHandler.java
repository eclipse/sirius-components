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

import org.eclipse.sirius.web.collaborative.api.dto.DeleteRepresentationInput;
import org.eclipse.sirius.web.collaborative.api.dto.DeleteRepresentationSuccessPayload;
import org.eclipse.sirius.web.collaborative.api.services.ChangeDescription;
import org.eclipse.sirius.web.collaborative.api.services.ChangeKind;
import org.eclipse.sirius.web.collaborative.api.services.EventHandlerResponse;
import org.eclipse.sirius.web.collaborative.api.services.IEditingContextEventHandler;
import org.eclipse.sirius.web.collaborative.api.services.IRepresentationDeletionService;
import org.eclipse.sirius.web.collaborative.api.services.Monitoring;
import org.eclipse.sirius.web.core.api.ErrorPayload;
import org.eclipse.sirius.web.core.api.IEditingContext;
import org.eclipse.sirius.web.core.api.IInput;
import org.eclipse.sirius.web.spring.collaborative.messages.ICollaborativeMessageService;
import org.springframework.stereotype.Service;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

/**
 * Handler used to delete a representation.
 *
 * @author lfasani
 */
@Service
public class DeleteRepresentationEventHandler implements IEditingContextEventHandler {

    private final IRepresentationDeletionService representationDeletionService;

    private final ICollaborativeMessageService messageService;

    private final Counter counter;

    public DeleteRepresentationEventHandler(IRepresentationDeletionService representationDeletionService, ICollaborativeMessageService messageService, MeterRegistry meterRegistry) {
        this.representationDeletionService = Objects.requireNonNull(representationDeletionService);
        this.messageService = Objects.requireNonNull(messageService);

        // @formatter:off
        this.counter = Counter.builder(Monitoring.EVENT_HANDLER)
                .tag(Monitoring.NAME, this.getClass().getSimpleName())
                .register(meterRegistry);
        // @formatter:on
    }

    @Override
    public boolean canHandle(IInput input) {
        return input instanceof DeleteRepresentationInput;
    }

    @Override
    public EventHandlerResponse handle(IEditingContext editingContext, IInput input) {
        this.counter.increment();

        String message = this.messageService.invalidInput(input.getClass().getSimpleName(), DeleteRepresentationInput.class.getSimpleName());
        EventHandlerResponse eventHandlerResponse = new EventHandlerResponse(new ChangeDescription(ChangeKind.NOTHING, editingContext.getId()), new ErrorPayload(input.getId(), message));
        if (input instanceof DeleteRepresentationInput) {
            DeleteRepresentationInput deleteRepresentationInput = (DeleteRepresentationInput) input;
            var payload = this.representationDeletionService.delete(deleteRepresentationInput);

            if (payload instanceof DeleteRepresentationSuccessPayload) {
                eventHandlerResponse = new EventHandlerResponse(new ChangeDescription(ChangeKind.REPRESENTATION_DELETION, editingContext.getId()), payload);
            } else if (payload instanceof ErrorPayload) {
                eventHandlerResponse = new EventHandlerResponse(new ChangeDescription(ChangeKind.NOTHING, editingContext.getId()), payload);
            }
        }

        return eventHandlerResponse;
    }
}
