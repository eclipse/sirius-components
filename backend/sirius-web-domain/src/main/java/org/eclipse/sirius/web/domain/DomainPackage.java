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
package org.eclipse.sirius.web.domain;

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
 * @see org.eclipse.sirius.web.domain.DomainFactory
 * @model kind="package"
 * @generated
 */
public interface DomainPackage extends EPackage {
    /**
     * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    String eNAME = "domain"; //$NON-NLS-1$

    /**
     * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    String eNS_URI = "http://www.eclipse.org/sirius-web/domain"; //$NON-NLS-1$

    /**
     * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    String eNS_PREFIX = "domain"; //$NON-NLS-1$

    /**
     * The singleton instance of the package. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    DomainPackage eINSTANCE = org.eclipse.sirius.web.domain.impl.DomainPackageImpl.init();

    /**
     * The meta object id for the '{@link org.eclipse.sirius.web.domain.impl.NamedElementImpl <em>Named Element</em>}'
     * class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.web.domain.impl.NamedElementImpl
     * @see org.eclipse.sirius.web.domain.impl.DomainPackageImpl#getNamedElement()
     * @generated
     */
    int NAMED_ELEMENT = 0;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NAMED_ELEMENT__NAME = 0;

    /**
     * The number of structural features of the '<em>Named Element</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NAMED_ELEMENT_FEATURE_COUNT = 1;

    /**
     * The number of operations of the '<em>Named Element</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int NAMED_ELEMENT_OPERATION_COUNT = 0;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.web.domain.impl.DomainImpl <em>Domain</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.web.domain.impl.DomainImpl
     * @see org.eclipse.sirius.web.domain.impl.DomainPackageImpl#getDomain()
     * @generated
     */
    int DOMAIN = 1;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DOMAIN__NAME = NAMED_ELEMENT__NAME;

    /**
     * The feature id for the '<em><b>Uri</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DOMAIN__URI = NAMED_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Types</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DOMAIN__TYPES = NAMED_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Domain</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DOMAIN_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The number of operations of the '<em>Domain</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int DOMAIN_OPERATION_COUNT = NAMED_ELEMENT_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.web.domain.impl.EntityImpl <em>Entity</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.web.domain.impl.EntityImpl
     * @see org.eclipse.sirius.web.domain.impl.DomainPackageImpl#getEntity()
     * @generated
     */
    int ENTITY = 2;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ENTITY__NAME = NAMED_ELEMENT__NAME;

    /**
     * The feature id for the '<em><b>Attributes</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ENTITY__ATTRIBUTES = NAMED_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Relations</b></em>' containment reference list. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ENTITY__RELATIONS = NAMED_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The feature id for the '<em><b>Super Types</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int ENTITY__SUPER_TYPES = NAMED_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The feature id for the '<em><b>Abstract</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ENTITY__ABSTRACT = NAMED_ELEMENT_FEATURE_COUNT + 3;

    /**
     * The number of structural features of the '<em>Entity</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ENTITY_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 4;

    /**
     * The number of operations of the '<em>Entity</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ENTITY_OPERATION_COUNT = NAMED_ELEMENT_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.web.domain.impl.FeatureImpl <em>Feature</em>}' class. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.web.domain.impl.FeatureImpl
     * @see org.eclipse.sirius.web.domain.impl.DomainPackageImpl#getFeature()
     * @generated
     */
    int FEATURE = 3;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int FEATURE__NAME = NAMED_ELEMENT__NAME;

    /**
     * The feature id for the '<em><b>Optional</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int FEATURE__OPTIONAL = NAMED_ELEMENT_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Many</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int FEATURE__MANY = NAMED_ELEMENT_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Feature</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int FEATURE_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 2;

    /**
     * The number of operations of the '<em>Feature</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int FEATURE_OPERATION_COUNT = NAMED_ELEMENT_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.web.domain.impl.AttributeImpl <em>Attribute</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.web.domain.impl.AttributeImpl
     * @see org.eclipse.sirius.web.domain.impl.DomainPackageImpl#getAttribute()
     * @generated
     */
    int ATTRIBUTE = 4;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ATTRIBUTE__NAME = FEATURE__NAME;

