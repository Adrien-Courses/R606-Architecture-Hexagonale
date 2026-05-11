package fr.school.library.application.mapper;

import fr.school.library.application.dto.CreateAuthorCommand;
import fr.school.library.application.dto.CreateBookCommand;
import fr.school.library.domain.model.Author;
import fr.school.library.domain.model.Book;
import org.springframework.stereotype.Component;

@Component
public class LibraryDomainMapper {

    public Author toNewAuthor(CreateAuthorCommand command) {
        return new Author(command.name());
    }

    public Book toNewBook(Long authorId, CreateBookCommand command) {
        return new Book(command.isbn(), command.title(), authorId);
    }
}
