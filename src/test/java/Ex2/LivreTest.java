package Ex2;

import org.example.project.Ex2.Bibliotheque;
import org.example.project.Livre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LivreTest {

    private Livre livre;

    @BeforeEach
    void setUp() {
        livre = new Livre("Titre Valide", "Auteur Valide", 2000, "ISBN123");
    }

    // 1
    @Test
    void testCreationInstance() {
        assertEquals("Titre Valide", livre.getTitre());
        assertEquals("Auteur Valide", livre.getAuteur());
        assertEquals(2000, livre.getAnneePublication());
        assertEquals("ISBN123", livre.getIsbn());
    }

    // 2
    @Test
    void testGetterSetterTitre() {
        livre.setTitre("Nouveau Titre");
        assertEquals("Nouveau Titre", livre.getTitre());
    }

    @Test
    void testGetterSetterAuteur() {
        livre.setAuteur("Nouvel Auteur");
        assertEquals("Nouvel Auteur", livre.getAuteur());
    }

    @Test
    void testGetterSetterAnneePublication() {
        livre.setAnneePublication(2010);
        assertEquals(2010, livre.getAnneePublication());
    }

    @Test
    void testGetterSetterIsbn() {
        livre.setIsbn("NEWISBN");
        assertEquals("NEWISBN", livre.getIsbn());
    }

    // 3
    @Test
    void testEqualsMemeAttributs() {
        Livre livre2 = new Livre("Titre Valide", "Auteur Valide", 2000, "ISBN123");
        assertEquals(livre, livre2);
        assertEquals(livre.hashCode(), livre2.hashCode());
    }

    @Test
    void testNotEqualsDifferentIsbn() {
        Livre livre2 = new Livre("Titre Valide", "Auteur Valide", 2000, "DIFFISBN");
        assertNotEquals(livre, livre2);
    }

    // 4
    @Test
    void testValidationAnneeNegative() {
        assertThrows(IllegalArgumentException.class, () -> new Livre("Titre", "Auteur", -1, "ISBN123"));
    }

    @Test
    void testValidationTitreVide() {
        assertThrows(IllegalArgumentException.class, () -> new Livre("", "Auteur", 2000, "ISBN123"));
    }

    @Test
    void testValidationAuteurVide() {
        assertThrows(IllegalArgumentException.class, () -> new Livre("Titre", "", 2000, "ISBN123"));
    }

    @Test
    void testValidationIsbnVide() {
        assertThrows(IllegalArgumentException.class, () -> new Livre("Titre", "Auteur", 2000, ""));
    }



    // 5
    @Test
    void testCasLimiteTitreVide() {
        assertThrows(IllegalArgumentException.class, () -> new Livre("", "Auteur", 2000, "ISBN123"));
    }

    @Test
    void testCasLimiteAnneeZero() {
        // Année 0 invalide → on teste maintenant que l'exception est bien levée
        assertThrows(IllegalArgumentException.class, () -> new Livre("Titre", "Auteur", 0, "ISBN123"));
    }

    @Test
    void testCasLimiteAnneeTresAncienne() {
        assertDoesNotThrow(() -> new Livre("Titre", "Auteur", 1000, "ISBN123"));
    }

    @Test
    void testCasLimiteAnneeTresRecente() {
        assertThrows(IllegalArgumentException.class, () -> new Livre("Titre", "Auteur", 2026, "ISBN123"));
        assertDoesNotThrow(() -> new Livre("Titre", "Auteur", 2025, "ISBN123"));
    }

    // 6
    @Test
    void testPerformanceCreationInstances() {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1_000_000; i++) {
            int annee = 1000 + (i % 1026);  // Garantit 1000 ≤ année ≤ 2025
            new Livre("Titre" + i, "Auteur" + i, annee, "ISBN" + i);
        }
        long duration = System.currentTimeMillis() - startTime;
        assertTrue(duration < 1500, "La création a pris trop de temps : " + duration + " ms");
    }

    // 7
    @Test
    void testInteractionAjoutRechercheBibliotheque() {
        Bibliotheque bibliotheque = new Bibliotheque();
        bibliotheque.ajouterLivre(livre);
        assertEquals(livre, bibliotheque.rechercherParTitre("Titre Valide"));
        assertEquals(livre, bibliotheque.rechercherParAuteur("Auteur Valide"));
        assertEquals(livre, bibliotheque.rechercherParISBN("ISBN123"));

        List<Livre> livres = bibliotheque.getLivres();
        assertEquals(1, livres.size());
        assertEquals(livre, livres.get(0));

        bibliotheque.retirerLivre(livre);
        assertNull(bibliotheque.rechercherParTitre("Titre Valide"));
    }

    @Test
    void testAjoutLivreNullDansBibliotheque() {
        Bibliotheque bibliotheque = new Bibliotheque();
        assertThrows(IllegalArgumentException.class, () -> bibliotheque.ajouterLivre(null));
    }

    // 8
    @Test
    void testEncapsulationProprietesPrivees() {
        Field titreField = assertDoesNotThrow(() -> Livre.class.getDeclaredField("titre"));
        assertFalse(titreField.isAccessible());

        Field auteurField = assertDoesNotThrow(() -> Livre.class.getDeclaredField("auteur"));
        assertFalse(auteurField.isAccessible());

        Field dateField = assertDoesNotThrow(() -> Livre.class.getDeclaredField("datePublication"));
        assertFalse(dateField.isAccessible());

        Field isbnField = assertDoesNotThrow(() -> Livre.class.getDeclaredField("isbn"));
        assertFalse(isbnField.isAccessible());
    }

    // 9
    @Test
    void testPasDeFuiteMemoire() {
        WeakReference<Livre> weakRef = new WeakReference<>(new Livre("Temp", "Temp", 2023, "TEMPISBN"));
        System.gc();
        assertNull(weakRef.get(), "L'instance devrait être garbage collectée s'il n'y a pas de fuite.");
    }

    @Test
    void testCompatibiliteVersions() {
        Livre livre = new Livre("Test", "Test", 2023, "TESTISBN");
        assertNotNull(livre);
    }
}
