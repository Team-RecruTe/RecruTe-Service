package com.hossi.recrute.common.util.mail;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class MailSender {
    private final MailInfo mailInfo;

    public MailSender(MailType mailType) {
        mailInfo = MailConfig.getMailInfo(mailType);
    }

    public boolean sendEmail(String userEmail) {
        Properties props = new Properties();
        props.put("mail.smtp.host", mailInfo.getHost());
        props.put("mail.smtp.port", mailInfo.getPort());
        props.put("mail.smtp.auth", mailInfo.getAuth());
        props.put("mail.smtp.ssl.enable", mailInfo.getEnable());
        props.put("mail.smtp.ssl.trust", mailInfo.getTrust());

        Session session = Session.getDefaultInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mailInfo.getEmail(), mailInfo.getPassword());
            }
        });

        String authURL = "localhost:8080/email?code=1234";

        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(mailInfo.getEmail(), "Recrute-Team Admin"));
            msg.addRecipient(Message.RecipientType.TO,
                new InternetAddress(userEmail, "Mr. User"));
            msg.setSubject("Your Example.com account has been activated");
            msg.setText("please click the link: " + authURL);
            // msg.setContent(content, "text/html;charset=UTF-8);

            Transport.send(msg);
        } catch (MessagingException | UnsupportedEncodingException e) {
            return false;
        }

        return true;
    }
}
