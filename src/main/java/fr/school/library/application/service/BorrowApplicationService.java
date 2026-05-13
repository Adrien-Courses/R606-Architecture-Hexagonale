package fr.school.library.application.service;

import fr.school.library.domain.exception.ActiveBorrowAlreadyExistsException;
import fr.school.library.domain.exception.ActiveBorrowNotFoundException;
import fr.school.library.domain.exception.BookNotFoundException;
import fr.school.library.domain.model.Borrow;
import fr.school.library.domain.model.Isbn;
import fr.school.library.domain.model.UserId;
import fr.school.library.domain.port.in.BorrowUseCase;
import fr.school.library.domain.port.out.BookRepositoryPort;
import fr.school.library.domain.port.out.BorrowRepositoryPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class BorrowApplicationService implements BorrowUseCase {

    private final BookRepositoryPort bookRepositoryPort;
    private final BorrowRepositoryPort borrowRepositoryPort;

    public BorrowApplicationService(
            BookRepositoryPort bookRepositoryPort,
            BorrowRepositoryPort borrowRepositoryPort
    ) {
        this.bookRepositoryPort = bookRepositoryPort;
        this.borrowRepositoryPort = borrowRepositoryPort;
    }

    @Override
    @Transactional
    public Borrow createBorrow(String isbn, Long userId, LocalDate borrowedAt) {
        ensureBookExists(isbn);
        LocalDate effectiveBorrowedAt = borrowedAt != null ? borrowedAt : LocalDate.now();

        Isbn bookIsbn = new Isbn(isbn);
        if (borrowRepositoryPort.existsActiveBorrowByBookIsbn(bookIsbn)) {
            throw new ActiveBorrowAlreadyExistsException(isbn);
        }

        Borrow borrow = Borrow.create(bookIsbn, new UserId(userId), effectiveBorrowedAt);
        return borrowRepositoryPort.save(borrow);
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

    private void ensureBookExists(String isbn) {
        if (bookRepositoryPort.findByIsbn(isbn).isEmpty()) {
            throw new BookNotFoundException(isbn);
        }
    }
}
