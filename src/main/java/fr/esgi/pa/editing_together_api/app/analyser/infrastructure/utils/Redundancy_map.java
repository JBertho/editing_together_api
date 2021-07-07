package fr.esgi.pa.editing_together_api.app.analyser.infrastructure.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Redundancy_map {
    private List<Line> lines;
    final int SIZE_REDUNDANCY = 6;

    public Redundancy_map(List<Line> lines) {
        this.lines = lines;
    }
    public Map<CodeInterval, String> create_map (){
        Map<CodeInterval, String> result = new HashMap<>();
        if (lines.size() <= SIZE_REDUNDANCY){
            return result;
        }
        for (int i = 0; i<lines.size() - SIZE_REDUNDANCY +1; i++){
            SnippetLine begin = new SnippetLine(lines.get(i).getSnippet_id(), lines.get(i).getSnippet_order(), lines.get(i).getNumber_line() );
            SnippetLine end = new SnippetLine(lines.get(i+SIZE_REDUNDANCY-1).getSnippet_id(), lines.get(i+SIZE_REDUNDANCY-1).getSnippet_order(), lines.get(i+SIZE_REDUNDANCY-1).getNumber_line());
            CodeInterval codeInterval = new CodeInterval(begin, end);
            String element = lines.subList(i, i + SIZE_REDUNDANCY).stream()
                    .map( n -> n.getContent() )
                    .collect( Collectors.joining( "\n" ) );
            result.put(codeInterval, element);
        }
        return result;

    }




}
