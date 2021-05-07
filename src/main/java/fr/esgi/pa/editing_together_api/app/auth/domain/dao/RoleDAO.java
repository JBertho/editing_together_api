package fr.esgi.pa.editing_together_api.app.auth.domain.dao;

import fr.esgi.pa.editing_together_api.app.auth.infrastructure.models.Role;
import fr.esgi.pa.editing_together_api.app.auth.infrastructure.models.RoleName;

public interface RoleDAO {
    Role findByRoleName(RoleName roleUser);
}
