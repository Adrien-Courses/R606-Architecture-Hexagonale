package fr.school.library.controller;

import fr.school.library.controller.dto.BookResponseDTO;
import fr.school.library.service.LibraryService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookController {

    private final LibraryService libraryService;

    public BookController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @PostMapping("/{isbn}/borrow")
    public BookResponseDTO borrowBook(@PathVariable String isbn) {
        return BookResponseDTO.from(libraryService.borrowBook(isbn));
    }
}
