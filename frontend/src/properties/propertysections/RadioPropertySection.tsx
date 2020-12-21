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
import FormControl from '@material-ui/core/FormControl';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import FormLabel from '@material-ui/core/FormLabel';
import Radio from '@material-ui/core/Radio';
import RadioGroup from '@material-ui/core/RadioGroup';
import { Permission } from 'project/Permission';
import PropTypes from 'prop-types';
import React from 'react';
import { editRadioMutation } from './mutations';

const propTypes = {
  projectId: PropTypes.string.isRequired,
  formId: PropTypes.string.isRequired,
  widgetId: PropTypes.string.isRequired,
  label: PropTypes.string.isRequired,
  options: PropTypes.array.isRequired,
};

export const RadioPropertySection = ({ projectId, formId, widgetId, label, options }) => {
  const [editRadio] = useMutation(editRadioMutation);

  const onChange = async (event) => {
    const newValue = event.target.value;
    const variables = {
      input: {
        projectId,
        representationId: formId,
        radioId: widgetId,
        newValue,
      },
    };
    await editRadio({ variables });
  };

  const opts = options.map((option) => {
    return (
      <FormControlLabel
        key={option.id}
        value={option.id}
        control={<Radio checked={option.selected} color="primary" />}
        label={option.label}
        data-testid={option.id}
      />
    );
  });
  return (
    <>
      <FormControl component="fieldset">
        <FormLabel component="legend">{label}</FormLabel>
        <Permission requiredAccessLevel="EDIT">
          <RadioGroup row aria-label={label} name={label} onChange={onChange} data-testid={label}>
            {opts}
          </RadioGroup>
        </Permission>
      </FormControl>
    </>
  );
};
RadioPropertySection.propTypes = propTypes;