    /**
     * The feature id for the '<em><b>Optional</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ATTRIBUTE__OPTIONAL = FEATURE__OPTIONAL;

    /**
     * The feature id for the '<em><b>Many</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ATTRIBUTE__MANY = FEATURE__MANY;

    /**
     * The feature id for the '<em><b>Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ATTRIBUTE__TYPE = FEATURE_FEATURE_COUNT + 0;

    /**
     * The number of structural features of the '<em>Attribute</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     * @ordered
     */
    int ATTRIBUTE_FEATURE_COUNT = FEATURE_FEATURE_COUNT + 1;

    /**
     * The number of operations of the '<em>Attribute</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int ATTRIBUTE_OPERATION_COUNT = FEATURE_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.web.domain.impl.RelationImpl <em>Relation</em>}' class.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.web.domain.impl.RelationImpl
     * @see org.eclipse.sirius.web.domain.impl.DomainPackageImpl#getRelation()
     * @generated
     */
    int RELATION = 5;

    /**
     * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RELATION__NAME = FEATURE__NAME;

    /**
     * The feature id for the '<em><b>Optional</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RELATION__OPTIONAL = FEATURE__OPTIONAL;

    /**
     * The feature id for the '<em><b>Many</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RELATION__MANY = FEATURE__MANY;

    /**
     * The feature id for the '<em><b>Containment</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RELATION__CONTAINMENT = FEATURE_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Target Type</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RELATION__TARGET_TYPE = FEATURE_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '<em>Relation</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RELATION_FEATURE_COUNT = FEATURE_FEATURE_COUNT + 2;

    /**
     * The number of operations of the '<em>Relation</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int RELATION_OPERATION_COUNT = FEATURE_OPERATION_COUNT + 0;

    /**
     * The meta object id for the '{@link org.eclipse.sirius.web.domain.DataType <em>Data Type</em>}' enum. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.sirius.web.domain.DataType
     * @see org.eclipse.sirius.web.domain.impl.DomainPackageImpl#getDataType()
     * @generated
     */
    int DATA_TYPE = 6;

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.web.domain.NamedElement <em>Named Element</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Named Element</em>'.
     * @see org.eclipse.sirius.web.domain.NamedElement
     * @generated
     */
    EClass getNamedElement();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.web.domain.NamedElement#getName
     * <em>Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Name</em>'.
     * @see org.eclipse.sirius.web.domain.NamedElement#getName()
     * @see #getNamedElement()
     * @generated
     */
    EAttribute getNamedElement_Name();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.web.domain.Domain <em>Domain</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Domain</em>'.
     * @see org.eclipse.sirius.web.domain.Domain
     * @generated
     */
    EClass getDomain();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.web.domain.Domain#getUri <em>Uri</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Uri</em>'.
     * @see org.eclipse.sirius.web.domain.Domain#getUri()
     * @see #getDomain()
     * @generated
     */
    EAttribute getDomain_Uri();

    /**
     * Returns the meta object for the containment reference list '{@link org.eclipse.sirius.web.domain.Domain#getTypes
     * <em>Types</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Types</em>'.
     * @see org.eclipse.sirius.web.domain.Domain#getTypes()
     * @see #getDomain()
     * @generated
     */
    EReference getDomain_Types();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.web.domain.Entity <em>Entity</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Entity</em>'.
     * @see org.eclipse.sirius.web.domain.Entity
     * @generated
     */
    EClass getEntity();

    /**
     * Returns the meta object for the containment reference list
     * '{@link org.eclipse.sirius.web.domain.Entity#getAttributes <em>Attributes</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Attributes</em>'.
     * @see org.eclipse.sirius.web.domain.Entity#getAttributes()
     * @see #getEntity()
     * @generated
     */
    EReference getEntity_Attributes();

