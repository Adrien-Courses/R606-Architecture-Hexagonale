package fr.school.library.domain.model;

import fr.school.library.domain.exception.BorrowAlreadyReturnedException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BorrowTest {

    @Test
    void shouldCreateActiveBorrow() {
        Borrow borrow = Borrow.create(
                new Isbn("9780061054884"),
                new UserId(42L),
                LocalDate.of(2026, 5, 13)
        );

        assertTrue(borrow.isActive());
        assertNull(borrow.getReturnedAt());
        assertEquals("9780061054884", borrow.getBookIsbn().value());
        assertEquals(42L, borrow.getUserId().value());
    }

    @Test
    void shouldRegisterReturn() {
        Borrow borrow = Borrow.create(
                new Isbn("9780061054884"),
                new UserId(42L),
                LocalDate.of(2026, 5, 13)
        );

        borrow.registerReturn(LocalDate.of(2026, 5, 20));

        assertFalse(borrow.isActive());
        assertEquals(LocalDate.of(2026, 5, 20), borrow.getReturnedAt());
    }

    @Test
    void shouldRejectSecondReturnRegistration() {
        Borrow borrow = new Borrow(
                new BorrowId(12L),
                new Isbn("9780061054884"),
                new UserId(42L),
                LocalDate.of(2026, 5, 13),
                LocalDate.of(2026, 5, 20)
        );

        assertThrows(
                BorrowAlreadyReturnedException.class,
                () -> borrow.registerReturn(LocalDate.of(2026, 5, 22))
        );
    }
}
