package fr.school.library.adapter.in.web.dto;

import java.time.LocalDate;

public record BorrowResponseDTO(
        Long id,
        String bookIsbn,
        Long userId,
        LocalDate borrowedAt,
        LocalDate returnedAt
) {
}
