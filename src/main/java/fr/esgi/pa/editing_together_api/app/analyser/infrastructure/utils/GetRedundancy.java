package fr.esgi.pa.editing_together_api.app.analyser.infrastructure.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GetRedundancy {

    private Map<CodeInterval, String> linesMap;

    public GetRedundancy(Map<CodeInterval, String> linesMap) {
        this.linesMap = linesMap;
    }

    private List<CodeIntervalRedundancy> createList () {
        List<CodeIntervalRedundancy> result = new ArrayList<>();

        if (linesMap.isEmpty()){
            return result;
        }
        for (CodeInterval sinit : linesMap.keySet()) {
            for (CodeInterval send : linesMap.keySet()) {
                if (sinit != send) {
                    if (linesMap.get(sinit).equals(linesMap.get(send)) && send.getBegin().isAfter(sinit.getBegin())){
                        CodeIntervalRedundancy cir = new CodeIntervalRedundancy(sinit, send);
                        result.add(cir);
                    }
                }
            }
        }
        return result;
    }

    public List<CodeIntervalRedundancy> getAll () {
        List<CodeIntervalRedundancy> codesIntervalRedundancy = this.createList();
        return codesIntervalRedundancy;
    }
}
