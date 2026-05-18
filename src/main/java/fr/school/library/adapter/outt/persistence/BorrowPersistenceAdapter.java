package fr.school.library.adapter.outt.persistence;

import fr.school.library.adapter.outt.persistence.mapper.BorrowPersistenceMapper;
import fr.school.library.adapter.outt.persistence.repository.SpringDataBorrowRepository;
import fr.school.library.domain.model.Borrow;
import fr.school.library.domain.model.Isbn;
import fr.school.library.domain.port.out.BorrowRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BorrowPersistenceAdapter implements BorrowRepositoryPort {

    private final SpringDataBorrowRepository springDataBorrowRepository;
    private final BorrowPersistenceMapper borrowPersistenceMapper;

    public BorrowPersistenceAdapter(
            SpringDataBorrowRepository springDataBorrowRepository,
            BorrowPersistenceMapper borrowPersistenceMapper
    ) {
        this.springDataBorrowRepository = springDataBorrowRepository;
        this.borrowPersistenceMapper = borrowPersistenceMapper;
    }

    @Override
    public Borrow save(Borrow borrow) {
        return borrowPersistenceMapper.toDomain(
                springDataBorrowRepository.save(borrowPersistenceMapper.toEntity(borrow))
        );
    }

    @Override
    public boolean existsActiveBorrowByBookIsbn(Isbn isbn) {
        return springDataBorrowRepository.existsByBookIsbnAndReturnedAtIsNull(isbn.value());
    }

    @Override
    public Optional<Borrow> findActiveByBookIsbn(Isbn isbn) {
        return springDataBorrowRepository.findFirstByBookIsbnAndReturnedAtIsNullOrderByBorrowedAtDesc(isbn.value())
                .map(borrowPersistenceMapper::toDomain);
    }

    @Override
    public List<Borrow> findByBookIsbn(Isbn isbn) {
        return springDataBorrowRepository.findByBookIsbnOrderByBorrowedAtDesc(isbn.value()).stream()
                .map(borrowPersistenceMapper::toDomain)
                .toList();
    }
}
