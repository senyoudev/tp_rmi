package library_management.global;

import Database.DatabaseConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LIbraryImpl implements Library {
    Connection connection;
    static int numOfBooks = 0;

    public LIbraryImpl() {
        try {
            connection = DatabaseConfig.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void ajouterLivre(Book livre) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO livres (id,isbn, titre, auteur, editeur, disponible, emplacement) VALUES (?,?, ?, ?, ?, ?, ?)")) {
            statement.setInt(1, ++numOfBooks);
            statement.setString(2, livre.getISBN());
            statement.setString(3, livre.getTitle());
            statement.setString(4, livre.getAuthr());
            statement.setString(5, livre.getPublisher());
            statement.setBoolean(6, livre.getDisponible());
            statement.setString(7, livre.getBorrower());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void supprimerLivre(Book livre) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM livres WHERE isbn = ?")) {
            statement.setString(1, livre.getISBN());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Book> consulterLivresParAuteur(String auteur) {
        List<Book> livres = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM livres WHERE auteur = ?")) {
            statement.setString(1, auteur);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Book livre = new Book();
                livre.setISBN(resultSet.getString("isbn"));
                livre.setTitle(resultSet.getString("titre"));
                livre.setAuthr(resultSet.getString("auteur"));
                livre.setPublisher(resultSet.getString("editeur"));
                livre.setDisponible(resultSet.getBoolean("disponible"));
                livre.setBorrower(resultSet.getString("emplacement"));

                livres.add(livre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return livres;
    }
}
