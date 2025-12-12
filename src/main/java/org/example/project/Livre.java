package org.example.project;

import java.util.Objects;
import java.time.LocalDate;

public class Livre {
    private String titre;
    private String auteur;
    private LocalDate datePublication;
    private String isbn;

    // Constructeur principal avec LocalDate (pour Exercice 3)
    public Livre(String titre, String auteur, LocalDate datePublication, String isbn) {
        validateCommonInputs(titre, auteur, isbn);
        validateDatePublication(datePublication);
        this.titre = titre;
        this.auteur = auteur;
        this.datePublication = datePublication;
        this.isbn = isbn;
    }

    public Livre(String titre, String auteur, int anneePublication, String isbn) {
        this(titre, auteur, LocalDate.of(anneePublication, 1, 1), isbn);
    }

    private void validateCommonInputs(String titre, String auteur, String isbn) {
        if (titre == null || titre.trim().isEmpty()) {
            throw new IllegalArgumentException("Le titre ne peut pas être nul ou vide.");
        }
        if (auteur == null || auteur.trim().isEmpty()) {
            throw new IllegalArgumentException("L'auteur ne peut pas être nul ou vide.");
        }
        if (isbn == null || isbn.trim().isEmpty()) {
            throw new IllegalArgumentException("L'ISBN ne peut pas être nul ou vide.");
        }
    }

    private void validateDatePublication(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("La date de publication ne peut pas être nulle.");
        }
        int year = date.getYear();
        int currentYear = LocalDate.now().getYear();  // 2025 actuellement
        if (year < 1000 || year > currentYear) {
            throw new IllegalArgumentException(
                    "La date de publication doit être comprise entre l'an 1000 et l'année actuelle (" + currentYear + ")."
            );
        }
    }

    // Getters
    public String getTitre() { return titre; }
    public String getAuteur() { return auteur; }

    public int getAnneePublication() {
        return datePublication.getYear();
    }

    public LocalDate getDatePublication() {
        return datePublication;
    }

    public String getIsbn() { return isbn; }

    // Setters avec validation
    public void setTitre(String titre) {
        if (titre == null || titre.trim().isEmpty()) {
            throw new IllegalArgumentException("Le titre ne peut pas être nul ou vide.");
        }
        this.titre = titre;
    }

    public void setAuteur(String auteur) {
        if (auteur == null || auteur.trim().isEmpty()) {
            throw new IllegalArgumentException("L'auteur ne peut pas être nul ou vide.");
        }
        this.auteur = auteur;
    }

    public void setDatePublication(LocalDate datePublication) {
        validateDatePublication(datePublication);
        this.datePublication = datePublication;
    }

    public void setAnneePublication(int anneePublication) {
        setDatePublication(LocalDate.of(anneePublication, 1, 1));
    }

    public void setIsbn(String isbn) {
        if (isbn == null || isbn.trim().isEmpty()) {
            throw new IllegalArgumentException("L'ISBN ne peut pas être nul ou vide.");
        }
        this.isbn = isbn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Livre livre = (Livre) o;
        return Objects.equals(isbn, livre.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }

    @Override
    public String toString() {
        return "Livre{" +
                "titre='" + titre + '\'' +
                ", auteur='" + auteur + '\'' +
                ", datePublication=" + datePublication +
                ", isbn='" + isbn + '\'' +
                '}';
    }
}