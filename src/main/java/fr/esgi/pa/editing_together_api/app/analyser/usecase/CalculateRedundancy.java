package fr.esgi.pa.editing_together_api.app.analyser.usecase;

import fr.esgi.pa.editing_together_api.app.analyser.infrastructure.Redundancy;
import fr.esgi.pa.editing_together_api.app.analyser.infrastructure.utils.CodeIntervalRedundancy;
import fr.esgi.pa.editing_together_api.app.projects.domain.entity.Snippet;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalculateRedundancy {

    public List<CodeIntervalRedundancy> get (List<Snippet> snippets) {
        return Redundancy.calculateRedundancy(snippets);
    }
}
