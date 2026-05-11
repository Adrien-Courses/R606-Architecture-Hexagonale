package fr.school.library.domain.exception;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(String isbn) {
        super("Book not found: " + isbn);
    }
}
