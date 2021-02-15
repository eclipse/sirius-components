/*******************************************************************************
 * Copyright (c) 2019, 2021 Obeo.
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
package org.eclipse.sirius.web.emf;

import org.eclipse.sirius.web.emf.architecture.CodingRulesTestCases;
import org.eclipse.sirius.web.emf.architecture.ConfigurationTestCases;
import org.eclipse.sirius.web.emf.architecture.ServiceTestCases;
import org.eclipse.sirius.web.emf.architecture.SpringCodingRulesTestCases;
import org.eclipse.sirius.web.emf.compatibility.DomainClassPredicateTestCases;
import org.eclipse.sirius.web.emf.compatibility.diagrams.EdgeMappingConverterTestCases;
import org.eclipse.sirius.web.emf.compatibility.diagrams.SemanticCandidatesProviderTestCases;
import org.eclipse.sirius.web.emf.compatibility.diagrams.ToolImageProviderTestCases;
import org.eclipse.sirius.web.emf.compatibility.operations.ChangeContextOperationHandlerTestCases;
import org.eclipse.sirius.web.emf.compatibility.operations.CreateInstanceOperationHandlerTestCases;
import org.eclipse.sirius.web.emf.compatibility.operations.ForOperationHandlerTestCases;
import org.eclipse.sirius.web.emf.compatibility.operations.IfOperationHandlerTestCases;
import org.eclipse.sirius.web.emf.compatibility.operations.LetOperationHandlerTestCases;
import org.eclipse.sirius.web.emf.compatibility.operations.MoveElementOperationHandlerTestCases;
import org.eclipse.sirius.web.emf.compatibility.operations.RemoveElementOperationHandlerTestCases;
import org.eclipse.sirius.web.emf.compatibility.operations.SetValueOperationHandlerTestCases;
import org.eclipse.sirius.web.emf.compatibility.operations.SwitchOperationHandlerTestCases;
import org.eclipse.sirius.web.emf.compatibility.operations.UnsetOperationHandlerTestCases;
import org.eclipse.sirius.web.emf.services.CreateDocumentEventHandlerTestCases;
import org.eclipse.sirius.web.emf.services.DeleteDocumentEventHandlerTestCases;
import org.eclipse.sirius.web.emf.services.EditingContextPersistenceServiceTestCases;
import org.eclipse.sirius.web.emf.services.EditingContextSearchServiceTestCases;
import org.eclipse.sirius.web.emf.services.IDManagerTestCases;
import org.eclipse.sirius.web.emf.services.LabelFeatureProviderRegistryTestCases;
import org.eclipse.sirius.web.emf.services.LabelFeatureProviderTestCases;
import org.eclipse.sirius.web.emf.services.ObjectServiceTestCases;
import org.eclipse.sirius.web.emf.services.RenameDocumentEventHandlerTestCases;
import org.eclipse.sirius.web.emf.services.UploadDocumentEventHandlerTestCases;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Test suite used to run all the unit tests of the forms.
 *
 * @author sbegaudeau
 */
@RunWith(Suite.class)
// @formatter:off
@SuiteClasses({
    CodingRulesTestCases.class,
    ConfigurationTestCases.class,
    CreateDocumentEventHandlerTestCases.class,
    DeleteDocumentEventHandlerTestCases.class,
    EditingContextSearchServiceTestCases.class,
    EditingContextPersistenceServiceTestCases.class,
    IDManagerTestCases.class,
    LabelFeatureProviderRegistryTestCases.class,
    LabelFeatureProviderTestCases.class,
    ObjectServiceTestCases.class,
    RenameDocumentEventHandlerTestCases.class,
    ServiceTestCases.class,
    SpringCodingRulesTestCases.class,
    UploadDocumentEventHandlerTestCases.class,

    EdgeMappingConverterTestCases.class,
    SemanticCandidatesProviderTestCases.class,
    ToolImageProviderTestCases.class,

    ChangeContextOperationHandlerTestCases.class,
    CreateInstanceOperationHandlerTestCases.class,
    ForOperationHandlerTestCases.class,
    IfOperationHandlerTestCases.class,
    LetOperationHandlerTestCases.class,
    MoveElementOperationHandlerTestCases.class,
    RemoveElementOperationHandlerTestCases.class,
    SetValueOperationHandlerTestCases.class,
    SwitchOperationHandlerTestCases.class,
    UnsetOperationHandlerTestCases.class,

    DomainClassPredicateTestCases.class,
 })
// @formatter:on
public final class AllSiriusWebEMFTests {
    private AllSiriusWebEMFTests() {
        // Prevent instantiation
    }
}
