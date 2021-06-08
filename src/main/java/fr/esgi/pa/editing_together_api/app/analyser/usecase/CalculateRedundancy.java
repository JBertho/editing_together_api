package fr.esgi.pa.editing_together_api.app.analyser.usecase;

import fr.esgi.pa.editing_together_api.app.analyser.infrastructure.Redundancy;
import org.springframework.stereotype.Service;

@Service
public class CalculateRedundancy {

    public String get (String code) {
        return Redundancy.calculateRedundancy(code);
    }
}
