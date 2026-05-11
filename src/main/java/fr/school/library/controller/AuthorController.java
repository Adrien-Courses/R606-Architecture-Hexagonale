package fr.school.library.controller;

import fr.school.library.controller.dto.AuthorResponseDTO;
import fr.school.library.controller.dto.BookResponseDTO;
import fr.school.library.controller.dto.CreateAuthorRequest;
import fr.school.library.controller.dto.CreateBookRequest;
import fr.school.library.service.LibraryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final LibraryService libraryService;

    public AuthorController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @PostMapping
    public AuthorResponseDTO createAuthor(@RequestBody CreateAuthorRequest request) {
        return AuthorResponseDTO.from(libraryService.createAuthor(request.name()));
    }

    @PostMapping("/{authorId}/books")
    public BookResponseDTO addBookToAuthor(
            @PathVariable Long authorId,
            @RequestBody CreateBookRequest request
    ) {
        return BookResponseDTO.from(libraryService.addBookToAuthor(authorId, request));
    }

    @GetMapping("/{authorId}/books")
    public List<BookResponseDTO> findBooksByAuthor(@PathVariable Long authorId) {
        return libraryService.findBooksByAuthor(authorId).stream()
                .map(BookResponseDTO::from)
                .toList();
    }
}
