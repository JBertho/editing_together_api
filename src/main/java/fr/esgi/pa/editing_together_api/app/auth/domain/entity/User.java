package fr.esgi.pa.editing_together_api.app.auth.domain.entity;

import fr.esgi.pa.editing_together_api.app.auth.infrastructure.models.Role;
import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class User {

    private Long id;

    private String username;

    private String email;


    private String password;

    private Set<Role> roles;
}
