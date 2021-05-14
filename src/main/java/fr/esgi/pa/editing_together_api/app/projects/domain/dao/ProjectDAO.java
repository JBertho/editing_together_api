package fr.esgi.pa.editing_together_api.app.projects.domain.dao;

import fr.esgi.pa.editing_together_api.app.projects.domain.entity.Project;

import java.util.List;

public interface ProjectDAO {

    Integer createProject(Project project);

    Integer joinProject(int projectId, Long userId);

    List<Project> getUserProjects(Long userId);

    Project getProjectById(int projectId);
}
