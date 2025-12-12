package org.example.project.Ex5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileManager {

    public String readFile(String path) throws IOException {
        if (path == null || path.trim().isEmpty()) {
            throw new IllegalArgumentException("Le chemin du fichier ne peut pas être nul ou vide.");
        }
        return Files.readString(Paths.get(path));
    }
}
