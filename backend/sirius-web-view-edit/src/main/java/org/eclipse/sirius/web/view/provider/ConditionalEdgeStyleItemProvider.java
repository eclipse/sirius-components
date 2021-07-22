/**
 * Copyright (c) 2021 Obeo.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *      Obeo - initial API and implementation
 */
package org.eclipse.sirius.web.view.provider;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.sirius.web.view.ConditionalEdgeStyle;
import org.eclipse.sirius.web.view.ViewPackage;

/**
 * This is the item provider adapter for a {@link org.eclipse.sirius.web.view.ConditionalEdgeStyle} object. <!--
 * begin-user-doc --> <!-- end-user-doc -->
 *
 * @generated
 */
public class ConditionalEdgeStyleItemProvider extends ConditionalItemProvider {
    /**
     * This constructs an instance from a factory and a notifier. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public ConditionalEdgeStyleItemProvider(AdapterFactory adapterFactory) {
        super(adapterFactory);
    }

    /**
     * This returns the property descriptors for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
        if (this.itemPropertyDescriptors == null) {
            super.getPropertyDescriptors(object);

            this.addColorPropertyDescriptor(object);
            this.addBorderColorPropertyDescriptor(object);
            this.addFontSizePropertyDescriptor(object);
            this.addLineStylePropertyDescriptor(object);
            this.addSourceArrowStylePropertyDescriptor(object);
            this.addTargetArrowStylePropertyDescriptor(object);
            this.addEdgeWidthPropertyDescriptor(object);
        }
        return this.itemPropertyDescriptors;
    }

    /**
     * This adds a property descriptor for the Color feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addColorPropertyDescriptor(Object object) {
        this.itemPropertyDescriptors
                .add(this.createItemPropertyDescriptor(((ComposeableAdapterFactory) this.adapterFactory).getRootAdapterFactory(), this.getResourceLocator(), this.getString("_UI_Style_color_feature"), //$NON-NLS-1$
                        this.getString("_UI_PropertyDescriptor_description", "_UI_Style_color_feature", "_UI_Style_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                        ViewPackage.Literals.STYLE__COLOR, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Border Color feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addBorderColorPropertyDescriptor(Object object) {
        this.itemPropertyDescriptors.add(
                this.createItemPropertyDescriptor(((ComposeableAdapterFactory) this.adapterFactory).getRootAdapterFactory(), this.getResourceLocator(), this.getString("_UI_Style_borderColor_feature"), //$NON-NLS-1$
                        this.getString("_UI_PropertyDescriptor_description", "_UI_Style_borderColor_feature", "_UI_Style_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                        ViewPackage.Literals.STYLE__BORDER_COLOR, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Font Size feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addFontSizePropertyDescriptor(Object object) {
        this.itemPropertyDescriptors.add(
                this.createItemPropertyDescriptor(((ComposeableAdapterFactory) this.adapterFactory).getRootAdapterFactory(), this.getResourceLocator(), this.getString("_UI_Style_fontSize_feature"), //$NON-NLS-1$
                        this.getString("_UI_PropertyDescriptor_description", "_UI_Style_fontSize_feature", "_UI_Style_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                        ViewPackage.Literals.STYLE__FONT_SIZE, true, false, false, ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Line Style feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addLineStylePropertyDescriptor(Object object) {
        this.itemPropertyDescriptors.add(this.createItemPropertyDescriptor(((ComposeableAdapterFactory) this.adapterFactory).getRootAdapterFactory(), this.getResourceLocator(),
                this.getString("_UI_EdgeStyle_lineStyle_feature"), //$NON-NLS-1$
                this.getString("_UI_PropertyDescriptor_description", "_UI_EdgeStyle_lineStyle_feature", "_UI_EdgeStyle_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                ViewPackage.Literals.EDGE_STYLE__LINE_STYLE, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Source Arrow Style feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addSourceArrowStylePropertyDescriptor(Object object) {
        this.itemPropertyDescriptors.add(this.createItemPropertyDescriptor(((ComposeableAdapterFactory) this.adapterFactory).getRootAdapterFactory(), this.getResourceLocator(),
                this.getString("_UI_EdgeStyle_sourceArrowStyle_feature"), //$NON-NLS-1$
                this.getString("_UI_PropertyDescriptor_description", "_UI_EdgeStyle_sourceArrowStyle_feature", "_UI_EdgeStyle_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                ViewPackage.Literals.EDGE_STYLE__SOURCE_ARROW_STYLE, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Target Arrow Style feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addTargetArrowStylePropertyDescriptor(Object object) {
        this.itemPropertyDescriptors.add(this.createItemPropertyDescriptor(((ComposeableAdapterFactory) this.adapterFactory).getRootAdapterFactory(), this.getResourceLocator(),
                this.getString("_UI_EdgeStyle_targetArrowStyle_feature"), //$NON-NLS-1$
                this.getString("_UI_PropertyDescriptor_description", "_UI_EdgeStyle_targetArrowStyle_feature", "_UI_EdgeStyle_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                ViewPackage.Literals.EDGE_STYLE__TARGET_ARROW_STYLE, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
    }

    /**
     * This adds a property descriptor for the Edge Width feature. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void addEdgeWidthPropertyDescriptor(Object object) {
        this.itemPropertyDescriptors.add(this.createItemPropertyDescriptor(((ComposeableAdapterFactory) this.adapterFactory).getRootAdapterFactory(), this.getResourceLocator(),
                this.getString("_UI_EdgeStyle_edgeWidth_feature"), //$NON-NLS-1$
                this.getString("_UI_PropertyDescriptor_description", "_UI_EdgeStyle_edgeWidth_feature", "_UI_EdgeStyle_type"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                ViewPackage.Literals.EDGE_STYLE__EDGE_WIDTH, true, false, false, ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE, null, null));
    }

    /**
     * This returns ConditionalEdgeStyle.gif. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated NOT
     */
    @Override
    public Object getImage(Object object) {
        return this.overlayImage(object, this.getResourceLocator().getImage("full/obj16/ConditionalStyle.svg")); //$NON-NLS-1$
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected boolean shouldComposeCreationImage() {
        return true;
    }

    /**
     * This returns the label text for the adapted class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getText(Object object) {
        String label = ((ConditionalEdgeStyle) object).getCondition();
        return label == null || label.length() == 0 ? this.getString("_UI_ConditionalEdgeStyle_type") : //$NON-NLS-1$
                this.getString("_UI_ConditionalEdgeStyle_type") + " " + label; //$NON-NLS-1$ //$NON-NLS-2$
    }

    /**
     * This handles model notifications by calling {@link #updateChildren} to update any cached children and by creating
     * a viewer notification, which it passes to {@link #fireNotifyChanged}. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     */
    @Override
    public void notifyChanged(Notification notification) {
        this.updateChildren(notification);

        switch (notification.getFeatureID(ConditionalEdgeStyle.class)) {
        case ViewPackage.CONDITIONAL_EDGE_STYLE__COLOR:
        case ViewPackage.CONDITIONAL_EDGE_STYLE__BORDER_COLOR:
        case ViewPackage.CONDITIONAL_EDGE_STYLE__FONT_SIZE:
        case ViewPackage.CONDITIONAL_EDGE_STYLE__LINE_STYLE:
        case ViewPackage.CONDITIONAL_EDGE_STYLE__SOURCE_ARROW_STYLE:
        case ViewPackage.CONDITIONAL_EDGE_STYLE__TARGET_ARROW_STYLE:
        case ViewPackage.CONDITIONAL_EDGE_STYLE__EDGE_WIDTH:
            this.fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
            return;
        }
        super.notifyChanged(notification);
    }

    /**
     * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children that can be created
     * under this object. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
        super.collectNewChildDescriptors(newChildDescriptors, object);
    }

}
