package fr.esgi.pa.editing_together_api.app.projects.usecase.snippet;

import fr.esgi.pa.editing_together_api.app.auth.domain.entity.User;
import fr.esgi.pa.editing_together_api.app.projects.domain.dao.ProjectDAO;
import fr.esgi.pa.editing_together_api.app.projects.domain.dao.SnippetDAO;
import fr.esgi.pa.editing_together_api.app.projects.domain.entity.Project;
import fr.esgi.pa.editing_together_api.app.projects.domain.entity.Snippet;
import fr.esgi.pa.editing_together_api.config.exceptions.http.ForbiddenException;
import fr.esgi.pa.editing_together_api.config.exceptions.http.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class DeleteSnippet {
    private final SnippetDAO snippetDAO;
    private final ProjectDAO projectDAO;

    public void execute(int snippetId, User currentUser) {
        Snippet snippetToDelete = snippetDAO.getSnippetById(snippetId);
        if (Objects.isNull(snippetToDelete)) {
            throw new NotFoundException("Snippet not found");
        }
        Project project = projectDAO.getProjectById(snippetToDelete.getProjectId());
        if (Objects.isNull(project)) {
            throw new NotFoundException("Project not found");
        }
        boolean isUserLinkToProject = projectDAO.findIfProjectUserExist(project, currentUser);
        if (!isUserLinkToProject) {
            throw new ForbiddenException("User is not link to projet");
        }

        snippetDAO.deleteSnippet(snippetId);

    }


}
