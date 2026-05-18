package fr.school.library.application.port.out;

import fr.school.library.domain.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepositoryPort {

    Book save(Book book);

    Optional<Book> findByIsbn(String isbn);

    List<Book> findByAuthorId(Long authorId);
}
