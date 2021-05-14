package fr.esgi.pa.editing_together_api.app.projects.infrastructure.repositories;

import fr.esgi.pa.editing_together_api.app.projects.infrastructure.models.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ProjectRepository extends JpaRepository<ProjectEntity, Integer> {
    Optional<ProjectEntity> findByToken(String token);
}
