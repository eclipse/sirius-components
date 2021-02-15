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
package org.eclipse.sirius.web.persistence.entities;

import java.text.MessageFormat;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

/**
 * Modeler entity used by the persistence layer.
 *
 * @author pcdavid
 */
@Entity
@Table(name = "Modeler")
public class ModelerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private ProjectEntity project;

    @OneToOne
    @JoinColumn(name = "editingcontext_id")
    private EditingContextEntity editingContext;

    @Enumerated(EnumType.STRING)
    @Type(type = "org.eclipse.sirius.web.persistence.util.VisibilityEnumType")
    private PublicationStatusEntity publicationStatus;

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProjectEntity getProject() {
        return this.project;
    }

    public void setProject(ProjectEntity project) {
        this.project = project;
    }

    public EditingContextEntity getEditingContext() {
        return this.editingContext;
    }

    public void setEditingContext(EditingContextEntity editingContext) {
        this.editingContext = editingContext;
    }

    public PublicationStatusEntity getPublicationStatus() {
        return this.publicationStatus;
    }

    public void setPublicationStatus(PublicationStatusEntity publicationStatus) {
        this.publicationStatus = publicationStatus;
    }

    @Override
    public String toString() {
        String pattern = "{0} '{'id: {1}, name: {2}, owner: {3}, project: '{' id: {4}, name: {5} '}', editingContext: '{' id: {6} '}', status: {7}'}'"; //$NON-NLS-1$
        // @formatter:off
        return MessageFormat.format(pattern, this.getClass().getSimpleName(), this.id, this.name,
                                             this.project.getId(), this.project.getName(),
                                             this.editingContext.getId(),
                                             this.publicationStatus);
        // @formatter:on
    }
}
