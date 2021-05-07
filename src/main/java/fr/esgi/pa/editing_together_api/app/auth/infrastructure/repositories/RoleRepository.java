package fr.esgi.pa.editing_together_api.app.auth.infrastructure.repositories;


import fr.esgi.pa.editing_together_api.app.auth.infrastructure.models.Role;
import fr.esgi.pa.editing_together_api.app.auth.infrastructure.models.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName name);
}
