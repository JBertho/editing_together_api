package fr.esgi.pa.editing_together_api.app.compiler.usecase;

import fr.esgi.pa.editing_together_api.app.compiler.infrastructure.DockerResponseStrategy.DockerErrorBuffer;
import fr.esgi.pa.editing_together_api.app.compiler.infrastructure.DockerResponseStrategy.DockerInputBuffer;
import fr.esgi.pa.editing_together_api.app.compiler.infrastructure.controller.CompilerController;
import fr.esgi.pa.editing_together_api.app.compiler.infrastructure.utils.CompilerUtils;
import fr.esgi.pa.editing_together_api.app.projects.domain.entity.Project;
import fr.esgi.pa.editing_together_api.app.projects.domain.entity.Snippet;
import fr.esgi.pa.editing_together_api.app.utils.FilesUtils;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Service
public class CompilerService {

    private final FilesUtils filesUtils;

    public String compileForC(DockerCompilation dockerCompilation, List<Snippet> snippetsCode, Project project) throws IOException, InterruptedException {

        String output;
        String sourceCode = CompilerUtils.getCodeFromSnippets(snippetsCode);

        filesUtils.saveUploadedFiles(sourceCode,  "utility_c/main.c");

        Logger logger = LogManager.getLogger(CompilerController.class);

        String imageName = "compile_in_c_" + new Date().getTime();
        String[] dockerBuildImageCommand = new String[] {"docker", "image", "build", "utility_c", "-t", imageName};
        dockerCompilation.setCommand(dockerBuildImageCommand);

        int status = dockerCompilation.runCommand();
        if(status == 0)
            logger.info("Docker image has been built");
        else {
            logger.info("Error while building image");
            logger.info(dockerCompilation.output(new DockerErrorBuffer()));
            return dockerCompilation.output(new DockerErrorBuffer());
        }

        logger.info("Running the container");
        String[] dockerRunContainerCommand = new String[] {"docker", "run", "--rm", imageName};
        dockerCompilation.setCommand(dockerRunContainerCommand);

        status = dockerCompilation.runCommand();

        logger.info("End of the execution of the container");
        logger.info("status is : " + status);
        logger.info("Result of compilation");
        if(status == 0) {
            output = dockerCompilation.output(new DockerInputBuffer());
            logger.info(output);
        }else {
            output = dockerCompilation.output(new DockerErrorBuffer());
            logger.warn(output);
        }

        return output;
    }
}
