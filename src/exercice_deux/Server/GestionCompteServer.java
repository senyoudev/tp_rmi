package exercice_deux.Server;

import exercice_deux.global.Compte.GestionCompte;
import exercice_deux.global.Compte.GestionCompteImpl;
import exercice_une.global.Parking.Parking;
import exercice_une.global.Parking.ParkingImpl;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class GestionCompteServer {
    public static void main(String[] args) {
        try {
            GestionCompte server = new GestionCompteImpl();
            GestionCompte stub = (GestionCompte) UnicastRemoteObject.exportObject(server, 0);

            Registry registry = LocateRegistry.createRegistry(1098);

            registry.bind("gestionCompte", stub);

            System.out.println("Serveur prêt...");
        } catch (Exception e) {
            System.err.println("Erreur du serveur : " + e.toString());
            e.printStackTrace();
        }
    }
}
