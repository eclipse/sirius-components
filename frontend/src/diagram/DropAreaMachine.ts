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

import { assign, Machine } from 'xstate';

export interface DropAreaStateSchema {
  states: {
    toast: {
      states: {
        visible: {};
        hidden: {};
      };
    };
    drop: {
      states: {
        pristine: {};
        dropping: {};
        success: {};
      };
    };
  };
}
export type SchemaValue = {
  drop: 'pristine' | 'dropping' | 'success';
  toast: 'visible' | 'hidden';
};
export interface DropAreaContext {
  message: string;
}

export type ShowToastEvent = { type: 'SHOW_TOAST'; message: string };
export type HideToastEvent = { type: 'HIDE_TOAST' };
export type HandleDropEvent = { type: 'HANDLE_DROP' };
export type HandleResponseEvent = { type: 'HANDLE_RESPONSE'; data: any };
export type DropEvent = ShowToastEvent | HideToastEvent | HandleDropEvent | HandleResponseEvent;

export const dropAreaMachine = Machine<DropAreaContext, DropAreaStateSchema, DropEvent>(
  {
    id: 'DropArea',
    type: 'parallel',
    context: {
      message: null,
    },
    states: {
      toast: {
        initial: 'hidden',
        states: {
          hidden: {
            on: {
              SHOW_TOAST: {
                target: 'visible',
                actions: 'setMessage',
              },
            },
          },
          visible: {
            on: {
              HIDE_TOAST: {
                target: 'hidden',
                actions: 'clearMessage',
              },
            },
          },
        },
      },
      drop: {
        initial: 'pristine',
        states: {
          pristine: {
            on: {
              HANDLE_DROP: {
                target: 'dropping',
              },
            },
          },
          dropping: {
            on: {
              HANDLE_RESPONSE: {
                target: 'success',
              },
            },
          },
          success: {
            type: 'final',
          },
        },
      },
    },
  },
  {
    actions: {
      setMessage: assign((_, event) => {
        const { message } = event as ShowToastEvent;
        return { message };
      }),
      clearMessage: assign((_) => {
        return { message: null };
      }),
    },
  }
);
