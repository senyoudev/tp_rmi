package exercice_une.client;

import exercice_une.global.Parking.Parking;
import exercice_une.global.Reservation.Reservation;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

public class ParkingClient {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);

            Parking parking = (Parking) registry.lookup("ParkingServer");


            boolean reservationReussie = parking.reserver(1, "NomClient", "2024-05-22");            System.out.println("reservationReussie: "+reservationReussie);
            List<Reservation> reservations = parking.chercher("NomClient");
            System.out.println("Reservations: "+reservations);
            boolean annulationReussie = parking.annuler(1);
            System.out.println("annulationReussie: "+annulationReussie);
        } catch (Exception e) {
            System.err.println("Erreur du exercice_une.client : " + e.toString());
            e.printStackTrace();
        }
    }
}
