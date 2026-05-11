package fr.school.library.adapter.in.web.mapper;

import fr.school.library.adapter.in.web.dto.AuthorResponseDTO;
import fr.school.library.adapter.in.web.dto.BookResponseDTO;
import fr.school.library.domain.model.Author;
import fr.school.library.domain.model.Book;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WebDtoMapper {

    public AuthorResponseDTO toAuthorResponse(Author author) {
        return new AuthorResponseDTO(author.getId(), author.getName(), List.of());
    }

    public BookResponseDTO toBookResponse(Book book) {
        return new BookResponseDTO(book.getIsbn(), book.getTitle(), book.isBorrowed(), book.getAuthorId());
    }
}
