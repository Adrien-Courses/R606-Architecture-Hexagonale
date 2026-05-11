package fr.school.library.controller.dto;

import fr.school.library.entity.Author;

import java.util.List;

public record AuthorResponseDTO(Long id, String name, List<BookResponseDTO> books) {

    public static AuthorResponseDTO from(Author author) {
        return new AuthorResponseDTO(
                author.getId(),
                author.getName(),
                author.getBooks().stream()
                        .map(BookResponseDTO::from)
                        .toList()
        );
    }
}
