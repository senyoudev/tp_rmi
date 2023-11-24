package library_management.client;

import library_management.global.Book;
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

            List<Book> booksToAdd = createBooks(5);
            booksToAdd.forEach(book -> {
                try {
                    libraryService.ajouterLivre(book);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            });

            List<Book> booksByAuthor = libraryService.consulterLivresParAuteur("AuthorName");

            System.out.println("Books by AuthorName:");
            booksByAuthor.forEach(book -> System.out.println("Book: " + book.getTitle() + " by " + book.getAuthr()));

            // Test supprimerLivre (deleteBook) - Delete the first book
            booksByAuthor.stream().findFirst().ifPresent(bookToDelete -> {
                try {
                    libraryService.supprimerLivre(bookToDelete);
                    System.out.println("Deleted book: " + bookToDelete.getTitle());
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            });

            List<Book> updatedBooks = libraryService.consulterLivresParAuteur("AuthorName");
            System.out.println("Updated Books by AuthorName:");
            updatedBooks.forEach(book -> System.out.println("Book: " + book.getTitle() + " by " + book.getAuthr()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static List<Book> createBooks(int count) {
        return java.util.stream.IntStream.range(1, count + 1)
                .mapToObj(i -> {
                    Book livre = new Book();
                    livre.setISBN("ISBN" + i);
                    livre.setTitle("BookTitle" + i);
                    livre.setAuthr("AuthorName");
                    livre.setPublisher("Publisher");
                    livre.setDisponible(true);
                    livre.setBorrower("Shelf" + i);
                    return livre;
                })
                .collect(Collectors.toList());
    }
}