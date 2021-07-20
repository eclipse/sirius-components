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
package org.eclipse.sirius.web.view;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc --> The <b>Package</b> for the model. It contains accessors for the meta objects to represent
 * <ul>
 * <li>each class,</li>
 * <li>each feature of each class,</li>
 * <li>each operation of each class,</li>
 * <li>each enum,</li>
 * <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 *
 * @see org.eclipse.sirius.web.view.ViewFactory
 * @model kind="package"
 * @generated
 */
public interface ViewPackage extends EPackage {
    /**
     * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    String eNAME = "view"; //$NON-NLS-1$

    /**
     * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    String eNS_URI = "http://www.eclipse.org/sirius-web/view"; //$NON-NLS-1$

    /**
     * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    String eNS_PREFIX = "view"; //$NON-NLS-1$

    /**
     * The singleton instance of the package. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    ViewPackage eINSTANCE = org.eclipse.sirius.web.view.impl.ViewPackageImpl.init();

    /**
     * The meta object id for the '{@link org.eclipse.sirius.web.view.impl.ViewImpl <em>View</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.web.view.impl.ViewImpl
     * @see org.eclipse.sirius.web.view.impl.ViewPackageImpl#getView()
     * @generated
     */
    int VIEW = 0;

    /**
     * The feature id for the '<em><b>Descriptions</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int VIEW__DESCRIPTIONS = 0;

    /**
     * The number of structural features of the '<em>View</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int VIEW_FEATURE_COUNT = 1;

    /**
     * The number of operations of the '<em>View</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int VIEW_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.web.view.impl.RepresentationDescriptionImpl
     * <em>Representation Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.web.view.impl.RepresentationDescriptionImpl
     * @see org.eclipse.sirius.web.view.impl.ViewPackageImpl#getRepresentationDescription()
     * @generated
     */
    int REPRESENTATION_DESCRIPTION = 1;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int REPRESENTATION_DESCRIPTION__NAME = 0;

    /**
     * The feature id for the '<em><b>Domain Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int REPRESENTATION_DESCRIPTION__DOMAIN_TYPE = 1;

    /**
     * The feature id for the '<em><b>Title Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int REPRESENTATION_DESCRIPTION__TITLE_EXPRESSION = 2;

    /**
     * The number of structural features of the '<em>Representation Description</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int REPRESENTATION_DESCRIPTION_FEATURE_COUNT = 3;

    /**
     * The number of operations of the '<em>Representation Description</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int REPRESENTATION_DESCRIPTION_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.web.view.impl.DiagramDescriptionImpl <em>Diagram
     * Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.web.view.impl.DiagramDescriptionImpl
     * @see org.eclipse.sirius.web.view.impl.ViewPackageImpl#getDiagramDescription()
     * @generated
     */
    int DIAGRAM_DESCRIPTION = 2;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_DESCRIPTION__NAME = REPRESENTATION_DESCRIPTION__NAME;

    /**
     * The feature id for the '<em><b>Domain Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_DESCRIPTION__DOMAIN_TYPE = REPRESENTATION_DESCRIPTION__DOMAIN_TYPE;

    /**
     * The feature id for the '<em><b>Title Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_DESCRIPTION__TITLE_EXPRESSION = REPRESENTATION_DESCRIPTION__TITLE_EXPRESSION;

    /**
     * The feature id for the '<em><b>Auto Layout</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_DESCRIPTION__AUTO_LAYOUT = REPRESENTATION_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Node Descriptions</b></em>' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_DESCRIPTION__NODE_DESCRIPTIONS = REPRESENTATION_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Edge Descriptions</b></em>' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_DESCRIPTION__EDGE_DESCRIPTIONS = REPRESENTATION_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Diagram Description</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_DESCRIPTION_FEATURE_COUNT = REPRESENTATION_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The number of operations of the '<em>Diagram Description</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_DESCRIPTION_OPERATION_COUNT = REPRESENTATION_DESCRIPTION_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.web.view.impl.DiagramElementDescriptionImpl <em>Diagram
     * Element Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.web.view.impl.DiagramElementDescriptionImpl
     * @see org.eclipse.sirius.web.view.impl.ViewPackageImpl#getDiagramElementDescription()
     * @generated
     */
    int DIAGRAM_ELEMENT_DESCRIPTION = 3;

    /**
     * The feature id for the '<em><b>Domain Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_ELEMENT_DESCRIPTION__DOMAIN_TYPE = 0;

    /**
     * The feature id for the '<em><b>Semantic Candidates Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_ELEMENT_DESCRIPTION__SEMANTIC_CANDIDATES_EXPRESSION = 1;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_ELEMENT_DESCRIPTION__LABEL_EXPRESSION = 2;

    /**
     * The feature id for the '<em><b>Delete Tool</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_ELEMENT_DESCRIPTION__DELETE_TOOL = 3;

    /**
     * The feature id for the '<em><b>Label Edit Tool</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_ELEMENT_DESCRIPTION__LABEL_EDIT_TOOL = 4;

    /**
     * The number of structural features of the '<em>Diagram Element Description</em>' class. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_ELEMENT_DESCRIPTION_FEATURE_COUNT = 5;

    /**
     * The number of operations of the '<em>Diagram Element Description</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DIAGRAM_ELEMENT_DESCRIPTION_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.web.view.impl.NodeDescriptionImpl <em>Node
     * Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.web.view.impl.NodeDescriptionImpl
     * @see org.eclipse.sirius.web.view.impl.ViewPackageImpl#getNodeDescription()
     * @generated
     */
    int NODE_DESCRIPTION = 4;

    /**
     * The feature id for the '<em><b>Domain Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_DESCRIPTION__DOMAIN_TYPE = DIAGRAM_ELEMENT_DESCRIPTION__DOMAIN_TYPE;

    /**
     * The feature id for the '<em><b>Semantic Candidates Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_DESCRIPTION__SEMANTIC_CANDIDATES_EXPRESSION = DIAGRAM_ELEMENT_DESCRIPTION__SEMANTIC_CANDIDATES_EXPRESSION;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int NODE_DESCRIPTION__LABEL_EXPRESSION = DIAGRAM_ELEMENT_DESCRIPTION__LABEL_EXPRESSION;

    /**
     * The feature id for the '<em><b>Delete Tool</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_DESCRIPTION__DELETE_TOOL = DIAGRAM_ELEMENT_DESCRIPTION__DELETE_TOOL;

    /**
     * The feature id for the '<em><b>Label Edit Tool</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_DESCRIPTION__LABEL_EDIT_TOOL = DIAGRAM_ELEMENT_DESCRIPTION__LABEL_EDIT_TOOL;

    /**
     * The feature id for the '<em><b>Children Descriptions</b></em>' containment reference list. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_DESCRIPTION__CHILDREN_DESCRIPTIONS = DIAGRAM_ELEMENT_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int NODE_DESCRIPTION__STYLE = DIAGRAM_ELEMENT_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Node Tools</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_DESCRIPTION__NODE_TOOLS = DIAGRAM_ELEMENT_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Conditional Styles</b></em>' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_DESCRIPTION__CONDITIONAL_STYLES = DIAGRAM_ELEMENT_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The number of structural features of the '<em>Node Description</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_DESCRIPTION_FEATURE_COUNT = DIAGRAM_ELEMENT_DESCRIPTION_FEATURE_COUNT + 4;

    /**
     * The number of operations of the '<em>Node Description</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_DESCRIPTION_OPERATION_COUNT = DIAGRAM_ELEMENT_DESCRIPTION_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.web.view.impl.EdgeDescriptionImpl <em>Edge
     * Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.web.view.impl.EdgeDescriptionImpl
     * @see org.eclipse.sirius.web.view.impl.ViewPackageImpl#getEdgeDescription()
     * @generated
     */
    int EDGE_DESCRIPTION = 5;

