package fr.esgi.pa.editing_together_api.app.projects.infrastructure.dto;

import fr.esgi.pa.editing_together_api.app.projects.domain.entity.LanguageType;
import lombok.Data;

@Data
public class NewProjectDTO {
    private String name;
    private LanguageType language;

}
