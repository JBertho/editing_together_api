package fr.esgi.pa.editing_together_api.app.analyser.infrastructure.antlr4;

import fr.esgi.pa.editing_together_api.app.projects.domain.entity.Snippet;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GetSimilarity {

    private Map<Snippet, String> mapOfVisitor;

    public GetSimilarity (Map<Snippet, String> mapOfVisitor) {
        this.mapOfVisitor = mapOfVisitor;

    }

    public List<String> getRedundancy () {
        List<String> result = new ArrayList<>();
        for (Snippet snippet1 : mapOfVisitor.keySet()) {
            for (Snippet snippet2: mapOfVisitor.keySet()) {
                if (snippet1 != snippet2 && snippet1.getId() < snippet2.getId()) {
                    if (mapOfVisitor.get(snippet1).equals(mapOfVisitor.get(snippet2))){
                        String s = String.format("Similarity between %s and %s", snippet1.getName(), snippet2.getName());
                        result.add(s);
                    }
                }
            }
        }
        return result;
    }

}
