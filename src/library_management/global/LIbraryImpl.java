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

    @Override
    public Book consulterLivreParTitre(String titre) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM livres WHERE titre = ?")) {
            statement.setString(1, titre);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Book livre = new Book();
                livre.setISBN(resultSet.getString("isbn"));
                livre.setTitle(resultSet.getString("titre"));
                livre.setAuthr(resultSet.getString("auteur"));
                livre.setPublisher(resultSet.getString("editeur"));
                livre.setDisponible(resultSet.getBoolean("disponible"));
                livre.setBorrower(resultSet.getString("emplacement"));

                return livre;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Book not found
    }

    @Override
    public Book consulterLivreParISBN(String isbn) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM livres WHERE isbn = ?")) {
            statement.setString(1, isbn);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Book livre = new Book();
                livre.setISBN(resultSet.getString("isbn"));
                livre.setTitle(resultSet.getString("titre"));
                livre.setAuthr(resultSet.getString("auteur"));
                livre.setPublisher(resultSet.getString("editeur"));
                livre.setDisponible(resultSet.getBoolean("disponible"));
                livre.setBorrower(resultSet.getString("emplacement"));

                return livre;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Book not found
    }


    @Override
    public void ajouterUsager(Usager usager) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO usagers (nom, categorie, adresse) VALUES (?, ?, ?)")) {
            statement.setString(1, usager.getNom());
            statement.setString(2, usager.getCategorie());
            statement.setString(3, usager.getAdresse());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void supprimerUsager(Usager usager) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM usagers WHERE nom = ?")) {
            statement.setString(1, usager.getNom());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Usager> consulterUsagersParNom(String nom) {
        List<Usager> usagers = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM usagers WHERE nom = ?")) {
            statement.setString(1, nom);

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Usager usager = new Usager(
                        resultSet.getString("nom"),
                        resultSet.getString("categorie"),
                        resultSet.getString("adresse"),
                        new ArrayList<>(),
                        resultSet.getDate("dateRetourPenalite")
                );

                usagers.add(usager);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usagers;
    }


    @Override
    public Usager consulterUsagerParNom(String nom) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM usagers WHERE nom = ?")) {
            statement.setString(1, nom);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Usager usager = new Usager();
                usager.setNom(resultSet.getString("nom"));
                usager.setCategorie(resultSet.getString("categorie"));
                usager.setAdresse(resultSet.getString("adresse"));

                return usager;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // User not found
    }

    @Override
    public void gererEmprunt(Usager usager, Book livre) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO emprunts (id_usager, isbn, date_emprunt) VALUES (?, ?, CURRENT_DATE)")) {
            statement.setString(1, usager.getNom());
            statement.setString(2, livre.getISBN());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void retournerLivre(Usager usager, Book livre) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM emprunts WHERE id_usager = ? AND isbn = ?")) {
            statement.setString(1, usager.getNom());
            statement.setString(2, livre.getISBN());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void demanderProlongation(Usager usager, Book livre)  {
        try (PreparedStatement statement = connection.prepareStatement("UPDATE emprunts SET date_retour_prevue = DATE_ADD(date_retour_prevue, INTERVAL 15 DAY) WHERE id_usager = ? AND isbn = ?")) {
            statement.setString(1, usager.getNom());
            statement.setString(2, livre.getISBN());

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Prolongation accordée pour le livre : " + livre.getTitle());
            } else {
                System.out.println("La prolongation n'a pas pu être accordée. Vérifiez les conditions d'emprunt.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int calculerPenalite(Usager usager, Book livre) {
        try (PreparedStatement statement = connection.prepareStatement("SELECT DATEDIFF(NOW(), date_retour_prevue) AS jours_retard FROM emprunts WHERE id_usager = ? AND isbn = ?")) {
            statement.setString(1, usager.getNom());
            statement.setString(2, livre.getISBN());

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int joursRetard = resultSet.getInt("jours_retard");

                if (joursRetard > 0) {
                    // Calculate penalty based on your business logic
                    int penaliteParJour = 1; // Placeholder, replace with actual penalty per day
                    int penaliteTotale = joursRetard * penaliteParJour;

                    System.out.println("Pénalité calculée pour le livre " + livre.getTitle() + " : " + penaliteTotale + " jours de retard.");
                    return penaliteTotale;
                } else {
                    System.out.println("Aucune pénalité. Le livre " + livre.getTitle() + " n'est pas en retard.");
                    return 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0; // Default return value if calculation fails
    }

    @Override
    public List<Book> consulterCatalogue(String critere) {
        List<Book> livres = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM livres WHERE titre LIKE ? OR auteur LIKE ? OR editeur LIKE ? OR isbn LIKE ?")) {
            String searchPattern = "%" + critere + "%";
            for (int i = 1; i <= 4; i++) {
                statement.setString(i, searchPattern);
            }

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
