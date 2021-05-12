package fr.esgi.pa.editing_together_api.app.projects.usecase;

import fr.esgi.pa.editing_together_api.app.projects.domain.dao.ProjectDAO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetUserProjects {
    private final ProjectDAO projectDAO;

}
