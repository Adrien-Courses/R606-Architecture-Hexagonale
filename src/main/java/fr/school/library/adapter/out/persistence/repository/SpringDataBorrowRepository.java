package fr.school.library.adapter.out.persistence.repository;

import fr.school.library.adapter.out.persistence.entity.BorrowEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SpringDataBorrowRepository extends JpaRepository<BorrowEntity, Long> {

    boolean existsByBookIsbnAndReturnedAtIsNull(String bookIsbn);

    Optional<BorrowEntity> findFirstByBookIsbnAndReturnedAtIsNullOrderByBorrowedAtDesc(String bookIsbn);

    List<BorrowEntity> findByBookIsbnOrderByBorrowedAtDesc(String bookIsbn);
}
