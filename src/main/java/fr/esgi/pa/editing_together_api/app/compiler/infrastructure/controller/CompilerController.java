package fr.esgi.pa.editing_together_api.app.compiler.infrastructure.controller;

import fr.esgi.pa.editing_together_api.app.compiler.infrastructure.dto.Request.CompileContent;
import fr.esgi.pa.editing_together_api.app.compiler.infrastructure.dto.Response.CompilationResponse;
import fr.esgi.pa.editing_together_api.app.compiler.usecase.DockerCompilation;
import fr.esgi.pa.editing_together_api.app.compiler.usecase.CompilerService;
import fr.esgi.pa.editing_together_api.app.projects.domain.entity.Project;
import fr.esgi.pa.editing_together_api.app.projects.domain.entity.Snippet;
import fr.esgi.pa.editing_together_api.app.projects.usecase.project.GetOneProjectById;
import fr.esgi.pa.editing_together_api.app.projects.usecase.snippet.GetSnippets;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collections;
import java.util.List;


@RestController
@RequestMapping("/compiler")
@CrossOrigin(origins = "http://localhost:3000")
@AllArgsConstructor
public class CompilerController {

    private final CompilerService compilerService;
    private final GetSnippets getSnippets;
    private final GetOneProjectById getOneProjectById;

    @PostMapping("c")
    public ResponseEntity<String> compileForC(@RequestBody String code) {
        DockerCompilation dockerCompilation = new DockerCompilation();
        String response = "";
        response = "Use /compiler with CompileContent as body";
        return ResponseEntity.ok(response);
    }

    @PostMapping("")
    public ResponseEntity<CompilationResponse> compile(@RequestBody CompileContent compileContent) {
        DockerCompilation dockerCompilation = new DockerCompilation();

        List<Snippet> snippets = getSnippets.execute(compileContent.getSnippetsId());
        Project project = getOneProjectById.execute(compileContent.getProjectId());

        CompilationResponse compilationResponse = new CompilationResponse();

        try {
            compilationResponse.setRedundancy("TODO ADD REDUNDANCY");
            compilationResponse.setResponse(compilerService.compileForC(dockerCompilation, snippets, project));
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(compilationResponse);
    }
}
