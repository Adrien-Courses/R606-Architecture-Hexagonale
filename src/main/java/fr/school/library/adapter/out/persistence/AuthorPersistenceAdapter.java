package fr.school.library.adapter.out.persistence;

import fr.school.library.adapter.out.persistence.mapper.PersistenceMapper;
import fr.school.library.adapter.out.persistence.repository.SpringDataAuthorRepository;
import fr.school.library.domain.model.Author;
import fr.school.library.domain.port.out.AuthorRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthorPersistenceAdapter implements AuthorRepositoryPort {

    private final SpringDataAuthorRepository springDataAuthorRepository;
    private final PersistenceMapper persistenceMapper;

    public AuthorPersistenceAdapter(
            SpringDataAuthorRepository springDataAuthorRepository,
            PersistenceMapper persistenceMapper
    ) {
        this.springDataAuthorRepository = springDataAuthorRepository;
        this.persistenceMapper = persistenceMapper;
    }

    @Override
    public Author save(Author author) {
        return persistenceMapper.toAuthor(
                springDataAuthorRepository.save(persistenceMapper.toAuthorEntity(author))
        );
    }

    @Override
    public Optional<Author> findById(Long authorId) {
        return springDataAuthorRepository.findById(authorId)
                .map(persistenceMapper::toAuthor);
    }
}
