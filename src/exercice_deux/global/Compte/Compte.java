package exercice_deux.global.Compte;

public class Compte {
    private String id;
    private double somme;

    public Compte(String id, double somme) {
        this.id = id;
        this.somme = somme;
    }

    public String getId() {
        return id;
    }

    public double getSomme() {
        return somme;
    }

    public void ajouter(double somme) {
        this.somme += somme;
    }

    public void retirer(double somme) {
        this.somme -= somme;
    }

    @Override
    public String toString() {
        return "Compte{" +
                "id='" + id + '\'' +
                ", somme=" + somme +
                '}';
    }
}
