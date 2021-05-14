package fr.esgi.pa.editing_together_api.app.projects.domain.dao;

import fr.esgi.pa.editing_together_api.app.auth.domain.entity.User;
import fr.esgi.pa.editing_together_api.app.projects.domain.entity.Project;

import java.util.List;

public interface ProjectDAO {

    Integer createProject(Project project);

    void joinProject(Project projectId, User userId);

    boolean findIfProjectUserExist(Project project, User user);

    List<Project> getUserProjects(Long userId);

    Project getProjectById(int projectId);
}
