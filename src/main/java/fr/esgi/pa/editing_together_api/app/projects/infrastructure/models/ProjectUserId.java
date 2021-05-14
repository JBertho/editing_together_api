package fr.esgi.pa.editing_together_api.app.projects.infrastructure.models;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@NoArgsConstructor
public class ProjectUserId implements Serializable   {

    private Integer projectId;
    private Long userId;
}
