package exercice_deux.global.Compte;

import Database.DatabaseConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;
import java.sql.ResultSet;

public class GestionCompteImpl implements GestionCompte {
    List<Compte> comptes;

    Connection connection;

    public GestionCompteImpl() {
        try {
            connection = DatabaseConfig.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public GestionCompteImpl() {
//        comptes = new ArrayList<>();
//    }

    @Override
    public void creerCompte(String id, double somme) {
//        comptes.add(new Compte(id, somme));
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO accounts (id, balance) VALUES (?, ?)");
            statement.setString(1, id);
            statement.setDouble(2, somme);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void ajouter(String id, double somme) {
//        comptes.stream().filter(compte -> compte.getId().equals(id)).findFirst().get().ajouter(somme);
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE accounts SET balance = balance + ? WHERE id = ?");
            statement.setDouble(1, somme);
            statement.setString(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void retirer(String id, double somme) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE accounts SET balance = balance - ? WHERE id = ?");
            statement.setDouble(1, somme);
            statement.setString(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }    }

    @Override
    public double consulterSolde(String id) {
//        return comptes.stream().filter(compte -> compte.getId().equals(id)).findFirst().get().getSomme();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT balance FROM accounts WHERE id = ?");
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getDouble("balance");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public double transfererSolde(String id_C, String id_D, double somme) {
        retirer(id_C, somme);
        ajouter(id_D, somme);
        return somme;
    }




}
