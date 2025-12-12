package Ex3;

import org.example.project.Livre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class LivreAvecDateTest {

    private LocalDate dateValide;
    private LocalDate dateMin;
    private LocalDate dateMax;
    private LocalDate dateAvantMin;
    private LocalDate dateApresMax;

    @BeforeEach
    void setUp() {
        int currentYear = LocalDate.now().getYear();  // 2025
        dateValide = LocalDate.of(2000, 6, 15);
        dateMin = LocalDate.of(1000, 1, 1);
        dateMax = LocalDate.of(currentYear, 12, 31);
        dateAvantMin = LocalDate.of(999, 12, 31);
        dateApresMax = LocalDate.of(currentYear + 1, 1, 1);
    }

    // 3
    @Test
    void testDatePublicationValide() {
        assertDoesNotThrow(() -> new Livre("Titre", "Auteur", dateValide, "ISBN123"));
    }

    @Test
    void testDatePublicationInvalideTropAncienne() {
        assertThrows(IllegalArgumentException.class,
                () -> new Livre("Titre", "Auteur", dateAvantMin, "ISBN123"));
    }

    @Test
    void testDatePublicationInvalideFuture() {
        assertThrows(IllegalArgumentException.class,
                () -> new Livre("Titre", "Auteur", dateApresMax, "ISBN123"));
    }

    @Test
    void testDatePublicationNulle() {
        assertThrows(IllegalArgumentException.class,
                () -> new Livre("Titre", "Auteur", null, "ISBN123"));
    }

    // 4
    @Test
    void testCompatibiliteConstructeurAnneeInt() {
        Livre livre = new Livre("Titre", "Auteur", 2000, "ISBN123");
        assertEquals(2000, livre.getAnneePublication());
        assertEquals(LocalDate.of(2000, 1, 1), livre.getDatePublication());
    }

    @Test
    void testCompatibiliteSetterAnneeInt() {
        Livre livre = new Livre("Titre", "Auteur", dateValide, "ISBN123");
        livre.setAnneePublication(2010);
        assertEquals(2010, livre.getAnneePublication());
    }

    // 5
    @Test
    void testPerformanceCreationAvecLocalDate() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1_000_000; i++) {
            new Livre("Titre" + i, "Auteur" + i,
                    LocalDate.of(1000 + (i % 1026), 1, 1), "ISBN" + i);
        }
        long duration = System.currentTimeMillis() - start;
        assertTrue(duration < 1500, "Création trop lente : " + duration + " ms");
    }

    // 6 & 7
    @Test
    void testSetterDateLanceExceptionSiInvalide() {
        Livre livre = new Livre("Titre", "Auteur", dateValide, "ISBN123");
        assertThrows(IllegalArgumentException.class,
                () -> livre.setDatePublication(dateAvantMin));
        assertThrows(IllegalArgumentException.class,
                () -> livre.setDatePublication(dateApresMax));
    }

    // 8
    @Test
    void testCasLimiteDateMinAutorisee() {
        assertDoesNotThrow(() -> new Livre("Titre", "Auteur", dateMin, "ISBN123"));
    }

    @Test
    void testCasLimiteDateMaxAutorisee() {
        assertDoesNotThrow(() -> new Livre("Titre", "Auteur", dateMax, "ISBN123"));
    }

    @Test
    void testCasLimiteAn1000MoisDecembre() {
        LocalDate limite = LocalDate.of(1000, 12, 31);
        assertDoesNotThrow(() -> new Livre("Titre", "Auteur", limite, "ISBN123"));
    }

    @Test
    void testCasLimiteAnneeActuelleDecembre31() {
        LocalDate limite = LocalDate.of(LocalDate.now().getYear(), 12, 31);
        assertDoesNotThrow(() -> new Livre("Titre", "Auteur", limite, "ISBN123"));
    }
}
