package fr.esgi.pa.editing_together_api.app.projects.infrastructure.controller;

import fr.esgi.pa.editing_together_api.app.auth.domain.entity.User;
import fr.esgi.pa.editing_together_api.app.auth.domain.exceptions.AlreadyCreatedException;
import fr.esgi.pa.editing_together_api.app.auth.usecase.GetUserInformations;
import fr.esgi.pa.editing_together_api.app.projects.domain.entity.Project;
import fr.esgi.pa.editing_together_api.app.projects.domain.exceptions.ProjectNotCreatedException;
import fr.esgi.pa.editing_together_api.app.projects.domain.exceptions.ProjectNotFoundException;
import fr.esgi.pa.editing_together_api.app.projects.infrastructure.dto.NewProjectDTO;
import fr.esgi.pa.editing_together_api.app.projects.usecase.CreateProject;
import fr.esgi.pa.editing_together_api.app.projects.usecase.GetOneProjectById;
import fr.esgi.pa.editing_together_api.app.projects.usecase.JoinProject;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.nio.file.attribute.UserPrincipalNotFoundException;

@RestController
@RequestMapping("/project")
@CrossOrigin(origins = "http://localhost:3000")
@AllArgsConstructor
public class ProjectsController {

    private final CreateProject createProject;
    private final GetUserInformations getUserInformations;
    private final GetOneProjectById getOneProjectById;
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
        return ResponseEntity.created(URI.create("http://localhost:8080/project/" + projectId)).build();
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<Project> getProjectById(
            @PathVariable int projectId
    ) throws ProjectNotFoundException {
        return  ResponseEntity.ok(getOneProjectById.execute(projectId));
    }

    @PostMapping("/join/{projectId}")
    public ResponseEntity<?> joinProject(
            @PathVariable int projectId
    ) throws ProjectNotFoundException, UserPrincipalNotFoundException, AlreadyCreatedException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        User currentUser = getUserInformations.execute(principal.getUsername());
        joinProject.execute(projectId, currentUser.getId());
        return ResponseEntity.created(URI.create("http://localhost:8080/project/" + projectId)).build();
    }
}
