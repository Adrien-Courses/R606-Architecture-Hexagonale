package fr.school.library.domain.exception;

public class BookAlreadyBorrowedException extends RuntimeException {

    public BookAlreadyBorrowedException(String isbn) {
        super("Book already borrowed: " + isbn);
    }
}
