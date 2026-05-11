package fr.school.library.controller.dto;

import fr.school.library.entity.Book;

public record BookResponseDTO(String isbn, String title, boolean borrowed, Long authorId) {

    public static BookResponseDTO from(Book book) {
        return new BookResponseDTO(
                book.getIsbn(),
                book.getTitle(),
                book.isBorrowed(),
                book.getAuthor() != null ? book.getAuthor().getId() : null
        );
    }
}
