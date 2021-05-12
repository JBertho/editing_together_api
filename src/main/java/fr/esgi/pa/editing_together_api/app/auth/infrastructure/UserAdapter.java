package fr.esgi.pa.editing_together_api.app.auth.infrastructure;

import fr.esgi.pa.editing_together_api.app.auth.domain.entity.User;
import fr.esgi.pa.editing_together_api.app.auth.infrastructure.models.UserEntity;

public class UserAdapter {

    public static User adapt(UserEntity userEntity) {
        return User.builder()
                .id(userEntity.getId())
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .username(userEntity.getUsername())
                .roles(userEntity.getRoles())
                .build();
    }
}
