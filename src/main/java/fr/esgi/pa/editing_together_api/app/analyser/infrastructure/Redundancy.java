package fr.esgi.pa.editing_together_api.app.analyser.infrastructure;

import fr.esgi.pa.editing_together_api.app.analyser.infrastructure.utils.GetRedundancy;
import fr.esgi.pa.editing_together_api.app.analyser.infrastructure.utils.LineParser;
import fr.esgi.pa.editing_together_api.app.analyser.infrastructure.utils.Redundancy_map;

import java.util.List;

public class Redundancy {

    public static String calculateRedundancy (String code) {
        LineParser lp = new LineParser(code);
        Redundancy_map redundancy_map = new Redundancy_map (lp.convert());
        GetRedundancy getRedundancy = new GetRedundancy(redundancy_map.create_map());
        List<String> redundancies = getRedundancy.getAll();
        StringBuilder sb = new StringBuilder();
        if (redundancies.size() == 0) {
            sb.append("no redundancy in the code");
        }
        else {
            sb.append("redundancy between: \n");
            for (String s: redundancies) {
                sb.append(s.concat("\n"));
            }
        }
        return sb.toString();
    }
}
