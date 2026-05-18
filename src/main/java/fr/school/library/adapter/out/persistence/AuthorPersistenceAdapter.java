package fr.school.library.adapter.out.persistence;

import fr.school.library.adapter.out.persistence.mapper.AuthorPersistenceMapper;
import fr.school.library.adapter.out.persistence.repository.SpringDataAuthorRepository;
import fr.school.library.domain.model.Author;
import fr.school.library.domain.port.out.AuthorRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthorPersistenceAdapter implements AuthorRepositoryPort {

    private final SpringDataAuthorRepository springDataAuthorRepository;
    private final AuthorPersistenceMapper authorPersistenceMapper;

    public AuthorPersistenceAdapter(
            SpringDataAuthorRepository springDataAuthorRepository,
            AuthorPersistenceMapper authorPersistenceMapper
    ) {
        this.springDataAuthorRepository = springDataAuthorRepository;
        this.authorPersistenceMapper = authorPersistenceMapper;
    }

    @Override
    public Author save(Author author) {
        return authorPersistenceMapper.toDomain(
                springDataAuthorRepository.save(authorPersistenceMapper.toEntity(author))
        );
    }

    @Override
    public Optional<Author> findById(Long authorId) {
        return springDataAuthorRepository.findById(authorId)
                .map(authorPersistenceMapper::toDomain);
    }
}
