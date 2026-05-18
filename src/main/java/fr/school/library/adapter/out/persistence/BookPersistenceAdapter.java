package fr.school.library.adapter.out.persistence;

import fr.school.library.adapter.out.persistence.mapper.PersistenceMapper;
import fr.school.library.adapter.out.persistence.repository.SpringDataBookRepository;
import fr.school.library.domain.model.Book;
import fr.school.library.application.port.out.BookRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookPersistenceAdapter implements BookRepositoryPort {

    private final SpringDataBookRepository springDataBookRepository;
    private final PersistenceMapper persistenceMapper;

    public BookPersistenceAdapter(
            SpringDataBookRepository springDataBookRepository,
            PersistenceMapper persistenceMapper
    ) {
        this.springDataBookRepository = springDataBookRepository;
        this.persistenceMapper = persistenceMapper;
    }

    @Override
    public Book save(Book book) {
        return persistenceMapper.toBook(
                springDataBookRepository.save(persistenceMapper.toBookEntity(book))
        );
    }

    @Override
    public Optional<Book> findByIsbn(String isbn) {
        return springDataBookRepository.findById(isbn)
                .map(persistenceMapper::toBook);
    }

    @Override
    public List<Book> findByAuthorId(Long authorId) {
        return springDataBookRepository.findByAuthorId(authorId).stream()
                .map(persistenceMapper::toBook)
                .toList();
    }
}
