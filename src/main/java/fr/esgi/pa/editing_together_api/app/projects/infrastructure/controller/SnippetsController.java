package fr.esgi.pa.editing_together_api.app.projects.infrastructure.controller;

import fr.esgi.pa.editing_together_api.app.auth.domain.entity.User;
import fr.esgi.pa.editing_together_api.app.auth.usecase.GetUserInformations;
import fr.esgi.pa.editing_together_api.app.projects.infrastructure.dto.NewSnippetDTO;
import fr.esgi.pa.editing_together_api.app.projects.infrastructure.dto.UpdateSnippetDTO;
import fr.esgi.pa.editing_together_api.app.projects.usecase.snippet.CreateSnippet;
import fr.esgi.pa.editing_together_api.app.projects.usecase.snippet.DeleteSnippet;
import fr.esgi.pa.editing_together_api.app.projects.usecase.snippet.UpdateSnippet;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static org.springframework.http.ResponseEntity.noContent;

@RestController
@RequestMapping("/snippets")
@CrossOrigin(origins = "http://localhost:3000")
@AllArgsConstructor
public class SnippetsController {

    private final GetUserInformations getUserInformations;
    private final CreateSnippet createSnippet;
    private final DeleteSnippet deleteSnippet;
    private final UpdateSnippet updateSnippet;


    @PostMapping("")
    public ResponseEntity<String> createSnippet(
            @RequestBody final NewSnippetDTO snippet
    ) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        User currentUser = getUserInformations.execute(principal.getUsername());

        Integer projectId = createSnippet.execute(snippet, currentUser);
        return ResponseEntity.created(URI.create("http://localhost:8080/snippets/" + projectId)).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSnippet(
            @PathVariable("id") Integer id
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        User currentUser = getUserInformations.execute(principal.getUsername());

        deleteSnippet.execute(id, currentUser);
        return noContent().build();
    }

    @PutMapping("")
    public ResponseEntity<?> updateSnippet(
            @RequestBody final UpdateSnippetDTO snippet
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        User currentUser = getUserInformations.execute(principal.getUsername());

        updateSnippet.execute(snippet, currentUser);
        return ResponseEntity.ok().build();
    }
}
