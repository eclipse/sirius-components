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
package org.eclipse.sirius.web.validation.render;

import org.eclipse.sirius.web.components.IComponentPropsValidator;
import org.eclipse.sirius.web.components.IProps;
import org.eclipse.sirius.web.validation.components.DiagnosticComponent;
import org.eclipse.sirius.web.validation.components.DiagnosticComponentProps;
import org.eclipse.sirius.web.validation.components.ValidationComponent;
import org.eclipse.sirius.web.validation.components.ValidationComponentProps;

/**
 * Used to validate the properties of a component.
 *
 * @author gcoutable
 */
public class ValidationComponentPropsValidator implements IComponentPropsValidator {

    @Override
    public boolean validateComponentProps(Class<?> componentType, IProps props) {
        boolean checkValidProps = false;

        if (ValidationComponent.class.equals(componentType)) {
            checkValidProps = props instanceof ValidationComponentProps;
        } else if (DiagnosticComponent.class.equals(componentType)) {
            checkValidProps = props instanceof DiagnosticComponentProps;
        }

        return checkValidProps;
    }

}
