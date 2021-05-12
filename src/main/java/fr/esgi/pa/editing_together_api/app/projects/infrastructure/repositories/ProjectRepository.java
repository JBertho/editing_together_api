package fr.esgi.pa.editing_together_api.app.projects.infrastructure.repositories;

import fr.esgi.pa.editing_together_api.app.projects.infrastructure.models.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProjectRepository extends JpaRepository<ProjectEntity, Integer> {
}
