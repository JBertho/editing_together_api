package fr.esgi.pa.editing_together_api.app.compiler.infrastructure.DockerResponseStrategy;

import java.io.IOException;

public interface DockerBuffer {
    String outputBuffer(ProcessBuilder processBuilder) throws IOException;
}
