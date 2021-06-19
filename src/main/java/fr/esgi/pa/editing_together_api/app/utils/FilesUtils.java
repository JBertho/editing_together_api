package fr.esgi.pa.editing_together_api.app.utils;


import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FilesUtils {

    public static void saveUploadedFiles(String sourceCode,String directoryName, String fileName) throws IOException {
        if (sourceCode.isEmpty())
            return;
        boolean folderCreated = createFolderIfNotExist(directoryName);
        if (folderCreated) {
            Path filePath = Paths.get(directoryName + '/' + fileName);
            Files.write(filePath, sourceCode.getBytes());
        }

    }

    public static void copyFileInOtherFolder(String sourceFolder, String destinationFolder, String fileName) throws IOException {
        Path sourcePath = Paths.get(sourceFolder + '/' + fileName);
        Path destinationPath = Paths.get(destinationFolder + '/' + fileName);
        File fileDestination = new File(destinationPath.toString());
        if (fileDestination.exists()) {
            boolean isFileDeleted = fileDestination.delete();
            if (!isFileDeleted) {
                throw new FileSystemException("Error when creating file");
            }

        }
        Files.copy(sourcePath, destinationPath);

    }

    public static boolean createFolderIfNotExist(String folderToCreate) {
        boolean folderCreated = true;
        File folder = new File(folderToCreate);
        if (!folder.exists()) {
            folderCreated = folder.mkdir();
        }

        return folderCreated;
    }
}
