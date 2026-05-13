package fr.school.library.domain.exception;

import fr.school.library.domain.model.BorrowId;

public class BorrowAlreadyReturnedException extends RuntimeException {

    public BorrowAlreadyReturnedException(BorrowId borrowId) {
        super("Borrow already returned: " + (borrowId != null ? borrowId.value() : "<new>"));
    }
}
