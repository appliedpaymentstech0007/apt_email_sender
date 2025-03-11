package com.apt.springemaildemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;

@Service
public class EmailSenderService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendHtmlEmail(String toEmail, String subject, String htmlBody) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

        helper.setFrom("appliedpaymentstech007@gmail.com");
        helper.setTo(toEmail);
        helper.setSubject(subject);
        helper.setText(htmlBody, true); // Enables HTML content

        mailSender.send(message);
        System.out.println("HTML Mail Sent...");
    }
}
