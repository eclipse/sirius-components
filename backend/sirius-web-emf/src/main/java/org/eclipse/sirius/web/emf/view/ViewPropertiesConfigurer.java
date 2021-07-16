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
package org.eclipse.sirius.web.emf.view;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.web.api.configuration.IPropertiesDescriptionRegistry;
import org.eclipse.sirius.web.api.configuration.IPropertiesDescriptionRegistryConfigurer;
import org.eclipse.sirius.web.core.api.IEditingContext;
import org.eclipse.sirius.web.forms.components.SelectComponent;
import org.eclipse.sirius.web.forms.description.AbstractControlDescription;
import org.eclipse.sirius.web.forms.description.CheckboxDescription;
import org.eclipse.sirius.web.forms.description.FormDescription;
import org.eclipse.sirius.web.forms.description.GroupDescription;
import org.eclipse.sirius.web.forms.description.PageDescription;
import org.eclipse.sirius.web.forms.description.SelectDescription;
import org.eclipse.sirius.web.forms.description.TextfieldDescription;
import org.eclipse.sirius.web.representations.GetOrCreateRandomIdProvider;
import org.eclipse.sirius.web.representations.IRepresentationDescription;
import org.eclipse.sirius.web.representations.Status;
import org.eclipse.sirius.web.representations.VariableManager;
import org.eclipse.sirius.web.view.ConditionalNodeStyle;
import org.eclipse.sirius.web.view.NodeStyle;
import org.springframework.stereotype.Component;

/**
 * Customizes the properties view for some of the View DSL elements.
 *
 * @author pcdavid
 */
@Component
public class ViewPropertiesConfigurer implements IPropertiesDescriptionRegistryConfigurer {

    private static final String EMPTY = ""; //$NON-NLS-1$

    private static final String UNNAMED = "<unnamed>"; //$NON-NLS-1$

    private final Function<VariableManager, List<Object>> semanticElementsProvider = variableManager -> variableManager.get(VariableManager.SELF, Object.class).stream().collect(Collectors.toList());

    private final ICustomImagesSearchService customImagesService;

    public ViewPropertiesConfigurer(ICustomImagesSearchService customImagesService) {
        this.customImagesService = Objects.requireNonNull(customImagesService);
    }

    @Override
    public void addPropertiesDescriptions(IPropertiesDescriptionRegistry registry) {
        registry.add(this.getConditionalNodeStyleProperties());
        registry.add(this.getNodeStyleProperties());
    }

    private FormDescription getConditionalNodeStyleProperties() {
        UUID formDescriptionId = UUID.nameUUIDFromBytes("conditionalnodestyle".getBytes()); //$NON-NLS-1$

        // @formatter:off
        Function<VariableManager, String> targetObjectIdProvider = variableManager -> variableManager.get(VariableManager.SELF, NodeStyle.class)
                                                                                                     .map(obj -> EcoreUtil.getURI(obj).toString())
                                                                                                     .orElse(null);

        List<AbstractControlDescription> controls = List.of(
                this.createTextField("conditionalnodestyle.condition", "Condition", //$NON-NLS-1$ //$NON-NLS-2$
                        style -> ((ConditionalNodeStyle) style).getCondition(),
                        (style, newCondition) -> ((ConditionalNodeStyle) style).setCondition(newCondition)),
                this.createTextField("conditionalnodestyle.color", "Color", //$NON-NLS-1$ //$NON-NLS-2$
                        style -> ((NodeStyle) style).getColor(),
                        (style, newColor) -> ((NodeStyle) style).setColor(newColor)),
                this.createTextField("conditionalnodestyle.borderColor", "Border Color", //$NON-NLS-1$ //$NON-NLS-2$
                        style -> ((NodeStyle) style).getBorderColor(),
                        (style, newColor) -> ((NodeStyle) style).setBorderColor(newColor)),
                this.createTextField("conditionalnodestyle.borderRadius", "Border Radius", //$NON-NLS-1$ //$NON-NLS-2$
                        style -> String.valueOf(((NodeStyle) style).getBorderRadius()),
                        (style, newBorderRadius) -> {
                            try {
                                ((NodeStyle) style).setBorderRadius(Integer.parseInt(newBorderRadius));
                            } catch (NumberFormatException nfe) {
                                // Ignore.
                            }
                        }),
                this.createCheckbox("conditionalnodestyle.listMost", "List Mode", //$NON-NLS-1$ //$NON-NLS-2$
                        style -> ((NodeStyle) style).isListMode(),
                        (style, newListMode) -> ((NodeStyle) style).setListMode(newListMode)),
                this.createTextField("conditionalnodestyle.fontSize", "Font Size", //$NON-NLS-1$ //$NON-NLS-2$
                        style -> String.valueOf(((NodeStyle) style).getFontSize()),
                        (style, newColor) -> {
                            try {
                                ((NodeStyle) style).setFontSize(Integer.parseInt(newColor));
                            } catch (NumberFormatException nfe) {
                                // Ignore.
                            }
                        }),
                this.createShapeSelectionField());

        GroupDescription groupDescription = this.createSimpleGroupDescription(controls);
        return FormDescription.newFormDescription(formDescriptionId)
                .label("Conditional Node Style") //$NON-NLS-1$
                .labelProvider(variableManager -> variableManager.get(VariableManager.SELF, ConditionalNodeStyle.class).map(ConditionalNodeStyle::getCondition).orElse(UNNAMED))
                .canCreatePredicate(variableManager -> {
                    var optionalClass = variableManager.get(IRepresentationDescription.CLASS, Object.class);
                    return optionalClass.isPresent() && optionalClass.get().equals(ConditionalNodeStyle.class);
                })
                .idProvider(new GetOrCreateRandomIdProvider())
                .targetObjectIdProvider(targetObjectIdProvider)
                .pageDescriptions(List.of(this.createSimplePageDescription(groupDescription,  variableManager -> {
                    Optional<?> optionalValue = variableManager.get(VariableManager.SELF, ConditionalNodeStyle.class);
                    return optionalValue.isPresent();
                })))
                .groupDescriptions(List.of(groupDescription))
                .build();
        // @formatter:on
    }

