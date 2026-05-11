package fr.school.library.domain.model;

public class Author {

    private final Long id;
    private final String name;

    public Author(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Author(String name) {
        this(null, name);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
