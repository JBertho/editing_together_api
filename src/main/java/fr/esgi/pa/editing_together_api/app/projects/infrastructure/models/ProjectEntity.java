package fr.esgi.pa.editing_together_api.app.projects.infrastructure.models;

import fr.esgi.pa.editing_together_api.app.projects.domain.entity.LanguageType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    private Long createUserId;

    @Column(name = "last_compilation")
    private Date lastCompilation;

    @Column
    private String token;


}
