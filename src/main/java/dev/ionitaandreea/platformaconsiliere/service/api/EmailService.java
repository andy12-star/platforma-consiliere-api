package dev.ionitaandreea.platformaconsiliere.service.api;

import jakarta.mail.MessagingException;
import org.springframework.mail.SimpleMailMessage;

import java.util.Map;

public interface EmailService {

    SimpleMailMessage sendEmail(String to, String subject, String body);

    void sendTemplateEmail(String to, String subject, String templateName, Map<String, Object> templateModel) throws MessagingException;
}
