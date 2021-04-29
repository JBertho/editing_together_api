package fr.esgi.pa.editing_together_api.controller;

import fr.esgi.pa.editing_together_api.compilation.DockerCompilation;
import fr.esgi.pa.editing_together_api.service.CompilerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@AllArgsConstructor
@RestController
@RequestMapping("/compiler")
public class CompilerController {

    private final CompilerService compilerService;

    @PostMapping("c")
    public String compileForC() {
        DockerCompilation dockerCompilation = new DockerCompilation();
        try {
            return compilerService.compileForC(dockerCompilation);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return "null";
    }
}
