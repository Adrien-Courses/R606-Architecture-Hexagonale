package fr.school.library.adapter.outt.persistence.mapper;

import fr.school.library.adapter.outt.persistence.entity.AuthorEntity;
import fr.school.library.adapter.outt.persistence.entity.BookEntity;
import fr.school.library.domain.model.Book;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class BookPersistenceMapper {

    public Book toDomain(BookEntity entity) {
        Long authorId = entity.getAuthor() != null ? entity.getAuthor().getId() : null;
        return new Book(entity.getIsbn(), entity.getTitle(), authorId);
    }

    public BookEntity toNewEntity(Book book, AuthorEntity authorEntity) {
        BookEntity entity = new BookEntity(book.getIsbn(), book.getTitle(), null);
        authorEntity.addBook(entity);
        return entity;
    }

    public void updateEntity(BookEntity entity, Book book, AuthorEntity authorEntity) {
        entity.setTitle(book.getTitle());

        Long currentAuthorId = entity.getAuthor() != null ? entity.getAuthor().getId() : null;
        if (!Objects.equals(currentAuthorId, authorEntity.getId())) {
            if (entity.getAuthor() != null) {
                entity.getAuthor().removeBook(entity);
            }
            authorEntity.addBook(entity);
        }
    }
}
