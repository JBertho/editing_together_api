package fr.esgi.pa.editing_together_api.app.projects.usecase;

import fr.esgi.pa.editing_together_api.app.projects.domain.dao.ProjectDAO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class JoinProject {
    private final ProjectDAO projectDAO;

    public int execute(int projectId, Long id) {
        projectDAO.joinProject(projectId, id);
        if ()
    }
}