    /**
     * The feature id for the '<em><b>Domain Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_DESCRIPTION__DOMAIN_TYPE = DIAGRAM_ELEMENT_DESCRIPTION__DOMAIN_TYPE;

    /**
     * The feature id for the '<em><b>Semantic Candidates Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_DESCRIPTION__SEMANTIC_CANDIDATES_EXPRESSION = DIAGRAM_ELEMENT_DESCRIPTION__SEMANTIC_CANDIDATES_EXPRESSION;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int EDGE_DESCRIPTION__LABEL_EXPRESSION = DIAGRAM_ELEMENT_DESCRIPTION__LABEL_EXPRESSION;

    /**
     * The feature id for the '<em><b>Delete Tool</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_DESCRIPTION__DELETE_TOOL = DIAGRAM_ELEMENT_DESCRIPTION__DELETE_TOOL;

    /**
     * The feature id for the '<em><b>Label Edit Tool</b></em>' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_DESCRIPTION__LABEL_EDIT_TOOL = DIAGRAM_ELEMENT_DESCRIPTION__LABEL_EDIT_TOOL;

    /**
     * The feature id for the '<em><b>Is Domain Based Edge</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_DESCRIPTION__IS_DOMAIN_BASED_EDGE = DIAGRAM_ELEMENT_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Source Node Description</b></em>' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_DESCRIPTION__SOURCE_NODE_DESCRIPTION = DIAGRAM_ELEMENT_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Target Node Description</b></em>' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_DESCRIPTION__TARGET_NODE_DESCRIPTION = DIAGRAM_ELEMENT_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Source Nodes Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_DESCRIPTION__SOURCE_NODES_EXPRESSION = DIAGRAM_ELEMENT_DESCRIPTION_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Target Nodes Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_DESCRIPTION__TARGET_NODES_EXPRESSION = DIAGRAM_ELEMENT_DESCRIPTION_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Style</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int EDGE_DESCRIPTION__STYLE = DIAGRAM_ELEMENT_DESCRIPTION_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Edge Tools</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_DESCRIPTION__EDGE_TOOLS = DIAGRAM_ELEMENT_DESCRIPTION_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Conditional Styles</b></em>' containment reference list. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_DESCRIPTION__CONDITIONAL_STYLES = DIAGRAM_ELEMENT_DESCRIPTION_FEATURE_COUNT + 7;

    /**
     * The number of structural features of the '<em>Edge Description</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_DESCRIPTION_FEATURE_COUNT = DIAGRAM_ELEMENT_DESCRIPTION_FEATURE_COUNT + 8;

    /**
     * The number of operations of the '<em>Edge Description</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_DESCRIPTION_OPERATION_COUNT = DIAGRAM_ELEMENT_DESCRIPTION_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.web.view.impl.StyleImpl <em>Style</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.web.view.impl.StyleImpl
     * @see org.eclipse.sirius.web.view.impl.ViewPackageImpl#getStyle()
     * @generated
     */
    int STYLE = 6;

    /**
     * The feature id for the '<em><b>Color</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int STYLE__COLOR = 0;

    /**
     * The feature id for the '<em><b>Border Color</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int STYLE__BORDER_COLOR = 1;

    /**
     * The feature id for the '<em><b>Font Size</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int STYLE__FONT_SIZE = 2;

    /**
     * The number of structural features of the '<em>Style</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int STYLE_FEATURE_COUNT = 3;

    /**
     * The number of operations of the '<em>Style</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int STYLE_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.web.view.impl.NodeStyleImpl <em>Node Style</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.web.view.impl.NodeStyleImpl
     * @see org.eclipse.sirius.web.view.impl.ViewPackageImpl#getNodeStyle()
     * @generated
     */
    int NODE_STYLE = 7;

    /**
     * The feature id for the '<em><b>Color</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_STYLE__COLOR = STYLE__COLOR;

    /**
     * The feature id for the '<em><b>Border Color</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_STYLE__BORDER_COLOR = STYLE__BORDER_COLOR;

    /**
     * The feature id for the '<em><b>Font Size</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_STYLE__FONT_SIZE = STYLE__FONT_SIZE;

    /**
     * The feature id for the '<em><b>List Mode</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_STYLE__LIST_MODE = STYLE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Border Radius</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_STYLE__BORDER_RADIUS = STYLE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Shape</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_STYLE__SHAPE = STYLE_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Border Size</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_STYLE__BORDER_SIZE = STYLE_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Label Color</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_STYLE__LABEL_COLOR = STYLE_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Italic</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_STYLE__ITALIC = STYLE_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Bold</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_STYLE__BOLD = STYLE_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Underline</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_STYLE__UNDERLINE = STYLE_FEATURE_COUNT + 7;

    /**
     * The feature id for the '<em><b>Strike Through</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_STYLE__STRIKE_THROUGH = STYLE_FEATURE_COUNT + 8;

    /**
     * The number of structural features of the '<em>Node Style</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int NODE_STYLE_FEATURE_COUNT = STYLE_FEATURE_COUNT + 9;

    /**
     * The number of operations of the '<em>Node Style</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_STYLE_OPERATION_COUNT = STYLE_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.web.view.impl.EdgeStyleImpl <em>Edge Style</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.web.view.impl.EdgeStyleImpl
     * @see org.eclipse.sirius.web.view.impl.ViewPackageImpl#getEdgeStyle()
     * @generated
     */
    int EDGE_STYLE = 8;

    /**
     * The feature id for the '<em><b>Color</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_STYLE__COLOR = STYLE__COLOR;

    /**
     * The feature id for the '<em><b>Border Color</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_STYLE__BORDER_COLOR = STYLE__BORDER_COLOR;

    /**
     * The feature id for the '<em><b>Font Size</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_STYLE__FONT_SIZE = STYLE__FONT_SIZE;

    /**
     * The feature id for the '<em><b>Line Style</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_STYLE__LINE_STYLE = STYLE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Source Arrow Style</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int EDGE_STYLE__SOURCE_ARROW_STYLE = STYLE_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Target Arrow Style</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int EDGE_STYLE__TARGET_ARROW_STYLE = STYLE_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Edge Width</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_STYLE__EDGE_WIDTH = STYLE_FEATURE_COUNT + 3;

    /**
     * The number of structural features of the '<em>Edge Style</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int EDGE_STYLE_FEATURE_COUNT = STYLE_FEATURE_COUNT + 4;

    /**
     * The number of operations of the '<em>Edge Style</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_STYLE_OPERATION_COUNT = STYLE_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.web.view.impl.ToolImpl <em>Tool</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.web.view.impl.ToolImpl
     * @see org.eclipse.sirius.web.view.impl.ViewPackageImpl#getTool()
     * @generated
     */
    int TOOL = 9;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TOOL__NAME = 0;

    /**
     * The feature id for the '<em><b>Body</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int TOOL__BODY = 1;

    /**
     * The number of structural features of the '<em>Tool</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TOOL_FEATURE_COUNT = 2;

    /**
     * The number of operations of the '<em>Tool</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int TOOL_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.web.view.impl.LabelEditToolImpl <em>Label Edit Tool</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.web.view.impl.LabelEditToolImpl
     * @see org.eclipse.sirius.web.view.impl.ViewPackageImpl#getLabelEditTool()
     * @generated
     */
    int LABEL_EDIT_TOOL = 10;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LABEL_EDIT_TOOL__NAME = TOOL__NAME;

    /**
     * The feature id for the '<em><b>Body</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int LABEL_EDIT_TOOL__BODY = TOOL__BODY;

    /**
     * The number of structural features of the '<em>Label Edit Tool</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int LABEL_EDIT_TOOL_FEATURE_COUNT = TOOL_FEATURE_COUNT + 0;

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.web.view.DiagramDescription#isAutoLayout
     * <em>Auto Layout</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Auto Layout</em>'.
     * @see org.eclipse.sirius.web.view.DiagramDescription#isAutoLayout()
     * @see #getDiagramDescription()
     * @generated
     */
    EAttribute getDiagramDescription_AutoLayout();

    /**
     * Returns the meta object for the containment reference list
     * '{@link org.eclipse.sirius.web.view.DiagramDescription#getNodeDescriptions <em>Node Descriptions</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc --> >>>>>>> 025361a0 ([512] View DSL: Add support for layout mode
     * configuration)
     *
     * @generated
     * @ordered
     */
    int LABEL_EDIT_TOOL_OPERATION_COUNT = TOOL_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.web.view.impl.DeleteToolImpl <em>Delete Tool</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.web.view.impl.DeleteToolImpl
     * @see org.eclipse.sirius.web.view.impl.ViewPackageImpl#getDeleteTool()
     * @generated
     */
    int DELETE_TOOL = 11;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DELETE_TOOL__NAME = TOOL__NAME;

    /**
     * The feature id for the '<em><b>Body</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int DELETE_TOOL__BODY = TOOL__BODY;

    /**
     * The number of structural features of the '<em>Delete Tool</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int DELETE_TOOL_FEATURE_COUNT = TOOL_FEATURE_COUNT + 0;

    /**
     * The number of operations of the '<em>Delete Tool</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DELETE_TOOL_OPERATION_COUNT = TOOL_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.web.view.impl.NodeToolImpl <em>Node Tool</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.web.view.impl.NodeToolImpl
     * @see org.eclipse.sirius.web.view.impl.ViewPackageImpl#getNodeTool()
     * @generated
     */
    int NODE_TOOL = 12;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_TOOL__NAME = TOOL__NAME;

