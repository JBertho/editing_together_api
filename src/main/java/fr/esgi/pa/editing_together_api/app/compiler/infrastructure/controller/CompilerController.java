package fr.esgi.pa.editing_together_api.app.compiler.infrastructure.controller;

import fr.esgi.pa.editing_together_api.app.auth.domain.entity.User;
import fr.esgi.pa.editing_together_api.app.auth.usecase.GetUserInformations;
import fr.esgi.pa.editing_together_api.app.compiler.infrastructure.dto.Request.CompileContent;
import fr.esgi.pa.editing_together_api.app.compiler.infrastructure.dto.Response.CompilationResponse;
import fr.esgi.pa.editing_together_api.app.compiler.infrastructure.utils.DockerCompilation;
import fr.esgi.pa.editing_together_api.app.compiler.usecase.CompileCode;
import fr.esgi.pa.editing_together_api.app.projects.domain.entity.Project;
import fr.esgi.pa.editing_together_api.app.projects.domain.entity.Snippet;
import fr.esgi.pa.editing_together_api.app.projects.usecase.project.GetOneProjectById;
import fr.esgi.pa.editing_together_api.app.projects.usecase.snippet.GetSnippets;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/api/compiler")
@CrossOrigin(origins = "http://localhost:3000")
@AllArgsConstructor
public class CompilerController {

    private final CompileCode compilerService;
    private final GetSnippets getSnippets;
    private final GetOneProjectById getOneProjectById;
    private final GetUserInformations getUserInformations;

    @PostMapping("c")
    public ResponseEntity<String> compileForC(@RequestBody String code) {
        DockerCompilation dockerCompilation = new DockerCompilation();
        String response = "";
        response = "Use /compiler with CompileContent as body";
        return ResponseEntity.ok(response);
    }

    @PostMapping("")
    public ResponseEntity<CompilationResponse> compile(@RequestBody CompileContent compileContent) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        User currentUser = getUserInformations.execute(principal.getUsername());

        DockerCompilation dockerCompilation = new DockerCompilation();

        List<Snippet> snippets = getSnippets.execute(compileContent.getSnippetsId());
        Project project = getOneProjectById.execute(compileContent.getProjectId());

        CompilationResponse compilationResponse = new CompilationResponse();

        try {
            compilationResponse.setRedundancy("TODO ADD REDUNDANCY");
            compilationResponse.setResponse(compilerService.compileForC(dockerCompilation, snippets, project, currentUser));
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(compilationResponse);
    }
}
