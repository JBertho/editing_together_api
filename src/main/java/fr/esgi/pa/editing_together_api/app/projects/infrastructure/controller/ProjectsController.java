package fr.esgi.pa.editing_together_api.app.projects.infrastructure.controller;

import fr.esgi.pa.editing_together_api.app.auth.domain.entity.User;
import fr.esgi.pa.editing_together_api.app.auth.usecase.GetUserInformations;
import fr.esgi.pa.editing_together_api.app.projects.domain.entity.Project;
import fr.esgi.pa.editing_together_api.app.projects.domain.exceptions.ProjectNotCreatedException;
import fr.esgi.pa.editing_together_api.app.projects.infrastructure.dto.NewProjectDTO;
import fr.esgi.pa.editing_together_api.app.projects.usecase.CreateProject;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/project")
@CrossOrigin(origins = "http://localhost:3000")
@AllArgsConstructor
public class ProjectsController {

    private final CreateProject createProject;
    private final GetUserInformations getUserInformations;


    @PostMapping("")
    public ResponseEntity<String> createProject(
            @RequestBody final NewProjectDTO project
            ) throws ProjectNotCreatedException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        User currentUser = getUserInformations.execute(principal.getUsername());
        System.out.println(currentUser);

        Integer projectId = createProject.execute(project);
        if (projectId == null ) {
            throw new ProjectNotCreatedException("Project could not be created");
        }
        return ResponseEntity.created(URI.create("http://localhost:8080/project/" + projectId)).build();
    }
}
