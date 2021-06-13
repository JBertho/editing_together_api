package fr.esgi.pa.editing_together_api.app.compiler.infrastructure.utils;

import fr.esgi.pa.editing_together_api.app.projects.domain.entity.Snippet;

import java.util.List;

public class CompilerUtils {

    public static String getCodeFromSnippets(List<Snippet> snippets) {
        StringBuilder stringBuilder = new StringBuilder("");
        snippets.forEach( snippet ->
                stringBuilder.append(snippet.getContent())
        );

        return stringBuilder.toString();
    }
}
