package fr.school.library.adapter.out.persistence.repository;

import fr.school.library.adapter.out.persistence.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringDataBookRepository extends JpaRepository<BookEntity, String> {

    List<BookEntity> findByAuthorId(Long authorId);
}
