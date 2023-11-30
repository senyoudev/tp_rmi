package library_management.client;

import library_management.global.Book;
import library_management.global.Usager;
import library_management.global.Library;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.stream.Collectors;

public class LibraryClient {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);

            Library libraryService = (Library) registry.lookup("LibraryService");

            // Add books to the library
            List<Book> booksToAdd = createBooks(5);
            booksToAdd.forEach(book -> {
                try {
                    libraryService.ajouterLivre(book);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            });

            // Retrieve books by author
            List<Book> booksByAuthor = libraryService.consulterLivresParAuteur("AuthorName");

            System.out.println("Books by AuthorName:");
            booksByAuthor.forEach(book -> System.out.println("Book: " + book.getTitle() + " by " + book.getAuthr()));

            // Delete a book
            booksByAuthor.stream().findFirst().ifPresent(bookToDelete -> {
                try {
                    libraryService.supprimerLivre(bookToDelete);
                    System.out.println("Deleted book: " + bookToDelete.getTitle());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            });

            // Retrieve updated list of books by author
            List<Book> updatedBooks = libraryService.consulterLivresParAuteur("AuthorName");
            System.out.println("Updated Books by AuthorName:");
            updatedBooks.forEach(book -> System.out.println("Book: " + book.getTitle() + " by " + book.getAuthr()));

            // Add a new user
            Usager newUser = new Usager("John Doe", "Student", "123 Main St", null, null);
            libraryService.ajouterUsager(newUser);
            System.out.println("User added: " + newUser);

            // Retrieve users by name
            List<Usager> usersByName = libraryService.consulterUsagersParNom("John Doe");
            System.out.println("Users by name John Doe: " + usersByName);



        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static List<Book> createBooks(int count) {
        return java.util.stream.IntStream.range(6, count + 1)
                .mapToObj(i -> {
                    Book book = new Book();
                    book.setISBN("ISBN" + i);
                    book.setTitle("BookTitle" + i);
                    book.setAuthr("AuthorName");
                    book.setPublisher("Publisher");
                    book.setDisponible(true);
                    book.setBorrower("Shelf" + i);
                    return book;
                })
                .collect(Collectors.toList());
    }
}
