package fr.esgi.pa.editing_together_api.app.analyser.infrastructure.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GetRedundancy {

    private Map<String, String> linesMap;

    public GetRedundancy(Map<String, String> linesMap) {
        this.linesMap = linesMap;
    }

    public List<String> getAll () {
        List<String> result = new ArrayList<>();

        if (linesMap.isEmpty()){
            return result;
        }
        for (String sinit : linesMap.keySet()) {
            for (String send : linesMap.keySet()) {
                if (sinit != send){
                    if (linesMap.get(sinit).equals(linesMap.get(send))) {
                        result.add(sinit.concat(" and ").concat(send));
                    }
                }
            }
        }
        return result;
    }
}
