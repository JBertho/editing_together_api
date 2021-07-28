package fr.esgi.pa.editing_together_api.app.analyser.infrastructure;

import fr.esgi.pa.editing_together_api.app.analyser.infrastructure.antlr4.GetSimilarity;
import fr.esgi.pa.editing_together_api.app.analyser.infrastructure.antlr4.SnippetToVisitor;
import fr.esgi.pa.editing_together_api.app.projects.domain.entity.LanguageType;
import fr.esgi.pa.editing_together_api.app.projects.domain.entity.Snippet;

import java.util.List;
import java.util.Optional;


public class Similarity {

    public static String calculateSimilarity (List<Snippet> snippets, LanguageType language) {
        StringBuilder sb = new StringBuilder();
        SnippetToVisitor stv = new SnippetToVisitor(snippets, language);
        GetSimilarity gs = new GetSimilarity(stv.getMapVisitor());
        List<String> ls = gs.getRedundancy();
        Optional<String> first = ls.stream().findFirst();
        return first.isPresent() ? first.get().toString() : "No similarity";
    }
}
