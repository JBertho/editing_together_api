package fr.esgi.pa.editing_together_api.app.projects.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Snippet {

    private Integer id;

    private String name;

    private String content;

    private Date createdDate;

    private Date updateDate;

    private Long createUserId;

    private Long updateUserId;

    private Integer projectId;
}
