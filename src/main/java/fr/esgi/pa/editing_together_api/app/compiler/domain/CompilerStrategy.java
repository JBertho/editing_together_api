package fr.esgi.pa.editing_together_api.app.compiler.domain;

import fr.esgi.pa.editing_together_api.app.compiler.infrastructure.utils.DockerCompilation;

import java.io.IOException;

public interface CompilerStrategy {

    void saveCode(String code, Long userId) throws IOException;

    String setupDocker(DockerCompilation dockerCompilation, String imageName, Long id) throws IOException, InterruptedException;

    String runDocker(DockerCompilation dockerCompilation, String imageName) throws IOException, InterruptedException;

    void copyStaticFile(Long userId) throws IOException;
}
