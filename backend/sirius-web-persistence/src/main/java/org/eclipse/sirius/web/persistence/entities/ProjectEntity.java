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
 * Project entity used by the persistence layer.
 *
 * @author sbegaudeau
 */
@Entity
@Table(name = "Project")
public class ProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private AccountEntity owner;

    @OneToOne
    @JoinColumn(name = "currenteditingcontext_id")
    private EditingContextEntity currentEditingContext;

    @Enumerated(EnumType.STRING)
    @Type(type = "org.eclipse.sirius.web.persistence.util.VisibilityEnumType")
    private VisibilityEntity visibility = VisibilityEntity.PUBLIC;

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

    public AccountEntity getOwner() {
        return this.owner;
    }

    public void setOwner(AccountEntity owner) {
        this.owner = owner;
    }

    public EditingContextEntity getCurrentEditingContext() {
        return this.currentEditingContext;
    }

    public void setCurrentEditingContext(EditingContextEntity currentEditingContext) {
        this.currentEditingContext = currentEditingContext;
    }

    public VisibilityEntity getVisibility() {
        return this.visibility;
    }

    public void setVisibility(VisibilityEntity visibility) {
        this.visibility = visibility;
    }

    @Override
    public String toString() {
        String pattern = "{0} '{'id: {1}, name: {2}, owner: {3}, visibility: {4}, currentEditingContext: '{' id: {5} '}' '}'"; //$NON-NLS-1$
        return MessageFormat.format(pattern, this.getClass().getSimpleName(), this.id, this.name, this.owner, this.visibility, this.currentEditingContext.getId());
    }
}
