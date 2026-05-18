package fr.school.library.application.service;

import fr.school.library.domain.exception.AuthorNotFoundException;
import fr.school.library.domain.model.Author;
import fr.school.library.domain.model.Book;
import fr.school.library.domain.port.in.AuthorUseCase;
import fr.school.library.domain.port.out.AuthorRepositoryPort;
import fr.school.library.domain.port.out.BookRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AuthorApplicationService implements AuthorUseCase {

    private final AuthorRepositoryPort authorRepositoryPort;
    private final BookRepositoryPort bookRepositoryPort;

    public AuthorApplicationService(
            AuthorRepositoryPort authorRepositoryPort,
            BookRepositoryPort bookRepositoryPort
    ) {
        this.authorRepositoryPort = authorRepositoryPort;
        this.bookRepositoryPort = bookRepositoryPort;
    }

    @Override
    @Transactional
    public Author createAuthor(String name) {
        return authorRepositoryPort.save(new Author(name));
    }

    @Override
    @Transactional
    public Book addBookToAuthor(Long authorId, String isbn, String title) {
        if (authorRepositoryPort.findById(authorId).isEmpty()) {
            throw new AuthorNotFoundException(authorId);
        }

        return bookRepositoryPort.save(new Book(isbn, title, authorId));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> findBooksByAuthor(Long authorId) {
        return bookRepositoryPort.findByAuthorId(authorId);
    }
}
