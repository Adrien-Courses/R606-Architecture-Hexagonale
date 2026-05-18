package fr.school.library.domain.port.out;

import fr.school.library.domain.model.Borrow;
import fr.school.library.domain.model.Isbn;

import java.util.List;
import java.util.Optional;

public interface BorrowRepositoryPort {

    Borrow save(Borrow borrow);

    boolean existsActiveBorrowByBookIsbn(Isbn isbn);

    Optional<Borrow> findActiveByBookIsbn(Isbn isbn);

    List<Borrow> findByBookIsbn(Isbn isbn);
}
