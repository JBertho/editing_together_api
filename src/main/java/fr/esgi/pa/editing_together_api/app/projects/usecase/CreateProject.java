package fr.esgi.pa.editing_together_api.app.projects.usecase;

import fr.esgi.pa.editing_together_api.app.projects.domain.dao.ProjectDAO;
import fr.esgi.pa.editing_together_api.app.projects.domain.entity.Project;
import fr.esgi.pa.editing_together_api.app.projects.infrastructure.adapter.ProjectAdapter;
import fr.esgi.pa.editing_together_api.app.projects.infrastructure.dto.NewProjectDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreateProject {

    private final ProjectDAO projectDAO;

    public Integer execute(NewProjectDTO newProject) {
        return projectDAO.createProject(ProjectAdapter.adaptNewProject(newProject));
    }

}
