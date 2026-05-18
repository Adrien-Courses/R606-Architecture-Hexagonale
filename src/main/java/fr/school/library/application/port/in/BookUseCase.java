package fr.school.library.application.port.in;

import fr.school.library.domain.model.Book;

public interface BookUseCase {

    Book borrowBook(String isbn);
}
