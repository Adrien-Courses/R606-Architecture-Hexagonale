package fr.school.library.domain.exception;

public class AuthorNotFoundException extends RuntimeException {

    public AuthorNotFoundException(Long authorId) {
        super("Author not found: " + authorId);
    }
}
