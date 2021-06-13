package fr.esgi.pa.editing_together_api.app.compiler.usecase;

import java.io.IOException;

public interface CompilerStrategy {

    public void saveCode(String code, int userId) throws IOException;

    public String setupDocker(DockerCompilation dockerCompilation, String imageName) throws IOException, InterruptedException;

    public String runDocker(DockerCompilation dockerCompilation, String imageName) throws IOException, InterruptedException;
}
