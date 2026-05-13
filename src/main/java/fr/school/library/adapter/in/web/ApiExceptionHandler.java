package fr.school.library.adapter.in.web;

import fr.school.library.domain.exception.ActiveBorrowAlreadyExistsException;
import fr.school.library.domain.exception.ActiveBorrowNotFoundException;
import fr.school.library.domain.exception.AuthorNotFoundException;
import fr.school.library.domain.exception.BookNotFoundException;
import fr.school.library.domain.exception.BorrowAlreadyReturnedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(AuthorNotFoundException.class)
    public ProblemDetail handleAuthorNotFound(AuthorNotFoundException exception) {
        return problem(HttpStatus.NOT_FOUND, exception.getMessage());
    }

    @ExceptionHandler(BookNotFoundException.class)
    public ProblemDetail handleBookNotFound(BookNotFoundException exception) {
        return problem(HttpStatus.NOT_FOUND, exception.getMessage());
    }

    @ExceptionHandler(ActiveBorrowAlreadyExistsException.class)
    public ProblemDetail handleActiveBorrowAlreadyExists(ActiveBorrowAlreadyExistsException exception) {
        return problem(HttpStatus.CONFLICT, exception.getMessage());
    }

    @ExceptionHandler(ActiveBorrowNotFoundException.class)
    public ProblemDetail handleActiveBorrowNotFound(ActiveBorrowNotFoundException exception) {
        return problem(HttpStatus.NOT_FOUND, exception.getMessage());
    }

    @ExceptionHandler(BorrowAlreadyReturnedException.class)
    public ProblemDetail handleBorrowAlreadyReturned(BorrowAlreadyReturnedException exception) {
        return problem(HttpStatus.CONFLICT, exception.getMessage());
    }

    private ProblemDetail problem(HttpStatus status, String detail) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(status);
        problemDetail.setDetail(detail);
        return problemDetail;
    }
}
