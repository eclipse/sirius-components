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
package org.eclipse.sirius.web.emf.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.sirius.web.domain.Attribute;
import org.eclipse.sirius.web.domain.DataType;
import org.eclipse.sirius.web.domain.Domain;
import org.eclipse.sirius.web.domain.Entity;
import org.eclipse.sirius.web.domain.Feature;
import org.eclipse.sirius.web.domain.Relation;

/**
 * Converts a Domain into an equivalent Ecore EPackage.
 *
 * @author pcdavid
 */
public class DomainConverter {

    public Optional<EPackage> convert(Domain domain) {
        Optional<EPackage> result = Optional.empty();

        EPackage ePackage = EcoreFactory.eINSTANCE.createEPackage();
        ePackage.setName(domain.getName());
        ePackage.setNsPrefix(Optional.ofNullable(domain.getName()).orElse("").toLowerCase()); //$NON-NLS-1$
        ePackage.setNsURI(domain.getUri());

        Map<Entity, EClass> convertedTypes = new HashMap<>();
        // First pass to create the EClasses and EAttributes
        for (Entity entity : domain.getTypes()) {
            EClass eClass = this.convertEntity(entity);
            convertedTypes.put(entity, eClass);
            ePackage.getEClassifiers().add(eClass);
        }
        // Second pass to convert references and resolve super-types.
        for (Entity entity : domain.getTypes()) {
            EClass eClass = convertedTypes.get(entity);
            for (Relation relation : entity.getRelations()) {
                EReference eReference = this.convertRelation(relation, convertedTypes);
                eClass.getEStructuralFeatures().add(eReference);
            }
            for (Entity superType : entity.getSuperTypes()) {
                if (convertedTypes.containsKey(superType)) {
                    eClass.getESuperTypes().add(convertedTypes.get(superType));
                }
            }
        }

        // Only return valid and safe EPackages
        if (!ePackage.getNsURI().startsWith(DomainValidator.DOMAIN_URI_SCHEME)) {
            result = Optional.empty();
        }
        Diagnostic diagnostic = Diagnostician.INSTANCE.validate(ePackage);
        if (diagnostic.getSeverity() < Diagnostic.ERROR) {
            result = Optional.of(ePackage);
        }

        return result;
    }

    private EClass convertEntity(Entity entity) {
        EClass eClass = EcoreFactory.eINSTANCE.createEClass();
        eClass.setName(entity.getName());
        eClass.setAbstract(entity.isAbstract());
        entity.getAttributes().forEach(attribute -> eClass.getEStructuralFeatures().add(this.convertAttribute(attribute)));
        return eClass;
    }

    private EAttribute convertAttribute(Attribute attribute) {
        EAttribute eAttribute = EcoreFactory.eINSTANCE.createEAttribute();
        eAttribute.setName(attribute.getName());
        this.convertCardinality(attribute, eAttribute);
        eAttribute.setEType(this.convertDataType(attribute.getType()));
        return eAttribute;
    }

    private EDataType convertDataType(DataType dataType) {
        final EDataType result;
        switch (dataType) {
        case BOOLEAN:
            result = EcorePackage.Literals.EBOOLEAN;
            break;
        case NUMBER:
            result = EcorePackage.Literals.EINT;
            break;
        case STRING:
            result = EcorePackage.Literals.ESTRING;
            break;
        default:
            result = EcorePackage.Literals.ESTRING;
            break;
        }
        return result;
    }

    private EReference convertRelation(Relation relation, Map<Entity, EClass> convertedTypes) {
        EReference eReference = EcoreFactory.eINSTANCE.createEReference();
        eReference.setName(relation.getName());
        this.convertCardinality(relation, eReference);
        eReference.setContainment(relation.isContainment());
        eReference.setEType(convertedTypes.get(relation.getTargetType()));
        return eReference;
    }

    private void convertCardinality(Feature feature, EStructuralFeature eStructuralFeature) {
        if (feature.isOptional()) {
            eStructuralFeature.setLowerBound(0);
        } else {
            eStructuralFeature.setLowerBound(1);
        }
        if (feature.isMany()) {
            eStructuralFeature.setUpperBound(-1);
        } else {
            eStructuralFeature.setUpperBound(1);
        }
    }

}
