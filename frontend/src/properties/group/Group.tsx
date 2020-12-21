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
import { Text } from 'core/text/Text';
import { CheckboxPropertySection } from 'properties/propertysections/CheckboxPropertySection';
import { ListPropertySection } from 'properties/propertysections/ListPropertySection';
import { RadioPropertySection } from 'properties/propertysections/RadioPropertySection';
import { SelectPropertySection } from 'properties/propertysections/SelectPropertySection';
import { TextareaPropertySection } from 'properties/propertysections/TextareaPropertySection';
import { TextfieldPropertySection } from 'properties/propertysections/TextfieldPropertySection';
import React from 'react';
import styles from './Group.module.css';

export const Group = ({ projectId, formId, group, widgetSubscriptions }) => {
  let propertySections = group.widgets.map((widget) =>
    widgetToPropertySection(projectId, formId, widget, widgetSubscriptions)
  );

  return (
    <div className={styles.group}>
      <Text className={styles.title}>{group.label}</Text>
      <div className={styles.sections}>{propertySections}</div>
    </div>
  );
};

const widgetToPropertySection = (projectId, formId, widget, widgetSubscriptions) => {
  let subscribers = [];
  widgetSubscriptions
    .filter((subscription) => subscription.widgetId === widget.id)
    .forEach((subscription) => subscribers.push(...subscription.subscribers));

  let propertySection = null;
  switch (widget.__typename) {
    case 'Textfield':
      propertySection = (
        <TextfieldPropertySection
          projectId={projectId}
          formId={formId}
          label={widget.label}
          textValue={widget.stringValue}
          widgetId={widget.id}
          subscribers={subscribers}
        />
      );
      break;
    case 'Textarea':
      propertySection = (
        <TextareaPropertySection
          label={widget.label}
          value={widget.stringValue}
          projectId={projectId}
          formId={formId}
          widgetId={widget.id}
          subscribers={subscribers}
          readOnly={false}
        />
      );
      break;
    case 'Checkbox':
      propertySection = (
        <CheckboxPropertySection
          projectId={projectId}
          formId={formId}
          label={widget.label}
          value={widget.booleanValue}
          widgetId={widget.id}
        />
      );
      break;
    case 'Select':
      propertySection = (
        <SelectPropertySection
          projectId={projectId}
          formId={formId}
          widgetId={widget.id}
          label={widget.label}
          options={widget.options}
          value={widget.value}
        />
      );
      break;
    case 'Radio':
      propertySection = (
        <RadioPropertySection
          projectId={projectId}
          formId={formId}
          widgetId={widget.id}
          label={widget.label}
          options={widget.options}
        />
      );
      break;
    case 'List':
      propertySection = <ListPropertySection label={widget.label} items={widget.items} key={widget.id} />;
      break;
    default:
      console.error(`Unsupported widget type ${widget.__typename}`);
  }
  return (
    <div key={widget.id} className={styles.propertySection}>
      {propertySection}
    </div>
  );
};
