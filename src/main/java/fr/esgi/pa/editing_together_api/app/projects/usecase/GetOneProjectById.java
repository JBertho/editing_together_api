package fr.esgi.pa.editing_together_api.app.projects.usecase;

import fr.esgi.pa.editing_together_api.app.projects.domain.dao.ProjectDAO;
import fr.esgi.pa.editing_together_api.app.projects.domain.entity.Project;
import fr.esgi.pa.editing_together_api.app.projects.domain.exceptions.ProjectNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetOneProjectById {

    private final ProjectDAO projectDAO;

    public Project execute(int projectId) throws ProjectNotFoundException {
        Project project = projectDAO.getProjectById(projectId);
        if (project == null) {
            throw new ProjectNotFoundException("Could not find project with id = " + projectId);
        }
        return project;
    }
}
