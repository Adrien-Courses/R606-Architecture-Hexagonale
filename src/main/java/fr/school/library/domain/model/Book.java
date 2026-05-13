package fr.school.library.domain.model;

public class Book {

    private final String isbn;
    private final String title;
    private final Long authorId;

    public Book(String isbn, String title, Long authorId) {
        this.isbn = isbn;
        this.title = title;
        this.authorId = authorId;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public Long getAuthorId() {
        return authorId;
    }
}
