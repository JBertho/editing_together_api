package fr.esgi.pa.editing_together_api.app.analyser.usecase;

import fr.esgi.pa.editing_together_api.app.analyser.infrastructure.Redundancy;
import fr.esgi.pa.editing_together_api.app.analyser.infrastructure.utils.CodeIntervalRedundancy;
import fr.esgi.pa.editing_together_api.app.projects.domain.entity.Snippet;

import java.util.List;
import java.util.Set;

public class CalculateRedundancy {
    private List<Snippet> snippets;
    private String delimiter;

    public CalculateRedundancy(List<Snippet> snippets, String delimiter) {
        this.snippets = snippets;
        this.delimiter = delimiter;

    }

    public Set<CodeIntervalRedundancy> get () {
        return Redundancy.calculateRedundancy(snippets, delimiter);
    }
}
