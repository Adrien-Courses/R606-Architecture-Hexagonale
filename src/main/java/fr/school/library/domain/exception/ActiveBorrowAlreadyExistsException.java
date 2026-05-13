package fr.school.library.domain.exception;

public class ActiveBorrowAlreadyExistsException extends RuntimeException {

    public ActiveBorrowAlreadyExistsException(String isbn) {
        super("Active borrow already exists for book: " + isbn);
    }
}
