package fr.esgi.pa.editing_together_api.app.projects.infrastructure.repositories;

import fr.esgi.pa.editing_together_api.app.projects.infrastructure.models.SnippetEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SnippetRepository extends JpaRepository<SnippetEntity, Integer> {

    List<SnippetEntity> findAllByProjectId(Integer projectId);
}
