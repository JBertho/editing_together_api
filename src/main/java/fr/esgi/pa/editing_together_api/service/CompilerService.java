package fr.esgi.pa.editing_together_api.service;

import fr.esgi.pa.editing_together_api.compilation.DockerCompilation;
import fr.esgi.pa.editing_together_api.compilation.DockerErrorBuffer;
import fr.esgi.pa.editing_together_api.compilation.DockerInputBuffer;
import fr.esgi.pa.editing_together_api.controller.CompilerController;
import fr.esgi.pa.editing_together_api.utils.FilesUtils;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CompilerService {

    private final FilesUtils filesUtils;

    public String compileForC(DockerCompilation dockerCompilation) throws IOException, InterruptedException {

        String sourceCode = "#include <stdio.h>\n" +
                "int main() {\n" +
                "   // printf() displays the string inside quotation\n" +
                "   printf(\"Hello, World!\");\n" +
                "   return 0;\n" +
                "}";
        String output;

        filesUtils.saveUploadedFiles(sourceCode,  "utility_c/main.c");

        Logger logger = LogManager.getLogger(CompilerController.class);

        String imageName = "compile_in_c_" + new Date().getTime();
        String[] dockerBuildImageCommand = new String[] {"docker", "image", "build", "utility_c", "-t", imageName};
        dockerCompilation.setCommand(dockerBuildImageCommand);

        int status = dockerCompilation.runCommand();
        if(status == 0)
            logger.info("Docker image has been built");
        else
            logger.info("Error while building image");

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
