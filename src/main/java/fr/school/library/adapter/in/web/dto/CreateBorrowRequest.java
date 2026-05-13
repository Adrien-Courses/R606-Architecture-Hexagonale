package fr.school.library.adapter.in.web.dto;

import java.time.LocalDate;

public record CreateBorrowRequest(Long userId, LocalDate borrowedAt) {
}
