package fr.school.library.adapter.in.web;

import fr.school.library.adapter.in.web.dto.BorrowResponseDTO;
import fr.school.library.adapter.in.web.dto.CreateBorrowRequest;
import fr.school.library.adapter.in.web.dto.RegisterReturnRequest;
import fr.school.library.adapter.in.web.mapper.WebDtoMapper;
import fr.school.library.application.port.in.BorrowUseCase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/books/{isbn}/borrows")
public class BorrowController {

    private final BorrowUseCase borrowUseCase;
    private final WebDtoMapper webDtoMapper;

    public BorrowController(BorrowUseCase borrowUseCase, WebDtoMapper webDtoMapper) {
        this.borrowUseCase = borrowUseCase;
        this.webDtoMapper = webDtoMapper;
    }

    @PostMapping
    public BorrowResponseDTO createBorrow(
            @PathVariable String isbn,
            @RequestBody CreateBorrowRequest request
    ) {
        LocalDate borrowedAt = request.borrowedAt() != null ? request.borrowedAt() : LocalDate.now();
        return webDtoMapper.toBorrowResponse(
                borrowUseCase.createBorrow(isbn, request.userId(), borrowedAt)
        );
    }

    @PostMapping("/return")
    public BorrowResponseDTO registerReturn(
            @PathVariable String isbn,
            @RequestBody RegisterReturnRequest request
    ) {
        LocalDate returnedAt = request.returnedAt() != null ? request.returnedAt() : LocalDate.now();
        return webDtoMapper.toBorrowResponse(
                borrowUseCase.registerReturn(isbn, returnedAt)
        );
    }

    @GetMapping
    public List<BorrowResponseDTO> getBorrowHistory(@PathVariable String isbn) {
        return borrowUseCase.getBorrowHistory(isbn).stream()
                .map(webDtoMapper::toBorrowResponse)
                .toList();
    }
}
