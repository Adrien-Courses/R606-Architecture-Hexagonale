package fr.school.library.adapter.in.web.dto;

import java.util.List;

public record AuthorResponseDTO(Long id, String name, List<BookResponseDTO> books) {
}
