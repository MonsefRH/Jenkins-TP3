package org.example.project.Ex2;

import org.example.project.Livre;

import java.util.ArrayList;
import java.util.List;

public class Bibliotheque {
    private List<Livre> livres = new ArrayList<>();

    public void ajouterLivre(Livre livre) {
        if (livre == null) {
            throw new IllegalArgumentException("Le livre ne peut pas être nul.");
        }
        livres.add(livre);
    }

    public void retirerLivre(Livre livre) {
        livres.remove(livre);
    }

    public Livre rechercherParTitre(String titre) {
        for (Livre livre : livres) {
            if (livre.getTitre().equals(titre)) {
                return livre;
            }
        }
        return null;
    }

    public Livre rechercherParAuteur(String auteur) {
        for (Livre livre : livres) {
            if (livre.getAuteur().equals(auteur)) {
                return livre;
            }
        }
        return null;
    }

    public Livre rechercherParISBN(String isbn) {
        for (Livre livre : livres) {
            if (livre.getIsbn().equals(isbn)) {
                return livre;
            }
        }
        return null;
    }

    public List<Livre> getLivres() {
        return new ArrayList<>(livres);
    }
}
