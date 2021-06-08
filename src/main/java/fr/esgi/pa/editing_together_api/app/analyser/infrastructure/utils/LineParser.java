package fr.esgi.pa.editing_together_api.app.analyser.infrastructure.utils;

import java.util.Arrays;
import java.util.List;

public class LineParser {
    private String initString;

    public LineParser (String initString) {
        this.initString = initString;
    }

    public List<String> convert() {
        final String END_OF_LINE = "\n";
        String [] arrayOfString = initString.split(END_OF_LINE);
        return Arrays.asList(arrayOfString);
    }
}
