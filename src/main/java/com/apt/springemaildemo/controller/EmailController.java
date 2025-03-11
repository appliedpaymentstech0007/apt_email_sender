package com.apt.springemaildemo.controller;

import com.apt.springemaildemo.dto.ContactRequest;
import com.apt.springemaildemo.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/emails")
public class EmailController {

    @Autowired
    private EmailSenderService emailSenderService;
    private static final String TO_EMAIL = "info@appliedpaymentstech.com";
    @PostMapping("/send")
    public String sendContactEnquiry(@RequestBody ContactRequest request) {
        try {
            String subject = "APT ENQUIRY THROUGH WEB";

            String htmlBody = generateHtmlTemplate(request);

            emailSenderService.sendHtmlEmail(TO_EMAIL, subject, htmlBody);
            return "Email sent successfully!";
        } catch (MessagingException e) {
            e.printStackTrace();
            return "Failed to send email!";
        }
    }


    private String generateHtmlTemplate(ContactRequest request) {
        try {
            String htmlTemplate = new String(Files.readAllBytes(Paths.get(getClass().getClassLoader().getResource("templates/contact-enquiry.html").toURI())));

            htmlTemplate = htmlTemplate.replace("{{name}}", request.getName())
                    .replace("{{email}}", request.getEmail())
                    .replace("{{phone}}", request.getPhone())
                    .replace("{{message}}", request.getMessage());

            return htmlTemplate;
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
            return "Error loading the email template.";
        }
    }
}
