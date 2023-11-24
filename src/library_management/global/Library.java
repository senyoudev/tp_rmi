package library_management.global;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Library extends Remote {
    void ajouterLivre(Book livre) throws RemoteException;
    void supprimerLivre(Book livre) throws RemoteException;
    List<Book> consulterLivresParAuteur(String auteur) throws RemoteException;


//    void ajouterUsager(Usager usager);
//    void supprimerUsager(Usager usager);
//    List<Usager> consulterUsagersParCategorie(String categorie);
//    // Other user-related operations
//
//    void gererEmprunt(Usager usager, Book livre);
//    void retournerLivre(Usager usager, Book livre);
//    void demanderProlongation(Usager usager, Book livre);
}
