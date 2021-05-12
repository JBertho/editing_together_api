package fr.esgi.pa.editing_together_api.app.projects.infrastructure.models;

import fr.esgi.pa.editing_together_api.app.projects.domain.entity.LanguageType;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "project")
public class ProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Enumerated(EnumType.STRING)
    @Column
    private LanguageType language;

    @CreatedDate
    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "created_user_id")
    private Integer createUserId;

    @Column(name = "last_compilation")
    private Date lastCompilation;

    @Column
    private String token;


}
