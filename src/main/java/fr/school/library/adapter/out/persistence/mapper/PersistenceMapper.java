package fr.school.library.adapter.out.persistence.mapper;

import fr.school.library.adapter.out.persistence.entity.AuthorEntity;
import fr.school.library.adapter.out.persistence.entity.BookEntity;
import fr.school.library.domain.model.Author;
import fr.school.library.domain.model.Book;
import org.springframework.stereotype.Component;

@Component
public class PersistenceMapper {

    public Author toAuthor(AuthorEntity authorEntity) {
        return new Author(authorEntity.getId(), authorEntity.getName());
    }

    public AuthorEntity toAuthorEntity(Author author) {
        return new AuthorEntity(author.getId(), author.getName());
    }

    public Book toBook(BookEntity bookEntity) {
        return new Book(
                bookEntity.getIsbn(),
                bookEntity.getTitle(),
                bookEntity.isBorrowed(),
                bookEntity.getAuthorId()
        );
    }

    public BookEntity toBookEntity(Book book) {
        return new BookEntity(
                book.getIsbn(),
                book.getTitle(),
                book.isBorrowed(),
                book.getAuthorId()
        );
    }
}
