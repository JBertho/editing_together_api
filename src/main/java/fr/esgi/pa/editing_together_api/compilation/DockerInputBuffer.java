package fr.esgi.pa.editing_together_api.compilation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class DockerInputBuffer implements DockerBuffer {

    @Override
    public String outputBuffer(ProcessBuilder processBuilder) throws IOException {
        Process process = processBuilder.start();
        InputStream inputStream = process.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        return bufferedReader.lines().collect(Collectors.joining());
    }
}
