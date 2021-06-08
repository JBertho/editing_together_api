package fr.esgi.pa.editing_together_api.app.projects.infrastructure.adapter;

import fr.esgi.pa.editing_together_api.app.auth.usecase.GetUserById;
import fr.esgi.pa.editing_together_api.app.projects.domain.entity.Snippet;
import fr.esgi.pa.editing_together_api.app.projects.infrastructure.dto.Response.SnippetInformationDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SnippetInformationAdapter {

    private final GetUserById getUserById;

    public SnippetInformationDTO adapt(Snippet source) {

        String updateUserName = null;
        if (source.getUpdateUserId() != null) {
            updateUserName =  getUserById.execute(source.getUpdateUserId()).getUsername();
        }

        String createUserName = null;
        if (source.getCreateUserId() != null) {
            createUserName =  getUserById.execute(source.getUpdateUserId()).getUsername();
        }




        return SnippetInformationDTO.builder()
                .id(source.getId())
                .name(source.getName())
                .projectId(source.getProjectId())
                .content(source.getContent())
                .createdDate(source.getCreatedDate())
                .createUserId(source.getCreateUserId())
                .createUserName(createUserName)
                .updateDate(source.getUpdateDate())
                .updateUserId(source.getUpdateUserId())
                .updateUserName(updateUserName)
                .build();

    }
}
