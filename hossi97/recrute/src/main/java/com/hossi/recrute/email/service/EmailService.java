package com.hossi.recrute.email.service;

import com.hossi.recrute.common.service.MailSender;
import com.hossi.recrute.member.dao.MemberDao;

import static com.hossi.recrute.common.service.MailType.GOOGLE;

public class EmailService {
    private static final MemberDao memberDao = new MemberDao();
    private static final MailSender gmailSender = new MailSender(GOOGLE);
    public boolean sendEmail(Integer id) {
        String userEmail = getEmail(id);
        return gmailSender.sendEmail(userEmail);
    }

    private String getEmail(Integer id) {
        return memberDao.getEmailById(id);
    }
}
