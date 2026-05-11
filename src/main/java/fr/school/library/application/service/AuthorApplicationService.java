package fr.school.library.application.service;

import fr.school.library.application.dto.CreateAuthorCommand;
import fr.school.library.application.dto.CreateBookCommand;
import fr.school.library.application.mapper.LibraryDomainMapper;
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
    private final LibraryDomainMapper libraryDomainMapper;

    public AuthorApplicationService(
            AuthorRepositoryPort authorRepositoryPort,
            BookRepositoryPort bookRepositoryPort,
            LibraryDomainMapper libraryDomainMapper
    ) {
        this.authorRepositoryPort = authorRepositoryPort;
        this.bookRepositoryPort = bookRepositoryPort;
        this.libraryDomainMapper = libraryDomainMapper;
    }

    @Override
    @Transactional
    public Author createAuthor(String name) {
        Author author = libraryDomainMapper.toNewAuthor(new CreateAuthorCommand(name));
        return authorRepositoryPort.save(author);
    }

    @Override
    @Transactional
    public Book addBookToAuthor(Long authorId, String isbn, String title) {
        if (authorRepositoryPort.findById(authorId).isEmpty()) {
            throw new AuthorNotFoundException(authorId);
        }

        Book book = libraryDomainMapper.toNewBook(authorId, new CreateBookCommand(isbn, title));
        return bookRepositoryPort.save(book);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> findBooksByAuthor(Long authorId) {
        return bookRepositoryPort.findByAuthorId(authorId);
    }
}
