package fr.school.library.application.port.in;

import fr.school.library.domain.model.Author;
import fr.school.library.domain.model.Book;

import java.util.List;

public interface AuthorUseCase {

    Author createAuthor(String name);

    Book addBookToAuthor(Long authorId, String isbn, String title);

    List<Book> findBooksByAuthor(Long authorId);
}
