package fr.school.library.adapter.outt.persistence.mapper;

import fr.school.library.adapter.outt.persistence.entity.AuthorEntity;
import fr.school.library.domain.model.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorPersistenceMapper {

    public Author toDomain(AuthorEntity entity) {
        return new Author(entity.getId(), entity.getName());
    }

    public AuthorEntity toEntity(Author author) {
        return new AuthorEntity(author.getId(), author.getName());
    }
}
