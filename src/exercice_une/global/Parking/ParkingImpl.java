package exercice_une.global.Parking;

import Database.DatabaseConfig;
import exercice_une.global.Parking.Parking;
import exercice_une.global.Reservation.Reservation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ParkingImpl implements Parking {
    private Connection connection;

    public ParkingImpl() {
        try {
            connection = DatabaseConfig.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Reservation> chercher(String nomClient) {
        List<Reservation> reservationsClient = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM reservations WHERE nom_client = ?");
            statement.setString(1, nomClient);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int idReservation = resultSet.getInt("id_reservation");
                int numeroPlace = resultSet.getInt("numero_place");
                String dateReservation = resultSet.getString("date_reservation");
                reservationsClient.add(new Reservation(idReservation, numeroPlace, nomClient, dateReservation));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservationsClient;
    }

    @Override
    public boolean reserver(int numeroPlace, String nomClient, String dateReservation) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO reservations (numero_place, nom_client, date_reservation) VALUES (?, ?, ?) RETURNING id_reservation");
            statement.setInt(1, numeroPlace);
            statement.setString(2, nomClient);

            // Convert the date string to a java.sql.Date object
            Date date = Date.valueOf(dateReservation);
            statement.setDate(3, date);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int idReservation = resultSet.getInt(1);
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean annuler(int idReservation) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM reservations WHERE id_reservation = ?");
            statement.setInt(1, idReservation);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Reservation> getReservations() {
        return chercher("");
    }
}
