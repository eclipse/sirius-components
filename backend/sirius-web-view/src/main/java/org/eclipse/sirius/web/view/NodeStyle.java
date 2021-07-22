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
package org.eclipse.sirius.web.view;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Node Style</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.web.view.NodeStyle#isListMode <em>List Mode</em>}</li>
 * <li>{@link org.eclipse.sirius.web.view.NodeStyle#getBorderRadius <em>Border Radius</em>}</li>
 * <li>{@link org.eclipse.sirius.web.view.NodeStyle#getShape <em>Shape</em>}</li>
 * <li>{@link org.eclipse.sirius.web.view.NodeStyle#getBorderSize <em>Border Size</em>}</li>
 * <li>{@link org.eclipse.sirius.web.view.NodeStyle#getLabelColor <em>Label Color</em>}</li>
 * <li>{@link org.eclipse.sirius.web.view.NodeStyle#isItalic <em>Italic</em>}</li>
 * <li>{@link org.eclipse.sirius.web.view.NodeStyle#isBold <em>Bold</em>}</li>
 * <li>{@link org.eclipse.sirius.web.view.NodeStyle#isUnderline <em>Underline</em>}</li>
 * <li>{@link org.eclipse.sirius.web.view.NodeStyle#isStrikeThrough <em>Strike Through</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.web.view.ViewPackage#getNodeStyle()
 * @model
 * @generated
 */
public interface NodeStyle extends Style {

    /**
     * Returns the value of the '<em><b>List Mode</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the value of the '<em>List Mode</em>' attribute.
     * @see #setListMode(boolean)
     * @see org.eclipse.sirius.web.view.ViewPackage#getNodeStyle_ListMode()
     * @model required="true"
     * @generated
     */
    boolean isListMode();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.web.view.NodeStyle#isListMode <em>List Mode</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>List Mode</em>' attribute.
     * @see #isListMode()
     * @generated
     */
    void setListMode(boolean value);

    /**
     * Returns the value of the '<em><b>Border Radius</b></em>' attribute. The default value is <code>"0"</code>. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the value of the '<em>Border Radius</em>' attribute.
     * @see #setBorderRadius(int)
     * @see org.eclipse.sirius.web.view.ViewPackage#getNodeStyle_BorderRadius()
     * @model default="0" required="true"
     * @generated
     */
    int getBorderRadius();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.web.view.NodeStyle#getBorderRadius <em>Border Radius</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Border Radius</em>' attribute.
     * @see #getBorderRadius()
     * @generated
     */
    void setBorderRadius(int value);

    /**
     * Returns the value of the '<em><b>Shape</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the value of the '<em>Shape</em>' attribute.
     * @see #setShape(String)
     * @see org.eclipse.sirius.web.view.ViewPackage#getNodeStyle_Shape()
     * @model
     * @generated
     */
    String getShape();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.web.view.NodeStyle#getShape <em>Shape</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Shape</em>' attribute.
     * @see #getShape()
     * @generated
     */
    void setShape(String value);

    /**
     * Returns the value of the '<em><b>Border Size</b></em>' attribute. The default value is <code>"1"</code>. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the value of the '<em>Border Size</em>' attribute.
     * @see #setBorderSize(int)
     * @see org.eclipse.sirius.web.view.ViewPackage#getNodeStyle_BorderSize()
     * @model default="1" required="true"
     * @generated
     */
    int getBorderSize();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.web.view.NodeStyle#getBorderSize <em>Border Size</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Border Size</em>' attribute.
     * @see #getBorderSize()
     * @generated
     */
    void setBorderSize(int value);

    /**
     * Returns the value of the '<em><b>Label Color</b></em>' attribute. The default value is <code>"black"</code>. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the value of the '<em>Label Color</em>' attribute.
     * @see #setLabelColor(String)
     * @see org.eclipse.sirius.web.view.ViewPackage#getNodeStyle_LabelColor()
     * @model default="black"
     * @generated
     */
    String getLabelColor();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.web.view.NodeStyle#getLabelColor <em>Label Color</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Label Color</em>' attribute.
     * @see #getLabelColor()
     * @generated
     */
    void setLabelColor(String value);

    /**
     * Returns the value of the '<em><b>Italic</b></em>' attribute. The default value is <code>"false"</code>. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the value of the '<em>Italic</em>' attribute.
     * @see #setItalic(boolean)
     * @see org.eclipse.sirius.web.view.ViewPackage#getNodeStyle_Italic()
     * @model default="false" required="true"
     * @generated
     */
    boolean isItalic();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.web.view.NodeStyle#isItalic <em>Italic</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Italic</em>' attribute.
     * @see #isItalic()
     * @generated
     */
    void setItalic(boolean value);

    /**
     * Returns the value of the '<em><b>Bold</b></em>' attribute. The default value is <code>"false"</code>. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the value of the '<em>Bold</em>' attribute.
     * @see #setBold(boolean)
     * @see org.eclipse.sirius.web.view.ViewPackage#getNodeStyle_Bold()
     * @model default="false" required="true"
     * @generated
     */
    boolean isBold();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.web.view.NodeStyle#isBold <em>Bold</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Bold</em>' attribute.
     * @see #isBold()
     * @generated
     */
    void setBold(boolean value);

    /**
     * Returns the value of the '<em><b>Underline</b></em>' attribute. The default value is <code>"false"</code>. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the value of the '<em>Underline</em>' attribute.
     * @see #setUnderline(boolean)
     * @see org.eclipse.sirius.web.view.ViewPackage#getNodeStyle_Underline()
     * @model default="false" required="true"
     * @generated
     */
    boolean isUnderline();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.web.view.NodeStyle#isUnderline <em>Underline</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Underline</em>' attribute.
     * @see #isUnderline()
     * @generated
     */
    void setUnderline(boolean value);

    /**
     * Returns the value of the '<em><b>Strike Through</b></em>' attribute. The default value is <code>"false"</code>.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the value of the '<em>Strike Through</em>' attribute.
     * @see #setStrikeThrough(boolean)
     * @see org.eclipse.sirius.web.view.ViewPackage#getNodeStyle_StrikeThrough()
     * @model default="false" required="true"
     * @generated
     */
    boolean isStrikeThrough();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.web.view.NodeStyle#isStrikeThrough <em>Strike Through</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Strike Through</em>' attribute.
     * @see #isStrikeThrough()
     * @generated
     */
    void setStrikeThrough(boolean value);
} // NodeStyle
