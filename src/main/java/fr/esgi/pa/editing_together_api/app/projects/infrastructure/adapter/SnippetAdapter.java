package fr.esgi.pa.editing_together_api.app.projects.infrastructure.adapter;

import fr.esgi.pa.editing_together_api.app.projects.domain.entity.Snippet;
import fr.esgi.pa.editing_together_api.app.projects.infrastructure.dto.NewSnippetDTO;
import fr.esgi.pa.editing_together_api.app.projects.infrastructure.models.SnippetEntity;

import java.util.Date;

public class SnippetAdapter {

    public static Snippet adaptNewSnippet(NewSnippetDTO newSnippetDTO, Long createUserId) {
        return Snippet.builder()
                .name(newSnippetDTO.getName())
                .content(newSnippetDTO.getContent())
                .projectId(newSnippetDTO.getProjectId())
                .createdDate(new Date())
                .createUserId(createUserId)
                .updateDate(null)
                .updateUserId(null)
                .build();
    }

    public static Snippet adaptToDomain(SnippetEntity snippetEntity) {
        return Snippet.builder()
                .name(snippetEntity.getName())
                .content(snippetEntity.getContent())
                .projectId(snippetEntity.getProjectId())
                .createdDate(snippetEntity.getCreatedDate())
                .createUserId(snippetEntity.getCreateUserId())
                .updateDate(snippetEntity.getUpdateDate())
                .updateUserId(snippetEntity.getUpdateUserId())
                .build();
    }
}
