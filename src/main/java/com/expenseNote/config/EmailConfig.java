package com.expenseNote.config;

import com.expenseNote.apps.user.model.User;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Component
@Data
public class EmailConfig {

    @Autowired
    private JavaMailSender mailSender;


    @Value("${spring.mail.host}")
    private String host;

    @Value("${spring.mail.port}")
    private short port;

    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.password}")
    private String password;

    @Value("${subject}")
    private String subject;

    @Value("${senderName}")
    private String senderName;

    public String getSenderName() {
        return senderName;
    }

    public String mailContent(User user) {

        String content = "<p>Dear " + user.getFirstName() + ",</p>";
        content += "<p> Welcome to XpenseNote. Your new account comes with </p>";
        content += "<p> access to many useful features of XpenseNote, </p>";
        content += "<p> such as, expense tracking, income tracking and budgeting. </p>";
        content += "<p></p><p>We are excited to have you onboard as we release new great features.</p>";
        content += "<p></p><p></p><p></p><p></p><p>Victor Ihedioha,<br>For XpenseNote Team.</p>";

        return content;
    }

    public void sendVerificationEmail(User user) throws UnsupportedEncodingException, MessagingException {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(getUsername(), getSenderName());
        helper.setTo(user.getEmail());
        helper.setSubject(getSubject());
        helper.setText(mailContent(user), true);

        mailSender.send(message);

    }
}
