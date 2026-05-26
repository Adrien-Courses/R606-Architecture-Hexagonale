package fr.school.library.adapter.out.notification;

import fr.school.library.domain.port.out.BorrowNotificationPort;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class SmtpBorrowNotificationAdapter implements BorrowNotificationPort {

    private final JavaMailSender mailSender;

    public SmtpBorrowNotificationAdapter(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendBorrowCreatedEmail(String recipientEmail, String bookTitle, LocalDate dueDate) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recipientEmail);
        message.setSubject("Nouvel emprunt enregistre");
        message.setText("Livre emprunte: " + bookTitle + "\nDate de rendu: " + dueDate);
        mailSender.send(message);
    }
}
