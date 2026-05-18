package fr.school.library.adapter.outt.persistence.mapper;

import fr.school.library.adapter.outt.persistence.entity.BorrowEntity;
import fr.school.library.domain.model.Borrow;
import fr.school.library.domain.model.BorrowId;
import fr.school.library.domain.model.Isbn;
import fr.school.library.domain.model.UserId;
import org.springframework.stereotype.Component;

@Component
public class BorrowPersistenceMapper {

    public Borrow toDomain(BorrowEntity entity) {
        return new Borrow(
                entity.getId() != null ? new BorrowId(entity.getId()) : null,
                new Isbn(entity.getBookIsbn()),
                new UserId(entity.getUserId()),
                entity.getBorrowedAt(),
                entity.getReturnedAt()
        );
    }

    public BorrowEntity toEntity(Borrow borrow) {
        Long id = borrow.getId() != null ? borrow.getId().value() : null;
        return new BorrowEntity(
                id,
                borrow.getBookIsbn().value(),
                borrow.getUserId().value(),
                borrow.getBorrowedAt(),
                borrow.getReturnedAt()
        );
    }
}
