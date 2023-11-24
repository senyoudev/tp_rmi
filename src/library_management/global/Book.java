package library_management.global;

import java.io.Serializable;
import java.util.Objects;

public class Book implements Serializable {
    String title;
    String authr;
    String ISBN;
    String publisher;
    Boolean disponible = true;
    String borrower;

    public Book() {

    }


    public Book(String title, String authr, String ISBN, String publisher, Boolean disponible, String borrower) {
        this.title = title;
        this.authr = authr;
        this.ISBN = ISBN;
        this.publisher = publisher;
        this.disponible = disponible;
        this.borrower = borrower;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthr() {
        return authr;
    }

    public void setAuthr(String authr) {
        this.authr = authr;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }

    public String getBorrower() {
        return borrower;
    }

    public void setBorrower(String borrower) {
        this.borrower = borrower;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(title, book.title) && Objects.equals(authr, book.authr) && Objects.equals(ISBN, book.ISBN) && Objects.equals(publisher, book.publisher) && Objects.equals(disponible, book.disponible) && Objects.equals(borrower, book.borrower);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, authr, ISBN, publisher, disponible, borrower);
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", authr='" + authr + '\'' +
                ", ISBN='" + ISBN + '\'' +
                ", publisher='" + publisher + '\'' +
                ", disponible=" + disponible +
                ", borrower='" + borrower + '\'' +
                '}';
    }


}
