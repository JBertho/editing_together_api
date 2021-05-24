package fr.esgi.pa.editing_together_api.app.projects.usecase.snippet;

import fr.esgi.pa.editing_together_api.app.projects.domain.dao.ProjectDAO;
import fr.esgi.pa.editing_together_api.app.projects.domain.dao.SnippetDAO;
import fr.esgi.pa.editing_together_api.app.projects.domain.entity.Project;
import fr.esgi.pa.editing_together_api.app.projects.domain.entity.Snippet;
import fr.esgi.pa.editing_together_api.config.exceptions.http.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Service
public class GetSnippetsByProjectId {

    private final SnippetDAO snippetDAO;
    private final ProjectDAO projectDAO;


    public List<Snippet> execute(Integer projectId) {

        Project project = projectDAO.getProjectById(projectId);
        if (Objects.isNull(project)) {
            throw new NotFoundException("Project not found");
        }
        return snippetDAO.getSnippetsByProjectId(projectId);
    }
}
