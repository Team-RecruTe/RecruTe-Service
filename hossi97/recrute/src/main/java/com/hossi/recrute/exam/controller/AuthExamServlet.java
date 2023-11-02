package com.hossi.recrute.exam.controller;

import com.google.common.net.MediaType;
import com.hossi.recrute.common.http.JsonManager;
import com.hossi.recrute.common.mybatis.ResultType;
import com.hossi.recrute.common.service.anno.EXM;
import com.hossi.recrute.common.service.message.ErrorMessage;
import com.hossi.recrute.common.service.message.Message;
import com.hossi.recrute.common.service.message.MessageCreator;
import com.hossi.recrute.common.servlet.HttpController;
import com.hossi.recrute.common.servlet.ViewResolver;
import com.hossi.recrute.exam.service.ExamService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.hossi.recrute.common.mybatis.ResultType.FAILURE;
import static com.hossi.recrute.common.mybatis.ResultType.SUCCESS;
import static jakarta.servlet.http.HttpServletResponse.*;


@WebServlet(name = "AuthExamServlet", value = "/exam/auth/*")
public class AuthExamServlet extends HttpController {

    @Override @EXM(value = "002", description = "시험 권한 인증 페이지 조회")
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        servletSetter()
            .setAttribute("mainViewPath", ViewResolver.resolveExamViewPath("exam-auth"), request)
            .setStatus(SC_OK, response)
            .forward(ViewResolver.getExamViewPath(), request, response);
    }

    @Override @EXM(value = "003", description = "시험 권한 인증")
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String requestURI = request.getRequestURI();
        String[] parsedURI = requestURI.split("/exam/auth/");
        String lastPiece = parsedURI[parsedURI.length - 1];
        if (isNumeric(lastPiece)) {
            String aptId = JsonManager.fromJson(getJson(request)).get("aptId").getAsString();
            Integer rctId = (Integer) getSession(aptId, request);
            ResultType result = (Integer.parseInt(lastPiece) == rctId) ? SUCCESS : FAILURE;
            if(result == SUCCESS) {
                Message<String> message = MessageCreator.create();
                Cookie cookie = new Cookie("eid", "462106867700218");
                servletSetter()
                    .setCookie(cookie, response)
                    .setStatus(SC_OK, response)
                    .setJson(MediaType.JSON_UTF_8, JsonManager.toJson(message), response);
            } else {
                ErrorMessage message = MessageCreator.createErrorMessage();
                servletSetter()
                    .setStatus(SC_UNAUTHORIZED, response)
                    .setJson(MediaType.JSON_UTF_8, JsonManager.toJson(message), response);
            }
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
