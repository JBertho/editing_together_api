package fr.esgi.pa.editing_together_api.app.projects.domain.dao;

import fr.esgi.pa.editing_together_api.app.projects.domain.entity.Snippet;

import java.util.List;

public interface SnippetDAO {

    Integer createSnippet(Snippet snippet);

    List<Snippet> getSnippetsByProjectId(int projectId);

    Snippet getSnippetById(int snippetId);

    void deleteSnippet(int snippetId);

    void saveSnippet(Snippet snippet);

    List<Snippet> getSnippetsByIds(List<Integer> snippetIds);
}
