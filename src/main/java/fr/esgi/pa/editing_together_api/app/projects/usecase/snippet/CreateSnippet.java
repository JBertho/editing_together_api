package fr.esgi.pa.editing_together_api.app.projects.usecase.snippet;

import fr.esgi.pa.editing_together_api.app.auth.domain.entity.User;
import fr.esgi.pa.editing_together_api.app.projects.domain.dao.ProjectDAO;
import fr.esgi.pa.editing_together_api.app.projects.domain.dao.SnippetDAO;
import fr.esgi.pa.editing_together_api.app.projects.domain.entity.Project;
import fr.esgi.pa.editing_together_api.app.projects.domain.entity.Snippet;
import fr.esgi.pa.editing_together_api.app.projects.infrastructure.adapter.SnippetAdapter;
import fr.esgi.pa.editing_together_api.app.projects.infrastructure.dto.NewSnippetDTO;
import fr.esgi.pa.editing_together_api.config.exceptions.http.ForbiddenException;
import fr.esgi.pa.editing_together_api.config.exceptions.http.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class CreateSnippet {

    private final SnippetDAO snippetDAO;
    private final ProjectDAO projectDAO;

    public Integer execute(NewSnippetDTO newSnippetDAO, User currentUser) {
        Project project = projectDAO.getProjectById(newSnippetDAO.getProjectId());
        if (Objects.isNull(project)) {
            throw new NotFoundException("Project not found");
        }
        System.out.println("WESH");
        boolean isUserLinkToProject = projectDAO.findIfProjectUserExist(project, currentUser);
        if (!isUserLinkToProject) {
            throw new ForbiddenException("User is not link to projet");
        }
        Snippet newSnippet = SnippetAdapter.adaptNewSnippet(newSnippetDAO, currentUser.getId());

        return snippetDAO.createSnippet(newSnippet);
    }
}
