package com.hossi.recrute.exam.service;

import com.hossi.recrute.common.mail.MailSender;
import com.hossi.recrute.common.mybatis.ResultType;
import com.hossi.recrute.exam.dao.EmailDao;
import com.hossi.recrute.exam.dto.SendExamEmailDto;
import org.checkerframework.checker.units.qual.A;
import org.checkerframework.checker.units.qual.C;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static com.hossi.recrute.common.mail.MailType.GOOGLE;
import static com.hossi.recrute.common.mybatis.ResultType.SUCCESS;

public class ExamService {
    private final EmailDao emailDao = new EmailDao();
    private final MailSender mailSender = new MailSender(GOOGLE);
    private final List<String> aptIds = new ArrayList<>();
    private static final String EMAIL_AUTH_URL = "localhost:8080/exam/auth/";
    private static final String EMAIL_AUTH_PREFIX = "<a href=";
    private static final String EMAIL_AUTH_SUFFIX = ">시험보러가기</a>";
    private static final String TITLE = " 채용 테스트";
    private static final String CONTENT = "<div>테스트에 참여하기 위해 다음 링크를 클릭해주세요</div><div>수험번호: ";
    public List<String> sendEmail(Integer rctId) {
        List<SendExamEmailDto> sendExamEmailDtos = emailDao.findExamInfos(rctId);
        sendEmails(sendExamEmailDtos, rctId);
        return aptIds;
    }

    private void sendEmail(SendExamEmailDto dto, Integer rctId) {
        String userEmail = dto.getUserEmail();
        String title = dto.getCompanyName() + TITLE;
        String content = CONTENT + dto.getAptId() + "</div>" + EMAIL_AUTH_PREFIX + EMAIL_AUTH_URL + rctId + EMAIL_AUTH_SUFFIX;
        ResultType result = mailSender.sendEmail(userEmail, title, content);
        if(result == SUCCESS) {
            aptIds.add(dto.getAptId());
        }
    }

    private void sendEmails(List<SendExamEmailDto> sendExamEmailDtos, Integer rctId) {
        ExecutorService executorService = Executors.newFixedThreadPool(50);
        sendExamEmailDtos.forEach(dto -> executorService.execute(() -> sendEmail(dto, rctId)));
        executorService.shutdown();
        try {
            executorService.awaitTermination(1, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
