package exercice_deux.global.Compte;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GestionCompte extends Remote {

    void creerCompte(String id, double somme) throws RemoteException;

    void ajouter(String id, double somme) throws RemoteException;

    void retirer(String id, double somme) throws RemoteException;

    double consulterSolde(String id) throws RemoteException;

    double transfererSolde(String id_C, String id_D, double somme) throws RemoteException;
}