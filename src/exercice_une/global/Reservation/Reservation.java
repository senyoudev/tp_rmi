package exercice_une.global.Reservation;

import java.io.Serializable;
import java.util.Objects;

public class Reservation implements Serializable {
    private int idReservation;
    private int numeroPlace;
    private String nomClient;
    private String dateReservation;

    public Reservation(int idReservation, int numeroPlace, String nomClient, String dateReservation) {
        this.idReservation = idReservation;
        this.numeroPlace = numeroPlace;
        this.nomClient = nomClient;
        this.dateReservation = dateReservation;
    }

    public int getIdReservation() {
        return idReservation;
    }

    public int getNumeroPlace() {
        return numeroPlace;
    }

    public String getNomClient() {
        return nomClient;
    }

    public String getDateReservation() {
        return dateReservation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return idReservation == that.idReservation && numeroPlace == that.numeroPlace && Objects.equals(nomClient, that.nomClient) && Objects.equals(dateReservation, that.dateReservation);
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "idReservation=" + idReservation +
                ", numeroPlace=" + numeroPlace +
                ", nomClient='" + nomClient + '\'' +
                ", dateReservation='" + dateReservation + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(idReservation, numeroPlace, nomClient, dateReservation);
    }
}

