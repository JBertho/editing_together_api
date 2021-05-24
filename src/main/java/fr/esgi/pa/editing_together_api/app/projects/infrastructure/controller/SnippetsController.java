package fr.esgi.pa.editing_together_api.app.projects.infrastructure.controller;

import fr.esgi.pa.editing_together_api.app.auth.domain.entity.User;
import fr.esgi.pa.editing_together_api.app.auth.usecase.GetUserInformations;
import fr.esgi.pa.editing_together_api.app.projects.infrastructure.dto.NewSnippetDTO;
import fr.esgi.pa.editing_together_api.app.projects.usecase.snippet.CreateSnippet;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/snippets")
@CrossOrigin(origins = "http://localhost:3000")
@AllArgsConstructor
public class SnippetsController {

    private final GetUserInformations getUserInformations;
    private final CreateSnippet createSnippet;


    @PostMapping("")
    public ResponseEntity<String> createProject(
            @RequestBody final NewSnippetDTO snippet
    ) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        User currentUser = getUserInformations.execute(principal.getUsername());

        Integer projectId = createSnippet.execute(snippet, currentUser);
        return ResponseEntity.created(URI.create("http://localhost:8080/snippets/" + projectId)).build();
    }
}
