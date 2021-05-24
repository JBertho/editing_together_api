package fr.esgi.pa.editing_together_api.app.projects.infrastructure.dto;

import lombok.Data;

@Data
public class UpdateSnippetDTO {
    private int id;
    private String name;
    private String content;
    private int projectId;
}
