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
package org.eclipse.sirius.web.view.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.sirius.web.view.ArrowStyle;
import org.eclipse.sirius.web.view.ChangeContext;
import org.eclipse.sirius.web.view.Conditional;
import org.eclipse.sirius.web.view.ConditionalEdgeStyle;
import org.eclipse.sirius.web.view.ConditionalNodeStyle;
import org.eclipse.sirius.web.view.CreateInstance;
import org.eclipse.sirius.web.view.DeleteElement;
import org.eclipse.sirius.web.view.DeleteTool;
import org.eclipse.sirius.web.view.DiagramDescription;
import org.eclipse.sirius.web.view.DiagramElementDescription;
import org.eclipse.sirius.web.view.EdgeDescription;
import org.eclipse.sirius.web.view.EdgeStyle;
import org.eclipse.sirius.web.view.EdgeTool;
import org.eclipse.sirius.web.view.LabelEditTool;
import org.eclipse.sirius.web.view.LineStyle;
import org.eclipse.sirius.web.view.NodeDescription;
import org.eclipse.sirius.web.view.NodeStyle;
import org.eclipse.sirius.web.view.NodeTool;
import org.eclipse.sirius.web.view.Operation;
import org.eclipse.sirius.web.view.RepresentationDescription;
import org.eclipse.sirius.web.view.SetValue;
import org.eclipse.sirius.web.view.Style;
import org.eclipse.sirius.web.view.Tool;
import org.eclipse.sirius.web.view.UnsetValue;
import org.eclipse.sirius.web.view.View;
import org.eclipse.sirius.web.view.ViewFactory;
import org.eclipse.sirius.web.view.ViewPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!-- end-user-doc -->
 *
 * @generated
 */
public class ViewPackageImpl extends EPackageImpl implements ViewPackage {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass viewEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass representationDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass diagramDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass diagramElementDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass nodeDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass edgeDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass styleEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass nodeStyleEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass edgeStyleEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass toolEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass labelEditToolEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass deleteToolEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass nodeToolEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass edgeToolEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass operationEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass changeContextEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass createInstanceEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass setValueEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass unsetValueEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass deleteElementEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass conditionalEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass conditionalNodeStyleEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass conditionalEdgeStyleEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EEnum arrowStyleEEnum = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EEnum lineStyleEEnum = null;

