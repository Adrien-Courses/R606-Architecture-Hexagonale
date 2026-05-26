package fr.school.library.domain.port.out;

import java.time.LocalDate;

public interface BorrowNotificationPort {

    void sendBorrowCreatedEmail(String recipientEmail, String bookTitle, LocalDate dueDate);
}
