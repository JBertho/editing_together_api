package fr.esgi.pa.editing_together_api.compilation;

import java.io.IOException;

public class DockerCompilation {

    String[] command;

    public void setCommand(String[] command) {
        this.command = command;
    }

    public int runCommand() throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder(command);
        Process process = processBuilder.start();
        int status = process.waitFor();

        return status;
    }

    public String output(DockerBuffer dockerBuffer) throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder(command);
        return dockerBuffer.outputBuffer(processBuilder);
    }
}
