package fr.esgi.pa.editing_together_api.app.projects.infrastructure.adapter;

import fr.esgi.pa.editing_together_api.app.projects.domain.entity.Project;
import fr.esgi.pa.editing_together_api.app.projects.infrastructure.dto.NewProjectDTO;

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
                .createUserId(null) //TODO passer le userId en param
                .build();
    }

}
