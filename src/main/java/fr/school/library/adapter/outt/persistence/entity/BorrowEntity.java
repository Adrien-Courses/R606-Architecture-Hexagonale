package fr.school.library.adapter.outt.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "borrows")
public class BorrowEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "book_isbn", nullable = false)
    private String bookIsbn;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "borrowed_at", nullable = false)
    private LocalDate borrowedAt;

    @Column(name = "returned_at")
    private LocalDate returnedAt;

    protected BorrowEntity() {
    }

    public BorrowEntity(Long id, String bookIsbn, Long userId, LocalDate borrowedAt, LocalDate returnedAt) {
        this.id = id;
        this.bookIsbn = bookIsbn;
        this.userId = userId;
        this.borrowedAt = borrowedAt;
        this.returnedAt = returnedAt;
    }

    public Long getId() {
        return id;
    }

    public String getBookIsbn() {
        return bookIsbn;
    }

    public Long getUserId() {
        return userId;
    }

    public LocalDate getBorrowedAt() {
        return borrowedAt;
    }

    public LocalDate getReturnedAt() {
        return returnedAt;
    }
}
