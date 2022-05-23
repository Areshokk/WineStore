package com.gmail.yurii.ecommerce.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSender {
    /**
     * Implementation of the {@link JavaMailSender} interface
     * to send messages to email.
     */
    private final JavaMailSender mailSender;

    /**
     * Login user of the SMTP server.
     */
    @Value("${spring.mail.username}")
    private String username;

    /**
     * Constructor for initializing the main variables of the mail service.
     * The @Autowired annotation will allow Spring to automatically initialize objects.
     *
     * @param mailSender implementation of the {@link JavaMailSender} interface
     *                   to send messages to email.
     */
    @Autowired
    public MailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }



    /**
     * Send an email to the specified email address with the specified subject and message.
     *
     * @param emailTo The email address of the recipient.
     * @param subject The subject of the email.
     * @param message The message to be sent.
     */
    public void send(String emailTo, String subject, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(username);
        mailMessage.setTo(emailTo);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);

        mailSender.send(mailMessage);
    }
}