package fr.esgi.pa.editing_together_api.app.compiler.infrastructure.Strategies;

import fr.esgi.pa.editing_together_api.app.compiler.domain.CompilerStrategy;
import fr.esgi.pa.editing_together_api.app.compiler.infrastructure.DockerResponseStrategy.DockerErrorBuffer;
import fr.esgi.pa.editing_together_api.app.compiler.infrastructure.DockerResponseStrategy.DockerInputBuffer;
import fr.esgi.pa.editing_together_api.app.compiler.infrastructure.utils.DockerCompilation;
import fr.esgi.pa.editing_together_api.app.utils.FilesUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.FileSystemException;

@AllArgsConstructor
@Service
@Slf4j
public class CompilerJava implements CompilerStrategy {

    private static final String COMPILER_DIRECTORY = "user_compiler";
    private static final String TEMPLATE_JAVA_DIRECTORY = "utility_java";
    private static final String USER_JAVA_DIRECTORY = "utility_java_";
    private static final String MAIN_FILE = "main.java";
    private static final String DOCKERFILE = "Dockerfile";
    private static final String SCRIPT_FILE = "entrypoint.sh";

    @Override
    public void saveCode(String code, Long userId) throws IOException {
        boolean isFolderCreated = FilesUtils.createFolderIfNotExist(COMPILER_DIRECTORY);
        if (!isFolderCreated) {
            throw new FileSystemException("COULD_NOT_CREATE_DIRECTORY");
        }
        FilesUtils.saveUploadedFiles(code,  COMPILER_DIRECTORY + "/" + USER_JAVA_DIRECTORY + userId, MAIN_FILE);
    }

    @Override
    public String setupDocker(DockerCompilation dockerCompilation, String imageName, Long id) throws IOException, InterruptedException {
        String[] dockerBuildImageCommand = new String[] {"docker", "image", "build", COMPILER_DIRECTORY + "/" + USER_JAVA_DIRECTORY + id, "-t", imageName};
        dockerCompilation.setCommand(dockerBuildImageCommand);

        int status = dockerCompilation.runCommand();
        if(status == 0)
            return "";
        else {
            log.info(dockerCompilation.output(new DockerErrorBuffer()));
            return dockerCompilation.output(new DockerErrorBuffer());
        }
    }

    @Override
    public String runDocker(DockerCompilation dockerCompilation, String imageName) throws IOException, InterruptedException {
        String[] dockerRunContainerCommand = new String[] {"docker", "run", "--rm", imageName};
        dockerCompilation.setCommand(dockerRunContainerCommand);

        int status = dockerCompilation.runCommand();

        if(status == 0) {
            return dockerCompilation.output(new DockerInputBuffer());
        }else {
            return dockerCompilation.output(new DockerErrorBuffer());
        }
    }

    @Override
    public void copyStaticFile(Long userId) throws IOException {
        FilesUtils.copyFileInOtherFolder(TEMPLATE_JAVA_DIRECTORY,  COMPILER_DIRECTORY + "/" + USER_JAVA_DIRECTORY + userId, DOCKERFILE);
        FilesUtils.copyFileInOtherFolder(TEMPLATE_JAVA_DIRECTORY,  COMPILER_DIRECTORY + "/" + USER_JAVA_DIRECTORY + userId, SCRIPT_FILE);
    }
}
