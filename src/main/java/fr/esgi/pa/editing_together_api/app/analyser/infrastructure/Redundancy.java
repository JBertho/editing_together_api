package fr.esgi.pa.editing_together_api.app.analyser.infrastructure;

import fr.esgi.pa.editing_together_api.app.analyser.infrastructure.utils.CodeIntervalRedundancy;
import fr.esgi.pa.editing_together_api.app.analyser.infrastructure.utils.GetRedundancy;
import fr.esgi.pa.editing_together_api.app.analyser.infrastructure.utils.LineParser;
import fr.esgi.pa.editing_together_api.app.analyser.infrastructure.utils.Redundancy_map;
import fr.esgi.pa.editing_together_api.app.projects.domain.entity.Snippet;


import java.util.List;

public class Redundancy {

    public static List<CodeIntervalRedundancy> calculateRedundancy (List<Snippet> snippets) {
        LineParser lp = new LineParser(snippets);
        Redundancy_map redundancy_map = new Redundancy_map (lp.convert());
        GetRedundancy getRedundancy = new GetRedundancy(redundancy_map.create_map());
        List<CodeIntervalRedundancy> redundancies = getRedundancy.getAll();
        return redundancies;
    }
}
