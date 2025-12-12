package Ex5;

import org.example.project.Ex5.FileManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class FileManagerTest {

    private final FileManager fileManager = new FileManager();

    @TempDir
    Path tempDir;

    // Test : Lecture réussie d’un fichier existant
    @Test
    void testReadFileExistant() throws IOException {
        Path fichierTest = tempDir.resolve("test.txt");
        String contenuAttendu = "Bonjour, ceci est un test !\nLigne 2\nLigne 3";
        Files.writeString(fichierTest, contenuAttendu);

        String contenuLu = fileManager.readFile(fichierTest.toString());

        assertEquals(contenuAttendu, contenuLu, "Le contenu lu doit correspondre au contenu écrit.");
    }

    // Test : Gestion d’une exception pour un fichier inexistant
    @Test
    void testReadFileInexistant() {
        String cheminInexistant = tempDir.resolve("fichier_qui_n_existe_pas.txt").toString();

        // Vérifie que IOException est bien levée
        assertThrows(IOException.class, () -> fileManager.readFile(cheminInexistant),
                "IOException doit être levée quand le fichier n'existe pas.");
    }

    // Test avec chemin null ou vide (bonne pratique)
    @Test
    void testReadFileCheminInvalide() {
        assertThrows(IllegalArgumentException.class, () -> fileManager.readFile(null));
        assertThrows(IllegalArgumentException.class, () -> fileManager.readFile(""));
        assertThrows(IllegalArgumentException.class, () -> fileManager.readFile("   "));
    }
    @Test
    void testLectureFichierVolumineux() throws IOException {
        Path grosFichier = tempDir.resolve("gros.txt");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10_000_000; i++) {  // ~100 Mo
            sb.append("Ligne de test très longue...\n");
        }
        Files.writeString(grosFichier, sb.toString());

        long debut = System.currentTimeMillis();
        String contenu = fileManager.readFile(grosFichier.toString());
        long duree = System.currentTimeMillis() - debut;

        assertTrue(contenu.length() > 50_000_000);
        assertTrue(duree < 5000, "Lecture trop lente");
    }
}
