package fr.esgi.pa.editing_together_api.controller;

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
        try {
            return compilerService.compileForC();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return "null";
    }
}
