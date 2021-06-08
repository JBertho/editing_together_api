package fr.esgi.pa.editing_together_api.app.analyser.infrastructure.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Redundancy_map {
    private List<String> lines;
    final int SIZE_REDUNDANCY = 6;

    public Redundancy_map(List<String> lines) {
        this.lines = lines;
    }
    public Map<String, String> create_map (){
        Map<String, String> result = new HashMap<>();
        if (lines.size() <= SIZE_REDUNDANCY){
            return result;
        }
        for (int i = 0; i<lines.size() - SIZE_REDUNDANCY; i++){
            String key = "line ".concat(String.valueOf(i+1)).concat(" to ").concat(String.valueOf(i + SIZE_REDUNDANCY));
            String element = lines.subList(i, i + SIZE_REDUNDANCY).stream().collect(Collectors.joining("\n"));
            result.put(key, element);
        }
        return result;

    }
}
