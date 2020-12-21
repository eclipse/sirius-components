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
import { assign, Machine } from 'xstate';

export interface EditTextfieldStateSchema {
  states: {
    editing: {};
    submitting: {};
  };
}
export type SchemaValue = {
  editTextfieldView: 'editing' | 'submitting';
};

export interface EditTextfieldContext {
  inputValue: string;
  edited: boolean;
}

export type HandleEditFiedlEvent = { type: 'CHANGE_VALUE'; newValue: string };
export type HandleSubmitEvent = { type: 'HANDLE_SUBMIT' };
export type HandleResponseEvent = { type: 'HANDLE_RESPONSE' };
export type EditTextfieldEvent = HandleEditFiedlEvent | HandleSubmitEvent | HandleResponseEvent;

export const createTextFieldPropertySectionMachine = (textValue) => {
  return Machine<EditTextfieldContext, EditTextfieldStateSchema, EditTextfieldEvent>(
    {
      initial: 'editing',
      context: {
        inputValue: textValue,
        edited: false,
      },
      states: {
        editing: {
          on: {
            HANDLE_SUBMIT: {
              target: 'submitting',
              actions: 'updateEdited',
            },
            CHANGE_VALUE: {
              target: 'editing',
              actions: 'updateTextValue',
            },
          },
        },
        submitting: {
          on: {
            HANDLE_RESPONSE: {
              target: 'editing',
            },
          },
        },
      },
    },
    {
      actions: {
        updateTextValue: assign((_, event) => {
          const { newValue } = event as HandleEditFiedlEvent;
          return { inputValue: newValue, edited: true };
        }),
        updateEdited: assign((_, event) => {
          return { edited: false };
        }),
      },
    }
  );
};
