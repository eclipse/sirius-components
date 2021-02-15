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
package org.eclipse.sirius.web.persistence.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.UUID;

import org.eclipse.sirius.web.persistence.entities.AccountEntity;
import org.eclipse.sirius.web.persistence.entities.DocumentEntity;
import org.eclipse.sirius.web.persistence.entities.EditingContextEntity;
import org.eclipse.sirius.web.persistence.entities.ProjectEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests of the document repository.
 *
 * @author sbegaudeau
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = PersistenceTestConfiguration.class)
public class DocumentRepositoryIntegrationTestCases {
    private static final String OWNER_NAME = "Jyn Erso"; //$NON-NLS-1$

    private static final String ROLE_USER = "user"; //$NON-NLS-1$

    private static final String PROJECT_NAME = "Cluster Prism"; //$NON-NLS-1$

    private static final String FIRST_DOCUMENT_NAME = "Concordia"; //$NON-NLS-1$

    private static final String SECOND_DOCUMENT_NAME = "Mandalore"; //$NON-NLS-1$

    private static final String THIRD_DOCUMENT_NAME = "Kalevala"; //$NON-NLS-1$

    // @formatter:off
    private static final String DOCUMENT_CONTENT = "{" + System.lineSeparator() //$NON-NLS-1$
    + "    \"json\": {" + System.lineSeparator() //$NON-NLS-1$
    + "      \"version\": \"1.0\"," + System.lineSeparator() //$NON-NLS-1$
    + "    \"encoding\": \"utf-8\"" + System.lineSeparator() //$NON-NLS-1$
    + "  }," + System.lineSeparator() //$NON-NLS-1$
    + "  \"content\": []" + System.lineSeparator() //$NON-NLS-1$
    + "}" + System.lineSeparator(); //$NON-NLS-1$
    // @formatter:on

    @Autowired
    private IAccountRepository accountRepository;

    @Autowired
    private IProjectRepository projectRepository;

    @Autowired
    private IEditingContextRepository editingContextRepository;

    @Autowired
    private IDocumentRepository documentRepository;

    @Test
    @Transactional
    public void testInsertAndRetrieveADocument() {
        ProjectEntity project = this.createAndSaveProjectEntity();

        DocumentEntity document = new DocumentEntity();
        document.setName(FIRST_DOCUMENT_NAME);
        document.setEditingContext(project.getCurrentEditingContext());
        document.setContent(DOCUMENT_CONTENT);

        var savedDocument = this.documentRepository.save(document);
        assertThat(savedDocument.getId()).isNotNull();
        var optionalDocumentFound = this.documentRepository.findById(savedDocument.getId());
        assertThat(optionalDocumentFound.isPresent()).isTrue();

        optionalDocumentFound.ifPresent(documentFound -> {
            assertThat(documentFound.getId()).isNotNull();
            assertThat(documentFound.getName()).isEqualTo(FIRST_DOCUMENT_NAME);
            assertThat(documentFound.getContent()).isEqualTo(DOCUMENT_CONTENT);
            assertThat(documentFound.getEditingContext().getId()).isEqualTo(project.getCurrentEditingContext().getId());
        });
    }

    @Test
    @Transactional
    public void testFindAllByProjectId() {
        ProjectEntity project = this.createAndSaveProjectEntity();

        DocumentEntity firstDocument = new DocumentEntity();
        firstDocument.setName(FIRST_DOCUMENT_NAME);
        firstDocument.setEditingContext(project.getCurrentEditingContext());
        firstDocument.setContent(DOCUMENT_CONTENT);

        this.documentRepository.save(firstDocument);

        DocumentEntity secondDocument = new DocumentEntity();
        secondDocument.setName(SECOND_DOCUMENT_NAME);
        secondDocument.setEditingContext(project.getCurrentEditingContext());
        secondDocument.setContent(DOCUMENT_CONTENT);

        this.documentRepository.save(secondDocument);

        DocumentEntity thirdDocument = new DocumentEntity();
        thirdDocument.setName(THIRD_DOCUMENT_NAME);
        thirdDocument.setEditingContext(project.getCurrentEditingContext());
        thirdDocument.setContent(DOCUMENT_CONTENT);

        this.documentRepository.save(thirdDocument);

        List<DocumentEntity> documents = this.documentRepository.findAllByProjectId(project.getId());
        assertThat(documents).hasSize(3);
    }

    @Test
    @Transactional
    public void testFindByProjectIdAndId() {

        ProjectEntity project = this.createAndSaveProjectEntity();

        DocumentEntity firstDocument = new DocumentEntity();
        firstDocument.setName(FIRST_DOCUMENT_NAME);
        firstDocument.setEditingContext(project.getCurrentEditingContext());
        firstDocument.setContent(DOCUMENT_CONTENT);
        this.documentRepository.save(firstDocument);

        var optionalDocument = this.documentRepository.findByProjectIdAndId(project.getId(), firstDocument.getId());
        assertThat(optionalDocument.isPresent()).isTrue();

        DocumentEntity documentEntity = optionalDocument.get();
        assertThat(documentEntity.getName()).isEqualTo(FIRST_DOCUMENT_NAME);
    }

    @Test
    @Transactional
    public void testInsertDocumentWithSameNameInSameProject() {
        ProjectEntity project = this.createAndSaveProjectEntity();

        DocumentEntity firstDocument = new DocumentEntity();
        firstDocument.setName(FIRST_DOCUMENT_NAME);
        firstDocument.setEditingContext(project.getCurrentEditingContext());
        firstDocument.setContent(DOCUMENT_CONTENT);
        DocumentEntity firstSavedDocument = this.documentRepository.save(firstDocument);

        DocumentEntity secondDocument = new DocumentEntity();
        secondDocument.setName(FIRST_DOCUMENT_NAME);
        secondDocument.setEditingContext(project.getCurrentEditingContext());
        secondDocument.setContent(DOCUMENT_CONTENT);
        DocumentEntity secondSavedDocument = this.documentRepository.save(secondDocument);

        assertThat(this.documentRepository.count()).isEqualTo(2);
        assertThat(firstSavedDocument.getId()).isNotEqualTo(secondSavedDocument.getId());
    }

    @Test
    @Transactional
    public void testCascadingOnDeleteProject() {
        ProjectEntity projectEntity = this.createAndSaveProjectEntity();

        DocumentEntity firstDocument = new DocumentEntity();
        firstDocument.setName(FIRST_DOCUMENT_NAME);
        firstDocument.setEditingContext(projectEntity.getCurrentEditingContext());
        firstDocument.setContent(DOCUMENT_CONTENT);
        this.documentRepository.save(firstDocument);

        this.projectRepository.delete(projectEntity);

        assertThat(this.documentRepository.count()).isEqualTo(0);
    }

    private ProjectEntity createAndSaveProjectEntity() {
        AccountEntity owner = new AccountEntity();
        owner.setUsername(OWNER_NAME);
        owner.setPassword(OWNER_NAME);
        owner.setRole(ROLE_USER);
        AccountEntity savedOwner = this.accountRepository.save(owner);

        EditingContextEntity editingContextEntity = new EditingContextEntity();
        editingContextEntity.setId(UUID.randomUUID());
        editingContextEntity = this.editingContextRepository.save(editingContextEntity);

        ProjectEntity project = new ProjectEntity();
        project.setName(PROJECT_NAME);
        project.setOwner(savedOwner);
        project.setCurrentEditingContext(editingContextEntity);
        ProjectEntity savedProject = this.projectRepository.save(project);
        return savedProject;
    }
}
