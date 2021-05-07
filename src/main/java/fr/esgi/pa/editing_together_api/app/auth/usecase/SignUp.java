package fr.esgi.pa.editing_together_api.app.auth.usecase;

import fr.esgi.pa.editing_together_api.app.auth.domain.dao.RoleDAO;
import fr.esgi.pa.editing_together_api.app.auth.domain.dao.UserDAO;
import fr.esgi.pa.editing_together_api.app.auth.domain.exceptions.AlreadyCreatedException;
import fr.esgi.pa.editing_together_api.app.auth.infrastructure.dtos.request.SignupRequest;
import fr.esgi.pa.editing_together_api.app.auth.infrastructure.models.Role;
import fr.esgi.pa.editing_together_api.app.auth.infrastructure.models.RoleName;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class SignUp {

    private final UserDAO userDAO;
    private final RoleDAO roleDAO;

    private final PasswordEncoder encoder;

    public void execute(SignupRequest signupRequest) throws AlreadyCreatedException, NotFoundException {

        checkIfUsernameOrEmailAlreadyExist(signupRequest.getUsername(), signupRequest.getEmail());
        checkIfRoleInListContainManageList(signupRequest.getRole(), Arrays.asList("user", "admin"));
        HashSet<Role> roles = getRoles(signupRequest.getRole());

        userDAO.createUser(signupRequest.getUsername(), signupRequest.getEmail(), encoder.encode(signupRequest.getPassword()), roles);

    }

    private void checkIfUsernameOrEmailAlreadyExist(String username, String email) throws AlreadyCreatedException {
        if (userDAO.existsByUsername(username)) {
            var exceptionMessage = String.format("User with username '%s' already exists", username);
            throw new AlreadyCreatedException(exceptionMessage);
        }
        if (userDAO.existsByEmail(email)) {
            var exceptionMessage = String.format("User with email '%s' already exists", username);
            throw new AlreadyCreatedException(exceptionMessage);
        }
    }

    private void checkIfRoleInListContainManageList(Set<String> stringRoles, List<String> availableRoles) throws NotFoundException {
        if (stringRoles == null) return;
        var notFoundRole = stringRoles.stream()
                .filter(role -> !availableRoles.contains(role))
                .findFirst();
        if (notFoundRole.isPresent()) {
            var message = String.format("Role name '%s' not found", notFoundRole.get());
            throw new NotFoundException(message);
        }

    }

    private HashSet<Role> getRoles(Set<String> stringRoles) {
        var roles = new HashSet<Role>();
        if (stringRoles == null) {
            roles.add(roleDAO.findByRoleName(RoleName.ROLE_USER));
            return roles;
        }
        var managedStrRoles = new HashMap<String, RoleName>();
        managedStrRoles.put("user", RoleName.ROLE_USER);
        managedStrRoles.put("admin", RoleName.ROLE_ADMIN);

        stringRoles.forEach(roleStr -> roles.add(roleDAO.findByRoleName(managedStrRoles.get(roleStr))));
        return roles;
    }

}