    /**
     * Creates an instance of the model <b>Package</b>, registered with {@link org.eclipse.emf.ecore.EPackage.Registry
     * EPackage.Registry} by the package package URI value.
     * <p>
     * Note: the correct way to create the package is via the static factory method {@link #init init()}, which also
     * performs initialization of the package, or returns the registered package, if one already exists. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.emf.ecore.EPackage.Registry
     * @see org.eclipse.sirius.web.view.ViewPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private ViewPackageImpl() {
        super(eNS_URI, ViewFactory.eINSTANCE);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private static boolean isInited = false;

    /**
     * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
     *
     * <p>
     * This method is used to initialize {@link ViewPackage#eINSTANCE} when that field is accessed. Clients should not
     * invoke it directly. Instead, they should simply access that field to obtain the package. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static ViewPackage init() {
        if (isInited)
            return (ViewPackage) EPackage.Registry.INSTANCE.getEPackage(ViewPackage.eNS_URI);

        // Obtain or create and register package
        Object registeredViewPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
        ViewPackageImpl theViewPackage = registeredViewPackage instanceof ViewPackageImpl ? (ViewPackageImpl) registeredViewPackage : new ViewPackageImpl();

        isInited = true;

        // Create package meta-data objects
        theViewPackage.createPackageContents();

        // Initialize created meta-data
        theViewPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theViewPackage.freeze();

        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(ViewPackage.eNS_URI, theViewPackage);
        return theViewPackage;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getView() {
        return this.viewEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getView_Descriptions() {
        return (EReference) this.viewEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getRepresentationDescription() {
        return this.representationDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getRepresentationDescription_Name() {
        return (EAttribute) this.representationDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getRepresentationDescription_DomainType() {
        return (EAttribute) this.representationDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getRepresentationDescription_TitleExpression() {
        return (EAttribute) this.representationDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getDiagramDescription() {
        return this.diagramDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getDiagramDescription_AutoLayout() {
        return (EAttribute) this.diagramDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDiagramDescription_NodeDescriptions() {
        return (EReference) this.diagramDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDiagramDescription_EdgeDescriptions() {
        return (EReference) this.diagramDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getDiagramElementDescription() {
        return this.diagramElementDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getDiagramElementDescription_DomainType() {
        return (EAttribute) this.diagramElementDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getDiagramElementDescription_SemanticCandidatesExpression() {
        return (EAttribute) this.diagramElementDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getDiagramElementDescription_LabelExpression() {
        return (EAttribute) this.diagramElementDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDiagramElementDescription_DeleteTool() {
        return (EReference) this.diagramElementDescriptionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDiagramElementDescription_LabelEditTool() {
        return (EReference) this.diagramElementDescriptionEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getNodeDescription() {
        return this.nodeDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getNodeDescription_ChildrenDescriptions() {
        return (EReference) this.nodeDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getNodeDescription_Style() {
        return (EReference) this.nodeDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getNodeDescription_NodeTools() {
        return (EReference) this.nodeDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getNodeDescription_ConditionalStyles() {
        return (EReference) this.nodeDescriptionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getEdgeDescription() {
        return this.edgeDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getEdgeDescription_IsDomainBasedEdge() {
        return (EAttribute) this.edgeDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getEdgeDescription_SourceNodeDescription() {
        return (EReference) this.edgeDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getEdgeDescription_TargetNodeDescription() {
        return (EReference) this.edgeDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getEdgeDescription_SourceNodesExpression() {
        return (EAttribute) this.edgeDescriptionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getEdgeDescription_TargetNodesExpression() {
        return (EAttribute) this.edgeDescriptionEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getEdgeDescription_Style() {
        return (EReference) this.edgeDescriptionEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getEdgeDescription_EdgeTools() {
        return (EReference) this.edgeDescriptionEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getEdgeDescription_ConditionalStyles() {
        return (EReference) this.edgeDescriptionEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getStyle() {
        return this.styleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getStyle_Color() {
        return (EAttribute) this.styleEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getStyle_BorderColor() {
        return (EAttribute) this.styleEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getStyle_FontSize() {
        return (EAttribute) this.styleEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getNodeStyle() {
        return this.nodeStyleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getNodeStyle_ListMode() {
        return (EAttribute) this.nodeStyleEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getNodeStyle_BorderRadius() {
        return (EAttribute) this.nodeStyleEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getNodeStyle_Shape() {
        return (EAttribute) this.nodeStyleEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getNodeStyle_BorderSize() {
        return (EAttribute) this.nodeStyleEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getNodeStyle_LabelColor() {
        return (EAttribute) this.nodeStyleEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getNodeStyle_Italic() {
        return (EAttribute) this.nodeStyleEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getNodeStyle_Bold() {
        return (EAttribute) this.nodeStyleEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getNodeStyle_Underline() {
        return (EAttribute) this.nodeStyleEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getNodeStyle_StrikeThrough() {
        return (EAttribute) this.nodeStyleEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getEdgeStyle() {
        return this.edgeStyleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getEdgeStyle_LineStyle() {
        return (EAttribute) this.edgeStyleEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getEdgeStyle_SourceArrowStyle() {
        return (EAttribute) this.edgeStyleEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getEdgeStyle_TargetArrowStyle() {
        return (EAttribute) this.edgeStyleEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getEdgeStyle_EdgeWidth() {
        return (EAttribute) this.edgeStyleEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getTool() {
        return this.toolEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getTool_Name() {
        return (EAttribute) this.toolEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getTool_Body() {
        return (EReference) this.toolEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getLabelEditTool() {
        return this.labelEditToolEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getDeleteTool() {
        return this.deleteToolEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getNodeTool() {
        return this.nodeToolEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getEdgeTool() {
        return this.edgeToolEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getOperation() {
        return this.operationEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getOperation_Children() {
        return (EReference) this.operationEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getChangeContext() {
        return this.changeContextEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getChangeContext_Expression() {
        return (EAttribute) this.changeContextEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getCreateInstance() {
        return this.createInstanceEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getCreateInstance_TypeName() {
        return (EAttribute) this.createInstanceEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getCreateInstance_ReferenceName() {
        return (EAttribute) this.createInstanceEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getCreateInstance_VariableName() {
        return (EAttribute) this.createInstanceEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getSetValue() {
        return this.setValueEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getSetValue_FeatureName() {
        return (EAttribute) this.setValueEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getSetValue_ValueExpression() {
        return (EAttribute) this.setValueEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getUnsetValue() {
        return this.unsetValueEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getUnsetValue_FeatureName() {
        return (EAttribute) this.unsetValueEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getUnsetValue_ElementExpression() {
        return (EAttribute) this.unsetValueEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getDeleteElement() {
        return this.deleteElementEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getConditional() {
        return this.conditionalEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getConditional_Condition() {
        return (EAttribute) this.conditionalEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getConditionalNodeStyle() {
        return this.conditionalNodeStyleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getConditionalEdgeStyle() {
        return this.conditionalEdgeStyleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EEnum getArrowStyle() {
        return this.arrowStyleEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EEnum getLineStyle() {
        return this.lineStyleEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ViewFactory getViewFactory() {
        return (ViewFactory) this.getEFactoryInstance();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private boolean isCreated = false;

    /**
     * Creates the meta-model objects for the package. This method is guarded to have no affect on any invocation but
     * its first. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public void createPackageContents() {
        if (this.isCreated)
            return;
        this.isCreated = true;

        // Create classes and their features
        this.viewEClass = this.createEClass(VIEW);
        this.createEReference(this.viewEClass, VIEW__DESCRIPTIONS);

        this.representationDescriptionEClass = this.createEClass(REPRESENTATION_DESCRIPTION);
        this.createEAttribute(this.representationDescriptionEClass, REPRESENTATION_DESCRIPTION__NAME);
        this.createEAttribute(this.representationDescriptionEClass, REPRESENTATION_DESCRIPTION__DOMAIN_TYPE);
        this.createEAttribute(this.representationDescriptionEClass, REPRESENTATION_DESCRIPTION__TITLE_EXPRESSION);

        this.diagramDescriptionEClass = this.createEClass(DIAGRAM_DESCRIPTION);
        this.createEAttribute(this.diagramDescriptionEClass, DIAGRAM_DESCRIPTION__AUTO_LAYOUT);
        this.createEReference(this.diagramDescriptionEClass, DIAGRAM_DESCRIPTION__NODE_DESCRIPTIONS);
        this.createEReference(this.diagramDescriptionEClass, DIAGRAM_DESCRIPTION__EDGE_DESCRIPTIONS);

        this.diagramElementDescriptionEClass = this.createEClass(DIAGRAM_ELEMENT_DESCRIPTION);
        this.createEAttribute(this.diagramElementDescriptionEClass, DIAGRAM_ELEMENT_DESCRIPTION__DOMAIN_TYPE);
        this.createEAttribute(this.diagramElementDescriptionEClass, DIAGRAM_ELEMENT_DESCRIPTION__SEMANTIC_CANDIDATES_EXPRESSION);
        this.createEAttribute(this.diagramElementDescriptionEClass, DIAGRAM_ELEMENT_DESCRIPTION__LABEL_EXPRESSION);
        this.createEReference(this.diagramElementDescriptionEClass, DIAGRAM_ELEMENT_DESCRIPTION__DELETE_TOOL);
        this.createEReference(this.diagramElementDescriptionEClass, DIAGRAM_ELEMENT_DESCRIPTION__LABEL_EDIT_TOOL);

        this.nodeDescriptionEClass = this.createEClass(NODE_DESCRIPTION);
        this.createEReference(this.nodeDescriptionEClass, NODE_DESCRIPTION__CHILDREN_DESCRIPTIONS);
        this.createEReference(this.nodeDescriptionEClass, NODE_DESCRIPTION__STYLE);
        this.createEReference(this.nodeDescriptionEClass, NODE_DESCRIPTION__NODE_TOOLS);
        this.createEReference(this.nodeDescriptionEClass, NODE_DESCRIPTION__CONDITIONAL_STYLES);

        this.edgeDescriptionEClass = this.createEClass(EDGE_DESCRIPTION);
        this.createEAttribute(this.edgeDescriptionEClass, EDGE_DESCRIPTION__IS_DOMAIN_BASED_EDGE);
        this.createEReference(this.edgeDescriptionEClass, EDGE_DESCRIPTION__SOURCE_NODE_DESCRIPTION);
        this.createEReference(this.edgeDescriptionEClass, EDGE_DESCRIPTION__TARGET_NODE_DESCRIPTION);
        this.createEAttribute(this.edgeDescriptionEClass, EDGE_DESCRIPTION__SOURCE_NODES_EXPRESSION);
        this.createEAttribute(this.edgeDescriptionEClass, EDGE_DESCRIPTION__TARGET_NODES_EXPRESSION);
        this.createEReference(this.edgeDescriptionEClass, EDGE_DESCRIPTION__STYLE);
        this.createEReference(this.edgeDescriptionEClass, EDGE_DESCRIPTION__EDGE_TOOLS);
        this.createEReference(this.edgeDescriptionEClass, EDGE_DESCRIPTION__CONDITIONAL_STYLES);

        this.styleEClass = this.createEClass(STYLE);
        this.createEAttribute(this.styleEClass, STYLE__COLOR);
        this.createEAttribute(this.styleEClass, STYLE__BORDER_COLOR);
        this.createEAttribute(this.styleEClass, STYLE__FONT_SIZE);

        this.nodeStyleEClass = this.createEClass(NODE_STYLE);
        this.createEAttribute(this.nodeStyleEClass, NODE_STYLE__LIST_MODE);
        this.createEAttribute(this.nodeStyleEClass, NODE_STYLE__BORDER_RADIUS);
        this.createEAttribute(this.nodeStyleEClass, NODE_STYLE__SHAPE);
        this.createEAttribute(this.nodeStyleEClass, NODE_STYLE__BORDER_SIZE);
        this.createEAttribute(this.nodeStyleEClass, NODE_STYLE__LABEL_COLOR);
        this.createEAttribute(this.nodeStyleEClass, NODE_STYLE__ITALIC);
        this.createEAttribute(this.nodeStyleEClass, NODE_STYLE__BOLD);
        this.createEAttribute(this.nodeStyleEClass, NODE_STYLE__UNDERLINE);
        this.createEAttribute(this.nodeStyleEClass, NODE_STYLE__STRIKE_THROUGH);

        this.edgeStyleEClass = this.createEClass(EDGE_STYLE);
        this.createEAttribute(this.edgeStyleEClass, EDGE_STYLE__LINE_STYLE);
        this.createEAttribute(this.edgeStyleEClass, EDGE_STYLE__SOURCE_ARROW_STYLE);
        this.createEAttribute(this.edgeStyleEClass, EDGE_STYLE__TARGET_ARROW_STYLE);
        this.createEAttribute(this.edgeStyleEClass, EDGE_STYLE__EDGE_WIDTH);

        this.toolEClass = this.createEClass(TOOL);
        this.createEAttribute(this.toolEClass, TOOL__NAME);
        this.createEReference(this.toolEClass, TOOL__BODY);

        this.labelEditToolEClass = this.createEClass(LABEL_EDIT_TOOL);

        this.deleteToolEClass = this.createEClass(DELETE_TOOL);

        this.nodeToolEClass = this.createEClass(NODE_TOOL);

        this.edgeToolEClass = this.createEClass(EDGE_TOOL);

        this.operationEClass = this.createEClass(OPERATION);
        this.createEReference(this.operationEClass, OPERATION__CHILDREN);

        this.changeContextEClass = this.createEClass(CHANGE_CONTEXT);
        this.createEAttribute(this.changeContextEClass, CHANGE_CONTEXT__EXPRESSION);

        this.createInstanceEClass = this.createEClass(CREATE_INSTANCE);
        this.createEAttribute(this.createInstanceEClass, CREATE_INSTANCE__TYPE_NAME);
        this.createEAttribute(this.createInstanceEClass, CREATE_INSTANCE__REFERENCE_NAME);
        this.createEAttribute(this.createInstanceEClass, CREATE_INSTANCE__VARIABLE_NAME);

        this.setValueEClass = this.createEClass(SET_VALUE);
        this.createEAttribute(this.setValueEClass, SET_VALUE__FEATURE_NAME);
        this.createEAttribute(this.setValueEClass, SET_VALUE__VALUE_EXPRESSION);

        this.unsetValueEClass = this.createEClass(UNSET_VALUE);
        this.createEAttribute(this.unsetValueEClass, UNSET_VALUE__FEATURE_NAME);
        this.createEAttribute(this.unsetValueEClass, UNSET_VALUE__ELEMENT_EXPRESSION);

        this.deleteElementEClass = this.createEClass(DELETE_ELEMENT);

        this.conditionalEClass = this.createEClass(CONDITIONAL);
        this.createEAttribute(this.conditionalEClass, CONDITIONAL__CONDITION);

        this.conditionalNodeStyleEClass = this.createEClass(CONDITIONAL_NODE_STYLE);

        this.conditionalEdgeStyleEClass = this.createEClass(CONDITIONAL_EDGE_STYLE);

        // Create enums
        this.arrowStyleEEnum = this.createEEnum(ARROW_STYLE);
        this.lineStyleEEnum = this.createEEnum(LINE_STYLE);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private boolean isInitialized = false;

    /**
     * Complete the initialization of the package and its meta-model. This method is guarded to have no affect on any
     * invocation but its first. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public void initializePackageContents() {
        if (this.isInitialized)
            return;
        this.isInitialized = true;

        // Initialize package
        this.setName(eNAME);
        this.setNsPrefix(eNS_PREFIX);
        this.setNsURI(eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        this.diagramDescriptionEClass.getESuperTypes().add(this.getRepresentationDescription());
        this.nodeDescriptionEClass.getESuperTypes().add(this.getDiagramElementDescription());
        this.edgeDescriptionEClass.getESuperTypes().add(this.getDiagramElementDescription());
        this.nodeStyleEClass.getESuperTypes().add(this.getStyle());
        this.edgeStyleEClass.getESuperTypes().add(this.getStyle());
        this.labelEditToolEClass.getESuperTypes().add(this.getTool());
        this.deleteToolEClass.getESuperTypes().add(this.getTool());
        this.nodeToolEClass.getESuperTypes().add(this.getTool());
        this.edgeToolEClass.getESuperTypes().add(this.getTool());
        this.changeContextEClass.getESuperTypes().add(this.getOperation());
        this.createInstanceEClass.getESuperTypes().add(this.getOperation());
        this.setValueEClass.getESuperTypes().add(this.getOperation());
        this.unsetValueEClass.getESuperTypes().add(this.getOperation());
        this.deleteElementEClass.getESuperTypes().add(this.getOperation());
        this.conditionalNodeStyleEClass.getESuperTypes().add(this.getConditional());
        this.conditionalNodeStyleEClass.getESuperTypes().add(this.getNodeStyle());
        this.conditionalEdgeStyleEClass.getESuperTypes().add(this.getConditional());
        this.conditionalEdgeStyleEClass.getESuperTypes().add(this.getEdgeStyle());

        // Initialize classes, features, and operations; add parameters
        this.initEClass(this.viewEClass, View.class, "View", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        this.initEReference(this.getView_Descriptions(), this.getRepresentationDescription(), null, "descriptions", null, 0, -1, View.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, //$NON-NLS-1$
                !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        this.initEClass(this.representationDescriptionEClass, RepresentationDescription.class, "RepresentationDescription", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        this.initEAttribute(this.getRepresentationDescription_Name(), this.ecorePackage.getEString(), "name", "NewRepresentationDescription", 0, 1, RepresentationDescription.class, !IS_TRANSIENT, //$NON-NLS-1$ //$NON-NLS-2$
                !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEAttribute(this.getRepresentationDescription_DomainType(), this.ecorePackage.getEString(), "domainType", "", 0, 1, RepresentationDescription.class, !IS_TRANSIENT, !IS_VOLATILE, //$NON-NLS-1$ //$NON-NLS-2$
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEAttribute(this.getRepresentationDescription_TitleExpression(), this.ecorePackage.getEString(), "titleExpression", "aql:\'New Representation\'", 0, 1, RepresentationDescription.class, //$NON-NLS-1$ //$NON-NLS-2$
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        this.initEClass(this.diagramDescriptionEClass, DiagramDescription.class, "DiagramDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        this.initEAttribute(this.getDiagramDescription_AutoLayout(), this.ecorePackage.getEBoolean(), "autoLayout", null, 1, 1, DiagramDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, //$NON-NLS-1$
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEReference(this.getDiagramDescription_NodeDescriptions(), this.getNodeDescription(), null, "nodeDescriptions", null, 0, -1, DiagramDescription.class, !IS_TRANSIENT, !IS_VOLATILE, //$NON-NLS-1$
                IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEReference(this.getDiagramDescription_EdgeDescriptions(), this.getEdgeDescription(), null, "edgeDescriptions", null, 0, -1, DiagramDescription.class, !IS_TRANSIENT, !IS_VOLATILE, //$NON-NLS-1$
                IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        this.initEClass(this.diagramElementDescriptionEClass, DiagramElementDescription.class, "DiagramElementDescription", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        this.initEAttribute(this.getDiagramElementDescription_DomainType(), this.ecorePackage.getEString(), "domainType", null, 0, 1, DiagramElementDescription.class, !IS_TRANSIENT, !IS_VOLATILE, //$NON-NLS-1$
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEAttribute(this.getDiagramElementDescription_SemanticCandidatesExpression(), this.ecorePackage.getEString(), "semanticCandidatesExpression", "aql:self.eContents()", 0, 1, //$NON-NLS-1$ //$NON-NLS-2$
                DiagramElementDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEAttribute(this.getDiagramElementDescription_LabelExpression(), this.ecorePackage.getEString(), "labelExpression", "aql:self.name", 0, 1, DiagramElementDescription.class, //$NON-NLS-1$ //$NON-NLS-2$
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEReference(this.getDiagramElementDescription_DeleteTool(), this.getDeleteTool(), null, "deleteTool", null, 0, 1, DiagramElementDescription.class, !IS_TRANSIENT, !IS_VOLATILE, //$NON-NLS-1$
                IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEReference(this.getDiagramElementDescription_LabelEditTool(), this.getLabelEditTool(), null, "labelEditTool", null, 0, 1, DiagramElementDescription.class, !IS_TRANSIENT, !IS_VOLATILE, //$NON-NLS-1$
                IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        this.initEClass(this.nodeDescriptionEClass, NodeDescription.class, "NodeDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        this.initEReference(this.getNodeDescription_ChildrenDescriptions(), this.getNodeDescription(), null, "childrenDescriptions", null, 0, -1, NodeDescription.class, !IS_TRANSIENT, !IS_VOLATILE, //$NON-NLS-1$
                IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEReference(this.getNodeDescription_Style(), this.getNodeStyle(), null, "style", null, 0, 1, NodeDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, //$NON-NLS-1$
                !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEReference(this.getNodeDescription_NodeTools(), this.getNodeTool(), null, "nodeTools", null, 0, -1, NodeDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, //$NON-NLS-1$
                !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEReference(this.getNodeDescription_ConditionalStyles(), this.getConditionalNodeStyle(), null, "conditionalStyles", null, 0, -1, NodeDescription.class, !IS_TRANSIENT, !IS_VOLATILE, //$NON-NLS-1$
                IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        this.initEClass(this.edgeDescriptionEClass, EdgeDescription.class, "EdgeDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        this.initEAttribute(this.getEdgeDescription_IsDomainBasedEdge(), this.ecorePackage.getEBoolean(), "isDomainBasedEdge", null, 0, 1, EdgeDescription.class, !IS_TRANSIENT, !IS_VOLATILE, //$NON-NLS-1$
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEReference(this.getEdgeDescription_SourceNodeDescription(), this.getNodeDescription(), null, "sourceNodeDescription", null, 1, 1, EdgeDescription.class, !IS_TRANSIENT, !IS_VOLATILE, //$NON-NLS-1$
                IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEReference(this.getEdgeDescription_TargetNodeDescription(), this.getNodeDescription(), null, "targetNodeDescription", null, 1, 1, EdgeDescription.class, !IS_TRANSIENT, !IS_VOLATILE, //$NON-NLS-1$
                IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEAttribute(this.getEdgeDescription_SourceNodesExpression(), this.ecorePackage.getEString(), "sourceNodesExpression", null, 0, 1, EdgeDescription.class, !IS_TRANSIENT, !IS_VOLATILE, //$NON-NLS-1$
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEAttribute(this.getEdgeDescription_TargetNodesExpression(), this.ecorePackage.getEString(), "targetNodesExpression", "aql:self.eCrossReferences()", 0, 1, EdgeDescription.class, //$NON-NLS-1$ //$NON-NLS-2$
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEReference(this.getEdgeDescription_Style(), this.getEdgeStyle(), null, "style", null, 0, 1, EdgeDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, //$NON-NLS-1$
                !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEReference(this.getEdgeDescription_EdgeTools(), this.getEdgeTool(), null, "edgeTools", null, 0, -1, EdgeDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, //$NON-NLS-1$
                !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEReference(this.getEdgeDescription_ConditionalStyles(), this.getConditionalEdgeStyle(), null, "conditionalStyles", null, 0, -1, EdgeDescription.class, !IS_TRANSIENT, !IS_VOLATILE, //$NON-NLS-1$
                IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        this.initEClass(this.styleEClass, Style.class, "Style", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        this.initEAttribute(this.getStyle_Color(), this.ecorePackage.getEString(), "color", "#E5F5F8", 0, 1, Style.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, //$NON-NLS-1$ //$NON-NLS-2$
                !IS_DERIVED, IS_ORDERED);
        this.initEAttribute(this.getStyle_BorderColor(), this.ecorePackage.getEString(), "borderColor", "#33B0C3", 1, 1, Style.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, //$NON-NLS-1$ //$NON-NLS-2$
                !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEAttribute(this.getStyle_FontSize(), this.ecorePackage.getEInt(), "fontSize", "14", 1, 1, Style.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, //$NON-NLS-1$ //$NON-NLS-2$
                !IS_DERIVED, IS_ORDERED);

        this.initEClass(this.nodeStyleEClass, NodeStyle.class, "NodeStyle", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        this.initEAttribute(this.getNodeStyle_ListMode(), this.ecorePackage.getEBoolean(), "listMode", null, 1, 1, NodeStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, //$NON-NLS-1$
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEAttribute(this.getNodeStyle_BorderRadius(), this.ecorePackage.getEInt(), "borderRadius", "3", 1, 1, NodeStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, //$NON-NLS-1$ //$NON-NLS-2$
                !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEAttribute(this.getNodeStyle_Shape(), this.ecorePackage.getEString(), "shape", null, 0, 1, NodeStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, //$NON-NLS-1$
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEAttribute(this.getNodeStyle_BorderSize(), this.ecorePackage.getEInt(), "borderSize", "1", 1, 1, NodeStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, //$NON-NLS-1$ //$NON-NLS-2$
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEAttribute(this.getNodeStyle_LabelColor(), this.ecorePackage.getEString(), "labelColor", "black", 0, 1, NodeStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, //$NON-NLS-1$ //$NON-NLS-2$
                !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEAttribute(this.getNodeStyle_Italic(), this.ecorePackage.getEBoolean(), "italic", "false", 1, 1, NodeStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, //$NON-NLS-1$ //$NON-NLS-2$
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEAttribute(this.getNodeStyle_Bold(), this.ecorePackage.getEBoolean(), "bold", "false", 1, 1, NodeStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, //$NON-NLS-1$ //$NON-NLS-2$
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEAttribute(this.getNodeStyle_Underline(), this.ecorePackage.getEBoolean(), "underline", "false", 1, 1, NodeStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, //$NON-NLS-1$ //$NON-NLS-2$
                !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEAttribute(this.getNodeStyle_StrikeThrough(), this.ecorePackage.getEBoolean(), "strikeThrough", "false", 1, 1, NodeStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, //$NON-NLS-1$ //$NON-NLS-2$
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        this.initEClass(this.edgeStyleEClass, EdgeStyle.class, "EdgeStyle", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        this.initEAttribute(this.getEdgeStyle_LineStyle(), this.getLineStyle(), "lineStyle", "Solid", 1, 1, EdgeStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, //$NON-NLS-1$ //$NON-NLS-2$
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEAttribute(this.getEdgeStyle_SourceArrowStyle(), this.getArrowStyle(), "sourceArrowStyle", "None", 1, 1, EdgeStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, //$NON-NLS-1$ //$NON-NLS-2$
                !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEAttribute(this.getEdgeStyle_TargetArrowStyle(), this.getArrowStyle(), "targetArrowStyle", "OutputArrow", 1, 1, EdgeStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, //$NON-NLS-1$ //$NON-NLS-2$
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEAttribute(this.getEdgeStyle_EdgeWidth(), this.ecorePackage.getEInt(), "edgeWidth", "1", 1, 1, EdgeStyle.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, //$NON-NLS-1$ //$NON-NLS-2$
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        this.initEClass(this.toolEClass, Tool.class, "Tool", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        this.initEAttribute(this.getTool_Name(), this.ecorePackage.getEString(), "name", "Tool", 1, 1, Tool.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, //$NON-NLS-1$ //$NON-NLS-2$
                !IS_DERIVED, IS_ORDERED);
        this.initEReference(this.getTool_Body(), this.getOperation(), null, "body", null, 0, -1, Tool.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, //$NON-NLS-1$
                !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        this.initEClass(this.labelEditToolEClass, LabelEditTool.class, "LabelEditTool", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        this.initEClass(this.deleteToolEClass, DeleteTool.class, "DeleteTool", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        this.initEClass(this.nodeToolEClass, NodeTool.class, "NodeTool", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        this.initEClass(this.edgeToolEClass, EdgeTool.class, "EdgeTool", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        this.initEClass(this.operationEClass, Operation.class, "Operation", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        this.initEReference(this.getOperation_Children(), this.getOperation(), null, "children", null, 0, -1, Operation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, //$NON-NLS-1$
                !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        this.initEClass(this.changeContextEClass, ChangeContext.class, "ChangeContext", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        this.initEAttribute(this.getChangeContext_Expression(), this.ecorePackage.getEString(), "expression", "aql:self", 1, 1, ChangeContext.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, //$NON-NLS-1$ //$NON-NLS-2$
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        this.initEClass(this.createInstanceEClass, CreateInstance.class, "CreateInstance", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        this.initEAttribute(this.getCreateInstance_TypeName(), this.ecorePackage.getEString(), "typeName", null, 1, 1, CreateInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, //$NON-NLS-1$
                !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEAttribute(this.getCreateInstance_ReferenceName(), this.ecorePackage.getEString(), "referenceName", null, 1, 1, CreateInstance.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, //$NON-NLS-1$
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEAttribute(this.getCreateInstance_VariableName(), this.ecorePackage.getEString(), "variableName", "newInstance", 1, 1, CreateInstance.class, !IS_TRANSIENT, !IS_VOLATILE, //$NON-NLS-1$ //$NON-NLS-2$
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        this.initEClass(this.setValueEClass, SetValue.class, "SetValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        this.initEAttribute(this.getSetValue_FeatureName(), this.ecorePackage.getEString(), "featureName", null, 1, 1, SetValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, //$NON-NLS-1$
                !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEAttribute(this.getSetValue_ValueExpression(), this.ecorePackage.getEString(), "valueExpression", null, 1, 1, SetValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, //$NON-NLS-1$
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        this.initEClass(this.unsetValueEClass, UnsetValue.class, "UnsetValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        this.initEAttribute(this.getUnsetValue_FeatureName(), this.ecorePackage.getEString(), "featureName", null, 1, 1, UnsetValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, //$NON-NLS-1$
                !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        this.initEAttribute(this.getUnsetValue_ElementExpression(), this.ecorePackage.getEString(), "elementExpression", null, 1, 1, UnsetValue.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, //$NON-NLS-1$
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        this.initEClass(this.deleteElementEClass, DeleteElement.class, "DeleteElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        this.initEClass(this.conditionalEClass, Conditional.class, "Conditional", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        this.initEAttribute(this.getConditional_Condition(), this.ecorePackage.getEString(), "condition", "aql:false", 1, 1, Conditional.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, //$NON-NLS-1$ //$NON-NLS-2$
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        this.initEClass(this.conditionalNodeStyleEClass, ConditionalNodeStyle.class, "ConditionalNodeStyle", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        this.initEClass(this.conditionalEdgeStyleEClass, ConditionalEdgeStyle.class, "ConditionalEdgeStyle", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        // Initialize enums and add enum literals
        this.initEEnum(this.arrowStyleEEnum, ArrowStyle.class, "ArrowStyle"); //$NON-NLS-1$
        this.addEEnumLiteral(this.arrowStyleEEnum, ArrowStyle.NONE);
        this.addEEnumLiteral(this.arrowStyleEEnum, ArrowStyle.OUTPUT_ARROW);
        this.addEEnumLiteral(this.arrowStyleEEnum, ArrowStyle.INPUT_ARROW);
        this.addEEnumLiteral(this.arrowStyleEEnum, ArrowStyle.OUTPUT_CLOSED_ARROW);
        this.addEEnumLiteral(this.arrowStyleEEnum, ArrowStyle.INPUT_CLOSED_ARROW);
        this.addEEnumLiteral(this.arrowStyleEEnum, ArrowStyle.OUTPUT_FILL_CLOSED_ARROW);
        this.addEEnumLiteral(this.arrowStyleEEnum, ArrowStyle.INPUT_FILL_CLOSED_ARROW);
        this.addEEnumLiteral(this.arrowStyleEEnum, ArrowStyle.DIAMOND);
        this.addEEnumLiteral(this.arrowStyleEEnum, ArrowStyle.FILL_DIAMOND);
        this.addEEnumLiteral(this.arrowStyleEEnum, ArrowStyle.INPUT_ARROW_WITH_DIAMOND);
        this.addEEnumLiteral(this.arrowStyleEEnum, ArrowStyle.INPUT_ARROW_WITH_FILL_DIAMOND);

        this.initEEnum(this.lineStyleEEnum, LineStyle.class, "LineStyle"); //$NON-NLS-1$
        this.addEEnumLiteral(this.lineStyleEEnum, LineStyle.SOLID);
        this.addEEnumLiteral(this.lineStyleEEnum, LineStyle.DASH);
        this.addEEnumLiteral(this.lineStyleEEnum, LineStyle.DOT);
        this.addEEnumLiteral(this.lineStyleEEnum, LineStyle.DASH_DOT);

        // Create resource
        this.createResource(eNS_URI);
    }

} // ViewPackageImpl
