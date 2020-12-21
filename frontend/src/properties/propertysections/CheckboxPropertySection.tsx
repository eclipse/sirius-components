/*******************************************************************************
 * Copyright (c) 2019, 2020 Obeo.
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
import { useMutation } from '@apollo/client';
import Checkbox from '@material-ui/core/Checkbox';
import FormControl from '@material-ui/core/FormControl';
import FormGroup from '@material-ui/core/FormGroup';
import FormLabel from '@material-ui/core/FormLabel';
import { Permission } from 'project/Permission';
import PropTypes from 'prop-types';
import React from 'react';
import { editCheckboxMutation } from './mutations';

const propTypes = {
  projectId: PropTypes.string.isRequired,
  formId: PropTypes.string.isRequired,
  widgetId: PropTypes.string.isRequired,
  label: PropTypes.string.isRequired,
  value: PropTypes.bool.isRequired,
};

export const CheckboxPropertySection = ({ projectId, formId, widgetId, label, value }) => {
  const [editCheckbox] = useMutation(editCheckboxMutation);
  const onChange = async (event) => {
    const newValue = event.target.checked;
    const variables = {
      input: {
        projectId,
        representationId: formId,
        checkboxId: widgetId,
        newValue,
      },
    };
    await editCheckbox({ variables });
  };

  return (
    <>
      <FormControl component="fieldset">
        <FormLabel component="legend">{label}</FormLabel>
        <Permission requiredAccessLevel="EDIT">
          <FormGroup aria-label={label} row>
            <Checkbox checked={value} onChange={onChange} name={label} color="primary" data-testid={label} />
          </FormGroup>
        </Permission>
      </FormControl>
    </>
  );
};
CheckboxPropertySection.propTypes = propTypes;
