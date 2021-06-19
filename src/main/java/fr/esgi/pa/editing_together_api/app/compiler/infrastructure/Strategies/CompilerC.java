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

@AllArgsConstructor
@Service
@Slf4j
public class CompilerC implements CompilerStrategy {


    @Override
    public void saveCode(String code, Long userId) throws IOException {
        FilesUtils.saveUploadedFiles(code,  "utility_c_" + userId, "main.c");
    }

    @Override
    public String setupDocker(DockerCompilation dockerCompilation, String imageName, Long id) throws IOException, InterruptedException {

        String[] dockerBuildImageCommand = new String[] {"docker", "image", "build", "utility_c_" + id, "-t", imageName};
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
        FilesUtils.copyFileInOtherFolder("utility_c",  "utility_c_" + userId, "Dockerfile");
        FilesUtils.copyFileInOtherFolder("utility_c",  "utility_c_" + userId, "entrypoint.sh");
    }
}
