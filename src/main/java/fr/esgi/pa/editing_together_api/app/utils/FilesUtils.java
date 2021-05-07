package fr.esgi.pa.editing_together_api.app.utils;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class FilesUtils {

    public void saveUploadedFiles(String sourceCode, String name) throws IOException {
        if (sourceCode.isEmpty())
            return;
        Path path = Paths.get(name);
        Files.write(path, sourceCode.getBytes());
    }
}
