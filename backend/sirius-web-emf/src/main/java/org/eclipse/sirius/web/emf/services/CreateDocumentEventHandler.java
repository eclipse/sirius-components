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
package org.eclipse.sirius.web.emf.services;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.sirius.emfjson.resource.JsonResource;
import org.eclipse.sirius.web.api.configuration.StereotypeDescription;
import org.eclipse.sirius.web.collaborative.api.dto.CreateDocumentInput;
import org.eclipse.sirius.web.collaborative.api.dto.CreateDocumentSuccessPayload;
import org.eclipse.sirius.web.collaborative.api.dto.Document;
import org.eclipse.sirius.web.collaborative.api.services.ChangeDescription;
import org.eclipse.sirius.web.collaborative.api.services.ChangeKind;
import org.eclipse.sirius.web.collaborative.api.services.EventHandlerResponse;
import org.eclipse.sirius.web.collaborative.api.services.IEditingContextEventHandler;
import org.eclipse.sirius.web.collaborative.api.services.Monitoring;
import org.eclipse.sirius.web.core.api.ErrorPayload;
import org.eclipse.sirius.web.core.api.IEditingContext;
import org.eclipse.sirius.web.core.api.IInput;
import org.eclipse.sirius.web.core.api.IPayload;
import org.eclipse.sirius.web.emf.services.messages.IEMFMessageService;
import org.eclipse.sirius.web.services.api.document.IDocumentService;
import org.eclipse.sirius.web.services.api.stereotypes.IStereotypeDescriptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

/**
 * Event handler used to create a new document from a stereotype.
 *
 * @author sbegaudeau
 */
@Service
public class CreateDocumentEventHandler implements IEditingContextEventHandler {

    private final Logger logger = LoggerFactory.getLogger(CreateDocumentEventHandler.class);

    private final IDocumentService documentService;

    private final IStereotypeDescriptionService stereotypeDescriptionService;

    private final IEMFMessageService messageService;

    private final Counter counter;

    public CreateDocumentEventHandler(IDocumentService documentService, IStereotypeDescriptionService stereotypeDescriptionService, IEMFMessageService messageService, MeterRegistry meterRegistry) {
        this.documentService = Objects.requireNonNull(documentService);
        this.stereotypeDescriptionService = Objects.requireNonNull(stereotypeDescriptionService);
        this.messageService = Objects.requireNonNull(messageService);

        // @formatter:off
        this.counter = Counter.builder(Monitoring.EVENT_HANDLER)
                .tag(Monitoring.NAME, this.getClass().getSimpleName())
                .register(meterRegistry);
        // @formatter:on
    }

    @Override
    public boolean canHandle(IInput input) {
        return input instanceof CreateDocumentInput;
    }

    @Override
    public EventHandlerResponse handle(IEditingContext editingContext, IInput input) {
        this.counter.increment();

        EventHandlerResponse response = new EventHandlerResponse(new ChangeDescription(ChangeKind.NOTHING, editingContext.getId()),
                new ErrorPayload(input.getId(), this.messageService.unexpectedError()));

        if (input instanceof CreateDocumentInput) {
            CreateDocumentInput createDocumentInput = (CreateDocumentInput) input;

            String name = createDocumentInput.getName().trim();
            UUID editingContextId = createDocumentInput.getEditingContextId();
            UUID stereotypeDescriptionId = createDocumentInput.getStereotypeDescriptionId();

            Optional<StereotypeDescription> optionalStereotypeDescription = this.stereotypeDescriptionService.getStereotypeDescriptionById(editingContextId, stereotypeDescriptionId);

            if (name.isBlank()) {
                IPayload payload = new ErrorPayload(input.getId(), this.messageService.invalidDocumentName(name));
                response = new EventHandlerResponse(new ChangeDescription(ChangeKind.NOTHING, editingContext.getId()), payload);
            } else if (optionalStereotypeDescription.isEmpty()) {
                IPayload payload = new ErrorPayload(input.getId(), this.messageService.stereotypeDescriptionNotFound(stereotypeDescriptionId));
                response = new EventHandlerResponse(new ChangeDescription(ChangeKind.NOTHING, editingContext.getId()), payload);
            } else if (optionalStereotypeDescription.isPresent()) {
                StereotypeDescription stereotypeDescription = optionalStereotypeDescription.get();
                response = this.createDocument(createDocumentInput.getId(), editingContext, editingContextId, name, stereotypeDescription);
            }
        }

        return response;
    }

    private EventHandlerResponse createDocument(UUID inputId, IEditingContext editingContext, UUID editingContextId, String name, StereotypeDescription stereotypeDescription) {
        // @formatter:off
        Optional<AdapterFactoryEditingDomain> optionalEditingDomain = Optional.of(editingContext)
                .filter(EditingContext.class::isInstance)
                .map(EditingContext.class::cast)
                .map(EditingContext::getDomain);
        // @formatter:on

        if (optionalEditingDomain.isPresent()) {
            AdapterFactoryEditingDomain adapterFactoryEditingDomain = optionalEditingDomain.get();
            ResourceSet resourceSet = adapterFactoryEditingDomain.getResourceSet();

            var optionalDocument = this.documentService.createDocument(editingContextId, name, stereotypeDescription.getContent());
            if (optionalDocument.isPresent()) {
                Document document = optionalDocument.get();
                URI uri = URI.createURI(document.getId().toString());

                JsonResource resource = new SiriusWebJSONResourceFactoryImpl().createResource(uri);
                try (var inputStream = new ByteArrayInputStream(document.getContent().getBytes())) {
                    resource.load(inputStream, null);
                } catch (IOException exception) {
                    this.logger.error(exception.getMessage(), exception);
                }

                resource.eAdapters().add(new DocumentMetadataAdapter(name));

                resourceSet.getResources().add(resource);

                return new EventHandlerResponse(new ChangeDescription(ChangeKind.SEMANTIC_CHANGE, editingContext.getId()), new CreateDocumentSuccessPayload(inputId, document));
            }
        }

        return new EventHandlerResponse(new ChangeDescription(ChangeKind.NOTHING, editingContext.getId()), new ErrorPayload(inputId, this.messageService.unexpectedError()));
    }

}
