package fr.esgi.pa.editing_together_api.app.auth.domain.dao;

import fr.esgi.pa.editing_together_api.app.auth.domain.entity.User;
import fr.esgi.pa.editing_together_api.app.auth.infrastructure.models.Role;
import fr.esgi.pa.editing_together_api.app.auth.infrastructure.models.UserEntity;

import java.util.HashSet;
import java.util.Optional;

public interface UserDAO {
    void createUser(String username, String email, String password, HashSet<Role> roles);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    Optional<UserEntity> findByUsername(String userName);

    User getUserById(Long userId);

    Optional<UserEntity> findByUserId(Long userId);
}
