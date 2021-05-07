package fr.esgi.pa.editing_together_api.app.auth.infrastructure.dao;

import fr.esgi.pa.editing_together_api.app.auth.domain.dao.UserDAO;
import fr.esgi.pa.editing_together_api.app.auth.infrastructure.models.Role;
import fr.esgi.pa.editing_together_api.app.auth.infrastructure.models.User;
import fr.esgi.pa.editing_together_api.app.auth.infrastructure.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
@AllArgsConstructor
public class UserSqlDAO implements UserDAO {

    private final UserRepository userRepository;

    @Override
    public void createUser(String username, String email, String password, HashSet<Role> roles) {
        userRepository.save(new User(username, email, password).setRoles(roles));
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
