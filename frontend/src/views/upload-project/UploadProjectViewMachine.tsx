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

export interface UploadProjectViewStateSchema {
  states: {
    toast: {
      states: {
        hidden: {};
        visible: {};
      };
    };
    uploadProjectView: {
      states: {
        idle: {};
        unauthorized: {};
        fileSelected: {};
        uploading: {};
        success: {};
      };
    };
  };
}
export type SchemaValue = {
  uploadProjectView: 'idle' | 'unauthorized' | 'fileSelected' | 'success';
  toast: 'visible' | 'hidden';
};

export interface UploadProjectViewContext {
  file: any;
  message: string;
  newProjectId: string;
}

export type ShowToastEvent = { type: 'SHOW_TOAST'; message: string };
export type HideToastEvent = { type: 'HIDE_TOAST' };
export type HandleSelectedFileEvent = { type: 'HANDLE_SELECTED_FILE'; file: string };
export type HandleUploadEvent = { type: 'HANDLE_UPLOAD' };
export type HandleResponseEvent = { type: 'HANDLE_RESPONSE'; response: any };
export type UploadProjectEvent =
  | ShowToastEvent
  | HideToastEvent
  | HandleSelectedFileEvent
  | HandleUploadEvent
  | HandleResponseEvent;

export const uploadProjectMachine = Machine<UploadProjectViewContext, UploadProjectViewStateSchema, UploadProjectEvent>(
  {
    id: 'uploadProjectView',
    type: 'parallel',
    context: {
      file: null,
      message: null,
      newProjectId: undefined,
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
      uploadProjectView: {
        initial: 'idle',
        states: {
          idle: {
            on: {
              HANDLE_SELECTED_FILE: {
                target: 'fileSelected',
                actions: 'setFile',
              },
            },
          },
          unauthorized: {},
          fileSelected: {
            on: {
              HANDLE_UPLOAD: {
                target: 'uploading',
              },
              HANDLE_SELECTED_FILE: {
                target: 'fileSelected',
                actions: 'setFile',
              },
            },
          },
          uploading: {
            on: {
              HANDLE_RESPONSE: [
                {
                  cond: 'isSuccess',
                  target: 'success',
                  actions: 'setNewProjectId',
                },
                {
                  target: 'fileSelected',
                },
              ],
              HANDLE_SELECTED_FILE: {
                target: 'fileSelected',
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
    guards: {
      isSuccess: (_, event) => {
        const { response } = event as HandleResponseEvent;
        return response?.data?.uploadProject?.project?.id;
      },
    },
    actions: {
      setFile: assign((_, event) => {
        const { file } = event as HandleSelectedFileEvent;
        return { file };
      }),
      setNewProjectId: assign((_, event) => {
        const { response } = event as HandleResponseEvent;
        return { newProjectId: response.data.uploadProject.project.id };
      }),
      setMessage: assign((_, event) => {
        const { message } = event as ShowToastEvent;
        return { message };
      }),
      clearMessage: assign((_, event) => {
        return { message: null };
      }),
    },
  }
);
