package fr.school.library.adapter.in.web.dto;

public record BookResponseDTO(String isbn, String title, boolean borrowed, Long authorId) {
}
