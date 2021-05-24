package fr.esgi.pa.editing_together_api.app.projects.domain.dao;

import fr.esgi.pa.editing_together_api.app.projects.domain.entity.Project;
import fr.esgi.pa.editing_together_api.app.projects.domain.entity.Snippet;

import java.util.List;

public interface SnippetDAO {

    Integer createSnippet(Snippet snippet);

    List<Snippet> getSnippetsByProjectId(int projectId);

    Boolean deleteSnippet(Snippet snippet);

    Snippet updateSnippet(Snippet snippet);

}
