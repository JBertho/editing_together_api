package fr.esgi.pa.editing_together_api.app.auth.infrastructure.dao;

import fr.esgi.pa.editing_together_api.app.auth.domain.dao.RoleDAO;
import fr.esgi.pa.editing_together_api.app.auth.infrastructure.models.Role;
import fr.esgi.pa.editing_together_api.app.auth.infrastructure.models.RoleName;
import fr.esgi.pa.editing_together_api.app.auth.infrastructure.repositories.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RoleSqlDAO implements RoleDAO {

    private final RoleRepository roleRepository;

    @Override
    public Role findByRoleName(RoleName roleUser) {
        return roleRepository.findByName(roleUser).orElse(null);
    }
}
