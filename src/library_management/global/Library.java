package library_management.global;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Library extends Remote {
    void ajouterLivre(Book livre) throws RemoteException;
    void supprimerLivre(Book livre) throws RemoteException;
    List<Book> consulterLivresParAuteur(String auteur) throws RemoteException;
    Book consulterLivreParTitre(String titre) throws RemoteException;
    Book consulterLivreParISBN(String isbn) throws RemoteException;

    void ajouterUsager(Usager usager) throws RemoteException;
    void supprimerUsager(Usager usager) throws RemoteException;
    List<Usager> consulterUsagersParNom(String nom) throws RemoteException;
    Usager consulterUsagerParNom(String nom) throws RemoteException;

    void gererEmprunt(Usager usager, Book livre) throws RemoteException;
    void retournerLivre(Usager usager, Book livre) throws RemoteException;
    void demanderProlongation(Usager usager, Book livre) throws RemoteException;
    int calculerPenalite(Usager usager, Book livre) throws RemoteException;

    List<Book> consulterCatalogue(String critere) throws RemoteException;
}
