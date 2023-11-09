package exercice_une.global.Parking;

import exercice_une.global.Reservation.Reservation;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Parking extends Remote {
    List<Reservation> chercher(String nomClient) throws RemoteException;
    boolean reserver(int numeroPlace, String nomClient, String dateReservation) throws RemoteException;
    boolean annuler(int idReservation) throws RemoteException;
}
