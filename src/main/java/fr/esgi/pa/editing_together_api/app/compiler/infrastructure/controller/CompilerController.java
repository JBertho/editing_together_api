package fr.esgi.pa.editing_together_api.app.compiler.infrastructure.controller;

import fr.esgi.pa.editing_together_api.app.analyser.usecase.CalculateRedundancy;
import fr.esgi.pa.editing_together_api.app.compiler.usecase.DockerCompilation;
import fr.esgi.pa.editing_together_api.app.compiler.usecase.CompilerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequestMapping("/compiler")
@CrossOrigin(origins = "http://localhost:3000")
@AllArgsConstructor
public class CompilerController {

    private final CompilerService compilerService;
    private final CalculateRedundancy calculateRedundancy;

    @PostMapping("c")
    public ResponseEntity<String> compileForC(@RequestBody String code) {
        DockerCompilation dockerCompilation = new DockerCompilation();
        String response = "";
        String redundancy = "";
        try {
            redundancy = calculateRedundancy.get(code);
            response = compilerService.compileForC(dockerCompilation, code);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(redundancy);
    }
}
