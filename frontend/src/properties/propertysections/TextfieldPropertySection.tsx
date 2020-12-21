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
import FormLabel from '@material-ui/core/FormLabel';
import Input from '@material-ui/core/Input';
import { useMachine } from '@xstate/react';
import { Permission } from 'project/Permission';
import PropTypes from 'prop-types';
import { PropertySectionSubscribers } from 'properties/propertysections/PropertySectionSubscribers';
import React, { useEffect } from 'react';
import { editTextfieldMutation, updateWidgetFocusMutation } from './mutations';
import { createTextFieldPropertySectionMachine } from './TextfieldPropertySectionMachine';

const propTypes = {
  projectId: PropTypes.string.isRequired,
  formId: PropTypes.string.isRequired,
  widgetId: PropTypes.string.isRequired,
  label: PropTypes.string.isRequired,
  textValue: PropTypes.string.isRequired,
};

/**
 * Defines the content of a Textfield property section.
 * The content is submitted when the focus is lost and when pressing the "Enter" key.
 */
export const TextfieldPropertySection = ({ projectId, formId, widgetId, label, textValue, subscribers }) => {
  const [{ value, context }, dispatch] = useMachine(createTextFieldPropertySectionMachine(textValue));
  const { inputValue, edited } = context;

  const onChange = (event) => {
    const newValue = event.target.value;

    dispatch({ type: 'CHANGE_VALUE', newValue });
  };

  const [editTextfield, editTextFieldResult] = useMutation(editTextfieldMutation);
  const sendEditedValue = async () => {
    if (edited) {
      const variables = {
        input: {
          projectId,
          representationId: formId,
          textfieldId: widgetId,
          newValue: inputValue,
        },
      };
      await editTextfield({ variables });
    }
  };

  const [updateWidgetFocus] = useMutation(updateWidgetFocusMutation);
  const sendUpdateWidgetFocus = async (selected) => {
    const variables = {
      input: {
        projectId,
        representationId: formId,
        widgetId,
        selected,
      },
    };
    await updateWidgetFocus({ variables });
  };

  useEffect(() => {
    console.log(editTextFieldResult);
    if (editTextFieldResult.loading) {
      console.log('HANDLE_SUBMIT');
      dispatch({ type: 'HANDLE_SUBMIT' });
    } else if (editTextFieldResult.called && editTextFieldResult.data?.editTextfield) {
      console.log('HANDLE_RESPONSE');
      editTextFieldResult.called = false;
      dispatch({ type: 'HANDLE_RESPONSE' });
    }
  }, [editTextFieldResult, dispatch]);

  const onFocus = async () => {
    await sendUpdateWidgetFocus(true);
  };
  const onBlur = async () => {
    await sendUpdateWidgetFocus(false);
    await sendEditedValue();
  };

  const onKeyPress = async (event) => {
    if ('Enter' === event.key) {
      await sendEditedValue();
    }
  };
  const fieldDisabled = value == 'submitting';
  console.log(fieldDisabled);
  return (
    <>
      <FormControl component="fieldset">
        <PropertySectionSubscribers subscribers={subscribers}>
          <FormLabel component="legend">{label}</FormLabel>
        </PropertySectionSubscribers>
        <Permission requiredAccessLevel="EDIT">
          <Input
            id={label}
            name={label}
            placeholder={label}
            value={inputValue}
            onChange={onChange}
            disabled={fieldDisabled}
            onBlur={onBlur}
            onFocus={onFocus}
            onKeyPress={onKeyPress}
            data-testid={label}
          />
        </Permission>
      </FormControl>
    </>
  );
};
TextfieldPropertySection.propTypes = propTypes;
