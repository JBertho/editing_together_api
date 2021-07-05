package fr.esgi.pa.editing_together_api.app.analyser.infrastructure.utils;

import fr.esgi.pa.editing_together_api.app.analyser.infrastructure.utils.Line;
import fr.esgi.pa.editing_together_api.app.projects.domain.entity.Snippet;

import java.util.ArrayList;
import java.util.List;

public class LineParser {
    private List<Snippet> snippets;

    public LineParser (List<Snippet> snippets) {
        this.snippets = snippets;
    }

    public List<Line> convert() {
        List<Line> result = new ArrayList<>();
        final String END_OF_LINE = "\n";
        int count = 1;
        for (Snippet snippet: snippets){
            String [] arrayOfString = snippet.getContent().split(END_OF_LINE);
            for (int i = 0; i<arrayOfString.length; i++){
                String s = arrayOfString[i];
                s=s.trim();
                if (!s.isEmpty()) {
                    Line line = new Line(s, snippet.getId(), i+1, count);
                    result.add(line);
                }
            }
            count ++;
        }
        return result;
    }
}