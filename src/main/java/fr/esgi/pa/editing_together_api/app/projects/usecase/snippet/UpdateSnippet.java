package fr.esgi.pa.editing_together_api.app.projects.usecase.snippet;

import fr.esgi.pa.editing_together_api.app.auth.domain.entity.User;
import fr.esgi.pa.editing_together_api.app.projects.domain.dao.ProjectDAO;
import fr.esgi.pa.editing_together_api.app.projects.domain.dao.SnippetDAO;
import fr.esgi.pa.editing_together_api.app.projects.domain.entity.Project;
import fr.esgi.pa.editing_together_api.app.projects.domain.entity.Snippet;
import fr.esgi.pa.editing_together_api.app.projects.infrastructure.adapter.SnippetAdapter;
import fr.esgi.pa.editing_together_api.app.projects.infrastructure.dto.UpdateSnippetDTO;
import fr.esgi.pa.editing_together_api.config.exceptions.http.ForbiddenException;
import fr.esgi.pa.editing_together_api.config.exceptions.http.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

@Service
@AllArgsConstructor
public class UpdateSnippet {

    private final SnippetDAO snippetDAO;
    private final ProjectDAO projectDAO;

    public void execute(UpdateSnippetDTO updateSnippet, User currentUser) {
        Snippet snippet = snippetDAO.getSnippetById(updateSnippet.getId());
        if (Objects.isNull(snippet)) {
            throw new NotFoundException("Snippet not found");
        }
        Project project = projectDAO.getProjectById(snippet.getProjectId());
        if (Objects.isNull(project)) {
            throw new NotFoundException("Project not found");
        }
        boolean isUserLinkToProject = projectDAO.findIfProjectUserExist(project, currentUser);
        if (!isUserLinkToProject) {
            throw new ForbiddenException("User is not link to projet");
        }

        updateSnippetData(snippet, updateSnippet, currentUser);

        snippetDAO.saveSnippet(snippet);
    }

    private void updateSnippetData(Snippet snippet, UpdateSnippetDTO updateSnippet, User currentUser) {
        snippet.setName(updateSnippet.getName());
        snippet.setContent(updateSnippet.getContent());
        snippet.setUpdateUserId(currentUser.getId());
        snippet.setUpdateDate(new Date());
    }
}
