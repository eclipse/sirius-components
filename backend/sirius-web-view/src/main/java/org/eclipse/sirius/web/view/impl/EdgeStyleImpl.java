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
package org.eclipse.sirius.web.view.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.sirius.web.view.ArrowStyle;
import org.eclipse.sirius.web.view.EdgeStyle;
import org.eclipse.sirius.web.view.LineStyle;
import org.eclipse.sirius.web.view.ViewPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Edge Style</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.web.view.impl.EdgeStyleImpl#getLineStyle <em>Line Style</em>}</li>
 * <li>{@link org.eclipse.sirius.web.view.impl.EdgeStyleImpl#getSourceArrowStyle <em>Source Arrow Style</em>}</li>
 * <li>{@link org.eclipse.sirius.web.view.impl.EdgeStyleImpl#getTargetArrowStyle <em>Target Arrow Style</em>}</li>
 * <li>{@link org.eclipse.sirius.web.view.impl.EdgeStyleImpl#getEdgeWidth <em>Edge Width</em>}</li>
 * </ul>
 *
 * @generated
 */
public class EdgeStyleImpl extends StyleImpl implements EdgeStyle {
    /**
     * The default value of the '{@link #getLineStyle() <em>Line Style</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getLineStyle()
     * @generated
     * @ordered
     */
    protected static final LineStyle LINE_STYLE_EDEFAULT = LineStyle.SOLID;

    /**
     * The cached value of the '{@link #getLineStyle() <em>Line Style</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getLineStyle()
     * @generated
     * @ordered
     */
    protected LineStyle lineStyle = LINE_STYLE_EDEFAULT;

    /**
     * The default value of the '{@link #getSourceArrowStyle() <em>Source Arrow Style</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getSourceArrowStyle()
     * @generated
     * @ordered
     */
    protected static final ArrowStyle SOURCE_ARROW_STYLE_EDEFAULT = ArrowStyle.NONE;

    /**
     * The cached value of the '{@link #getSourceArrowStyle() <em>Source Arrow Style</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getSourceArrowStyle()
     * @generated
     * @ordered
     */
    protected ArrowStyle sourceArrowStyle = SOURCE_ARROW_STYLE_EDEFAULT;

    /**
     * The default value of the '{@link #getTargetArrowStyle() <em>Target Arrow Style</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getTargetArrowStyle()
     * @generated
     * @ordered
     */
    protected static final ArrowStyle TARGET_ARROW_STYLE_EDEFAULT = ArrowStyle.OUTPUT_ARROW;

    /**
     * The cached value of the '{@link #getTargetArrowStyle() <em>Target Arrow Style</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #getTargetArrowStyle()
     * @generated
     * @ordered
     */
    protected ArrowStyle targetArrowStyle = TARGET_ARROW_STYLE_EDEFAULT;

    /**
     * The default value of the '{@link #getEdgeWidth() <em>Edge Width</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getEdgeWidth()
     * @generated
     * @ordered
     */
    protected static final int EDGE_WIDTH_EDEFAULT = 1;

    /**
     * The cached value of the '{@link #getEdgeWidth() <em>Edge Width</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getEdgeWidth()
     * @generated
     * @ordered
     */
    protected int edgeWidth = EDGE_WIDTH_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected EdgeStyleImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ViewPackage.Literals.EDGE_STYLE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public LineStyle getLineStyle() {
        return this.lineStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setLineStyle(LineStyle newLineStyle) {
        LineStyle oldLineStyle = this.lineStyle;
        this.lineStyle = newLineStyle == null ? LINE_STYLE_EDEFAULT : newLineStyle;
        if (this.eNotificationRequired())
            this.eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.EDGE_STYLE__LINE_STYLE, oldLineStyle, this.lineStyle));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ArrowStyle getSourceArrowStyle() {
        return this.sourceArrowStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setSourceArrowStyle(ArrowStyle newSourceArrowStyle) {
        ArrowStyle oldSourceArrowStyle = this.sourceArrowStyle;
        this.sourceArrowStyle = newSourceArrowStyle == null ? SOURCE_ARROW_STYLE_EDEFAULT : newSourceArrowStyle;
        if (this.eNotificationRequired())
            this.eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.EDGE_STYLE__SOURCE_ARROW_STYLE, oldSourceArrowStyle, this.sourceArrowStyle));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ArrowStyle getTargetArrowStyle() {
        return this.targetArrowStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setTargetArrowStyle(ArrowStyle newTargetArrowStyle) {
        ArrowStyle oldTargetArrowStyle = this.targetArrowStyle;
        this.targetArrowStyle = newTargetArrowStyle == null ? TARGET_ARROW_STYLE_EDEFAULT : newTargetArrowStyle;
        if (this.eNotificationRequired())
            this.eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.EDGE_STYLE__TARGET_ARROW_STYLE, oldTargetArrowStyle, this.targetArrowStyle));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public int getEdgeWidth() {
        return this.edgeWidth;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setEdgeWidth(int newEdgeWidth) {
        int oldEdgeWidth = this.edgeWidth;
        this.edgeWidth = newEdgeWidth;
        if (this.eNotificationRequired())
            this.eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.EDGE_STYLE__EDGE_WIDTH, oldEdgeWidth, this.edgeWidth));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case ViewPackage.EDGE_STYLE__LINE_STYLE:
            return this.getLineStyle();
        case ViewPackage.EDGE_STYLE__SOURCE_ARROW_STYLE:
            return this.getSourceArrowStyle();
        case ViewPackage.EDGE_STYLE__TARGET_ARROW_STYLE:
            return this.getTargetArrowStyle();
        case ViewPackage.EDGE_STYLE__EDGE_WIDTH:
            return this.getEdgeWidth();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
        case ViewPackage.EDGE_STYLE__LINE_STYLE:
            this.setLineStyle((LineStyle) newValue);
            return;
        case ViewPackage.EDGE_STYLE__SOURCE_ARROW_STYLE:
            this.setSourceArrowStyle((ArrowStyle) newValue);
            return;
        case ViewPackage.EDGE_STYLE__TARGET_ARROW_STYLE:
            this.setTargetArrowStyle((ArrowStyle) newValue);
            return;
        case ViewPackage.EDGE_STYLE__EDGE_WIDTH:
            this.setEdgeWidth((Integer) newValue);
            return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
        case ViewPackage.EDGE_STYLE__LINE_STYLE:
            this.setLineStyle(LINE_STYLE_EDEFAULT);
            return;
        case ViewPackage.EDGE_STYLE__SOURCE_ARROW_STYLE:
            this.setSourceArrowStyle(SOURCE_ARROW_STYLE_EDEFAULT);
            return;
        case ViewPackage.EDGE_STYLE__TARGET_ARROW_STYLE:
            this.setTargetArrowStyle(TARGET_ARROW_STYLE_EDEFAULT);
            return;
        case ViewPackage.EDGE_STYLE__EDGE_WIDTH:
            this.setEdgeWidth(EDGE_WIDTH_EDEFAULT);
            return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
        case ViewPackage.EDGE_STYLE__LINE_STYLE:
            return this.lineStyle != LINE_STYLE_EDEFAULT;
        case ViewPackage.EDGE_STYLE__SOURCE_ARROW_STYLE:
            return this.sourceArrowStyle != SOURCE_ARROW_STYLE_EDEFAULT;
        case ViewPackage.EDGE_STYLE__TARGET_ARROW_STYLE:
            return this.targetArrowStyle != TARGET_ARROW_STYLE_EDEFAULT;
        case ViewPackage.EDGE_STYLE__EDGE_WIDTH:
            return this.edgeWidth != EDGE_WIDTH_EDEFAULT;
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String toString() {
        if (this.eIsProxy())
            return super.toString();

        StringBuilder result = new StringBuilder(super.toString());
        result.append(" (lineStyle: "); //$NON-NLS-1$
        result.append(this.lineStyle);
        result.append(", sourceArrowStyle: "); //$NON-NLS-1$
        result.append(this.sourceArrowStyle);
        result.append(", targetArrowStyle: "); //$NON-NLS-1$
        result.append(this.targetArrowStyle);
        result.append(", edgeWidth: "); //$NON-NLS-1$
        result.append(this.edgeWidth);
        result.append(')');
        return result.toString();
    }

} // EdgeStyleImpl
