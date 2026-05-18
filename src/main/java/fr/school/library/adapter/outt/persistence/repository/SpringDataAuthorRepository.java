package fr.school.library.adapter.outt.persistence.repository;

import fr.school.library.adapter.outt.persistence.entity.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataAuthorRepository extends JpaRepository<AuthorEntity, Long> {
}
