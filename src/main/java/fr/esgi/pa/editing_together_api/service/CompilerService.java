package fr.esgi.pa.editing_together_api.service;

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

    public String compileForC() throws IOException, InterruptedException {
        String sourceCode = "#include <stdio.h>\n" +
                "int main() {\n" +
                "   // printf() displays the string inside quotation\n" +
                "   printf(\"Hello, World!\")\n" +
                "   return 0;\n" +
                "}";
        filesUtils.saveUploadedFiles(sourceCode,  "utility_c/main.c");

        Logger logger = LogManager.getLogger(CompilerController.class);

        String imageName = "compile_in_c_" + new Date().getTime();
        String[] dockerCommand = new String[] {"docker", "image", "build", "utility_c", "-t", imageName};
        ProcessBuilder processbuilder = new ProcessBuilder(dockerCommand);
        Process process = processbuilder.start();

        int status = process.waitFor();
        if(status == 0)
            logger.info("Docker image has been built");
        else
            logger.info("Error while building image");
        logger.info("Running the container");
        String[] dockerCommand2 = new String[] {"docker", "run", "--rm", imageName};
        ProcessBuilder processbuilder2 = new ProcessBuilder(dockerCommand2);
        Process process2 = processbuilder2.start();
        status = process2.waitFor();
        InputStream errorStream = process2.getErrorStream();
        BufferedReader errorReader = new BufferedReader(new InputStreamReader(errorStream));
        logger.warn(errorReader.lines().collect(Collectors.joining()));

        logger.info("End of the execution of the container");
        logger.info("status is : " + status);

        process2.getInputStream();
        logger.info("Result of compilation");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process2.getInputStream()));
        return bufferedReader.lines().collect(Collectors.joining());
    }
}
