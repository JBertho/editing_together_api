package fr.esgi.pa.editing_together_api.app.projects.infrastructure.adapter;

import fr.esgi.pa.editing_together_api.app.projects.domain.entity.Snippet;
import fr.esgi.pa.editing_together_api.app.projects.infrastructure.dto.NewSnippetDTO;

import java.util.Date;

public class SnippetAdapter {

    public static Snippet adaptNewSnippet(NewSnippetDTO newSnippetDTO) {
        return Snippet.builder()
                .name(newSnippetDTO.getName())
                .content(newSnippetDTO.getContent())
                .projectId(newSnippetDTO.getProjectId())
                .createdDate(new Date())
                .createUserId(null)
                .updateDate(null)
                .updateUserId(null)
                .build();
    }
}
