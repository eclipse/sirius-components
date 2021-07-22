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
import org.eclipse.sirius.web.view.NodeStyle;
import org.eclipse.sirius.web.view.ViewPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Node Style</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.web.view.impl.NodeStyleImpl#isListMode <em>List Mode</em>}</li>
 * <li>{@link org.eclipse.sirius.web.view.impl.NodeStyleImpl#getBorderRadius <em>Border Radius</em>}</li>
 * <li>{@link org.eclipse.sirius.web.view.impl.NodeStyleImpl#getShape <em>Shape</em>}</li>
 * <li>{@link org.eclipse.sirius.web.view.impl.NodeStyleImpl#getBorderSize <em>Border Size</em>}</li>
 * <li>{@link org.eclipse.sirius.web.view.impl.NodeStyleImpl#getLabelColor <em>Label Color</em>}</li>
 * <li>{@link org.eclipse.sirius.web.view.impl.NodeStyleImpl#isItalic <em>Italic</em>}</li>
 * <li>{@link org.eclipse.sirius.web.view.impl.NodeStyleImpl#isBold <em>Bold</em>}</li>
 * <li>{@link org.eclipse.sirius.web.view.impl.NodeStyleImpl#isUnderline <em>Underline</em>}</li>
 * <li>{@link org.eclipse.sirius.web.view.impl.NodeStyleImpl#isStrikeThrough <em>Strike Through</em>}</li>
 * </ul>
 *
 * @generated
 */
public class NodeStyleImpl extends StyleImpl implements NodeStyle {
    /**
     * The default value of the '{@link #isListMode() <em>List Mode</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #isListMode()
     * @generated
     * @ordered
     */
    protected static final boolean LIST_MODE_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isListMode() <em>List Mode</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #isListMode()
     * @generated
     * @ordered
     */
    protected boolean listMode = LIST_MODE_EDEFAULT;

    /**
     * The default value of the '{@link #getBorderRadius() <em>Border Radius</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #getBorderRadius()
     * @generated
     * @ordered
     */
    protected static final int BORDER_RADIUS_EDEFAULT = 3;

    /**
     * The cached value of the '{@link #getBorderRadius() <em>Border Radius</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #getBorderRadius()
     * @generated
     * @ordered
     */
    protected int borderRadius = BORDER_RADIUS_EDEFAULT;

    /**
     * The default value of the '{@link #getShape() <em>Shape</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getShape()
     * @generated
     * @ordered
     */
    protected static final String SHAPE_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getShape() <em>Shape</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see #getShape()
     * @generated
     * @ordered
     */
    protected String shape = SHAPE_EDEFAULT;

    /**
     * The default value of the '{@link #getBorderSize() <em>Border Size</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getBorderSize()
     * @generated
     * @ordered
     */
    protected static final int BORDER_SIZE_EDEFAULT = 1;

    /**
     * The cached value of the '{@link #getBorderSize() <em>Border Size</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getBorderSize()
     * @generated
     * @ordered
     */
    protected int borderSize = BORDER_SIZE_EDEFAULT;

    /**
     * The default value of the '{@link #getLabelColor() <em>Label Color</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getLabelColor()
     * @generated
     * @ordered
     */
    protected static final String LABEL_COLOR_EDEFAULT = "black"; //$NON-NLS-1$

    /**
     * The cached value of the '{@link #getLabelColor() <em>Label Color</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #getLabelColor()
     * @generated
     * @ordered
     */
    protected String labelColor = LABEL_COLOR_EDEFAULT;

    /**
     * The default value of the '{@link #isItalic() <em>Italic</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #isItalic()
     * @generated
     * @ordered
     */
    protected static final boolean ITALIC_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isItalic() <em>Italic</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #isItalic()
     * @generated
     * @ordered
     */
    protected boolean italic = ITALIC_EDEFAULT;

    /**
     * The default value of the '{@link #isBold() <em>Bold</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see #isBold()
     * @generated
     * @ordered
     */
    protected static final boolean BOLD_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isBold() <em>Bold</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @see #isBold()
     * @generated
     * @ordered
     */
    protected boolean bold = BOLD_EDEFAULT;

    /**
     * The default value of the '{@link #isUnderline() <em>Underline</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #isUnderline()
     * @generated
     * @ordered
     */
    protected static final boolean UNDERLINE_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isUnderline() <em>Underline</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #isUnderline()
     * @generated
     * @ordered
     */
    protected boolean underline = UNDERLINE_EDEFAULT;

    /**
     * The default value of the '{@link #isStrikeThrough() <em>Strike Through</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #isStrikeThrough()
     * @generated
     * @ordered
     */
    protected static final boolean STRIKE_THROUGH_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isStrikeThrough() <em>Strike Through</em>}' attribute. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #isStrikeThrough()
     * @generated
     * @ordered
     */
    protected boolean strikeThrough = STRIKE_THROUGH_EDEFAULT;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected NodeStyleImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return ViewPackage.Literals.NODE_STYLE;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public boolean isListMode() {
        return this.listMode;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setListMode(boolean newListMode) {
        boolean oldListMode = this.listMode;
        this.listMode = newListMode;
        if (this.eNotificationRequired())
            this.eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.NODE_STYLE__LIST_MODE, oldListMode, this.listMode));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public int getBorderRadius() {
        return this.borderRadius;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setBorderRadius(int newBorderRadius) {
        int oldBorderRadius = this.borderRadius;
        this.borderRadius = newBorderRadius;
        if (this.eNotificationRequired())
            this.eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.NODE_STYLE__BORDER_RADIUS, oldBorderRadius, this.borderRadius));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getShape() {
        return this.shape;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setShape(String newShape) {
        String oldShape = this.shape;
        this.shape = newShape;
        if (this.eNotificationRequired())
            this.eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.NODE_STYLE__SHAPE, oldShape, this.shape));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public int getBorderSize() {
        return this.borderSize;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setBorderSize(int newBorderSize) {
        int oldBorderSize = this.borderSize;
        this.borderSize = newBorderSize;
        if (this.eNotificationRequired())
            this.eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.NODE_STYLE__BORDER_SIZE, oldBorderSize, this.borderSize));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getLabelColor() {
        return this.labelColor;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setLabelColor(String newLabelColor) {
        String oldLabelColor = this.labelColor;
        this.labelColor = newLabelColor;
        if (this.eNotificationRequired())
            this.eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.NODE_STYLE__LABEL_COLOR, oldLabelColor, this.labelColor));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public boolean isItalic() {
        return this.italic;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setItalic(boolean newItalic) {
        boolean oldItalic = this.italic;
        this.italic = newItalic;
        if (this.eNotificationRequired())
            this.eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.NODE_STYLE__ITALIC, oldItalic, this.italic));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public boolean isBold() {
        return this.bold;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setBold(boolean newBold) {
        boolean oldBold = this.bold;
        this.bold = newBold;
        if (this.eNotificationRequired())
            this.eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.NODE_STYLE__BOLD, oldBold, this.bold));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public boolean isUnderline() {
        return this.underline;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setUnderline(boolean newUnderline) {
        boolean oldUnderline = this.underline;
        this.underline = newUnderline;
        if (this.eNotificationRequired())
            this.eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.NODE_STYLE__UNDERLINE, oldUnderline, this.underline));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public boolean isStrikeThrough() {
        return this.strikeThrough;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public void setStrikeThrough(boolean newStrikeThrough) {
        boolean oldStrikeThrough = this.strikeThrough;
        this.strikeThrough = newStrikeThrough;
        if (this.eNotificationRequired())
            this.eNotify(new ENotificationImpl(this, Notification.SET, ViewPackage.NODE_STYLE__STRIKE_THROUGH, oldStrikeThrough, this.strikeThrough));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
        case ViewPackage.NODE_STYLE__LIST_MODE:
            return this.isListMode();
        case ViewPackage.NODE_STYLE__BORDER_RADIUS:
            return this.getBorderRadius();
        case ViewPackage.NODE_STYLE__SHAPE:
            return this.getShape();
        case ViewPackage.NODE_STYLE__BORDER_SIZE:
            return this.getBorderSize();
        case ViewPackage.NODE_STYLE__LABEL_COLOR:
            return this.getLabelColor();
        case ViewPackage.NODE_STYLE__ITALIC:
            return this.isItalic();
        case ViewPackage.NODE_STYLE__BOLD:
            return this.isBold();
        case ViewPackage.NODE_STYLE__UNDERLINE:
            return this.isUnderline();
        case ViewPackage.NODE_STYLE__STRIKE_THROUGH:
            return this.isStrikeThrough();
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
        case ViewPackage.NODE_STYLE__LIST_MODE:
            this.setListMode((Boolean) newValue);
            return;
        case ViewPackage.NODE_STYLE__BORDER_RADIUS:
            this.setBorderRadius((Integer) newValue);
            return;
        case ViewPackage.NODE_STYLE__SHAPE:
            this.setShape((String) newValue);
            return;
        case ViewPackage.NODE_STYLE__BORDER_SIZE:
            this.setBorderSize((Integer) newValue);
            return;
        case ViewPackage.NODE_STYLE__LABEL_COLOR:
            this.setLabelColor((String) newValue);
            return;
        case ViewPackage.NODE_STYLE__ITALIC:
            this.setItalic((Boolean) newValue);
            return;
        case ViewPackage.NODE_STYLE__BOLD:
            this.setBold((Boolean) newValue);
            return;
        case ViewPackage.NODE_STYLE__UNDERLINE:
            this.setUnderline((Boolean) newValue);
            return;
        case ViewPackage.NODE_STYLE__STRIKE_THROUGH:
            this.setStrikeThrough((Boolean) newValue);
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
        case ViewPackage.NODE_STYLE__LIST_MODE:
            this.setListMode(LIST_MODE_EDEFAULT);
            return;
        case ViewPackage.NODE_STYLE__BORDER_RADIUS:
            this.setBorderRadius(BORDER_RADIUS_EDEFAULT);
            return;
        case ViewPackage.NODE_STYLE__SHAPE:
            this.setShape(SHAPE_EDEFAULT);
            return;
        case ViewPackage.NODE_STYLE__BORDER_SIZE:
            this.setBorderSize(BORDER_SIZE_EDEFAULT);
            return;
        case ViewPackage.NODE_STYLE__LABEL_COLOR:
            this.setLabelColor(LABEL_COLOR_EDEFAULT);
            return;
        case ViewPackage.NODE_STYLE__ITALIC:
            this.setItalic(ITALIC_EDEFAULT);
            return;
        case ViewPackage.NODE_STYLE__BOLD:
            this.setBold(BOLD_EDEFAULT);
            return;
        case ViewPackage.NODE_STYLE__UNDERLINE:
            this.setUnderline(UNDERLINE_EDEFAULT);
            return;
        case ViewPackage.NODE_STYLE__STRIKE_THROUGH:
            this.setStrikeThrough(STRIKE_THROUGH_EDEFAULT);
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
        case ViewPackage.NODE_STYLE__LIST_MODE:
            return this.listMode != LIST_MODE_EDEFAULT;
        case ViewPackage.NODE_STYLE__BORDER_RADIUS:
            return this.borderRadius != BORDER_RADIUS_EDEFAULT;
        case ViewPackage.NODE_STYLE__SHAPE:
            return SHAPE_EDEFAULT == null ? this.shape != null : !SHAPE_EDEFAULT.equals(this.shape);
        case ViewPackage.NODE_STYLE__BORDER_SIZE:
            return this.borderSize != BORDER_SIZE_EDEFAULT;
        case ViewPackage.NODE_STYLE__LABEL_COLOR:
            return LABEL_COLOR_EDEFAULT == null ? this.labelColor != null : !LABEL_COLOR_EDEFAULT.equals(this.labelColor);
        case ViewPackage.NODE_STYLE__ITALIC:
            return this.italic != ITALIC_EDEFAULT;
        case ViewPackage.NODE_STYLE__BOLD:
            return this.bold != BOLD_EDEFAULT;
        case ViewPackage.NODE_STYLE__UNDERLINE:
            return this.underline != UNDERLINE_EDEFAULT;
        case ViewPackage.NODE_STYLE__STRIKE_THROUGH:
            return this.strikeThrough != STRIKE_THROUGH_EDEFAULT;
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
        result.append(" (listMode: "); //$NON-NLS-1$
        result.append(this.listMode);
        result.append(", borderRadius: "); //$NON-NLS-1$
        result.append(this.borderRadius);
        result.append(", shape: "); //$NON-NLS-1$
        result.append(this.shape);
        result.append(", borderSize: "); //$NON-NLS-1$
        result.append(this.borderSize);
        result.append(", labelColor: "); //$NON-NLS-1$
        result.append(this.labelColor);
        result.append(", italic: "); //$NON-NLS-1$
        result.append(this.italic);
        result.append(", bold: "); //$NON-NLS-1$
        result.append(this.bold);
        result.append(", underline: "); //$NON-NLS-1$
        result.append(this.underline);
        result.append(", strikeThrough: "); //$NON-NLS-1$
        result.append(this.strikeThrough);
        result.append(')');
        return result.toString();
    }

} // NodeStyleImpl