    /**
     * Returns the meta object for the containment reference list
     * '{@link org.eclipse.sirius.web.domain.Entity#getRelations <em>Relations</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for the containment reference list '<em>Relations</em>'.
     * @see org.eclipse.sirius.web.domain.Entity#getRelations()
     * @see #getEntity()
     * @generated
     */
    EReference getEntity_Relations();

    /**
     * Returns the meta object for the reference list '{@link org.eclipse.sirius.web.domain.Entity#getSuperTypes
     * <em>Super Types</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference list '<em>Super Types</em>'.
     * @see org.eclipse.sirius.web.domain.Entity#getSuperTypes()
     * @see #getEntity()
     * @generated
     */
    EReference getEntity_SuperTypes();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.web.domain.Entity#isAbstract
     * <em>Abstract</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Abstract</em>'.
     * @see org.eclipse.sirius.web.domain.Entity#isAbstract()
     * @see #getEntity()
     * @generated
     */
    EAttribute getEntity_Abstract();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.web.domain.Feature <em>Feature</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Feature</em>'.
     * @see org.eclipse.sirius.web.domain.Feature
     * @generated
     */
    EClass getFeature();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.web.domain.Feature#isOptional
     * <em>Optional</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Optional</em>'.
     * @see org.eclipse.sirius.web.domain.Feature#isOptional()
     * @see #getFeature()
     * @generated
     */
    EAttribute getFeature_Optional();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.web.domain.Feature#isMany <em>Many</em>}'.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Many</em>'.
     * @see org.eclipse.sirius.web.domain.Feature#isMany()
     * @see #getFeature()
     * @generated
     */
    EAttribute getFeature_Many();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.web.domain.Attribute <em>Attribute</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Attribute</em>'.
     * @see org.eclipse.sirius.web.domain.Attribute
     * @generated
     */
    EClass getAttribute();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.web.domain.Attribute#getType
     * <em>Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Type</em>'.
     * @see org.eclipse.sirius.web.domain.Attribute#getType()
     * @see #getAttribute()
     * @generated
     */
    EAttribute getAttribute_Type();

    /**
     * Returns the meta object for class '{@link org.eclipse.sirius.web.domain.Relation <em>Relation</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for class '<em>Relation</em>'.
     * @see org.eclipse.sirius.web.domain.Relation
     * @generated
     */
    EClass getRelation();

    /**
     * Returns the meta object for the attribute '{@link org.eclipse.sirius.web.domain.Relation#isContainment
     * <em>Containment</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the attribute '<em>Containment</em>'.
     * @see org.eclipse.sirius.web.domain.Relation#isContainment()
     * @see #getRelation()
     * @generated
     */
    EAttribute getRelation_Containment();

    /**
     * Returns the meta object for the reference '{@link org.eclipse.sirius.web.domain.Relation#getTargetType <em>Target
     * Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for the reference '<em>Target Type</em>'.
     * @see org.eclipse.sirius.web.domain.Relation#getTargetType()
     * @see #getRelation()
     * @generated
     */
    EReference getRelation_TargetType();

    /**
     * Returns the meta object for enum '{@link org.eclipse.sirius.web.domain.DataType <em>Data Type</em>}'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the meta object for enum '<em>Data Type</em>'.
     * @see org.eclipse.sirius.web.domain.DataType
     * @generated
     */
    EEnum getDataType();

    /**
     * Returns the factory that creates the instances of the model. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the factory that creates the instances of the model.
     * @generated
     */
    DomainFactory getDomainFactory();

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
         * The meta object literal for the '{@link org.eclipse.sirius.web.domain.impl.NamedElementImpl <em>Named
         * Element</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.web.domain.impl.NamedElementImpl
         * @see org.eclipse.sirius.web.domain.impl.DomainPackageImpl#getNamedElement()
         * @generated
         */
        EClass NAMED_ELEMENT = eINSTANCE.getNamedElement();

