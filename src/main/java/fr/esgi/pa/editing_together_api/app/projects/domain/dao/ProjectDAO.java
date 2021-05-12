package fr.esgi.pa.editing_together_api.app.projects.domain.dao;

import fr.esgi.pa.editing_together_api.app.projects.domain.entity.Project;

import java.util.List;

public interface ProjectDAO {

    Integer createProject(Project project);

    void joinProject(int projectId, int userId);

    List<Project> getUserProjects(int userId);

    Project getProjectById(int projectId);
}
