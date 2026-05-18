package fr.school.library.domain.port.out;

import fr.school.library.domain.model.Author;

import java.util.Optional;

public interface AuthorRepositoryPort {

    Author save(Author author);

    Optional<Author> findById(Long authorId);
}
