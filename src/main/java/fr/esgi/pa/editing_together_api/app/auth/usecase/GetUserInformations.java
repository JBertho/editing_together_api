package fr.esgi.pa.editing_together_api.app.auth.usecase;

import fr.esgi.pa.editing_together_api.app.auth.domain.dao.UserDAO;
import fr.esgi.pa.editing_together_api.app.auth.domain.entity.User;
import fr.esgi.pa.editing_together_api.app.auth.infrastructure.UserAdapter;
import fr.esgi.pa.editing_together_api.app.auth.infrastructure.models.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GetUserInformations {

    private final UserDAO userDAO;

    public User execute(String userName) {
        Optional<UserEntity> byUsername = userDAO.findByUsername(userName);
        if (byUsername.isPresent()) {
            return UserAdapter.adapt(byUsername.get());
        } else {
            throw new UsernameNotFoundException("User Not found with username : " + userName);
        }
    }


}
