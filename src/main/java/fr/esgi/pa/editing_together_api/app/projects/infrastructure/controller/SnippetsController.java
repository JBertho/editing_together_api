package fr.esgi.pa.editing_together_api.app.projects.infrastructure.controller;

import fr.esgi.pa.editing_together_api.app.auth.domain.entity.User;
import fr.esgi.pa.editing_together_api.app.auth.usecase.GetUserInformations;
import fr.esgi.pa.editing_together_api.app.projects.domain.entity.Snippet;
import fr.esgi.pa.editing_together_api.app.projects.infrastructure.adapter.SnippetInformationAdapter;
import fr.esgi.pa.editing_together_api.app.projects.infrastructure.dto.NewSnippetDTO;
import fr.esgi.pa.editing_together_api.app.projects.infrastructure.dto.Response.SnippetInformationDTO;
import fr.esgi.pa.editing_together_api.app.projects.infrastructure.dto.UpdateSnippetDTO;
import fr.esgi.pa.editing_together_api.app.projects.usecase.snippet.CreateSnippet;
import fr.esgi.pa.editing_together_api.app.projects.usecase.snippet.DeleteSnippet;
import fr.esgi.pa.editing_together_api.app.projects.usecase.snippet.GetSnippetsByProjectId;
import fr.esgi.pa.editing_together_api.app.projects.usecase.snippet.UpdateSnippet;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.noContent;

@RestController
@RequestMapping("/api/snippets")
@CrossOrigin(origins = "*", maxAge = 3600)
@AllArgsConstructor
public class SnippetsController {

    private final GetUserInformations getUserInformations;
    private final CreateSnippet createSnippet;
    private final DeleteSnippet deleteSnippet;
    private final UpdateSnippet updateSnippet;
    private final GetSnippetsByProjectId getSnippetsByProjectId;

    private final SnippetInformationAdapter snippetInformationAdapter;
    SimpMessagingTemplate template;


    @PostMapping("")
    public ResponseEntity<String> createSnippet(
            @RequestBody final NewSnippetDTO snippet
    ) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        User currentUser = getUserInformations.execute(principal.getUsername());

        Integer projectId = createSnippet.execute(snippet, currentUser);

        wsSendAllSnippets(projectId);

        return ResponseEntity.created(URI.create("http://localhost:8080/api/snippets/project/" + projectId))
                .header("Access-Control-Expose-Headers", "Location")
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSnippet(
            @PathVariable("id") Integer id
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        User currentUser = getUserInformations.execute(principal.getUsername());
        Integer projectId = deleteSnippet.execute(id, currentUser);
        wsSendAllSnippets(projectId);

        return noContent().build();
    }

    @PutMapping("")
    public ResponseEntity<?> updateSnippet(
            @RequestBody final UpdateSnippetDTO snippet
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        String username = principal.getUsername();
        User currentUser = getUserInformations.execute(username);

        updateSnippet.execute(snippet, currentUser);

        wsSendAllSnippets(snippet.getProjectId());

        return ResponseEntity.ok().build();
    }

    @GetMapping("/project/{id}")
    public ResponseEntity<List<SnippetInformationDTO>> getAllSnipetByProjectId(
            @PathVariable("id") Integer projectId
    ) {
        List<Snippet> snippets = getSnippetsByProjectId.execute(projectId);
        List<SnippetInformationDTO> snippetsInformations = snippets.stream().map(snippetInformationAdapter::adapt).collect(Collectors.toList());
        return ResponseEntity.ok(snippetsInformations);
    }

    private void wsSendAllSnippets(Integer projectId) {
        List<Snippet> snippets = getSnippetsByProjectId.execute(projectId);
        List<SnippetInformationDTO> snippetsInformations = snippets.stream().map(snippetInformationAdapter::adapt).collect(Collectors.toList());
        template.convertAndSend("/listener/projects/" + projectId, snippetsInformations);
    }

}
