package fr.esgi.pa.editing_together_api.app.projects.infrastructure.adapter;

import fr.esgi.pa.editing_together_api.app.projects.domain.entity.Project;
import fr.esgi.pa.editing_together_api.app.projects.infrastructure.dto.NewProjectDTO;
import fr.esgi.pa.editing_together_api.app.projects.infrastructure.models.ProjectEntity;

import java.util.Date;
import java.util.UUID;

public class ProjectAdapter {

    public static Project adaptNewProject(NewProjectDTO newProject) {
        UUID uuid = UUID.randomUUID();
        return Project.builder()
                .id(null)
                .language(newProject.getLanguage())
                .name(newProject.getName())
                .createdDate(new Date())
                .token(uuid.toString())
                .lastCompilation(null)
                .createUserId(null)
                .build();
    }

    public static ProjectEntity adaptToEntity(Project project) {
        return ProjectEntity.builder()
                .id(project.getId())
                .createdDate(project.getCreatedDate())
                .createUserId(project.getCreateUserId())
                .language(project.getLanguage())
                .lastCompilation(project.getLastCompilation())
                .name(project.getName())
                .token(project.getToken())
                .build();
    }

    public static Project adaptToDomain(ProjectEntity projectEntity) {
        return Project.builder()
                .id(projectEntity.getId())
                .language(projectEntity.getLanguage())
                .name(projectEntity.getName())
                .createdDate(projectEntity.getCreatedDate())
                .token(projectEntity.getToken())
                .lastCompilation(projectEntity.getLastCompilation())
                .createUserId(projectEntity.getCreateUserId())
                .build();
    }
}
