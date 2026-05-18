package fr.school.library.application.service;

import fr.school.library.domain.exception.BookNotFoundException;
import fr.school.library.domain.model.Book;
import fr.school.library.application.port.in.BookUseCase;
import fr.school.library.application.port.out.BookRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookApplicationService implements BookUseCase {

    private final BookRepositoryPort bookRepositoryPort;

    public BookApplicationService(BookRepositoryPort bookRepositoryPort) {
        this.bookRepositoryPort = bookRepositoryPort;
    }

    @Override
    @Transactional
    public Book borrowBook(String isbn) {
        Book book = bookRepositoryPort.findByIsbn(isbn)
                .orElseThrow(() -> new BookNotFoundException(isbn));

        book.borrow();

        return bookRepositoryPort.save(book);
    }
}
