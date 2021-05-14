package fr.esgi.pa.editing_together_api.app.projects.infrastructure.repositories;

import fr.esgi.pa.editing_together_api.app.projects.infrastructure.models.ProjectUserEntity;
import fr.esgi.pa.editing_together_api.app.projects.infrastructure.models.ProjectUserId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectUserRepository extends JpaRepository<ProjectUserEntity, ProjectUserId> {
}
