package fr.esgi.pa.editing_together_api.app.analyser.usecase;


import fr.esgi.pa.editing_together_api.app.analyser.infrastructure.Similarity;
import fr.esgi.pa.editing_together_api.app.projects.domain.entity.LanguageType;
import fr.esgi.pa.editing_together_api.app.projects.domain.entity.Snippet;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalculateSimilarity {

    public String get (List<Snippet> snippets, LanguageType language) {
        return Similarity.calculateSimilarity(snippets, language);
    }
}
