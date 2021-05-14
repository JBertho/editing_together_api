package fr.esgi.pa.editing_together_api.app.projects.usecase;

import fr.esgi.pa.editing_together_api.app.auth.domain.dao.UserDAO;
import fr.esgi.pa.editing_together_api.app.auth.domain.entity.User;
import fr.esgi.pa.editing_together_api.app.auth.domain.exceptions.AlreadyCreatedException;
import fr.esgi.pa.editing_together_api.app.projects.domain.dao.ProjectDAO;
import fr.esgi.pa.editing_together_api.app.projects.domain.entity.Project;
import fr.esgi.pa.editing_together_api.app.projects.domain.exceptions.ProjectNotFoundException;
import fr.esgi.pa.editing_together_api.app.projects.infrastructure.models.ProjectUserEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;

@Service
@AllArgsConstructor
public class JoinProject {
    private final ProjectDAO projectDAO;
    private final UserDAO userDAO;

    public void execute(int projectId, Long userId) throws ProjectNotFoundException, UserPrincipalNotFoundException, AlreadyCreatedException {
        Project project = projectDAO.getProjectById(projectId);
        if (project == null) {
            throw new ProjectNotFoundException("Project not found");
        }

        User user = userDAO.getUserById(userId);
        if(user == null) {
            throw new UserPrincipalNotFoundException("");
        }
        if (projectDAO.findIfProjectUserExist(project, user)) {
            throw new AlreadyCreatedException("User already in group");
        }
        projectDAO.joinProject(project, user);
    }
}
