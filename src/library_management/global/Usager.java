package library_management.global;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Usager implements Serializable {
    String nom;
    String categorie;
    String adresse;
    List<Book> livresEmpruntes = new ArrayList<>();
    Date dateRetourPenalite;

    public Usager() {
        // Default constructor with no parameters
    }

    public Usager(String nom, String categorie, String adresse, List<Book> livresEmpruntes, Date dateRetourPenalite) {
        this.nom = nom;
        this.categorie = categorie;
        this.adresse = adresse;
        this.livresEmpruntes = livresEmpruntes;
        this.dateRetourPenalite = dateRetourPenalite;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public List<Book> getLivresEmpruntes() {
        return livresEmpruntes;
    }

    public void setLivresEmpruntes(List<Book> livresEmpruntes) {
        this.livresEmpruntes = livresEmpruntes;
    }

    public Date getDateRetourPenalite() {
        return dateRetourPenalite;
    }

    public void setDateRetourPenalite(Date dateRetourPenalite) {
        this.dateRetourPenalite = dateRetourPenalite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usager usager = (Usager) o;
        return Objects.equals(nom, usager.nom) && Objects.equals(categorie, usager.categorie) && Objects.equals(adresse, usager.adresse) && Objects.equals(livresEmpruntes, usager.livresEmpruntes) && Objects.equals(dateRetourPenalite, usager.dateRetourPenalite);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, categorie, adresse, livresEmpruntes, dateRetourPenalite);
    }

    @Override
    public String toString() {
        return "Usager{" +
                "nom='" + nom + '\'' +
                ", categorie='" + categorie + '\'' +
                ", adresse='" + adresse + '\'' +
                ", livresEmpruntes=" + livresEmpruntes +
                ", dateRetourPenalite=" + dateRetourPenalite +
                '}';
    }

    public void emprunterLivre(Book livre) {
        // Logique pour emprunter un livre
        if (livresEmpruntes.size() < 3) {
            livresEmpruntes.add(livre);
            livre.disponible = false;
        } else {
            // Gérer le cas où l'usager a déjà emprunté le maximum de livres
            System.out.println("Vous avez atteint le nombre maximum d'emprunts (3 livres).");
        }
    }

    public void retournerLivre(Book livre) {
        // Logique pour retourner un livre emprunté
        if (livresEmpruntes.remove(livre)) {
            livre.disponible = true;
        } else {
            // Gérer le cas où l'usager tente de retourner un livre qu'il n'a pas emprunté
            System.out.println("Vous n'avez pas emprunté ce livre.");
        }
    }



}
