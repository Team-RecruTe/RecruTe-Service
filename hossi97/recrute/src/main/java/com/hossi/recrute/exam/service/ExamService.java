package com.hossi.recrute.exam.service;

import com.hossi.recrute.common.mail.MailSender;
import com.hossi.recrute.exam.dao.EmailDao;
import com.hossi.recrute.exam.dto.SendExamEmailDto;

import java.util.ArrayList;
import java.util.List;

import static com.hossi.recrute.common.mail.MailType.GOOGLE;
import static com.hossi.recrute.common.mybatis.ResultType.SUCCESS;

public class ExamService {
    private final EmailDao emailDao = new EmailDao();
    MailSender mailSender = new MailSender(GOOGLE);
    private static final String EMAIL_AUTH_URL = "<a href=" + "localhost:8080/exam/auth" + ">시험보러가기</a>";
    private static final String TITLE = " 채용 테스트";
    private static final String CONTENT = "<div>테스트에 참여하기 위해 다음 링크를 클릭해주세요</div><div>수험번호: ";
    public List<String> sendEmail(Integer recruitmentId) {
        List<SendExamEmailDto> sendExamEmailDtos = emailDao.findExamInfos(recruitmentId);
        List<String> aptIds = new ArrayList<>();
        sendExamEmailDtos.forEach(dto -> {
            String userEmail = dto.getUserEmail();
            String title = dto.getCompanyName() + TITLE;
            String content = CONTENT + dto.getAptId() + "</div>" + EMAIL_AUTH_URL;
            if(SUCCESS == mailSender.sendEmail(userEmail, title, content)) {
                aptIds.add(dto.getAptId());
            }
        });
        return aptIds;
    }

}
