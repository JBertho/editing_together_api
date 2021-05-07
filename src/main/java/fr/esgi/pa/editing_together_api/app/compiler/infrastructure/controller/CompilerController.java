package fr.esgi.pa.editing_together_api.app.controller;

import fr.esgi.pa.editing_together_api.app.compilation.DockerCompilation;
import fr.esgi.pa.editing_together_api.app.service.CompilerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@AllArgsConstructor
@RestController
@RequestMapping("/compiler")
@CrossOrigin(origins = "http://localhost:3000")
public class CompilerController {

    private final CompilerService compilerService;

    @PostMapping("c")
    public ResponseEntity<String> compileForC(@RequestBody String code) {
        DockerCompilation dockerCompilation = new DockerCompilation();
        String response = "";
        try {
            response = compilerService.compileForC(dockerCompilation, code);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(response);
    }
}
