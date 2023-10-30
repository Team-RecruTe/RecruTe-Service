package com.hossi.recrute.exam.controller;

import com.google.common.net.MediaType;
import com.hossi.recrute.common.http.JsonManager;
import com.hossi.recrute.common.service.anno.EXM;
import com.hossi.recrute.common.service.message.Message;
import com.hossi.recrute.common.service.message.MessageCreator;
import com.hossi.recrute.common.service.message.ResponseData;
import com.hossi.recrute.common.servlet.HttpController;
import com.hossi.recrute.common.servlet.ViewResolver;
import com.hossi.recrute.exam.service.ExamService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static jakarta.servlet.http.HttpServletResponse.SC_NOT_FOUND;
import static jakarta.servlet.http.HttpServletResponse.SC_OK;

@WebServlet(name = "sendExamEmailServlet", value = "/exam/email/*")
public class SendExamEmailServlet extends HttpController {
    private final ExamService examService = new ExamService();

    @Override @EXM(value = "001", description = "시험 응시 이메일 전송")
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String requestURI = request.getRequestURI();
        String[] parsedURI = requestURI.split("/exam/email/");
        String lastPiece = parsedURI[parsedURI.length - 1];
        if (isNumeric(lastPiece)) {
            List<String> aptIds = examService.sendEmail(Integer.parseInt(lastPiece));
            aptIds.forEach(aptId -> {
                servletSetter().setSession(aptId, Integer.parseInt(lastPiece), request);
            });
            ResponseData responseData = new ResponseData.Builder().set("aptIds", aptIds).build();
            Message<Map<String, Object>> message = MessageCreator.create(responseData);
            servletSetter()
                .setStatus(SC_OK, response)
                .setJson(MediaType.JSON_UTF_8, JsonManager.toJson(message), response);
        } else {
            servletSetter()
                .setAttribute("errorViewPath", ViewResolver.resolveErrorViewPath("err404"), request)
                .setStatus(SC_NOT_FOUND, response)
                .forward(ViewResolver.getErrorViewPath(), request, response);
        }
    }

    public boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
