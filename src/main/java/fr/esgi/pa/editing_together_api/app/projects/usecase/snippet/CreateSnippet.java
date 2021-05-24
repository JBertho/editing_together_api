package fr.esgi.pa.editing_together_api.app.projects.usecase.snippet;

import fr.esgi.pa.editing_together_api.app.projects.domain.dao.ProjectDAO;
import fr.esgi.pa.editing_together_api.app.projects.domain.dao.SnippetDAO;
import fr.esgi.pa.editing_together_api.app.projects.domain.entity.Project;
import fr.esgi.pa.editing_together_api.app.projects.domain.entity.Snippet;
import fr.esgi.pa.editing_together_api.app.projects.infrastructure.adapter.SnippetAdapter;
import fr.esgi.pa.editing_together_api.app.projects.infrastructure.dto.NewSnippetDTO;
import fr.esgi.pa.editing_together_api.config.exceptions.http.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class CreateSnippet {

    private final SnippetDAO snippetDAO;
    private final ProjectDAO projectDAO;

    public Integer execute(NewSnippetDTO newSnippetDAO) {
        Project project = projectDAO.getProjectById(newSnippetDAO.getProjectId());
        if (Objects.isNull(project)) {
            throw new NotFoundException("Project not found");
        }
        Snippet newSnippet = SnippetAdapter.adaptNewSnippet(newSnippetDAO);

        return snippetDAO.createSnippet(newSnippet);
    }
}
