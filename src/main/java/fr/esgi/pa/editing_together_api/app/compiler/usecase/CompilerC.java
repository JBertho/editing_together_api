package fr.esgi.pa.editing_together_api.app.compiler.usecase;

import fr.esgi.pa.editing_together_api.app.compiler.infrastructure.DockerResponseStrategy.DockerErrorBuffer;
import fr.esgi.pa.editing_together_api.app.compiler.infrastructure.DockerResponseStrategy.DockerInputBuffer;
import fr.esgi.pa.editing_together_api.app.utils.FilesUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;

@AllArgsConstructor
@Service
@Slf4j
public class CompilerC implements CompilerStrategy  {

    private final FilesUtils filesUtils;

    @Override
    public void saveCode(String code, int userId) throws IOException {
        filesUtils.saveUploadedFiles(code,  "utility_c_" + userId  + "/main.c");
    }

    @Override
    public String setupDocker(DockerCompilation dockerCompilation, String imageName) throws IOException, InterruptedException {

        String[] dockerBuildImageCommand = new String[] {"docker", "image", "build", "utility_c", "-t", imageName};
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
}
