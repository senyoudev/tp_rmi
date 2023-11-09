package exercice_deux.Client;

import exercice_deux.global.Compte.GestionCompte;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RemoteManager {
    public static GestionCompte getGestionCompte() throws Exception {
        Registry registry = LocateRegistry.getRegistry("localhost", 1098);
        return (GestionCompte) registry.lookup("gestionCompte");
    }
}

