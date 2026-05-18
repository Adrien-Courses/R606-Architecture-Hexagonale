package fr.school.library.adapter.out.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "books")
public class BookEntity {

    @Id
    @Column(nullable = false, updatable = false)
    private String isbn;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private boolean borrowed;

    @Column(name = "author_id", nullable = false)
    private Long authorId;

    protected BookEntity() {
    }

    public BookEntity(String isbn, String title, boolean borrowed, Long authorId) {
        this.isbn = isbn;
        this.title = title;
        this.borrowed = borrowed;
        this.authorId = authorId;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public boolean isBorrowed() {
        return borrowed;
    }

    public Long getAuthorId() {
        return authorId;
    }
}
