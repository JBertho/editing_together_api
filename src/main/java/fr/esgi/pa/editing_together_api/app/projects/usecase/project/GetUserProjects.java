package fr.esgi.pa.editing_together_api.app.projects.usecase.project;

import fr.esgi.pa.editing_together_api.app.auth.domain.entity.User;
import fr.esgi.pa.editing_together_api.app.projects.domain.dao.ProjectDAO;
import fr.esgi.pa.editing_together_api.app.projects.domain.entity.Project;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetUserProjects {
    private final ProjectDAO projectDAO;

    public List<Project> execute(User currentUser) {
        return projectDAO.getUserProjects(currentUser.getId());
    }
}
