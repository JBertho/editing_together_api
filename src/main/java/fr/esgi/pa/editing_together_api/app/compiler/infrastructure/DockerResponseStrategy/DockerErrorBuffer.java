package fr.esgi.pa.editing_together_api.app.compiler.infrastructure.DockerResponseStrategy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class DockerErrorBuffer implements DockerBuffer {

    @Override
    public String outputBuffer(ProcessBuilder processBuilder) throws IOException {
        Process process = processBuilder.start();
        InputStream errorStream = process.getErrorStream();
        BufferedReader errorReader = new BufferedReader(new InputStreamReader(errorStream));
        return errorReader.lines().collect(Collectors.joining());
    }
}
