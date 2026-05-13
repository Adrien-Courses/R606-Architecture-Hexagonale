package fr.school.library.application.service;

import fr.school.library.domain.exception.ActiveBorrowAlreadyExistsException;
import fr.school.library.domain.model.Book;
import fr.school.library.domain.model.Borrow;
import fr.school.library.domain.model.BorrowId;
import fr.school.library.domain.model.Isbn;
import fr.school.library.domain.model.UserId;
import fr.school.library.domain.port.out.BookRepositoryPort;
import fr.school.library.domain.port.out.BorrowRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BorrowApplicationServiceTest {

    @Mock
    private BookRepositoryPort bookRepositoryPort;

    @Mock
    private BorrowRepositoryPort borrowRepositoryPort;

    @InjectMocks
    private BorrowApplicationService borrowApplicationService;

    @Test
    void shouldCreateBorrowWhenBookExistsAndNoActiveBorrow() {
        Book book = new Book("9780061054884", "The Left Hand of Darkness", 3L);
        when(bookRepositoryPort.findByIsbn("9780061054884")).thenReturn(Optional.of(book));
        when(borrowRepositoryPort.existsActiveBorrowByBookIsbn(new Isbn("9780061054884")))
                .thenReturn(false);
        when(borrowRepositoryPort.save(any(Borrow.class))).thenAnswer(invocation -> {
            Borrow borrow = invocation.getArgument(0);
            return new Borrow(
                    new BorrowId(1L),
                    borrow.getBookIsbn(),
                    borrow.getUserId(),
                    borrow.getBorrowedAt(),
                    borrow.getReturnedAt()
            );
        });

        Borrow created = borrowApplicationService.createBorrow(
                "9780061054884",
                42L,
                LocalDate.of(2026, 5, 13)
        );

        assertEquals(1L, created.getId().value());
        assertEquals("9780061054884", created.getBookIsbn().value());
        assertEquals(42L, created.getUserId().value());
        assertEquals(LocalDate.of(2026, 5, 13), created.getBorrowedAt());
    }

    @Test
    void shouldRejectBorrowCreationWhenBookAlreadyActivelyBorrowed() {
        Book book = new Book("9780061054884", "The Left Hand of Darkness", 3L);
        when(bookRepositoryPort.findByIsbn("9780061054884")).thenReturn(Optional.of(book));
        when(borrowRepositoryPort.existsActiveBorrowByBookIsbn(new Isbn("9780061054884")))
                .thenReturn(true);

        assertThrows(
                ActiveBorrowAlreadyExistsException.class,
                () -> borrowApplicationService.createBorrow("9780061054884", 42L, LocalDate.now())
        );
    }

    @Test
    void shouldRegisterReturnOnActiveBorrow() {
        Book book = new Book("9780061054884", "The Left Hand of Darkness", 3L);
        Borrow activeBorrow = new Borrow(
                new BorrowId(3L),
                new Isbn("9780061054884"),
                new UserId(42L),
                LocalDate.of(2026, 5, 13),
                null
        );

        when(bookRepositoryPort.findByIsbn("9780061054884")).thenReturn(Optional.of(book));
        when(borrowRepositoryPort.findActiveByBookIsbn(new Isbn("9780061054884")))
                .thenReturn(Optional.of(activeBorrow));
        when(borrowRepositoryPort.save(any(Borrow.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Borrow returned = borrowApplicationService.registerReturn(
                "9780061054884",
                LocalDate.of(2026, 5, 20)
        );

        assertFalse(returned.isActive());
        assertEquals(LocalDate.of(2026, 5, 20), returned.getReturnedAt());
    }

    @Test
    void shouldReturnBorrowHistoryForBook() {
        Book book = new Book("9780061054884", "The Left Hand of Darkness", 3L);
        Borrow first = new Borrow(
                new BorrowId(1L),
                new Isbn("9780061054884"),
                new UserId(42L),
                LocalDate.of(2026, 5, 1),
                LocalDate.of(2026, 5, 4)
        );
        Borrow second = new Borrow(
                new BorrowId(2L),
                new Isbn("9780061054884"),
                new UserId(9L),
                LocalDate.of(2026, 5, 7),
                null
        );

        when(bookRepositoryPort.findByIsbn("9780061054884")).thenReturn(Optional.of(book));
        when(borrowRepositoryPort.findByBookIsbn(new Isbn("9780061054884")))
                .thenReturn(List.of(second, first));

        List<Borrow> history = borrowApplicationService.getBorrowHistory("9780061054884");

        assertEquals(2, history.size());
        verify(borrowRepositoryPort).findByBookIsbn(new Isbn("9780061054884"));
    }
}