    private FormDescription getNodeStyleProperties() {
        UUID formDescriptionId = UUID.nameUUIDFromBytes("nodestyle".getBytes()); //$NON-NLS-1$

        // @formatter:off
        Function<VariableManager, String> targetObjectIdProvider = variableManager -> variableManager.get(VariableManager.SELF, NodeStyle.class)
                                                                                                     .map(obj -> EcoreUtil.getURI(obj).toString())
                                                                                                     .orElse(null);

        List<AbstractControlDescription> controls = List.of(
                this.createTextField("nodestyle.color", "Color", //$NON-NLS-1$ //$NON-NLS-2$
                                     style -> ((NodeStyle) style).getColor(),
                                     (style, newColor) -> ((NodeStyle) style).setColor(newColor)),
                this.createTextField("nodestyle.borderColor", "Border Color", //$NON-NLS-1$ //$NON-NLS-2$
                        style -> ((NodeStyle) style).getBorderColor(),
                        (style, newColor) -> ((NodeStyle) style).setBorderColor(newColor)),
                this.createTextField("nodestyle.borderRadius", "Border Radius", //$NON-NLS-1$ //$NON-NLS-2$
                        style -> String.valueOf(((NodeStyle) style).getBorderRadius()),
                        (style, newBorderRadius) -> {
                            try {
                                ((NodeStyle) style).setBorderRadius(Integer.parseInt(newBorderRadius));
                            } catch (NumberFormatException nfe) {
                                // Ignore.
                            }
                        }),
                this.createCheckbox("nodestyle.listMost", "List Mode", //$NON-NLS-1$ //$NON-NLS-2$
                        style -> ((NodeStyle) style).isListMode(),
                        (style, newListMode) -> ((NodeStyle) style).setListMode(newListMode)),
                this.createTextField("nodestyle.fontSize", "Font Size", //$NON-NLS-1$ //$NON-NLS-2$
                        style -> String.valueOf(((NodeStyle) style).getFontSize()),
                        (style, newColor) -> {
                            try {
                                ((NodeStyle) style).setFontSize(Integer.parseInt(newColor));
                            } catch (NumberFormatException nfe) {
                                // Ignore.
                            }
                        }),
                this.createShapeSelectionField());

        GroupDescription groupDescription = this.createSimpleGroupDescription(controls);
        return FormDescription.newFormDescription(formDescriptionId)
                .label("Node Style") //$NON-NLS-1$
                .labelProvider(variableManager -> variableManager.get(VariableManager.SELF, NodeStyle.class).map(style -> style.getColor()).orElse(UNNAMED))
                .canCreatePredicate(variableManager -> {
                    var optionalClass = variableManager.get(IRepresentationDescription.CLASS, Object.class);
                    return optionalClass.isPresent() && optionalClass.get().equals(NodeStyle.class);
                })
                .idProvider(new GetOrCreateRandomIdProvider())
                .targetObjectIdProvider(targetObjectIdProvider)
                .pageDescriptions(List.of(this.createSimplePageDescription(groupDescription, variableManager -> {
                    Optional<?> optionalValue = variableManager.get(VariableManager.SELF, NodeStyle.class);
                    return optionalValue.isPresent() && !(optionalValue.get() instanceof ConditionalNodeStyle);
                })))
                .groupDescriptions(List.of(groupDescription))
                .build();
        // @formatter:on
    }

