package fr.school.library.application.service;

import fr.school.library.domain.exception.ActiveBorrowAlreadyExistsException;
import fr.school.library.domain.exception.ActiveBorrowNotFoundException;
import fr.school.library.domain.exception.BookNotFoundException;
import fr.school.library.domain.model.Borrow;
import fr.school.library.domain.model.Book;
import fr.school.library.domain.model.Isbn;
import fr.school.library.domain.model.UserId;
import fr.school.library.application.port.in.BorrowUseCase;
import fr.school.library.domain.port.out.BorrowNotificationPort;
import fr.school.library.domain.port.out.BookRepositoryPort;
import fr.school.library.domain.port.out.BorrowRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class BorrowApplicationService implements BorrowUseCase {
    private static final String LIBRARY_EMAIL = "bibliotheque@fake.fr";
    private static final int BORROW_DURATION_DAYS = 14;

    private final BookRepositoryPort bookRepositoryPort;
    private final BorrowRepositoryPort borrowRepositoryPort;
    private final BorrowNotificationPort borrowNotificationPort;

    public BorrowApplicationService(
            BookRepositoryPort bookRepositoryPort,
            BorrowRepositoryPort borrowRepositoryPort,
            BorrowNotificationPort borrowNotificationPort
    ) {
        this.bookRepositoryPort = bookRepositoryPort;
        this.borrowRepositoryPort = borrowRepositoryPort;
        this.borrowNotificationPort = borrowNotificationPort;
    }

    @Override
    @Transactional
    public Borrow createBorrow(String isbn, Long userId, LocalDate borrowedAt) {
        Book book = ensureBookExists(isbn);
        LocalDate effectiveBorrowedAt = borrowedAt != null ? borrowedAt : LocalDate.now();

        Isbn bookIsbn = new Isbn(isbn);
        if (borrowRepositoryPort.existsActiveBorrowByBookIsbn(bookIsbn)) {
            throw new ActiveBorrowAlreadyExistsException(isbn);
        }

        Borrow borrow = Borrow.create(bookIsbn, new UserId(userId), effectiveBorrowedAt);
        Borrow savedBorrow = borrowRepositoryPort.save(borrow);

        LocalDate dueDate = effectiveBorrowedAt.plusDays(BORROW_DURATION_DAYS);
        borrowNotificationPort.sendBorrowCreatedEmail(LIBRARY_EMAIL, book.getTitle(), dueDate);

        return savedBorrow;
    }

    @Override
    @Transactional
    public Borrow registerReturn(String isbn, LocalDate returnedAt) {
        ensureBookExists(isbn);
        LocalDate effectiveReturnedAt = returnedAt != null ? returnedAt : LocalDate.now();

        Borrow borrow = borrowRepositoryPort.findActiveByBookIsbn(new Isbn(isbn))
                .orElseThrow(() -> new ActiveBorrowNotFoundException(isbn));
        borrow.registerReturn(effectiveReturnedAt);

        return borrowRepositoryPort.save(borrow);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Borrow> getBorrowHistory(String isbn) {
        ensureBookExists(isbn);
        return borrowRepositoryPort.findByBookIsbn(new Isbn(isbn));
    }

    private Book ensureBookExists(String isbn) {
        return bookRepositoryPort.findByIsbn(isbn)
                .orElseThrow(() -> new BookNotFoundException(isbn));
    }
}
