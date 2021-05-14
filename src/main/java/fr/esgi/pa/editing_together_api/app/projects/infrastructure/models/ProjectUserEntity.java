package fr.esgi.pa.editing_together_api.app.projects.infrastructure.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "project_user")
@IdClass(ProjectUserId.class)
public class ProjectUserEntity {

    @Id
    private Integer projectId;

    @Id
    private Long userId;

}
