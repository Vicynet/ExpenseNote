package com.expenseNote.config;

import com.expenseNote.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EmailConfig {

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

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public short getPort() {
        return port;
    }

    public void setPort(short port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSubject() {
        return subject;
    }

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
}
