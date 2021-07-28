package fr.esgi.pa.editing_together_api.app.analyser.infrastructure;

import fr.esgi.pa.editing_together_api.app.analyser.infrastructure.utils.CodeIntervalRedundancy;
import fr.esgi.pa.editing_together_api.app.analyser.infrastructure.utils.GetRedundancy;
import fr.esgi.pa.editing_together_api.app.analyser.infrastructure.utils.LineParser;
import fr.esgi.pa.editing_together_api.app.analyser.infrastructure.utils.Redundancy_map;
import fr.esgi.pa.editing_together_api.app.projects.domain.entity.Snippet;

import java.util.List;
import java.util.Optional;

public class Redundancy {

    public static String calculateRedundancy (List<Snippet> snippets, String delimiter) {
        LineParser lp = new LineParser(snippets);
        Redundancy_map redundancy_map = new Redundancy_map (lp.convert(delimiter));
        GetRedundancy getRedundancy = new GetRedundancy(redundancy_map.create_map());
        List<CodeIntervalRedundancy> redundancies = getRedundancy.getAll();
        Optional<CodeIntervalRedundancy> first = redundancies.stream().findFirst();
        return first.isPresent() ? first.get().toString() : "No duplication";
    }
}
