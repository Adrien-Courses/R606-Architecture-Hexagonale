package fr.school.library.adapter.in.web;

import fr.school.library.adapter.in.web.dto.BookResponseDTO;
import fr.school.library.adapter.in.web.mapper.WebDtoMapper;
import fr.school.library.domain.port.in.BookUseCase;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookUseCase bookUseCase;
    private final WebDtoMapper webDtoMapper;

    public BookController(BookUseCase bookUseCase, WebDtoMapper webDtoMapper) {
        this.bookUseCase = bookUseCase;
        this.webDtoMapper = webDtoMapper;
    }

    @PostMapping("/{isbn}/borrow")
    public BookResponseDTO borrowBook(@PathVariable String isbn) {
        return webDtoMapper.toBookResponse(bookUseCase.borrowBook(isbn));
    }
}
