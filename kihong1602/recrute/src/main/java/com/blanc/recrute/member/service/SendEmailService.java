package com.blanc.recrute.member.service;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class SendEmailService {
    public static boolean mailSend(String hostName, String sendEmail, String password, String receiveEmail, String authKey) {

        Properties props = setProperties(hostName);

        return props != null && sendEmail(sendEmail, password, receiveEmail, authKey, props);
    }

    private static Properties setProperties(String hostName) {
        //호스팅 메일서버 지정, properties 설정
        switch (hostName) {
            case "naver":
                String host = "smtp.naver.com";

                Properties props = new Properties();
                props.put("mail.smtp.host", host);
                props.put("mail.smtp.port", 587);
                props.put("mail.smtp.auth", "true");

                return props;
            case "gmail":
                host = "smtp.gmail.com";

                props = new Properties();
                props.put("mail.smtp.host", host);
                props.put("mail.smtp.port", 465);
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.ssl.enable", "true");
                props.put("mail.smtp.ssl.trust", host);
                return props;
            default:
                return null;
        }

    }

    private static boolean sendEmail(String sendEmail, String password, String receiveEmail, String authKey, Properties props) {
        //권한 등록
        Session session = Session.getDefaultInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sendEmail, password);
            }
        });


        //메일 작성
        try {
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(sendEmail));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiveEmail));

            message.setSubject("RecruTe 이메일 인증메일입니다.");
            String msgContent = new StringBuffer().append("<h1>[이메일 인증]</h1>")
                    .append("<p>아래 링크를 클릭하시면 이메일 인증이 완료됩니다.</p>")
                    .append("<a href='http://localhost:9080/mail?email=")
                    .append(receiveEmail)
                    .append("&authKey=")
                    .append(authKey)
                    .append("' target='_blank'>이메일 인증 확인</a>")
                    .toString();
            message.setContent(msgContent, "text/html;charset=UTF-8");


            Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}