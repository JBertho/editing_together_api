package fr.esgi.pa.editing_together_api.app.projects.domain.exceptions;

public class ProjectNotFoundException extends Exception {
    public ProjectNotFoundException(String message) {
        super(message);
    }
}
