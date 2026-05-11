package fr.school.library.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "books")
public class Book {

    @Id
    private String isbn;

    private String title;

    private boolean borrowed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    protected Book() {
    }

    public Book(String isbn, String title) {
        this.isbn = isbn;
        this.title = title;
        this.borrowed = false;
    }

    public void borrow() {
        if (borrowed) {
            throw new IllegalStateException("Book already borrowed");
        }

        this.borrowed = true;
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

    public Author getAuthor() {
        return author;
    }

    void setAuthor(Author author) {
        this.author = author;
    }
}
