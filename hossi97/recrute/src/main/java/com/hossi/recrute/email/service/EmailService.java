package com.hossi.recrute.email.service;

import com.hossi.recrute.member.dao.MemberDao;
import jakarta.mail.*;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class EmailService {
    MemberDao memberDao = new MemberDao();
    public String sendEmail(Integer id) {
        String userEmail = getEmail(id);
        String host = "smtp.gmail.com";
        String email = "team.recrute4@gmail.com";
        String password = "vpiu vqlz pftz jbak";

        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", 465);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.ssl.trust", host);

        Session session = Session.getDefaultInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email, password);
            }
        });

        String authURL = "localhost:8080/email?code=1234";

        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(email, "Recrute-Team Admin"));
            msg.addRecipient(Message.RecipientType.TO,
                new InternetAddress(userEmail, "Mr. User"));
            msg.setSubject("Your Example.com account has been activated");
            msg.setText("please click the link: " + authURL);
            // msg.setContent(content, "text/html;charset=UTF-8);

            Transport.send(msg);
        } catch (AddressException e) {
            return "Not OK";
        } catch (MessagingException e) {
            return "Not OK";
        } catch (UnsupportedEncodingException e) {
            return "Not OK";
        }

        return "OK";
    }

    private String getEmail(Integer id) {
        return memberDao.getEmailById(id);
    }
}
