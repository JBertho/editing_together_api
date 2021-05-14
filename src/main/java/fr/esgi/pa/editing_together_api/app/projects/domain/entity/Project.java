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
public class Project {

    private Integer id;

    private String name;

    private LanguageType language;

    private Date createdDate;

    private Long createUserId;

    private Date lastCompilation;

    private String token;
}
