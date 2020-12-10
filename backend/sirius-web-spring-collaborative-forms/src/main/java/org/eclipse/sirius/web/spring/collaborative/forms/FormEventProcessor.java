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
package org.eclipse.sirius.web.spring.collaborative.forms;

import java.text.MessageFormat;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

import org.eclipse.sirius.web.collaborative.api.dto.PreDestroyPayload;
import org.eclipse.sirius.web.collaborative.api.services.EventHandlerResponse;
import org.eclipse.sirius.web.collaborative.api.services.ISubscriptionManager;
import org.eclipse.sirius.web.collaborative.forms.api.IFormEventHandler;
import org.eclipse.sirius.web.collaborative.forms.api.IFormEventProcessor;
import org.eclipse.sirius.web.collaborative.forms.api.IFormInput;
import org.eclipse.sirius.web.collaborative.forms.api.IWidgetSubscriptionManager;
import org.eclipse.sirius.web.collaborative.forms.api.dto.FormRefreshedEventPayload;
import org.eclipse.sirius.web.collaborative.forms.api.dto.UpdateWidgetFocusInput;
import org.eclipse.sirius.web.collaborative.forms.api.dto.UpdateWidgetFocusSuccessPayload;
import org.eclipse.sirius.web.components.Element;
import org.eclipse.sirius.web.forms.Form;
import org.eclipse.sirius.web.forms.components.FormComponent;
import org.eclipse.sirius.web.forms.components.FormComponentProps;
import org.eclipse.sirius.web.forms.description.FormDescription;
import org.eclipse.sirius.web.forms.renderer.FormRenderer;
import org.eclipse.sirius.web.representations.GetOrCreateRandomIdProvider;
import org.eclipse.sirius.web.representations.IRepresentation;
import org.eclipse.sirius.web.representations.VariableManager;
import org.eclipse.sirius.web.services.api.Context;
import org.eclipse.sirius.web.services.api.dto.IPayload;
import org.eclipse.sirius.web.services.api.dto.IRepresentationInput;
import org.eclipse.sirius.web.services.api.objects.IEditingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import reactor.core.publisher.DirectProcessor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.Mono;

/**
 * Reacts to the input that target the property sheet of a specific object and publishes updated versions of the
 * {@link Form} to interested subscribers.
 *
 * @author pcdavid
 */
public class FormEventProcessor implements IFormEventProcessor {

    private final Logger logger = LoggerFactory.getLogger(FormEventProcessor.class);

    private final IEditingContext editingContext;

    private final FormDescription formDescription;

    private final UUID formId;

    private final Object object;

    private final List<IFormEventHandler> formEventHandlers;

    private final ISubscriptionManager subscriptionManager;

    private final IWidgetSubscriptionManager widgetSubscriptionManager;

    private final DirectProcessor<IPayload> flux;

    private final FluxSink<IPayload> sink;

    private final AtomicReference<Form> currentForm = new AtomicReference<>();

    public FormEventProcessor(IEditingContext editingContext, FormDescription formDescription, UUID formId, Object object, List<IFormEventHandler> formEventHandlers,
            ISubscriptionManager subscriptionManager, IWidgetSubscriptionManager widgetSubscriptionManager) {
        this.formDescription = Objects.requireNonNull(formDescription);
        this.editingContext = Objects.requireNonNull(editingContext);
        this.formId = Objects.requireNonNull(formId);
        this.object = Objects.requireNonNull(object);
        this.formEventHandlers = Objects.requireNonNull(formEventHandlers);
        this.subscriptionManager = Objects.requireNonNull(subscriptionManager);
        this.widgetSubscriptionManager = Objects.requireNonNull(widgetSubscriptionManager);

        this.flux = DirectProcessor.create();
        this.sink = this.flux.sink();

        Form form = this.refreshForm();
        this.currentForm.set(form);
    }

    @Override
    public IRepresentation getRepresentation() {
        return this.currentForm.get();
    }

    @Override
    public ISubscriptionManager getSubscriptionManager() {
        return this.subscriptionManager;
    }

    @Override
    public Optional<EventHandlerResponse> handle(IRepresentationInput representationInput, Context context) {
        Optional<EventHandlerResponse> result = Optional.empty();
        if (representationInput instanceof IFormInput) {
            IFormInput formInput = (IFormInput) representationInput;

            if (formInput instanceof UpdateWidgetFocusInput) {
                UpdateWidgetFocusInput input = (UpdateWidgetFocusInput) formInput;
                this.widgetSubscriptionManager.handle(input, context);
                result = Optional.of(new EventHandlerResponse(false, representation -> false, new UpdateWidgetFocusSuccessPayload(input.getWidgetId())));
            } else {
                Optional<IFormEventHandler> optionalFormEventHandler = this.formEventHandlers.stream().filter(handler -> handler.canHandle(formInput)).findFirst();

                if (optionalFormEventHandler.isPresent()) {
                    IFormEventHandler formEventHandler = optionalFormEventHandler.get();
                    EventHandlerResponse eventHandlerResponse = formEventHandler.handle(this.currentForm.get(), formInput);
                    if (eventHandlerResponse.getShouldRefreshPredicate().test(this.currentForm.get())) {
                        this.refresh();
                    }
                    result = Optional.of(eventHandlerResponse);
                } else {
                    this.logger.warn("No handler found for event: {}", formInput); //$NON-NLS-1$
                }
            }
        }

        return result;
    }

    @Override
    public void refresh() {
        Form form = this.refreshForm();

        this.currentForm.set(form);
        this.sink.next(new FormRefreshedEventPayload(form));
    }

    private Form refreshForm() {
        VariableManager variableManager = new VariableManager();
        variableManager.put(VariableManager.SELF, this.object);
        variableManager.put(GetOrCreateRandomIdProvider.PREVIOUS_REPRESENTATION_ID, this.formId);
        variableManager.put(IEditingContext.EDITING_CONTEXT, this.editingContext);

        FormComponentProps formComponentProps = new FormComponentProps(variableManager, this.formDescription);
        Element element = new Element(FormComponent.class, formComponentProps);
        Form form = new FormRenderer(this.logger).render(element);

        this.logger.debug(MessageFormat.format("Form refreshed: {0})", form)); //$NON-NLS-1$

        return form;
    }

    @Override
    public Flux<IPayload> getOutputEvents() {
        var initialRefresh = Mono.fromCallable(() -> new FormRefreshedEventPayload(this.currentForm.get()));
        var refreshEventFlux = Flux.concat(initialRefresh, this.flux);

        // @formatter:off
        return Flux.merge(
            refreshEventFlux,
            this.widgetSubscriptionManager.getFlux(),
            this.subscriptionManager.getFlux()
        );
        // @formatter:on
    }

    @Override
    public void dispose() {
        this.subscriptionManager.dispose();
        this.widgetSubscriptionManager.dispose();
        this.flux.onComplete();
    }

    @Override
    public void preDestroy() {
        this.sink.next(new PreDestroyPayload(this.getRepresentation().getId()));
    }

}
