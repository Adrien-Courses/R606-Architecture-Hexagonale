package fr.school.library.domain.exception;

public class ActiveBorrowNotFoundException extends RuntimeException {

    public ActiveBorrowNotFoundException(String isbn) {
        super("No active borrow found for book: " + isbn);
    }
}
