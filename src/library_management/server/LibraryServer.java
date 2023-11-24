package library_management.server;

import exercice_une.global.Parking.Parking;
import exercice_une.global.Parking.ParkingImpl;
import library_management.global.LIbraryImpl;
import library_management.global.Library;

import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class LibraryServer {


    public static void main(String[] args) {
        try {
            Library libraryService = new LIbraryImpl();
            Library stub = (Library) UnicastRemoteObject.exportObject(libraryService, 1099);

            Registry registry = LocateRegistry.createRegistry(1099);

            registry.bind("LibraryService", stub);

            System.out.println("Serveur prêt...");
        } catch (Exception e) {
            System.err.println("Erreur du serveur : " + e.toString());
            e.printStackTrace();
        }
    }
}