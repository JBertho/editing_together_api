package fr.esgi.pa.editing_together_api.compilation;

import java.io.IOException;

interface DockerBuffer {
    String outputBuffer(ProcessBuilder processBuilder) throws IOException;
}
