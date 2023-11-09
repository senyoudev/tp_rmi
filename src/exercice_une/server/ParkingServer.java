package exercice_une.server;

import exercice_une.global.Parking.ParkingImpl;
import exercice_une.global.Parking.Parking;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ParkingServer  {

    public static void main(String[] args) {
        try {
            Parking server = new ParkingImpl();
            Parking stub = (Parking) UnicastRemoteObject.exportObject(server, 0);

            Registry registry = LocateRegistry.createRegistry(1099);

            registry.bind("ParkingServer", stub);

            System.out.println("Serveur prêt...");
        } catch (Exception e) {
            System.err.println("Erreur du serveur : " + e.toString());
            e.printStackTrace();
        }
    }
}