    /**
     * The feature id for the '<em><b>Body</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int NODE_TOOL__BODY = TOOL__BODY;

    /**
     * The number of structural features of the '<em>Node Tool</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int NODE_TOOL_FEATURE_COUNT = TOOL_FEATURE_COUNT + 0;

    /**
     * The number of operations of the '<em>Node Tool</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NODE_TOOL_OPERATION_COUNT = TOOL_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.web.view.impl.EdgeToolImpl <em>Edge Tool</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.web.view.impl.EdgeToolImpl
     * @see org.eclipse.sirius.web.view.impl.ViewPackageImpl#getEdgeTool()
     * @generated
     */
    int EDGE_TOOL = 13;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_TOOL__NAME = TOOL__NAME;

    /**
     * The feature id for the '<em><b>Body</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int EDGE_TOOL__BODY = TOOL__BODY;

    /**
     * The number of structural features of the '<em>Edge Tool</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int EDGE_TOOL_FEATURE_COUNT = TOOL_FEATURE_COUNT + 0;

    /**
     * The number of operations of the '<em>Edge Tool</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EDGE_TOOL_OPERATION_COUNT = TOOL_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.web.view.impl.OperationImpl <em>Operation</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.web.view.impl.OperationImpl
     * @see org.eclipse.sirius.web.view.impl.ViewPackageImpl#getOperation()
     * @generated
     */
    int OPERATION = 14;

    /**
     * The feature id for the '<em><b>Children</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int OPERATION__CHILDREN = 0;

    /**
     * The number of structural features of the '<em>Operation</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int OPERATION_FEATURE_COUNT = 1;

    /**
     * The number of operations of the '<em>Operation</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int OPERATION_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.web.view.impl.ChangeContextImpl <em>Change Context</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.web.view.impl.ChangeContextImpl
     * @see org.eclipse.sirius.web.view.impl.ViewPackageImpl#getChangeContext()
     * @generated
     */
    int CHANGE_CONTEXT = 15;

    /**
     * The feature id for the '<em><b>Children</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CHANGE_CONTEXT__CHILDREN = OPERATION__CHILDREN;

    /**
     * The feature id for the '<em><b>Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CHANGE_CONTEXT__EXPRESSION = OPERATION_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Change Context</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CHANGE_CONTEXT_FEATURE_COUNT = OPERATION_FEATURE_COUNT + 1;

    /**
     * The number of operations of the '<em>Change Context</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CHANGE_CONTEXT_OPERATION_COUNT = OPERATION_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.web.view.impl.CreateInstanceImpl <em>Create Instance</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.web.view.impl.CreateInstanceImpl
     * @see org.eclipse.sirius.web.view.impl.ViewPackageImpl#getCreateInstance()
     * @generated
     */
    int CREATE_INSTANCE = 16;

    /**
     * The feature id for the '<em><b>Children</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CREATE_INSTANCE__CHILDREN = OPERATION__CHILDREN;

    /**
     * The feature id for the '<em><b>Type Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CREATE_INSTANCE__TYPE_NAME = OPERATION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Reference Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CREATE_INSTANCE__REFERENCE_NAME = OPERATION_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Variable Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CREATE_INSTANCE__VARIABLE_NAME = OPERATION_FEATURE_COUNT + 2;

    /**
     * The number of structural features of the '<em>Create Instance</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CREATE_INSTANCE_FEATURE_COUNT = OPERATION_FEATURE_COUNT + 3;

    /**
     * The number of operations of the '<em>Create Instance</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CREATE_INSTANCE_OPERATION_COUNT = OPERATION_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.web.view.impl.SetValueImpl <em>Set Value</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.web.view.impl.SetValueImpl
     * @see org.eclipse.sirius.web.view.impl.ViewPackageImpl#getSetValue()
     * @generated
     */
    int SET_VALUE = 17;

    /**
     * The feature id for the '<em><b>Children</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SET_VALUE__CHILDREN = OPERATION__CHILDREN;

    /**
     * The feature id for the '<em><b>Feature Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SET_VALUE__FEATURE_NAME = OPERATION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Value Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int SET_VALUE__VALUE_EXPRESSION = OPERATION_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Set Value</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int SET_VALUE_FEATURE_COUNT = OPERATION_FEATURE_COUNT + 2;

    /**
     * The number of operations of the '<em>Set Value</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int SET_VALUE_OPERATION_COUNT = OPERATION_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.web.view.impl.UnsetValueImpl <em>Unset Value</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.web.view.impl.UnsetValueImpl
     * @see org.eclipse.sirius.web.view.impl.ViewPackageImpl#getUnsetValue()
     * @generated
     */
    int UNSET_VALUE = 18;

    /**
     * The feature id for the '<em><b>Children</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int UNSET_VALUE__CHILDREN = OPERATION__CHILDREN;

    /**
     * The feature id for the '<em><b>Feature Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int UNSET_VALUE__FEATURE_NAME = OPERATION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Element Expression</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int UNSET_VALUE__ELEMENT_EXPRESSION = OPERATION_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Unset Value</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int UNSET_VALUE_FEATURE_COUNT = OPERATION_FEATURE_COUNT + 2;

    /**
     * The number of operations of the '<em>Unset Value</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int UNSET_VALUE_OPERATION_COUNT = OPERATION_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.web.view.impl.DeleteElementImpl <em>Delete Element</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.web.view.impl.DeleteElementImpl
     * @see org.eclipse.sirius.web.view.impl.ViewPackageImpl#getDeleteElement()
     * @generated
     */
    int DELETE_ELEMENT = 19;

    /**
     * The feature id for the '<em><b>Children</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DELETE_ELEMENT__CHILDREN = OPERATION__CHILDREN;

    /**
     * The number of structural features of the '<em>Delete Element</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DELETE_ELEMENT_FEATURE_COUNT = OPERATION_FEATURE_COUNT + 0;

    /**
     * The number of operations of the '<em>Delete Element</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DELETE_ELEMENT_OPERATION_COUNT = OPERATION_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.web.view.impl.ConditionalImpl <em>Conditional</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.web.view.impl.ConditionalImpl
     * @see org.eclipse.sirius.web.view.impl.ViewPackageImpl#getConditional()
     * @generated
     */
    int CONDITIONAL = 20;

    /**
     * The feature id for the '<em><b>Condition</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONDITIONAL__CONDITION = 0;

    /**
     * The number of structural features of the '<em>Conditional</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int CONDITIONAL_FEATURE_COUNT = 1;

    /**
     * The number of operations of the '<em>Conditional</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONDITIONAL_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.web.view.impl.ConditionalNodeStyleImpl <em>Conditional Node
     * Style</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.web.view.impl.ConditionalNodeStyleImpl
     * @see org.eclipse.sirius.web.view.impl.ViewPackageImpl#getConditionalNodeStyle()
     * @generated
     */
    int CONDITIONAL_NODE_STYLE = 21;

    /**
     * The feature id for the '<em><b>Condition</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONDITIONAL_NODE_STYLE__CONDITION = CONDITIONAL__CONDITION;

    /**
     * The feature id for the '<em><b>Color</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONDITIONAL_NODE_STYLE__COLOR = CONDITIONAL_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Border Color</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONDITIONAL_NODE_STYLE__BORDER_COLOR = CONDITIONAL_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Font Size</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONDITIONAL_NODE_STYLE__FONT_SIZE = CONDITIONAL_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>List Mode</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONDITIONAL_NODE_STYLE__LIST_MODE = CONDITIONAL_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Border Radius</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONDITIONAL_NODE_STYLE__BORDER_RADIUS = CONDITIONAL_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Shape</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONDITIONAL_NODE_STYLE__SHAPE = CONDITIONAL_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Border Size</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONDITIONAL_NODE_STYLE__BORDER_SIZE = CONDITIONAL_FEATURE_COUNT + 6;

    /**
     * The feature id for the '<em><b>Label Color</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONDITIONAL_NODE_STYLE__LABEL_COLOR = CONDITIONAL_FEATURE_COUNT + 7;

    /**
     * The feature id for the '<em><b>Italic</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONDITIONAL_NODE_STYLE__ITALIC = CONDITIONAL_FEATURE_COUNT + 8;

    /**
     * The feature id for the '<em><b>Bold</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONDITIONAL_NODE_STYLE__BOLD = CONDITIONAL_FEATURE_COUNT + 9;

    /**
     * The feature id for the '<em><b>Underline</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONDITIONAL_NODE_STYLE__UNDERLINE = CONDITIONAL_FEATURE_COUNT + 10;

    /**
     * The feature id for the '<em><b>Strike Through</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONDITIONAL_NODE_STYLE__STRIKE_THROUGH = CONDITIONAL_FEATURE_COUNT + 11;

    /**
     * The number of structural features of the '<em>Conditional Node Style</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONDITIONAL_NODE_STYLE_FEATURE_COUNT = CONDITIONAL_FEATURE_COUNT + 12;

    /**
     * The number of operations of the '<em>Conditional Node Style</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONDITIONAL_NODE_STYLE_OPERATION_COUNT = CONDITIONAL_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.web.view.impl.ConditionalEdgeStyleImpl <em>Conditional Edge
     * Style</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.web.view.impl.ConditionalEdgeStyleImpl
     * @see org.eclipse.sirius.web.view.impl.ViewPackageImpl#getConditionalEdgeStyle()
     * @generated
     */
    int CONDITIONAL_EDGE_STYLE = 22;

