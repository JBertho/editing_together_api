package fr.esgi.pa.editing_together_api.app.projects.infrastructure.dao;

import fr.esgi.pa.editing_together_api.app.projects.domain.dao.SnippetDAO;
import fr.esgi.pa.editing_together_api.app.projects.domain.entity.Snippet;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SnippetSqlDAO implements SnippetDAO {
    @Override
    public Integer createSnippet(Snippet snippet) {
        return null;
    }

    @Override
    public List<Snippet> getSnippetsByProjectId(int projectId) {
        return null;
    }

    @Override
    public Boolean deleteSnippet(Snippet snippet) {
        return null;
    }

    @Override
    public Snippet updateSnippet(Snippet snippet) {
        return null;
    }
}
