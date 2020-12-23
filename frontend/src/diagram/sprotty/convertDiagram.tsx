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
import {
  createFeatureSet,
  connectableFeature,
  deletableFeature,
  selectFeature,
  boundsFeature,
  layoutContainerFeature,
  fadeFeature,
  hoverFeedbackFeature,
  popupFeature,
} from 'sprotty';

/**
 * Convert the given diagram object to a Sprotty diagram.
 *
 * SiriusWeb diagram and Sprotty diagram does not match exactly the same API.
 * This converter will ensure the creation of a proper Sprotty diagram from a given Sirius Web diagram..
 *
 * @param diagram the diagram object to convert
 * @return a Sprotty diagram object
 */
export const convertDiagram = (diagram) => {
  const { id, kind, targetObjectId, label, position, size } = diagram;
  const nodes = diagram.nodes.map((node) => convertNode(node));
  const edges = diagram.edges.map((edge) => convertEdge(edge));

  return { id, kind, type: 'graph', targetObjectId, label, position, size, children: [...nodes, ...edges] };
};

const convertNode = (node) => {
  const {
    id,
    type,
    targetObjectId,
    targetObjectKind,
    targetObjectLabel,
    descriptionId,
    label,
    style,
    size,
    position,
  } = node;

  let borderNodes = [];
  if (node.borderNodes) {
    borderNodes = node.borderNodes.map((borderNode) => convertNode(borderNode));
  }
  let childNodes = [];
  if (node.childNodes) {
    childNodes = node.childNodes.map((childNode) => convertNode(childNode));
  }

  return {
    id,
    type,
    targetObjectId,
    targetObjectKind,
    targetObjectLabel,
    descriptionId,
    style,
    size,
    position,
    features: createFeatureSet([
      connectableFeature,
      deletableFeature,
      selectFeature,
      boundsFeature,
      layoutContainerFeature,
      fadeFeature,
      hoverFeedbackFeature,
      popupFeature,
    ]),
    editableLabel: label,
    children: [label, ...borderNodes, ...childNodes],
  };
};

const convertEdge = (edge) => {
  const {
    id,
    type,
    targetObjectId,
    targetObjectKind,
    targetObjectLabel,
    descriptionId,
    beginLabel,
    centerLabel,
    endLabel,
    sourceId,
    targetId,
    style,
    routingPoints,
  } = edge;

  let children = [];
  if (beginLabel) {
    children.push(beginLabel);
  }
  if (centerLabel) {
    children.push(centerLabel);
  }
  if (endLabel) {
    children.push(endLabel);
  }

  return {
    id,
    type,
    targetObjectId,
    targetObjectKind,
    targetObjectLabel,
    descriptionId,
    sourceId,
    targetId,
    style,
    routingPoints,
    features: createFeatureSet([deletableFeature, selectFeature, fadeFeature, hoverFeedbackFeature]),
    editableLabel: centerLabel,
    children: children,
  };
};
