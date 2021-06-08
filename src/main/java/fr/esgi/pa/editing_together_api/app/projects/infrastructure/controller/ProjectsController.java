package fr.esgi.pa.editing_together_api.app.projects.infrastructure.controller;

import fr.esgi.pa.editing_together_api.app.auth.domain.entity.User;
import fr.esgi.pa.editing_together_api.app.auth.usecase.GetUserInformations;
import fr.esgi.pa.editing_together_api.app.projects.domain.entity.Project;
import fr.esgi.pa.editing_together_api.app.projects.domain.exceptions.ProjectNotCreatedException;
import fr.esgi.pa.editing_together_api.app.projects.domain.exceptions.ProjectNotFoundException;
import fr.esgi.pa.editing_together_api.app.projects.infrastructure.dto.NewProjectDTO;
import fr.esgi.pa.editing_together_api.app.projects.usecase.project.CreateProject;
import fr.esgi.pa.editing_together_api.app.projects.usecase.project.GetOneProjectById;
import fr.esgi.pa.editing_together_api.app.projects.usecase.project.GetUserProjects;
import fr.esgi.pa.editing_together_api.app.projects.usecase.project.JoinProject;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/projects")
@CrossOrigin(origins = "http://localhost:3000")
@AllArgsConstructor
public class ProjectsController {

    private final CreateProject createProject;
    private final GetUserInformations getUserInformations;
    private final GetOneProjectById getOneProjectById;
    private final GetUserProjects getUserProjects;
    private final JoinProject joinProject;


    @PostMapping("")
    public ResponseEntity<String> createProject(
            @RequestBody final NewProjectDTO project
            ) throws ProjectNotCreatedException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        User currentUser = getUserInformations.execute(principal.getUsername());

        Integer projectId = createProject.execute(project, currentUser);
        if (projectId == null ) {
            throw new ProjectNotCreatedException("Project could not be created");
        }
        return ResponseEntity.created(URI.create("http://localhost:8080/api/projects/" + projectId))
                .header("Access-Control-Expose-Headers", "Location")
                .build();
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<Project> getProjectById(
            @PathVariable int projectId
    ) throws ProjectNotFoundException {
        return  ResponseEntity.ok(getOneProjectById.execute(projectId));
    }

    @PostMapping("/join/{projectToken}")
    public ResponseEntity<?> joinProject(
            @PathVariable String projectToken
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        User currentUser = getUserInformations.execute(principal.getUsername());
        Integer projectId = joinProject.execute(projectToken, currentUser.getId());
        return ResponseEntity.created(URI.create("http://localhost:8080/api/projects/" + projectId))
                .header("Access-Control-Expose-Headers", "Location")
                .build();
    }

    @GetMapping("/me")
    public ResponseEntity<List<Project>> getAllUserProjects() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        User currentUser = getUserInformations.execute(principal.getUsername());

        return ResponseEntity.ok(getUserProjects.execute(currentUser));

    }
}
