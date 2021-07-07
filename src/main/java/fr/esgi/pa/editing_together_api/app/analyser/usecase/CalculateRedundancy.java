package fr.esgi.pa.editing_together_api.app.analyser.usecase;

import fr.esgi.pa.editing_together_api.app.analyser.infrastructure.Redundancy;
import fr.esgi.pa.editing_together_api.app.projects.domain.entity.Snippet;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalculateRedundancy {

    public String get (List<Snippet> snippets, String delimiter) {
        return Redundancy.calculateRedundancy(snippets, delimiter);
    }
}
