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
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Edge Style</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.web.view.EdgeStyle#getLineStyle <em>Line Style</em>}</li>
 * <li>{@link org.eclipse.sirius.web.view.EdgeStyle#getSourceArrowStyle <em>Source Arrow Style</em>}</li>
 * <li>{@link org.eclipse.sirius.web.view.EdgeStyle#getTargetArrowStyle <em>Target Arrow Style</em>}</li>
 * <li>{@link org.eclipse.sirius.web.view.EdgeStyle#getEdgeWidth <em>Edge Width</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.web.view.ViewPackage#getEdgeStyle()
 * @model
 * @generated
 */
public interface EdgeStyle extends Style {
    /**
     * Returns the value of the '<em><b>Line Style</b></em>' attribute. The default value is <code>"Solid"</code>. The
     * literals are from the enumeration {@link org.eclipse.sirius.web.view.LineStyle}. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the value of the '<em>Line Style</em>' attribute.
     * @see org.eclipse.sirius.web.view.LineStyle
     * @see #setLineStyle(LineStyle)
     * @see org.eclipse.sirius.web.view.ViewPackage#getEdgeStyle_LineStyle()
     * @model default="Solid" required="true"
     * @generated
     */
    LineStyle getLineStyle();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.web.view.EdgeStyle#getLineStyle <em>Line Style</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Line Style</em>' attribute.
     * @see org.eclipse.sirius.web.view.LineStyle
     * @see #getLineStyle()
     * @generated
     */
    void setLineStyle(LineStyle value);

    /**
     * Returns the value of the '<em><b>Source Arrow Style</b></em>' attribute. The default value is
     * <code>"None"</code>. The literals are from the enumeration {@link org.eclipse.sirius.web.view.ArrowStyle}. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the value of the '<em>Source Arrow Style</em>' attribute.
     * @see org.eclipse.sirius.web.view.ArrowStyle
     * @see #setSourceArrowStyle(ArrowStyle)
     * @see org.eclipse.sirius.web.view.ViewPackage#getEdgeStyle_SourceArrowStyle()
     * @model default="None" required="true"
     * @generated
     */
    ArrowStyle getSourceArrowStyle();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.web.view.EdgeStyle#getSourceArrowStyle <em>Source Arrow
     * Style</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Source Arrow Style</em>' attribute.
     * @see org.eclipse.sirius.web.view.ArrowStyle
     * @see #getSourceArrowStyle()
     * @generated
     */
    void setSourceArrowStyle(ArrowStyle value);

    /**
     * Returns the value of the '<em><b>Target Arrow Style</b></em>' attribute. The default value is
     * <code>"None"</code>. The literals are from the enumeration {@link org.eclipse.sirius.web.view.ArrowStyle}. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the value of the '<em>Target Arrow Style</em>' attribute.
     * @see org.eclipse.sirius.web.view.ArrowStyle
     * @see #setTargetArrowStyle(ArrowStyle)
     * @see org.eclipse.sirius.web.view.ViewPackage#getEdgeStyle_TargetArrowStyle()
     * @model default="None" required="true"
     * @generated
     */
    ArrowStyle getTargetArrowStyle();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.web.view.EdgeStyle#getTargetArrowStyle <em>Target Arrow
     * Style</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Target Arrow Style</em>' attribute.
     * @see org.eclipse.sirius.web.view.ArrowStyle
     * @see #getTargetArrowStyle()
     * @generated
     */
    void setTargetArrowStyle(ArrowStyle value);

    /**
     * Returns the value of the '<em><b>Edge Width</b></em>' attribute. The default value is <code>"1"</code>. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the value of the '<em>Edge Width</em>' attribute.
     * @see #setEdgeWidth(int)
     * @see org.eclipse.sirius.web.view.ViewPackage#getEdgeStyle_EdgeWidth()
     * @model default="1" required="true"
     * @generated
     */
    int getEdgeWidth();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.web.view.EdgeStyle#getEdgeWidth <em>Edge Width</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Edge Width</em>' attribute.
     * @see #getEdgeWidth()
     * @generated
     */
    void setEdgeWidth(int value);

} // EdgeStyle
