package Ex1;

import org.example.project.Ex1.Main;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainTest {

    @Test
    void testMainValidInput() {
        String input = "100\n100\n";
        System.setIn(new java.io.ByteArrayInputStream(input.getBytes()));

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Main.main(new String[]{});

        String output = outContent.toString();
        assertTrue(output.contains("100.0 MAD = 9.0 EUR"));
        assertTrue(output.contains("100.0 EUR = 1111.0 MAD"));
    }

    @Test
    void testMainNegativeInput() {
        String input = "-100\n-100\n";
        System.setIn(new java.io.ByteArrayInputStream(input.getBytes()));

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Main.main(new String[]{});

        String output = outContent.toString();
        assertTrue(output.contains("Le montant ne peut pas être négatif"));
    }
}
