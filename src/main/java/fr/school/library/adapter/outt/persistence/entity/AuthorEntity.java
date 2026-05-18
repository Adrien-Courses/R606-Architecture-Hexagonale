package fr.school.library.adapter.outt.persistence.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "authors")
public class AuthorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookEntity> books = new ArrayList<>();

    protected AuthorEntity() {
    }

    public AuthorEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addBook(BookEntity book) {
        books.add(book);
        book.setAuthor(this);
    }

    public void removeBook(BookEntity book) {
        books.remove(book);
        book.setAuthor(null);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<BookEntity> getBooks() {
        return books;
    }
}
