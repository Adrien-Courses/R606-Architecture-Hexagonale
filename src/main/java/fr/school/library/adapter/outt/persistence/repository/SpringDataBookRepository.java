package fr.school.library.adapter.outt.persistence.repository;

import fr.school.library.adapter.outt.persistence.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringDataBookRepository extends JpaRepository<BookEntity, String> {

    List<BookEntity> findByAuthor_Id(Long authorId);
}
