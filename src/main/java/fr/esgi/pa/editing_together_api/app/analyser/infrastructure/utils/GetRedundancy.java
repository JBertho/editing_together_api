package fr.esgi.pa.editing_together_api.app.analyser.infrastructure.utils;

import java.util.*;
import java.util.stream.Collectors;

public class GetRedundancy {

    private Map<CodeInterval, String> linesMap;

    public GetRedundancy(Map<CodeInterval, String> linesMap) {
        this.linesMap = linesMap;
    }

    private Set<CodeIntervalRedundancy> createList () {
        Set<CodeIntervalRedundancy> result = new HashSet<>();

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
        List<CodeIntervalRedundancy> result = new ArrayList<>(this.createList());
        if (result.isEmpty()){
            return Collections.emptyList();
        }
        boolean hasChange = true;
        while (hasChange) {
            hasChange = false;
            Set<CodeIntervalRedundancy> add = new HashSet<>();
            Set<CodeIntervalRedundancy> supp = new HashSet<>();
            for (int i = 0; i < result.size(); i++) {
                CodeIntervalRedundancy cir1 = result.get(i);
                for (int j = i + 1; j < result.size(); j++) {
                    CodeIntervalRedundancy cir2 = result.get(i);
                    if (cir2.getFirstRedundancy().getBegin().isBetween(cir1.getFirstRedundancy().getBegin(),cir1.getFirstRedundancy().getEnd())) {
                        hasChange = true;
                        CodeInterval newFirst = new CodeInterval(cir1.getFirstRedundancy().getBegin(), cir2.getFirstRedundancy().getEnd());
                        CodeInterval newSecond = new CodeInterval(cir1.getSecondRedundancy().getBegin(), cir2.getSecondRedundancy().getEnd());
                        CodeIntervalRedundancy cir = new CodeIntervalRedundancy(newFirst, newSecond);
                        add.add(cir);
                        supp.add(cir1);
                        supp.add(cir2);
                    }
                }
            }
            result.removeAll(supp);
            result.addAll(add);
        }
        return result;
    }
}
