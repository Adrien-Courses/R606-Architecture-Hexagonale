package fr.school.library.service;

import fr.school.library.controller.dto.CreateBookRequest;
import fr.school.library.entity.Author;
import fr.school.library.entity.Book;
import fr.school.library.repository.AuthorRepository;
import fr.school.library.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LibraryService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public LibraryService(
            AuthorRepository authorRepository,
            BookRepository bookRepository
    ) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Transactional
    public Author createAuthor(String name) {
        Author author = new Author(name);
        return authorRepository.save(author);
    }

    @Transactional
    public Book addBookToAuthor(Long authorId, CreateBookRequest request) {
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new IllegalArgumentException("Author not found"));

        Book book = new Book(request.isbn(), request.title());

        author.addBook(book);

        authorRepository.save(author);

        return book;
    }

    @Transactional(readOnly = true)
    public List<Book> findBooksByAuthor(Long authorId) {
        return bookRepository.findByAuthorId(authorId);
    }

    @Transactional
    public Book borrowBook(String isbn) {
        Book book = bookRepository.findById(isbn)
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));

        book.borrow();

        return bookRepository.save(book);
    }
}