        /**
         * The meta object literal for the '<em><b>Name</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute NAMED_ELEMENT__NAME = eINSTANCE.getNamedElement_Name();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.web.domain.impl.DomainImpl <em>Domain</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.web.domain.impl.DomainImpl
         * @see org.eclipse.sirius.web.domain.impl.DomainPackageImpl#getDomain()
         * @generated
         */
        EClass DOMAIN = eINSTANCE.getDomain();

        /**
         * The meta object literal for the '<em><b>Uri</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute DOMAIN__URI = eINSTANCE.getDomain_Uri();

        /**
         * The meta object literal for the '<em><b>Types</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference DOMAIN__TYPES = eINSTANCE.getDomain_Types();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.web.domain.impl.EntityImpl <em>Entity</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.web.domain.impl.EntityImpl
         * @see org.eclipse.sirius.web.domain.impl.DomainPackageImpl#getEntity()
         * @generated
         */
        EClass ENTITY = eINSTANCE.getEntity();

        /**
         * The meta object literal for the '<em><b>Attributes</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference ENTITY__ATTRIBUTES = eINSTANCE.getEntity_Attributes();

        /**
         * The meta object literal for the '<em><b>Relations</b></em>' containment reference list feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EReference ENTITY__RELATIONS = eINSTANCE.getEntity_Relations();

        /**
         * The meta object literal for the '<em><b>Super Types</b></em>' reference list feature. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @generated
         */
        EReference ENTITY__SUPER_TYPES = eINSTANCE.getEntity_SuperTypes();

        /**
         * The meta object literal for the '<em><b>Abstract</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute ENTITY__ABSTRACT = eINSTANCE.getEntity_Abstract();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.web.domain.impl.FeatureImpl <em>Feature</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.web.domain.impl.FeatureImpl
         * @see org.eclipse.sirius.web.domain.impl.DomainPackageImpl#getFeature()
         * @generated
         */
        EClass FEATURE = eINSTANCE.getFeature();

        /**
         * The meta object literal for the '<em><b>Optional</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute FEATURE__OPTIONAL = eINSTANCE.getFeature_Optional();

        /**
         * The meta object literal for the '<em><b>Many</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute FEATURE__MANY = eINSTANCE.getFeature_Many();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.web.domain.impl.AttributeImpl <em>Attribute</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.web.domain.impl.AttributeImpl
         * @see org.eclipse.sirius.web.domain.impl.DomainPackageImpl#getAttribute()
         * @generated
         */
        EClass ATTRIBUTE = eINSTANCE.getAttribute();

        /**
         * The meta object literal for the '<em><b>Type</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute ATTRIBUTE__TYPE = eINSTANCE.getAttribute_Type();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.web.domain.impl.RelationImpl <em>Relation</em>}'
         * class. <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.web.domain.impl.RelationImpl
         * @see org.eclipse.sirius.web.domain.impl.DomainPackageImpl#getRelation()
         * @generated
         */
        EClass RELATION = eINSTANCE.getRelation();

        /**
         * The meta object literal for the '<em><b>Containment</b></em>' attribute feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EAttribute RELATION__CONTAINMENT = eINSTANCE.getRelation_Containment();

        /**
         * The meta object literal for the '<em><b>Target Type</b></em>' reference feature. <!-- begin-user-doc --> <!--
         * end-user-doc -->
         *
         * @generated
         */
        EReference RELATION__TARGET_TYPE = eINSTANCE.getRelation_TargetType();

        /**
         * The meta object literal for the '{@link org.eclipse.sirius.web.domain.DataType <em>Data Type</em>}' enum.
         * <!-- begin-user-doc --> <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.web.domain.DataType
         * @see org.eclipse.sirius.web.domain.impl.DomainPackageImpl#getDataType()
         * @generated
         */
        EEnum DATA_TYPE = eINSTANCE.getDataType();

    }

} // DomainPackage
