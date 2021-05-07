package fr.esgi.pa.editing_together_api.app.auth.infrastructure.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private RoleName name;

    public Role() {

    }

    public Role(RoleName name) {
        this.name = name;
    }
}
