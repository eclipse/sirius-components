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
package org.eclipse.sirius.web.services.modelers;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.eclipse.sirius.web.core.api.ErrorPayload;
import org.eclipse.sirius.web.core.api.IPayload;
import org.eclipse.sirius.web.persistence.entities.EditingContextEntity;
import org.eclipse.sirius.web.persistence.entities.ModelerEntity;
import org.eclipse.sirius.web.persistence.entities.PublicationStatusEntity;
import org.eclipse.sirius.web.persistence.repositories.IEditingContextRepository;
import org.eclipse.sirius.web.persistence.repositories.IModelerRepository;
import org.eclipse.sirius.web.persistence.repositories.IProjectRepository;
import org.eclipse.sirius.web.services.api.modelers.CreateModelerInput;
import org.eclipse.sirius.web.services.api.modelers.CreateModelerSuccessPayload;
import org.eclipse.sirius.web.services.api.modelers.IModelerService;
import org.eclipse.sirius.web.services.api.modelers.Modeler;
import org.eclipse.sirius.web.services.api.modelers.PublishModelerInput;
import org.eclipse.sirius.web.services.api.modelers.PublishModelerSuccessPayload;
import org.eclipse.sirius.web.services.api.modelers.RenameModelerInput;
import org.eclipse.sirius.web.services.api.modelers.RenameModelerSuccessPayload;
import org.eclipse.sirius.web.services.api.projects.Project;
import org.eclipse.sirius.web.services.messages.IServicesMessageService;
import org.springframework.stereotype.Service;

/**
 * Service used to manipulate modelers.
 *
 * @author pcdavid
 */
@Service
public class ModelerService implements IModelerService {

    private final IServicesMessageService messageService;

    private final IProjectRepository projectRepository;

    private final IModelerRepository modelerRepository;

    private final IEditingContextRepository editingContextRepository;

    public ModelerService(IServicesMessageService messageService, IProjectRepository projectRepository, IModelerRepository modelerRepository, IEditingContextRepository editingContextRepository) {
        this.messageService = Objects.requireNonNull(messageService);
        this.projectRepository = Objects.requireNonNull(projectRepository);
        this.modelerRepository = Objects.requireNonNull(modelerRepository);
        this.editingContextRepository = Objects.requireNonNull(editingContextRepository);
    }

    @Override
    public IPayload createModeler(CreateModelerInput input) {
        return this.projectRepository.findById(input.getProjectId()).map(projectEntity -> {
            String name = input.getName().trim();
            if (!this.isValidModelerName(name)) {
                return new ErrorPayload(this.messageService.invalidModelerName());
            } else {
                ModelerEntity modelerEntity = new ModelerEntity();
                modelerEntity.setName(input.getName());
                modelerEntity.setProject(this.projectRepository.findById(input.getProjectId()).get());
                modelerEntity.setPublicationStatus(PublicationStatusEntity.DRAFT);
                modelerEntity.setEditingContext(this.createEditingContextEntity());

                modelerEntity = this.modelerRepository.save(modelerEntity);
                return new CreateModelerSuccessPayload(this.toDTO(modelerEntity));
            }
        }).orElse(new ErrorPayload(this.messageService.projectNotFound()));

    }

    private EditingContextEntity createEditingContextEntity() {
        EditingContextEntity editingContextEntity = new EditingContextEntity();
        editingContextEntity = this.editingContextRepository.save(editingContextEntity);
        return editingContextEntity;
    }

    @Override
    public IPayload renameModeler(RenameModelerInput input) {
        return this.modelerRepository.findById(input.getModelerId()).map(modelerEntity -> {
            if (!this.isValidModelerName(input.getNewName())) {
                return new ErrorPayload(this.messageService.invalidModelerName());
            } else {
                modelerEntity.setName(input.getNewName());
                modelerEntity = this.modelerRepository.save(modelerEntity);
                return new RenameModelerSuccessPayload(this.toDTO(modelerEntity));
            }
        }).orElse(new ErrorPayload(this.messageService.modelerNotFound()));
    }

    @Override
    public IPayload publishModeler(PublishModelerInput input) {
        var optionalModelerEntity = this.modelerRepository.findById(input.getModelerId());
        return optionalModelerEntity.map(modelerEntity -> {
            modelerEntity.setPublicationStatus(PublicationStatusEntity.PUBLISHED);
            modelerEntity = this.modelerRepository.save(modelerEntity);
            return (IPayload) new PublishModelerSuccessPayload(this.toDTO(modelerEntity));
        }).orElse(new ErrorPayload(this.messageService.modelerNotFound()));
    }

    @Override
    public Optional<Modeler> getModeler(UUID modelerId) {
        return this.modelerRepository.findById(modelerId).map(this::toDTO);
    }

    @Override
    public List<Modeler> getModelers(Project project) {
        // @formatter:off
        return this.modelerRepository.findAllByProjectId(project.getId()).stream()
                                     .map(this::toDTO)
                                     .sorted(Comparator.comparing(Modeler::getName))
                                     .collect(Collectors.toList());
        // @formatter:on
    }

    private Modeler toDTO(ModelerEntity modelerEntity) {
        return new ModelerMapper().toDTO(modelerEntity);
    }

    private boolean isValidModelerName(String name) {
        return 3 <= name.length() && name.length() <= 20;
    }
}
