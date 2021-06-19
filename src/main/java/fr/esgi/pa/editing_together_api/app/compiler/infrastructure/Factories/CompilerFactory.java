package fr.esgi.pa.editing_together_api.app.compiler.infrastructure.Factories;

import fr.esgi.pa.editing_together_api.app.compiler.domain.CompilerStrategy;
import fr.esgi.pa.editing_together_api.app.compiler.infrastructure.Strategies.CompilerC;
import fr.esgi.pa.editing_together_api.app.projects.domain.entity.Project;
import fr.esgi.pa.editing_together_api.config.exceptions.http.NotFoundException;


public class CompilerFactory {

    public static CompilerStrategy get(Project project) {
        switch (project.getLanguage()) {
            case C:
                return new CompilerC();
            default:
                throw new NotFoundException("NO IMPLEMENTED LANGUAGE FIND");
        }
    }
}
