package fr.school.library.adapter.out.persistence.repository;

import fr.school.library.adapter.out.persistence.entity.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataAuthorRepository extends JpaRepository<AuthorEntity, Long> {
}
