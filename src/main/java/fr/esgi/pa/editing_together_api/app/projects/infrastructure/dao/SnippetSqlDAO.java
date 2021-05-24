package fr.esgi.pa.editing_together_api.app.projects.infrastructure.dao;

import fr.esgi.pa.editing_together_api.app.projects.domain.dao.SnippetDAO;
import fr.esgi.pa.editing_together_api.app.projects.domain.entity.Snippet;
import fr.esgi.pa.editing_together_api.app.projects.infrastructure.adapter.SnippetAdapter;
import fr.esgi.pa.editing_together_api.app.projects.infrastructure.models.SnippetEntity;
import fr.esgi.pa.editing_together_api.app.projects.infrastructure.repositories.SnippetRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SnippetSqlDAO implements SnippetDAO {

    private final SnippetRepository snippetRepository;

    @Override
    public Integer createSnippet(Snippet snippet) {
        return null;
    }

    @Override
    public List<Snippet> getSnippetsByProjectId(int projectId) {
        return null;
    }

    @Override
    public Snippet getSnippetById(int snippetId) {
        Optional<SnippetEntity> optionalSnippetEntity = snippetRepository.findById(snippetId);
        if (optionalSnippetEntity.isEmpty()) {
            return null;
        }
        return SnippetAdapter.adaptToDomain(optionalSnippetEntity.get());
    }

    @Override
    public void deleteSnippet(int snippetId) {
        snippetRepository.deleteById(snippetId);
    }

    @Override
    public Snippet updateSnippet(Snippet snippet) {
        return null;
    }
}
