package fr.esgi.pa.editing_together_api.app.analyser.usecase;

import fr.esgi.pa.editing_together_api.app.analyser.infrastructure.Redundancy;

public class CalculateRedundancy {
    private String code;

    public CalculateRedundancy(String code) {
        this.code = code;
    }

    public String get () {
        return Redundancy.calculateRedundancy(code);
    }
}
