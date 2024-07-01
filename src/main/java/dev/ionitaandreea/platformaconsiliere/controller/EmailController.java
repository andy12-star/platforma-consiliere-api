package dev.ionitaandreea.platformaconsiliere.controller;


import dev.ionitaandreea.platformaconsiliere.dto.request.EmailContactRequest;
import dev.ionitaandreea.platformaconsiliere.dto.request.EmailRequest;
import dev.ionitaandreea.platformaconsiliere.service.api.EmailService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/v1/email")
@RequiredArgsConstructor
public class EmailController {
    private static final String EMAIL_SUBJECT = "Un nou mesaj de contact a fost trimis";
    private static final String EMAIL_TEMPLATE = "email-contact";

    private final EmailService emailService;

    @PostMapping()
    public ResponseEntity<SimpleMailMessage> sendEmail(@RequestBody EmailRequest emailRequest) {
        SimpleMailMessage message = emailService.sendEmail(emailRequest.getTo(), emailRequest.getSubject(), emailRequest.getBody());
        return ResponseEntity.ok(message);
    }

    @PostMapping("contact")
    public ResponseEntity<String> sendContactEmail(@RequestBody EmailContactRequest emailContactRequest) {

        Map<String, Object> templateModel = new HashMap<>();
        templateModel.put("contact", emailContactRequest);

        try {
            emailService.sendTemplateEmail(
                "platforma.consiliere.studenti@gmail.com",
                    EMAIL_SUBJECT,
                    EMAIL_TEMPLATE,
                    templateModel
            );
        } catch (MessagingException e) {
            log.error("Failed to send email for new appointment");
        }
        return ResponseEntity.ok("Email for contact sent successfully");
    }
}
