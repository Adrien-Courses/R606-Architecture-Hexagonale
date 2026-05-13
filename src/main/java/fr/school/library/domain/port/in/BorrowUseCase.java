package fr.school.library.domain.port.in;

import fr.school.library.domain.model.Borrow;

import java.time.LocalDate;
import java.util.List;

public interface BorrowUseCase {

    Borrow createBorrow(String isbn, Long userId, LocalDate borrowedAt);

    Borrow registerReturn(String isbn, LocalDate returnedAt);

    List<Borrow> getBorrowHistory(String isbn);
}
