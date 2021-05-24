package fr.esgi.pa.editing_together_api.app.projects.infrastructure.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/snippets")
@CrossOrigin(origins = "http://localhost:3000")
@AllArgsConstructor
public class SnippetsController {
}
