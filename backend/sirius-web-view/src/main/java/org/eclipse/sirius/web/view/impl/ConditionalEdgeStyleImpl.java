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
import org.eclipse.sirius.web.view.ConditionalEdgeStyle;
import org.eclipse.sirius.web.view.EdgeStyle;
import org.eclipse.sirius.web.view.LineStyle;
import org.eclipse.sirius.web.view.Style;
import org.eclipse.sirius.web.view.ViewPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Conditional Edge Style</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.web.view.impl.ConditionalEdgeStyleImpl#getColor <em>Color</em>}</li>
 * <li>{@link org.eclipse.sirius.web.view.impl.ConditionalEdgeStyleImpl#getBorderColor <em>Border Color</em>}</li>
 * <li>{@link org.eclipse.sirius.web.view.impl.ConditionalEdgeStyleImpl#getFontSize <em>Font Size</em>}</li>
 * <li>{@link org.eclipse.sirius.web.view.impl.ConditionalEdgeStyleImpl#getLineStyle <em>Line Style</em>}</li>
 * <li>{@link org.eclipse.sirius.web.view.impl.ConditionalEdgeStyleImpl#getSourceArrowStyle <em>Source Arrow
 * Style</em>}</li>
 * <li>{@link org.eclipse.sirius.web.view.impl.ConditionalEdgeStyleImpl#getTargetArrowStyle <em>Target Arrow
 * Style</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ConditionalEdgeStyleImpl extends ConditionalImpl implements ConditionalEdgeStyle {
    /**
     * The default value of the '{@link #getColor() <em>Color</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getColor()
     * @generated
     * @ordered
     */
    protected static final String COLOR_EDEFAULT = "#E5F5F8"; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getColor() <em>Color</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see #getColor()
     * @generated
     * @ordered
     */
    protected String color = COLOR_EDEFAULT;

    /**
     * The default value of the '{@link #getBorderColor() <em>Border Color</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #getBorderColor()
     * @generated
     * @ordered
     */
    protected static final String BORDER_COLOR_EDEFAULT = "#33B0C3"; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getBorderColor() <em>Border Color</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getBorderColor()
     * @generated
     * @ordered
     */
    protected String borderColor = BORDER_COLOR_EDEFAULT;

    /**
     * The default value of the '{@link #getFontSize() <em>Font Size</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getFontSize()
     * @generated
     * @ordered
     */
    protected static final int FONT_SIZE_EDEFAULT = 14;

    /**
     * The cached value of the '{@link #getFontSize() <em>Font Size</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getFontSize()
     * @generated
     * @ordered
     */
    protected int fontSize = FONT_SIZE_EDEFAULT;

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
    protected static final ArrowStyle TARGET_ARROW_STYLE_EDEFAULT = ArrowStyle.INPUT_ARROW;

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
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected ConditionalEdgeStyleImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ViewPackage.Literals.CONDITIONAL_EDGE_STYLE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getColor() {
        return this.color;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setColor(String newColor) {
        String oldColor = this.color;
        this.color = newColor;
        if (this.eNotificationRequired())
            this.eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.CONDITIONAL_EDGE_STYLE__COLOR, oldColor, this.color));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getBorderColor() {
        return this.borderColor;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setBorderColor(String newBorderColor) {
        String oldBorderColor = this.borderColor;
        this.borderColor = newBorderColor;
        if (this.eNotificationRequired())
            this.eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.CONDITIONAL_EDGE_STYLE__BORDER_COLOR, oldBorderColor, this.borderColor));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public int getFontSize() {
        return this.fontSize;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setFontSize(int newFontSize) {
        int oldFontSize = this.fontSize;
        this.fontSize = newFontSize;
        if (this.eNotificationRequired())
            this.eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.CONDITIONAL_EDGE_STYLE__FONT_SIZE, oldFontSize, this.fontSize));
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
            this.eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.CONDITIONAL_EDGE_STYLE__LINE_STYLE, oldLineStyle, this.lineStyle));
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
            this.eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.CONDITIONAL_EDGE_STYLE__SOURCE_ARROW_STYLE, oldSourceArrowStyle, this.sourceArrowStyle));
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
            this.eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.CONDITIONAL_EDGE_STYLE__TARGET_ARROW_STYLE, oldTargetArrowStyle, this.targetArrowStyle));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case ViewPackage.CONDITIONAL_EDGE_STYLE__COLOR:
            return this.getColor();
        case ViewPackage.CONDITIONAL_EDGE_STYLE__BORDER_COLOR:
            return this.getBorderColor();
        case ViewPackage.CONDITIONAL_EDGE_STYLE__FONT_SIZE:
            return this.getFontSize();
        case ViewPackage.CONDITIONAL_EDGE_STYLE__LINE_STYLE:
            return this.getLineStyle();
        case ViewPackage.CONDITIONAL_EDGE_STYLE__SOURCE_ARROW_STYLE:
            return this.getSourceArrowStyle();
        case ViewPackage.CONDITIONAL_EDGE_STYLE__TARGET_ARROW_STYLE:
            return this.getTargetArrowStyle();
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
        case ViewPackage.CONDITIONAL_EDGE_STYLE__COLOR:
            this.setColor((String) newValue);
            return;
        case ViewPackage.CONDITIONAL_EDGE_STYLE__BORDER_COLOR:
            this.setBorderColor((String) newValue);
            return;
        case ViewPackage.CONDITIONAL_EDGE_STYLE__FONT_SIZE:
            this.setFontSize((Integer) newValue);
            return;
        case ViewPackage.CONDITIONAL_EDGE_STYLE__LINE_STYLE:
            this.setLineStyle((LineStyle) newValue);
            return;
        case ViewPackage.CONDITIONAL_EDGE_STYLE__SOURCE_ARROW_STYLE:
            this.setSourceArrowStyle((ArrowStyle) newValue);
            return;
        case ViewPackage.CONDITIONAL_EDGE_STYLE__TARGET_ARROW_STYLE:
            this.setTargetArrowStyle((ArrowStyle) newValue);
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
        case ViewPackage.CONDITIONAL_EDGE_STYLE__COLOR:
            this.setColor(COLOR_EDEFAULT);
            return;
        case ViewPackage.CONDITIONAL_EDGE_STYLE__BORDER_COLOR:
            this.setBorderColor(BORDER_COLOR_EDEFAULT);
            return;
        case ViewPackage.CONDITIONAL_EDGE_STYLE__FONT_SIZE:
            this.setFontSize(FONT_SIZE_EDEFAULT);
            return;
        case ViewPackage.CONDITIONAL_EDGE_STYLE__LINE_STYLE:
            this.setLineStyle(LINE_STYLE_EDEFAULT);
            return;
        case ViewPackage.CONDITIONAL_EDGE_STYLE__SOURCE_ARROW_STYLE:
            this.setSourceArrowStyle(SOURCE_ARROW_STYLE_EDEFAULT);
            return;
        case ViewPackage.CONDITIONAL_EDGE_STYLE__TARGET_ARROW_STYLE:
            this.setTargetArrowStyle(TARGET_ARROW_STYLE_EDEFAULT);
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
        case ViewPackage.CONDITIONAL_EDGE_STYLE__COLOR:
            return COLOR_EDEFAULT == null ? this.color != null : !COLOR_EDEFAULT.equals(this.color);
        case ViewPackage.CONDITIONAL_EDGE_STYLE__BORDER_COLOR:
            return BORDER_COLOR_EDEFAULT == null ? this.borderColor != null : !BORDER_COLOR_EDEFAULT.equals(this.borderColor);
        case ViewPackage.CONDITIONAL_EDGE_STYLE__FONT_SIZE:
            return this.fontSize != FONT_SIZE_EDEFAULT;
        case ViewPackage.CONDITIONAL_EDGE_STYLE__LINE_STYLE:
            return this.lineStyle != LINE_STYLE_EDEFAULT;
        case ViewPackage.CONDITIONAL_EDGE_STYLE__SOURCE_ARROW_STYLE:
            return this.sourceArrowStyle != SOURCE_ARROW_STYLE_EDEFAULT;
        case ViewPackage.CONDITIONAL_EDGE_STYLE__TARGET_ARROW_STYLE:
            return this.targetArrowStyle != TARGET_ARROW_STYLE_EDEFAULT;
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
        if (baseClass == Style.class) {
            switch (derivedFeatureID) {
            case ViewPackage.CONDITIONAL_EDGE_STYLE__COLOR:
                return ViewPackage.STYLE__COLOR;
            case ViewPackage.CONDITIONAL_EDGE_STYLE__BORDER_COLOR:
                return ViewPackage.STYLE__BORDER_COLOR;
            case ViewPackage.CONDITIONAL_EDGE_STYLE__FONT_SIZE:
                return ViewPackage.STYLE__FONT_SIZE;
            default:
                return -1;
            }
        }
        if (baseClass == EdgeStyle.class) {
            switch (derivedFeatureID) {
            case ViewPackage.CONDITIONAL_EDGE_STYLE__LINE_STYLE:
                return ViewPackage.EDGE_STYLE__LINE_STYLE;
            case ViewPackage.CONDITIONAL_EDGE_STYLE__SOURCE_ARROW_STYLE:
                return ViewPackage.EDGE_STYLE__SOURCE_ARROW_STYLE;
            case ViewPackage.CONDITIONAL_EDGE_STYLE__TARGET_ARROW_STYLE:
                return ViewPackage.EDGE_STYLE__TARGET_ARROW_STYLE;
            default:
                return -1;
            }
        }
        return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
        if (baseClass == Style.class) {
            switch (baseFeatureID) {
            case ViewPackage.STYLE__COLOR:
                return ViewPackage.CONDITIONAL_EDGE_STYLE__COLOR;
            case ViewPackage.STYLE__BORDER_COLOR:
                return ViewPackage.CONDITIONAL_EDGE_STYLE__BORDER_COLOR;
            case ViewPackage.STYLE__FONT_SIZE:
                return ViewPackage.CONDITIONAL_EDGE_STYLE__FONT_SIZE;
            default:
                return -1;
            }
        }
        if (baseClass == EdgeStyle.class) {
            switch (baseFeatureID) {
            case ViewPackage.EDGE_STYLE__LINE_STYLE:
                return ViewPackage.CONDITIONAL_EDGE_STYLE__LINE_STYLE;
            case ViewPackage.EDGE_STYLE__SOURCE_ARROW_STYLE:
                return ViewPackage.CONDITIONAL_EDGE_STYLE__SOURCE_ARROW_STYLE;
            case ViewPackage.EDGE_STYLE__TARGET_ARROW_STYLE:
                return ViewPackage.CONDITIONAL_EDGE_STYLE__TARGET_ARROW_STYLE;
            default:
                return -1;
            }
        }
        return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
        result.append(" (color: "); //$NON-NLS-1$
        result.append(this.color);
        result.append(", borderColor: "); //$NON-NLS-1$
        result.append(this.borderColor);
        result.append(", fontSize: "); //$NON-NLS-1$
        result.append(this.fontSize);
        result.append(", lineStyle: "); //$NON-NLS-1$
        result.append(this.lineStyle);
        result.append(", sourceArrowStyle: "); //$NON-NLS-1$
        result.append(this.sourceArrowStyle);
        result.append(", targetArrowStyle: "); //$NON-NLS-1$
        result.append(this.targetArrowStyle);
        result.append(')');
        return result.toString();
    }

} // ConditionalEdgeStyleImpl