    private PageDescription createSimplePageDescription(GroupDescription groupDescription, Predicate<VariableManager> canCreatePredicate) {
        // @formatter:off
        return PageDescription.newPageDescription("page") //$NON-NLS-1$
                              .idProvider(variableManager -> "page") //$NON-NLS-1$
                              .labelProvider(variableManager -> "Properties") //$NON-NLS-1$
                              .semanticElementsProvider(this.semanticElementsProvider)
                              .canCreatePredicate(canCreatePredicate)
                              .groupDescriptions(List.of(groupDescription))
                              .build();
        // @formatter:on
    }

    private GroupDescription createSimpleGroupDescription(List<AbstractControlDescription> controls) {
        // @formatter:off
        return GroupDescription.newGroupDescription("group") //$NON-NLS-1$
                               .idProvider(variableManager -> "group") //$NON-NLS-1$
                               .labelProvider(variableManager -> "General") //$NON-NLS-1$
                               .semanticElementsProvider(this.semanticElementsProvider)
                               .controlDescriptions(controls)
                               .build();
        // @formatter:on
    }

    private TextfieldDescription createTextField(String id, String title, Function<Object, String> reader, BiConsumer<Object, String> writer) {
        Function<VariableManager, String> valueProvider = variableManager -> variableManager.get(VariableManager.SELF, Object.class).map(reader).orElse(EMPTY);
        BiFunction<VariableManager, String, Status> newValueHandler = (variableManager, newValue) -> {
            var optionalDiagramMapping = variableManager.get(VariableManager.SELF, Object.class);
            if (optionalDiagramMapping.isPresent()) {
                writer.accept(optionalDiagramMapping.get(), newValue);
                return Status.OK;
            } else {
                return Status.ERROR;
            }
        };
        // @formatter:off
        return TextfieldDescription.newTextfieldDescription(id)
                                   .idProvider(variableManager -> id)
                                   .labelProvider(variableManager -> title)
                                   .valueProvider(valueProvider)
                                   .newValueHandler(newValueHandler)
                                   .build();
        // @formatter:on
    }

    private CheckboxDescription createCheckbox(String id, String title, Function<Object, Boolean> reader, BiConsumer<Object, Boolean> writer) {
        Function<VariableManager, Boolean> valueProvider = variableManager -> variableManager.get(VariableManager.SELF, Object.class).map(reader).orElse(Boolean.FALSE);
        BiFunction<VariableManager, Boolean, Status> newValueHandler = (variableManager, newValue) -> {
            var optionalDiagramMapping = variableManager.get(VariableManager.SELF, Object.class);
            if (optionalDiagramMapping.isPresent()) {
                writer.accept(optionalDiagramMapping.get(), newValue);
                return Status.OK;
            } else {
                return Status.ERROR;
            }
        };
        // @formatter:off
        return CheckboxDescription.newCheckboxDescription(id)
                                   .idProvider(variableManager -> id)
                                   .labelProvider(variableManager -> title)
                                   .valueProvider(valueProvider)
                                   .newValueHandler(newValueHandler)
                                   .build();
        // @formatter:on
    }

    private SelectDescription createShapeSelectionField() {
        // @formatter:off
        return SelectDescription.newSelectDescription("nodestyle.shapeSelector") //$NON-NLS-1$
                                .idProvider(variableManager -> "nodestyle.shapeSelector") //$NON-NLS-1$
                                .labelProvider(variableManager -> "Shape") //$NON-NLS-1$
                                .valueProvider(variableManager -> variableManager.get(VariableManager.SELF, NodeStyle.class).map(NodeStyle::getShape).orElse(EMPTY))
                                .optionsProvider(variableManager -> {
                                    Optional<IEditingContext> optionalEditingContext = variableManager.get(IEditingContext.EDITING_CONTEXT, IEditingContext.class);
                                    if (optionalEditingContext.isPresent()) {
                                        return this.customImagesService.getAvailableImages(optionalEditingContext.get().getId()).stream().sorted(Comparator.comparing(CustomImage::getLabel)).collect(Collectors.toList());
                                    } else {
                                        return List.of();
                                    }
                                })
                                .optionIdProvider(variableManager -> variableManager.get(SelectComponent.CANDIDATE_VARIABLE, CustomImage.class).map(CustomImage::getId).map(UUID::toString).orElse(EMPTY))
                                .optionLabelProvider(variableManager -> variableManager.get(SelectComponent.CANDIDATE_VARIABLE, CustomImage.class).map(CustomImage::getLabel).orElse(EMPTY))
                                .newValueHandler(this.getNewShapeValueHandler())
                                .build();
        // @formatter:on
    }

    private BiFunction<VariableManager, String, Status> getNewShapeValueHandler() {
        return (variableManager, newValue) -> {
            var optionalNodeStyle = variableManager.get(VariableManager.SELF, NodeStyle.class);
            if (optionalNodeStyle.isPresent()) {
                if (newValue != null && newValue.isBlank()) {
                    newValue = null;
                }
                optionalNodeStyle.get().setShape(newValue);
                return Status.OK;
            }
            return Status.ERROR;
        };
    }

}
