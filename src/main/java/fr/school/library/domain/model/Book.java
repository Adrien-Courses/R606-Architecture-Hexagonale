package fr.school.library.domain.model;

import fr.school.library.domain.exception.BookAlreadyBorrowedException;

public class Book {

    private final String isbn;
    private final String title;
    private boolean borrowed;
    private final Long authorId;

    public Book(String isbn, String title, boolean borrowed, Long authorId) {
        this.isbn = isbn;
        this.title = title;
        this.borrowed = borrowed;
        this.authorId = authorId;
    }

    public Book(String isbn, String title, Long authorId) {
        this(isbn, title, false, authorId);
    }

    public void borrow() {
        if (borrowed) {
            throw new BookAlreadyBorrowedException(isbn);
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

    public Long getAuthorId() {
        return authorId;
    }
}
