package fr.esgi.pa.editing_together_api.app.auth.domain.dao;

import fr.esgi.pa.editing_together_api.app.auth.infrastructure.models.Role;

import java.util.HashSet;

public interface UserDAO {
    void createUser(String username, String email, String password, HashSet<Role> roles);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
