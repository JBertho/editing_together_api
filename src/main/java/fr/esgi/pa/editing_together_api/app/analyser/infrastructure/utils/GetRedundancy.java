package fr.esgi.pa.editing_together_api.app.analyser.infrastructure.utils;

import java.util.*;

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

    public Set<CodeIntervalRedundancy> getAll () {
        Set<CodeIntervalRedundancy> result = this.createList();
        if (result.isEmpty()){
            return result;
        }
        boolean hasChange = true;
        while (hasChange == true) {
            hasChange = false;
            Set<CodeIntervalRedundancy> add = new HashSet<>();
            Set<CodeIntervalRedundancy> supp = new HashSet<>();
            for (CodeIntervalRedundancy cir1 : result) {
                for (CodeIntervalRedundancy cir2 : result) {
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
            System.out.println("nouvelle etape");
            for (CodeIntervalRedundancy cir : result) {
                System.out.println(cir.getFirstRedundancy());
                System.out.println(cir.getSecondRedundancy());
            }
            System.out.println("");
        }
        return result;
    }
}
