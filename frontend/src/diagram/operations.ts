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
import gql from 'graphql-tag';
export const diagramEventSubscription = gql`
  subscription diagramEvent($input: DiagramEventInput!) {
    diagramEvent(input: $input) {
      __typename
      ... on PreDestroyPayload {
        id
      }
      ... on SubscribersUpdatedEventPayload {
        subscribers {
          username
        }
      }
      ... on DiagramRefreshedEventPayload {
        diagram {
          id
          kind
          targetObjectId
          label
          descriptionId
          position {
            x
            y
          }
          size {
            width
            height
          }
          nodes {
            ...nodeFields
            borderNodes {
              ...nodeFields
              borderNodes {
                ...nodeFields
              }
              childNodes {
                ...nodeFields
                borderNodes {
                  ...nodeFields
                }
                childNodes {
                  ...nodeFields
                  borderNodes {
                    ...nodeFields
                  }
                }
              }
            }
            childNodes {
              ...nodeFields
              borderNodes {
                ...nodeFields
              }
              childNodes {
                ...nodeFields
                borderNodes {
                  ...nodeFields
                }
                childNodes {
                  ...nodeFields
                  borderNodes {
                    ...nodeFields
                  }
                  childNodes {
                    ...nodeFields
                    borderNodes {
                      ...nodeFields
                    }
                    childNodes {
                      ...nodeFields
                    }
                  }
                }
              }
            }
          }
          edges {
            ...edgeFields
          }
        }
      }
    }
  }

  fragment nodeFields on Node {
    id
    type
    targetObjectId
    targetObjectKind
    targetObjectLabel
    descriptionId
    label {
      ...labelFields
    }
    style {
      ... on RectangularNodeStyle {
        color
        borderColor
        borderStyle
        borderSize
      }
      ... on ImageNodeStyle {
        imageURL
      }
    }
    position {
      x
      y
    }
    size {
      width
      height
    }
  }

  fragment edgeFields on Edge {
    id
    type
    targetObjectId
    targetObjectKind
    targetObjectLabel
    descriptionId
    sourceId
    targetId
    beginLabel {
      ...labelFields
    }
    centerLabel {
      ...labelFields
    }
    endLabel {
      ...labelFields
    }
    style {
      size
      lineStyle
      sourceArrow
      targetArrow
      color
    }
    routingPoints {
      x
      y
    }
  }

  fragment labelFields on Label {
    id
    type
    text
    style {
      color
      fontSize
      bold
      italic
      underline
      strikeThrough
      iconURL
    }
    position {
      x
      y
    }
    size {
      width
      height
    }
    alignment {
      x
      y
    }
  }
`;

export const deleteFromDiagramMutation = gql`
  mutation deleteFromDiagram($input: DeleteFromDiagramInput!) {
    deleteFromDiagram(input: $input) {
      __typename
      ... on DeleteFromDiagramSuccessPayload {
        diagram {
          id
        }
      }
      ... on ErrorPayload {
        message
      }
    }
  }
`;

export const editLabelMutation = gql`
  mutation editLabel($input: EditLabelInput!) {
    editLabel(input: $input) {
      __typename
      ... on EditLabelSuccessPayload {
        diagram {
          id
        }
      }
      ... on ErrorPayload {
        message
      }
    }
  }
`;

export const invokeNodeToolOnDiagramMutation = gql`
  mutation invokeNodeToolOnDiagram($input: InvokeNodeToolOnDiagramInput!) {
    invokeNodeToolOnDiagram(input: $input) {
      __typename
      ... on InvokeNodeToolOnDiagramSuccessPayload {
        diagram {
          id
        }
      }
      ... on ErrorPayload {
        message
      }
    }
  }
`;

export const invokeEdgeToolOnDiagramMutation = gql`
  mutation invokeEdgeToolOnDiagram($input: InvokeEdgeToolOnDiagramInput!) {
    invokeEdgeToolOnDiagram(input: $input) {
      __typename
      ... on InvokeEdgeToolOnDiagramSuccessPayload {
        diagram {
          id
        }
      }
      ... on ErrorPayload {
        message
      }
    }
  }
`;

export const invokeDropToolOnDiagramMutation = gql`
  mutation invokeDropToolOnDiagram($input: InvokeDropToolOnDiagramInput!) {
    invokeDropToolOnDiagram(input: $input) {
      __typename
      ... on InvokeDropToolOnDiagramSuccessPayload {
        diagram {
          id
        }
      }
      ... on ErrorPayload {
        message
      }
    }
  }
`;

export const getToolSectionsQuery = gql`
  fragment edgeCandidateField on EdgeCandidate {
    sources {
      id
    }
    targets {
      id
    }
  }

  query getToolSections($diagramId: ID!) {
    viewer {
      toolSections(diagramId: $diagramId) {
        id
        label
        imageURL
        tools {
          __typename
          id
          label
          imageURL
          ... on CreateNodeTool {
            targetDescriptions {
              id
            }
            appliesToDiagramRoot
          }
          ... on CreateEdgeTool {
            edgeCandidates {
              ...edgeCandidateField
            }
          }
          ... on DropTool {
            targetDescriptions {
              id
            }
            appliesToDiagramRoot
          }
        }
      }
    }
  }
`;
