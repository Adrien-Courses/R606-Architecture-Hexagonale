package fr.school.library.adapter.in.web;

import fr.school.library.adapter.in.web.dto.AuthorResponseDTO;
import fr.school.library.adapter.in.web.dto.BookResponseDTO;
import fr.school.library.adapter.in.web.dto.CreateAuthorRequest;
import fr.school.library.adapter.in.web.dto.CreateBookRequest;
import fr.school.library.adapter.in.web.mapper.WebDtoMapper;
import fr.school.library.domain.port.in.AuthorUseCase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorUseCase authorUseCase;
    private final WebDtoMapper webDtoMapper;

    public AuthorController(AuthorUseCase authorUseCase, WebDtoMapper webDtoMapper) {
        this.authorUseCase = authorUseCase;
        this.webDtoMapper = webDtoMapper;
    }

    @PostMapping
    public AuthorResponseDTO createAuthor(@RequestBody CreateAuthorRequest request) {
        return webDtoMapper.toAuthorResponse(authorUseCase.createAuthor(request.name()));
    }

    @PostMapping("/{authorId}/books")
    public BookResponseDTO addBookToAuthor(
            @PathVariable Long authorId,
            @RequestBody CreateBookRequest request
    ) {
        return webDtoMapper.toBookResponse(
                authorUseCase.addBookToAuthor(authorId, request.isbn(), request.title())
        );
    }

    @GetMapping("/{authorId}/books")
    public List<BookResponseDTO> findBooksByAuthor(@PathVariable Long authorId) {
        return authorUseCase.findBooksByAuthor(authorId).stream()
                .map(webDtoMapper::toBookResponse)
                .toList();
    }
}
