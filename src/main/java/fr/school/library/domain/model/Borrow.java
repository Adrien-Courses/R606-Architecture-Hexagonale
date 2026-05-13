package fr.school.library.domain.model;

import fr.school.library.domain.exception.BorrowAlreadyReturnedException;

import java.time.LocalDate;

public class Borrow {

    private final BorrowId id;
    private final Isbn bookIsbn;
    private final UserId userId;
    private final LocalDate borrowedAt;
    private LocalDate returnedAt;

    public Borrow(
            BorrowId id,
            Isbn bookIsbn,
            UserId userId,
            LocalDate borrowedAt,
            LocalDate returnedAt
    ) {
        this.id = id;
        this.bookIsbn = bookIsbn;
        this.userId = userId;
        this.borrowedAt = borrowedAt;
        this.returnedAt = returnedAt;
    }

    public static Borrow create(Isbn bookIsbn, UserId userId, LocalDate borrowedAt) {
        return new Borrow(null, bookIsbn, userId, borrowedAt, null);
    }

    public void registerReturn(LocalDate returnedAt) {
        if (this.returnedAt != null) {
            throw new BorrowAlreadyReturnedException(id);
        }
        this.returnedAt = returnedAt;
    }

    public boolean isActive() {
        return returnedAt == null;
    }

    public BorrowId getId() {
        return id;
    }

    public Isbn getBookIsbn() {
        return bookIsbn;
    }

    public UserId getUserId() {
        return userId;
    }

    public LocalDate getBorrowedAt() {
        return borrowedAt;
    }

    public LocalDate getReturnedAt() {
        return returnedAt;
    }
}
