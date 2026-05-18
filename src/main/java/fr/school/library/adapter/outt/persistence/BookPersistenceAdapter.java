package fr.school.library.adapter.outt.persistence;

import fr.school.library.adapter.outt.persistence.entity.AuthorEntity;
import fr.school.library.adapter.outt.persistence.entity.BookEntity;
import fr.school.library.adapter.outt.persistence.mapper.BookPersistenceMapper;
import fr.school.library.adapter.outt.persistence.repository.SpringDataAuthorRepository;
import fr.school.library.adapter.outt.persistence.repository.SpringDataBookRepository;
import fr.school.library.domain.exception.AuthorNotFoundException;
import fr.school.library.domain.model.Book;
import fr.school.library.domain.port.out.BookRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookPersistenceAdapter implements BookRepositoryPort {

    private final SpringDataBookRepository springDataBookRepository;
    private final SpringDataAuthorRepository springDataAuthorRepository;
    private final BookPersistenceMapper bookPersistenceMapper;

    public BookPersistenceAdapter(
            SpringDataBookRepository springDataBookRepository,
            SpringDataAuthorRepository springDataAuthorRepository,
            BookPersistenceMapper bookPersistenceMapper
    ) {
        this.springDataBookRepository = springDataBookRepository;
        this.springDataAuthorRepository = springDataAuthorRepository;
        this.bookPersistenceMapper = bookPersistenceMapper;
    }

    @Override
    public Book save(Book book) {
        AuthorEntity authorEntity = springDataAuthorRepository.findById(book.getAuthorId())
                .orElseThrow(() -> new AuthorNotFoundException(book.getAuthorId()));

        Optional<BookEntity> existingBookEntity = springDataBookRepository.findById(book.getIsbn());

        BookEntity bookEntity;
        if (existingBookEntity.isPresent()) {
            bookEntity = existingBookEntity.get();
            bookPersistenceMapper.updateEntity(bookEntity, book, authorEntity);
        } else {
            bookEntity = bookPersistenceMapper.toNewEntity(book, authorEntity);
        }

        BookEntity saved = springDataBookRepository.save(bookEntity);
        return bookPersistenceMapper.toDomain(saved);
    }

    @Override
    public Optional<Book> findByIsbn(String isbn) {
        return springDataBookRepository.findById(isbn)
                .map(bookPersistenceMapper::toDomain);
    }

    @Override
    public List<Book> findByAuthorId(Long authorId) {
        return springDataBookRepository.findByAuthor_Id(authorId).stream()
                .map(bookPersistenceMapper::toDomain)
                .toList();
    }
}