    /**
     * The feature id for the '<em><b>Condition</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONDITIONAL_EDGE_STYLE__CONDITION = CONDITIONAL__CONDITION;

    /**
     * The feature id for the '<em><b>Color</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONDITIONAL_EDGE_STYLE__COLOR = CONDITIONAL_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Border Color</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONDITIONAL_EDGE_STYLE__BORDER_COLOR = CONDITIONAL_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Font Size</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONDITIONAL_EDGE_STYLE__FONT_SIZE = CONDITIONAL_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Line Style</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONDITIONAL_EDGE_STYLE__LINE_STYLE = CONDITIONAL_FEATURE_COUNT + 3;

    /**
     * The feature id for the '<em><b>Source Arrow Style</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int CONDITIONAL_EDGE_STYLE__SOURCE_ARROW_STYLE = CONDITIONAL_FEATURE_COUNT + 4;

    /**
     * The feature id for the '<em><b>Target Arrow Style</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int CONDITIONAL_EDGE_STYLE__TARGET_ARROW_STYLE = CONDITIONAL_FEATURE_COUNT + 5;

    /**
     * The feature id for the '<em><b>Edge Width</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONDITIONAL_EDGE_STYLE__EDGE_WIDTH = CONDITIONAL_FEATURE_COUNT + 6;

    /**
     * The number of structural features of the '<em>Conditional Edge Style</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONDITIONAL_EDGE_STYLE_FEATURE_COUNT = CONDITIONAL_FEATURE_COUNT + 7;

    /**
     * The number of operations of the '<em>Conditional Edge Style</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int CONDITIONAL_EDGE_STYLE_OPERATION_COUNT = CONDITIONAL_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.web.view.ArrowStyle <em>Arrow Style</em>}' enum. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.web.view.ArrowStyle
     * @see org.eclipse.sirius.web.view.impl.ViewPackageImpl#getArrowStyle()
     * @generated
     */
    int ARROW_STYLE = 23;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.web.view.LineStyle <em>Line Style</em>}' enum. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.web.view.LineStyle
     * @see org.eclipse.sirius.web.view.impl.ViewPackageImpl#getLineStyle()
     * @generated
     */
    int LINE_STYLE = 24;

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.web.view.View <em>View</em>}'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>View</em>'.
     * @see org.eclipse.sirius.web.view.View
     * @generated
     */
    EClass getView();

    /**
     * Returns the meta object for the containment reference list
     * '{@link org.eclipse.sirius.web.view.View#getDescriptions <em>Descriptions</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Descriptions</em>'.
     * @see org.eclipse.sirius.web.view.View#getDescriptions()
     * @see #getView()
     * @generated
     */
    EReference getView_Descriptions();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.web.view.RepresentationDescription
     * <em>Representation Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Representation Description</em>'.
     * @see org.eclipse.sirius.web.view.RepresentationDescription
     * @generated
     */
    EClass getRepresentationDescription();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.web.view.RepresentationDescription#getName
     * <em>Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.eclipse.sirius.web.view.RepresentationDescription#getName()
     * @see #getRepresentationDescription()
     * @generated
     */
    EAttribute getRepresentationDescription_Name();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.web.view.RepresentationDescription#getDomainType <em>Domain Type</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Domain Type</em>'.
     * @see org.eclipse.sirius.web.view.RepresentationDescription#getDomainType()
     * @see #getRepresentationDescription()
     * @generated
     */
    EAttribute getRepresentationDescription_DomainType();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.web.view.RepresentationDescription#getTitleExpression <em>Title Expression</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Title Expression</em>'.
     * @see org.eclipse.sirius.web.view.RepresentationDescription#getTitleExpression()
     * @see #getRepresentationDescription()
     * @generated
     */
    EAttribute getRepresentationDescription_TitleExpression();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.web.view.DiagramDescription <em>Diagram
     * Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Diagram Description</em>'.
     * @see org.eclipse.sirius.web.view.DiagramDescription
     * @generated
     */
    EClass getDiagramDescription();

    /**
     * Returns the meta object for the containment reference list
     * '{@link org.eclipse.sirius.web.view.DiagramDescription#getNodeDescriptions <em>Node Descriptions</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Node Descriptions</em>'.
     * @see org.eclipse.sirius.web.view.DiagramDescription#getNodeDescriptions()
     * @see #getDiagramDescription()
     * @generated
     */
    EReference getDiagramDescription_NodeDescriptions();

    /**
     * Returns the meta object for the containment reference list
     * '{@link org.eclipse.sirius.web.view.DiagramDescription#getEdgeDescriptions <em>Edge Descriptions</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Edge Descriptions</em>'.
     * @see org.eclipse.sirius.web.view.DiagramDescription#getEdgeDescriptions()
     * @see #getDiagramDescription()
     * @generated
     */
    EReference getDiagramDescription_EdgeDescriptions();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.web.view.DiagramElementDescription <em>Diagram
     * Element Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Diagram Element Description</em>'.
     * @see org.eclipse.sirius.web.view.DiagramElementDescription
     * @generated
     */
    EClass getDiagramElementDescription();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.web.view.DiagramElementDescription#getDomainType <em>Domain Type</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Domain Type</em>'.
     * @see org.eclipse.sirius.web.view.DiagramElementDescription#getDomainType()
     * @see #getDiagramElementDescription()
     * @generated
     */
    EAttribute getDiagramElementDescription_DomainType();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.web.view.DiagramElementDescription#getSemanticCandidatesExpression <em>Semantic
     * Candidates Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Semantic Candidates Expression</em>'.
     * @see org.eclipse.sirius.web.view.DiagramElementDescription#getSemanticCandidatesExpression()
     * @see #getDiagramElementDescription()
     * @generated
     */
    EAttribute getDiagramElementDescription_SemanticCandidatesExpression();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.web.view.DiagramElementDescription#getLabelExpression <em>Label Expression</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Label Expression</em>'.
     * @see org.eclipse.sirius.web.view.DiagramElementDescription#getLabelExpression()
     * @see #getDiagramElementDescription()
     * @generated
     */
    EAttribute getDiagramElementDescription_LabelExpression();

    /**
     * Returns the meta object for the containment reference
     * '{@link org.eclipse.sirius.web.view.DiagramElementDescription#getDeleteTool <em>Delete Tool</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Delete Tool</em>'.
     * @see org.eclipse.sirius.web.view.DiagramElementDescription#getDeleteTool()
     * @see #getDiagramElementDescription()
     * @generated
     */
    EReference getDiagramElementDescription_DeleteTool();

    /**
     * Returns the meta object for the containment reference
     * '{@link org.eclipse.sirius.web.view.DiagramElementDescription#getLabelEditTool <em>Label Edit Tool</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Label Edit Tool</em>'.
     * @see org.eclipse.sirius.web.view.DiagramElementDescription#getLabelEditTool()
     * @see #getDiagramElementDescription()
     * @generated
     */
    EReference getDiagramElementDescription_LabelEditTool();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.web.view.NodeDescription <em>Node
     * Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Node Description</em>'.
     * @see org.eclipse.sirius.web.view.NodeDescription
     * @generated
     */
    EClass getNodeDescription();

    /**
     * Returns the meta object for the containment reference list
     * '{@link org.eclipse.sirius.web.view.NodeDescription#getChildrenDescriptions <em>Children Descriptions</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Children Descriptions</em>'.
     * @see org.eclipse.sirius.web.view.NodeDescription#getChildrenDescriptions()
     * @see #getNodeDescription()
     * @generated
     */
    EReference getNodeDescription_ChildrenDescriptions();

    /**
     * Returns the meta object for the containment reference
     * '{@link org.eclipse.sirius.web.view.NodeDescription#getStyle <em>Style</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Style</em>'.
     * @see org.eclipse.sirius.web.view.NodeDescription#getStyle()
     * @see #getNodeDescription()
     * @generated
     */
    EReference getNodeDescription_Style();

    /**
     * Returns the meta object for the containment reference list
     * '{@link org.eclipse.sirius.web.view.NodeDescription#getNodeTools <em>Node Tools</em>}'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Node Tools</em>'.
     * @see org.eclipse.sirius.web.view.NodeDescription#getNodeTools()
     * @see #getNodeDescription()
     * @generated
     */
    EReference getNodeDescription_NodeTools();

    /**
     * Returns the meta object for the containment reference list
     * '{@link org.eclipse.sirius.web.view.NodeDescription#getConditionalStyles <em>Conditional Styles</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Conditional Styles</em>'.
     * @see org.eclipse.sirius.web.view.NodeDescription#getConditionalStyles()
     * @see #getNodeDescription()
     * @generated
     */
    EReference getNodeDescription_ConditionalStyles();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.web.view.EdgeDescription <em>Edge
     * Description</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Edge Description</em>'.
     * @see org.eclipse.sirius.web.view.EdgeDescription
     * @generated
     */
    EClass getEdgeDescription();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.web.view.EdgeDescription#isIsDomainBasedEdge
     * <em>Is Domain Based Edge</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Is Domain Based Edge</em>'.
     * @see org.eclipse.sirius.web.view.EdgeDescription#isIsDomainBasedEdge()
     * @see #getEdgeDescription()
     * @generated
     */
    EAttribute getEdgeDescription_IsDomainBasedEdge();

    /**
     * Returns the meta object for the reference
     * '{@link org.eclipse.sirius.web.view.EdgeDescription#getSourceNodeDescription <em>Source Node Description</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Source Node Description</em>'.
     * @see org.eclipse.sirius.web.view.EdgeDescription#getSourceNodeDescription()
     * @see #getEdgeDescription()
     * @generated
     */
    EReference getEdgeDescription_SourceNodeDescription();

    /**
     * Returns the meta object for the reference
     * '{@link org.eclipse.sirius.web.view.EdgeDescription#getTargetNodeDescription <em>Target Node Description</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Target Node Description</em>'.
     * @see org.eclipse.sirius.web.view.EdgeDescription#getTargetNodeDescription()
     * @see #getEdgeDescription()
     * @generated
     */
    EReference getEdgeDescription_TargetNodeDescription();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.web.view.EdgeDescription#getSourceNodesExpression <em>Source Nodes Expression</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Source Nodes Expression</em>'.
     * @see org.eclipse.sirius.web.view.EdgeDescription#getSourceNodesExpression()
     * @see #getEdgeDescription()
     * @generated
     */
    EAttribute getEdgeDescription_SourceNodesExpression();

    /**
     * Returns the meta object for the attribute
     * '{@link org.eclipse.sirius.web.view.EdgeDescription#getTargetNodesExpression <em>Target Nodes Expression</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Target Nodes Expression</em>'.
     * @see org.eclipse.sirius.web.view.EdgeDescription#getTargetNodesExpression()
     * @see #getEdgeDescription()
     * @generated
     */
    EAttribute getEdgeDescription_TargetNodesExpression();

    /**
     * Returns the meta object for the containment reference
     * '{@link org.eclipse.sirius.web.view.EdgeDescription#getStyle <em>Style</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for the containment reference '<em>Style</em>'.
     * @see org.eclipse.sirius.web.view.EdgeDescription#getStyle()
     * @see #getEdgeDescription()
     * @generated
     */
    EReference getEdgeDescription_Style();

    /**
     * Returns the meta object for the containment reference list
     * '{@link org.eclipse.sirius.web.view.EdgeDescription#getEdgeTools <em>Edge Tools</em>}'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Edge Tools</em>'.
     * @see org.eclipse.sirius.web.view.EdgeDescription#getEdgeTools()
     * @see #getEdgeDescription()
     * @generated
     */
    EReference getEdgeDescription_EdgeTools();

    /**
     * Returns the meta object for the containment reference list
     * '{@link org.eclipse.sirius.web.view.EdgeDescription#getConditionalStyles <em>Conditional Styles</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Conditional Styles</em>'.
     * @see org.eclipse.sirius.web.view.EdgeDescription#getConditionalStyles()
     * @see #getEdgeDescription()
     * @generated
     */
    EReference getEdgeDescription_ConditionalStyles();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.web.view.Style <em>Style</em>}'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Style</em>'.
     * @see org.eclipse.sirius.web.view.Style
     * @generated
     */
    EClass getStyle();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.web.view.Style#getColor <em>Color</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Color</em>'.
     * @see org.eclipse.sirius.web.view.Style#getColor()
     * @see #getStyle()
     * @generated
     */
    EAttribute getStyle_Color();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.web.view.Style#getBorderColor <em>Border
     * Color</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Border Color</em>'.
     * @see org.eclipse.sirius.web.view.Style#getBorderColor()
     * @see #getStyle()
     * @generated
     */
    EAttribute getStyle_BorderColor();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.web.view.Style#getFontSize <em>Font
     * Size</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Font Size</em>'.
     * @see org.eclipse.sirius.web.view.Style#getFontSize()
     * @see #getStyle()
     * @generated
     */
    EAttribute getStyle_FontSize();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.web.view.NodeStyle <em>Node Style</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Node Style</em>'.
     * @see org.eclipse.sirius.web.view.NodeStyle
     * @generated
     */
    EClass getNodeStyle();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.web.view.NodeStyle#isListMode <em>List
     * Mode</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>List Mode</em>'.
     * @see org.eclipse.sirius.web.view.NodeStyle#isListMode()
     * @see #getNodeStyle()
     * @generated
     */
    EAttribute getNodeStyle_ListMode();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.web.view.NodeStyle#getBorderRadius
     * <em>Border Radius</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Border Radius</em>'.
     * @see org.eclipse.sirius.web.view.NodeStyle#getBorderRadius()
     * @see #getNodeStyle()
     * @generated
     */
    EAttribute getNodeStyle_BorderRadius();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.web.view.NodeStyle#getShape
     * <em>Shape</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Shape</em>'.
     * @see org.eclipse.sirius.web.view.NodeStyle#getShape()
     * @see #getNodeStyle()
     * @generated
     */
    EAttribute getNodeStyle_Shape();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.web.view.NodeStyle#getBorderSize <em>Border
     * Size</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Border Size</em>'.
     * @see org.eclipse.sirius.web.view.NodeStyle#getBorderSize()
     * @see #getNodeStyle()
     * @generated
     */
    EAttribute getNodeStyle_BorderSize();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.web.view.NodeStyle#getLabelColor <em>Label
     * Color</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Label Color</em>'.
     * @see org.eclipse.sirius.web.view.NodeStyle#getLabelColor()
     * @see #getNodeStyle()
     * @generated
     */
    EAttribute getNodeStyle_LabelColor();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.web.view.NodeStyle#isItalic
     * <em>Italic</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Italic</em>'.
     * @see org.eclipse.sirius.web.view.NodeStyle#isItalic()
     * @see #getNodeStyle()
     * @generated
     */
    EAttribute getNodeStyle_Italic();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.web.view.NodeStyle#isBold <em>Bold</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Bold</em>'.
     * @see org.eclipse.sirius.web.view.NodeStyle#isBold()
     * @see #getNodeStyle()
     * @generated
     */
    EAttribute getNodeStyle_Bold();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.web.view.NodeStyle#isUnderline
     * <em>Underline</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Underline</em>'.
     * @see org.eclipse.sirius.web.view.NodeStyle#isUnderline()
     * @see #getNodeStyle()
     * @generated
     */
    EAttribute getNodeStyle_Underline();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.web.view.NodeStyle#isStrikeThrough
     * <em>Strike Through</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Strike Through</em>'.
     * @see org.eclipse.sirius.web.view.NodeStyle#isStrikeThrough()
     * @see #getNodeStyle()
     * @generated
     */
    EAttribute getNodeStyle_StrikeThrough();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.web.view.EdgeStyle <em>Edge Style</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Edge Style</em>'.
     * @see org.eclipse.sirius.web.view.EdgeStyle
     * @generated
     */
    EClass getEdgeStyle();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.web.view.EdgeStyle#getLineStyle <em>Line
     * Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Line Style</em>'.
     * @see org.eclipse.sirius.web.view.EdgeStyle#getLineStyle()
     * @see #getEdgeStyle()
     * @generated
     */
    EAttribute getEdgeStyle_LineStyle();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.web.view.EdgeStyle#getSourceArrowStyle
     * <em>Source Arrow Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Source Arrow Style</em>'.
     * @see org.eclipse.sirius.web.view.EdgeStyle#getSourceArrowStyle()
     * @see #getEdgeStyle()
     * @generated
     */
    EAttribute getEdgeStyle_SourceArrowStyle();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.web.view.EdgeStyle#getTargetArrowStyle
     * <em>Target Arrow Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Target Arrow Style</em>'.
     * @see org.eclipse.sirius.web.view.EdgeStyle#getTargetArrowStyle()
     * @see #getEdgeStyle()
     * @generated
     */
    EAttribute getEdgeStyle_TargetArrowStyle();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.web.view.EdgeStyle#getEdgeWidth <em>Edge
     * Width</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Edge Width</em>'.
     * @see org.eclipse.sirius.web.view.EdgeStyle#getEdgeWidth()
     * @see #getEdgeStyle()
     * @generated
     */
    EAttribute getEdgeStyle_EdgeWidth();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.web.view.Tool <em>Tool</em>}'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Tool</em>'.
     * @see org.eclipse.sirius.web.view.Tool
     * @generated
     */
    EClass getTool();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.web.view.Tool#getName <em>Name</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.eclipse.sirius.web.view.Tool#getName()
     * @see #getTool()
     * @generated
     */
    EAttribute getTool_Name();

    /**
     * Returns the meta object for the containment reference list '{@link org.eclipse.sirius.web.view.Tool#getBody
     * <em>Body</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Body</em>'.
     * @see org.eclipse.sirius.web.view.Tool#getBody()
     * @see #getTool()
     * @generated
     */
    EReference getTool_Body();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.web.view.LabelEditTool <em>Label Edit Tool</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Label Edit Tool</em>'.
     * @see org.eclipse.sirius.web.view.LabelEditTool
     * @generated
     */
    EClass getLabelEditTool();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.web.view.DeleteTool <em>Delete Tool</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Delete Tool</em>'.
     * @see org.eclipse.sirius.web.view.DeleteTool
     * @generated
     */
    EClass getDeleteTool();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.web.view.NodeTool <em>Node Tool</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Node Tool</em>'.
     * @see org.eclipse.sirius.web.view.NodeTool
     * @generated
     */
    EClass getNodeTool();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.web.view.EdgeTool <em>Edge Tool</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Edge Tool</em>'.
     * @see org.eclipse.sirius.web.view.EdgeTool
     * @generated
     */
    EClass getEdgeTool();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.web.view.Operation <em>Operation</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Operation</em>'.
     * @see org.eclipse.sirius.web.view.Operation
     * @generated
     */
    EClass getOperation();

    /**
     * Returns the meta object for the containment reference list
     * '{@link org.eclipse.sirius.web.view.Operation#getChildren <em>Children</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Children</em>'.
     * @see org.eclipse.sirius.web.view.Operation#getChildren()
     * @see #getOperation()
     * @generated
     */
    EReference getOperation_Children();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.web.view.ChangeContext <em>Change Context</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Change Context</em>'.
     * @see org.eclipse.sirius.web.view.ChangeContext
     * @generated
     */
    EClass getChangeContext();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.web.view.ChangeContext#getExpression
     * <em>Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Expression</em>'.
     * @see org.eclipse.sirius.web.view.ChangeContext#getExpression()
     * @see #getChangeContext()
     * @generated
     */
    EAttribute getChangeContext_Expression();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.web.view.CreateInstance <em>Create Instance</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Create Instance</em>'.
     * @see org.eclipse.sirius.web.view.CreateInstance
     * @generated
     */
    EClass getCreateInstance();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.web.view.CreateInstance#getTypeName <em>Type
     * Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Type Name</em>'.
     * @see org.eclipse.sirius.web.view.CreateInstance#getTypeName()
     * @see #getCreateInstance()
     * @generated
     */
    EAttribute getCreateInstance_TypeName();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.web.view.CreateInstance#getReferenceName
     * <em>Reference Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Reference Name</em>'.
     * @see org.eclipse.sirius.web.view.CreateInstance#getReferenceName()
     * @see #getCreateInstance()
     * @generated
     */
    EAttribute getCreateInstance_ReferenceName();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.web.view.CreateInstance#getVariableName
     * <em>Variable Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Variable Name</em>'.
     * @see org.eclipse.sirius.web.view.CreateInstance#getVariableName()
     * @see #getCreateInstance()
     * @generated
     */
    EAttribute getCreateInstance_VariableName();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.web.view.SetValue <em>Set Value</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Set Value</em>'.
     * @see org.eclipse.sirius.web.view.SetValue
     * @generated
     */
    EClass getSetValue();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.web.view.SetValue#getFeatureName <em>Feature
     * Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Feature Name</em>'.
     * @see org.eclipse.sirius.web.view.SetValue#getFeatureName()
     * @see #getSetValue()
     * @generated
     */
    EAttribute getSetValue_FeatureName();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.web.view.SetValue#getValueExpression
     * <em>Value Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Value Expression</em>'.
     * @see org.eclipse.sirius.web.view.SetValue#getValueExpression()
     * @see #getSetValue()
     * @generated
     */
    EAttribute getSetValue_ValueExpression();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.web.view.UnsetValue <em>Unset Value</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Unset Value</em>'.
     * @see org.eclipse.sirius.web.view.UnsetValue
     * @generated
     */
    EClass getUnsetValue();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.web.view.UnsetValue#getFeatureName
     * <em>Feature Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Feature Name</em>'.
     * @see org.eclipse.sirius.web.view.UnsetValue#getFeatureName()
     * @see #getUnsetValue()
     * @generated
     */
    EAttribute getUnsetValue_FeatureName();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.web.view.UnsetValue#getElementExpression
     * <em>Element Expression</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Element Expression</em>'.
     * @see org.eclipse.sirius.web.view.UnsetValue#getElementExpression()
     * @see #getUnsetValue()
     * @generated
     */
    EAttribute getUnsetValue_ElementExpression();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.web.view.DeleteElement <em>Delete Element</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Delete Element</em>'.
     * @see org.eclipse.sirius.web.view.DeleteElement
     * @generated
     */
    EClass getDeleteElement();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.web.view.Conditional <em>Conditional</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Conditional</em>'.
     * @see org.eclipse.sirius.web.view.Conditional
     * @generated
     */
    EClass getConditional();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.web.view.Conditional#getCondition
     * <em>Condition</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Condition</em>'.
     * @see org.eclipse.sirius.web.view.Conditional#getCondition()
     * @see #getConditional()
     * @generated
     */
    EAttribute getConditional_Condition();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.web.view.ConditionalNodeStyle <em>Conditional Node
     * Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Conditional Node Style</em>'.
     * @see org.eclipse.sirius.web.view.ConditionalNodeStyle
     * @generated
     */
    EClass getConditionalNodeStyle();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.web.view.ConditionalEdgeStyle <em>Conditional Edge
     * Style</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Conditional Edge Style</em>'.
     * @see org.eclipse.sirius.web.view.ConditionalEdgeStyle
     * @generated
     */
    EClass getConditionalEdgeStyle();

    /**
     * Returns the meta object for enum '{@link org.eclipse.sirius.web.view.ArrowStyle <em>Arrow Style</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for enum '<em>Arrow Style</em>'.
     * @see org.eclipse.sirius.web.view.ArrowStyle
     * @generated
     */
    EEnum getArrowStyle();

    /**
     * Returns the meta object for enum '{@link org.eclipse.sirius.web.view.LineStyle <em>Line Style</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for enum '<em>Line Style</em>'.
     * @see org.eclipse.sirius.web.view.LineStyle
     * @generated
     */
    EEnum getLineStyle();

    /**
     * Returns the factory that creates the instances of the model. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the factory that creates the instances of the model.
     * @generated
     */
    ViewFactory getViewFactory();

    /**
     * <!-- begin-user-doc --> Defines literals for the meta objects that represent
     * <ul>
     * <li>each class,</li>
     * <li>each feature of each class,</li>
     * <li>each operation of each class,</li>
     * <li>each enum,</li>
     * <li>and each data type</li>
     * </ul>
     * <!-- end-user-doc -->
     *
     * @generated
     */
    interface Literals {
        /**
         * The meta object literal for the '{@link org.eclipse.sirius.web.view.impl.ViewImpl <em>View</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.web.view.impl.ViewImpl
         * @see org.eclipse.sirius.web.view.impl.ViewPackageImpl#getView()
         * @generated
         */
        EClass VIEW = eINSTANCE.getView();

        /**
         * The meta object literal for the '<em><b>Descriptions</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference VIEW__DESCRIPTIONS = eINSTANCE.getView_Descriptions();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.web.view.impl.RepresentationDescriptionImpl
         * <em>Representation Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.web.view.impl.RepresentationDescriptionImpl
         * @see org.eclipse.sirius.web.view.impl.ViewPackageImpl#getRepresentationDescription()
         * @generated
         */
        EClass REPRESENTATION_DESCRIPTION = eINSTANCE.getRepresentationDescription();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute REPRESENTATION_DESCRIPTION__NAME = eINSTANCE.getRepresentationDescription_Name();

        /**
         * The meta object literal for the '<em><b>Domain Type</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute REPRESENTATION_DESCRIPTION__DOMAIN_TYPE = eINSTANCE.getRepresentationDescription_DomainType();

        /**
         * The meta object literal for the '<em><b>Title Expression</b></em>' attribute feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute REPRESENTATION_DESCRIPTION__TITLE_EXPRESSION = eINSTANCE.getRepresentationDescription_TitleExpression();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.web.view.impl.DiagramDescriptionImpl <em>Diagram
         * Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.web.view.impl.DiagramDescriptionImpl
         * @see org.eclipse.sirius.web.view.impl.ViewPackageImpl#getDiagramDescription()
         * @generated
         */
        EClass DIAGRAM_DESCRIPTION = eINSTANCE.getDiagramDescription();

        /**
         * The meta object literal for the '<em><b>Auto Layout</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute DIAGRAM_DESCRIPTION__AUTO_LAYOUT = eINSTANCE.getDiagramDescription_AutoLayout();

        /**
         * The meta object literal for the '<em><b>Node Descriptions</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DIAGRAM_DESCRIPTION__NODE_DESCRIPTIONS = eINSTANCE.getDiagramDescription_NodeDescriptions();

        /**
         * The meta object literal for the '<em><b>Edge Descriptions</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DIAGRAM_DESCRIPTION__EDGE_DESCRIPTIONS = eINSTANCE.getDiagramDescription_EdgeDescriptions();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.web.view.impl.DiagramElementDescriptionImpl
         * <em>Diagram Element Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.web.view.impl.DiagramElementDescriptionImpl
         * @see org.eclipse.sirius.web.view.impl.ViewPackageImpl#getDiagramElementDescription()
         * @generated
         */
        EClass DIAGRAM_ELEMENT_DESCRIPTION = eINSTANCE.getDiagramElementDescription();

        /**
         * The meta object literal for the '<em><b>Domain Type</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute DIAGRAM_ELEMENT_DESCRIPTION__DOMAIN_TYPE = eINSTANCE.getDiagramElementDescription_DomainType();

        /**
         * The meta object literal for the '<em><b>Semantic Candidates Expression</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute DIAGRAM_ELEMENT_DESCRIPTION__SEMANTIC_CANDIDATES_EXPRESSION = eINSTANCE.getDiagramElementDescription_SemanticCandidatesExpression();

        /**
         * The meta object literal for the '<em><b>Label Expression</b></em>' attribute feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute DIAGRAM_ELEMENT_DESCRIPTION__LABEL_EXPRESSION = eINSTANCE.getDiagramElementDescription_LabelExpression();

        /**
         * The meta object literal for the '<em><b>Delete Tool</b></em>' containment reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DIAGRAM_ELEMENT_DESCRIPTION__DELETE_TOOL = eINSTANCE.getDiagramElementDescription_DeleteTool();

        /**
         * The meta object literal for the '<em><b>Label Edit Tool</b></em>' containment reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DIAGRAM_ELEMENT_DESCRIPTION__LABEL_EDIT_TOOL = eINSTANCE.getDiagramElementDescription_LabelEditTool();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.web.view.impl.NodeDescriptionImpl <em>Node
         * Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.web.view.impl.NodeDescriptionImpl
         * @see org.eclipse.sirius.web.view.impl.ViewPackageImpl#getNodeDescription()
         * @generated
         */
        EClass NODE_DESCRIPTION = eINSTANCE.getNodeDescription();

        /**
         * The meta object literal for the '<em><b>Children Descriptions</b></em>' containment reference list feature.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference NODE_DESCRIPTION__CHILDREN_DESCRIPTIONS = eINSTANCE.getNodeDescription_ChildrenDescriptions();

        /**
         * The meta object literal for the '<em><b>Style</b></em>' containment reference feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference NODE_DESCRIPTION__STYLE = eINSTANCE.getNodeDescription_Style();

        /**
         * The meta object literal for the '<em><b>Node Tools</b></em>' containment reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference NODE_DESCRIPTION__NODE_TOOLS = eINSTANCE.getNodeDescription_NodeTools();

        /**
         * The meta object literal for the '<em><b>Conditional Styles</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference NODE_DESCRIPTION__CONDITIONAL_STYLES = eINSTANCE.getNodeDescription_ConditionalStyles();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.web.view.impl.EdgeDescriptionImpl <em>Edge
         * Description</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.web.view.impl.EdgeDescriptionImpl
         * @see org.eclipse.sirius.web.view.impl.ViewPackageImpl#getEdgeDescription()
         * @generated
         */
        EClass EDGE_DESCRIPTION = eINSTANCE.getEdgeDescription();

        /**
         * The meta object literal for the '<em><b>Is Domain Based Edge</b></em>' attribute feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute EDGE_DESCRIPTION__IS_DOMAIN_BASED_EDGE = eINSTANCE.getEdgeDescription_IsDomainBasedEdge();

        /**
         * The meta object literal for the '<em><b>Source Node Description</b></em>' reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference EDGE_DESCRIPTION__SOURCE_NODE_DESCRIPTION = eINSTANCE.getEdgeDescription_SourceNodeDescription();

        /**
         * The meta object literal for the '<em><b>Target Node Description</b></em>' reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference EDGE_DESCRIPTION__TARGET_NODE_DESCRIPTION = eINSTANCE.getEdgeDescription_TargetNodeDescription();

        /**
         * The meta object literal for the '<em><b>Source Nodes Expression</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute EDGE_DESCRIPTION__SOURCE_NODES_EXPRESSION = eINSTANCE.getEdgeDescription_SourceNodesExpression();

        /**
         * The meta object literal for the '<em><b>Target Nodes Expression</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute EDGE_DESCRIPTION__TARGET_NODES_EXPRESSION = eINSTANCE.getEdgeDescription_TargetNodesExpression();

        /**
         * The meta object literal for the '<em><b>Style</b></em>' containment reference feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference EDGE_DESCRIPTION__STYLE = eINSTANCE.getEdgeDescription_Style();

        /**
         * The meta object literal for the '<em><b>Edge Tools</b></em>' containment reference feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference EDGE_DESCRIPTION__EDGE_TOOLS = eINSTANCE.getEdgeDescription_EdgeTools();

        /**
         * The meta object literal for the '<em><b>Conditional Styles</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference EDGE_DESCRIPTION__CONDITIONAL_STYLES = eINSTANCE.getEdgeDescription_ConditionalStyles();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.web.view.impl.StyleImpl <em>Style</em>}' class.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.web.view.impl.StyleImpl
         * @see org.eclipse.sirius.web.view.impl.ViewPackageImpl#getStyle()
         * @generated
         */
        EClass STYLE = eINSTANCE.getStyle();

        /**
         * The meta object literal for the '<em><b>Color</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute STYLE__COLOR = eINSTANCE.getStyle_Color();

        /**
         * The meta object literal for the '<em><b>Border Color</b></em>' attribute feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute STYLE__BORDER_COLOR = eINSTANCE.getStyle_BorderColor();

        /**
         * The meta object literal for the '<em><b>Font Size</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute STYLE__FONT_SIZE = eINSTANCE.getStyle_FontSize();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.web.view.impl.NodeStyleImpl <em>Node Style</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.web.view.impl.NodeStyleImpl
         * @see org.eclipse.sirius.web.view.impl.ViewPackageImpl#getNodeStyle()
         * @generated
         */
        EClass NODE_STYLE = eINSTANCE.getNodeStyle();

        /**
         * The meta object literal for the '<em><b>List Mode</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute NODE_STYLE__LIST_MODE = eINSTANCE.getNodeStyle_ListMode();

        /**
         * The meta object literal for the '<em><b>Border Radius</b></em>' attribute feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute NODE_STYLE__BORDER_RADIUS = eINSTANCE.getNodeStyle_BorderRadius();

        /**
         * The meta object literal for the '<em><b>Shape</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute NODE_STYLE__SHAPE = eINSTANCE.getNodeStyle_Shape();

        /**
         * The meta object literal for the '<em><b>Border Size</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute NODE_STYLE__BORDER_SIZE = eINSTANCE.getNodeStyle_BorderSize();

        /**
         * The meta object literal for the '<em><b>Label Color</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute NODE_STYLE__LABEL_COLOR = eINSTANCE.getNodeStyle_LabelColor();

        /**
         * The meta object literal for the '<em><b>Italic</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute NODE_STYLE__ITALIC = eINSTANCE.getNodeStyle_Italic();

        /**
         * The meta object literal for the '<em><b>Bold</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute NODE_STYLE__BOLD = eINSTANCE.getNodeStyle_Bold();

        /**
         * The meta object literal for the '<em><b>Underline</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute NODE_STYLE__UNDERLINE = eINSTANCE.getNodeStyle_Underline();

        /**
         * The meta object literal for the '<em><b>Strike Through</b></em>' attribute feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute NODE_STYLE__STRIKE_THROUGH = eINSTANCE.getNodeStyle_StrikeThrough();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.web.view.impl.EdgeStyleImpl <em>Edge Style</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.web.view.impl.EdgeStyleImpl
         * @see org.eclipse.sirius.web.view.impl.ViewPackageImpl#getEdgeStyle()
         * @generated
         */
        EClass EDGE_STYLE = eINSTANCE.getEdgeStyle();

        /**
         * The meta object literal for the '<em><b>Line Style</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute EDGE_STYLE__LINE_STYLE = eINSTANCE.getEdgeStyle_LineStyle();

        /**
         * The meta object literal for the '<em><b>Source Arrow Style</b></em>' attribute feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute EDGE_STYLE__SOURCE_ARROW_STYLE = eINSTANCE.getEdgeStyle_SourceArrowStyle();

        /**
         * The meta object literal for the '<em><b>Target Arrow Style</b></em>' attribute feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute EDGE_STYLE__TARGET_ARROW_STYLE = eINSTANCE.getEdgeStyle_TargetArrowStyle();

        /**
         * The meta object literal for the '<em><b>Edge Width</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute EDGE_STYLE__EDGE_WIDTH = eINSTANCE.getEdgeStyle_EdgeWidth();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.web.view.impl.ToolImpl <em>Tool</em>}' class. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.web.view.impl.ToolImpl
         * @see org.eclipse.sirius.web.view.impl.ViewPackageImpl#getTool()
         * @generated
         */
        EClass TOOL = eINSTANCE.getTool();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute TOOL__NAME = eINSTANCE.getTool_Name();

        /**
         * The meta object literal for the '<em><b>Body</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference TOOL__BODY = eINSTANCE.getTool_Body();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.web.view.impl.LabelEditToolImpl <em>Label Edit
         * Tool</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.web.view.impl.LabelEditToolImpl
         * @see org.eclipse.sirius.web.view.impl.ViewPackageImpl#getLabelEditTool()
         * @generated
         */
        EClass LABEL_EDIT_TOOL = eINSTANCE.getLabelEditTool();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.web.view.impl.DeleteToolImpl <em>Delete
         * Tool</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.web.view.impl.DeleteToolImpl
         * @see org.eclipse.sirius.web.view.impl.ViewPackageImpl#getDeleteTool()
         * @generated
         */
        EClass DELETE_TOOL = eINSTANCE.getDeleteTool();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.web.view.impl.NodeToolImpl <em>Node Tool</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.web.view.impl.NodeToolImpl
         * @see org.eclipse.sirius.web.view.impl.ViewPackageImpl#getNodeTool()
         * @generated
         */
        EClass NODE_TOOL = eINSTANCE.getNodeTool();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.web.view.impl.EdgeToolImpl <em>Edge Tool</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.web.view.impl.EdgeToolImpl
         * @see org.eclipse.sirius.web.view.impl.ViewPackageImpl#getEdgeTool()
         * @generated
         */
        EClass EDGE_TOOL = eINSTANCE.getEdgeTool();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.web.view.impl.OperationImpl <em>Operation</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.web.view.impl.OperationImpl
         * @see org.eclipse.sirius.web.view.impl.ViewPackageImpl#getOperation()
         * @generated
         */
        EClass OPERATION = eINSTANCE.getOperation();

        /**
         * The meta object literal for the '<em><b>Children</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference OPERATION__CHILDREN = eINSTANCE.getOperation_Children();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.web.view.impl.ChangeContextImpl <em>Change
         * Context</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.web.view.impl.ChangeContextImpl
         * @see org.eclipse.sirius.web.view.impl.ViewPackageImpl#getChangeContext()
         * @generated
         */
        EClass CHANGE_CONTEXT = eINSTANCE.getChangeContext();

        /**
         * The meta object literal for the '<em><b>Expression</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute CHANGE_CONTEXT__EXPRESSION = eINSTANCE.getChangeContext_Expression();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.web.view.impl.CreateInstanceImpl <em>Create
         * Instance</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.web.view.impl.CreateInstanceImpl
         * @see org.eclipse.sirius.web.view.impl.ViewPackageImpl#getCreateInstance()
         * @generated
         */
        EClass CREATE_INSTANCE = eINSTANCE.getCreateInstance();

        /**
         * The meta object literal for the '<em><b>Type Name</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute CREATE_INSTANCE__TYPE_NAME = eINSTANCE.getCreateInstance_TypeName();

        /**
         * The meta object literal for the '<em><b>Reference Name</b></em>' attribute feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute CREATE_INSTANCE__REFERENCE_NAME = eINSTANCE.getCreateInstance_ReferenceName();

        /**
         * The meta object literal for the '<em><b>Variable Name</b></em>' attribute feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute CREATE_INSTANCE__VARIABLE_NAME = eINSTANCE.getCreateInstance_VariableName();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.web.view.impl.SetValueImpl <em>Set Value</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.web.view.impl.SetValueImpl
         * @see org.eclipse.sirius.web.view.impl.ViewPackageImpl#getSetValue()
         * @generated
         */
        EClass SET_VALUE = eINSTANCE.getSetValue();

        /**
         * The meta object literal for the '<em><b>Feature Name</b></em>' attribute feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SET_VALUE__FEATURE_NAME = eINSTANCE.getSetValue_FeatureName();

        /**
         * The meta object literal for the '<em><b>Value Expression</b></em>' attribute feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute SET_VALUE__VALUE_EXPRESSION = eINSTANCE.getSetValue_ValueExpression();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.web.view.impl.UnsetValueImpl <em>Unset
         * Value</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.web.view.impl.UnsetValueImpl
         * @see org.eclipse.sirius.web.view.impl.ViewPackageImpl#getUnsetValue()
         * @generated
         */
        EClass UNSET_VALUE = eINSTANCE.getUnsetValue();

        /**
         * The meta object literal for the '<em><b>Feature Name</b></em>' attribute feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute UNSET_VALUE__FEATURE_NAME = eINSTANCE.getUnsetValue_FeatureName();

        /**
         * The meta object literal for the '<em><b>Element Expression</b></em>' attribute feature. <!-- begin-user-doc
         * --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute UNSET_VALUE__ELEMENT_EXPRESSION = eINSTANCE.getUnsetValue_ElementExpression();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.web.view.impl.DeleteElementImpl <em>Delete
         * Element</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.web.view.impl.DeleteElementImpl
         * @see org.eclipse.sirius.web.view.impl.ViewPackageImpl#getDeleteElement()
         * @generated
         */
        EClass DELETE_ELEMENT = eINSTANCE.getDeleteElement();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.web.view.impl.ConditionalImpl
         * <em>Conditional</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.web.view.impl.ConditionalImpl
         * @see org.eclipse.sirius.web.view.impl.ViewPackageImpl#getConditional()
         * @generated
         */
        EClass CONDITIONAL = eINSTANCE.getConditional();

        /**
         * The meta object literal for the '<em><b>Condition</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute CONDITIONAL__CONDITION = eINSTANCE.getConditional_Condition();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.web.view.impl.ConditionalNodeStyleImpl
         * <em>Conditional Node Style</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.web.view.impl.ConditionalNodeStyleImpl
         * @see org.eclipse.sirius.web.view.impl.ViewPackageImpl#getConditionalNodeStyle()
         * @generated
         */
        EClass CONDITIONAL_NODE_STYLE = eINSTANCE.getConditionalNodeStyle();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.web.view.impl.ConditionalEdgeStyleImpl
         * <em>Conditional Edge Style</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.web.view.impl.ConditionalEdgeStyleImpl
         * @see org.eclipse.sirius.web.view.impl.ViewPackageImpl#getConditionalEdgeStyle()
         * @generated
         */
        EClass CONDITIONAL_EDGE_STYLE = eINSTANCE.getConditionalEdgeStyle();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.web.view.ArrowStyle <em>Arrow Style</em>}' enum.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.web.view.ArrowStyle
         * @see org.eclipse.sirius.web.view.impl.ViewPackageImpl#getArrowStyle()
         * @generated
         */
        EEnum ARROW_STYLE = eINSTANCE.getArrowStyle();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.web.view.LineStyle <em>Line Style</em>}' enum.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.web.view.LineStyle
         * @see org.eclipse.sirius.web.view.impl.ViewPackageImpl#getLineStyle()
         * @generated
         */
        EEnum LINE_STYLE = eINSTANCE.getLineStyle();

    }

} // ViewPackage
