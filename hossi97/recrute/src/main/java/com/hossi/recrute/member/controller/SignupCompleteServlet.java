package com.hossi.recrute.member.controller;

import com.hossi.recrute.common.Authenticater;
import com.hossi.recrute.common.ViewResolver;
import com.hossi.recrute.email.service.EmailDto;
import com.hossi.recrute.email.service.EmailService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "signupCompleteServlet", value = "/signup/complete")
public class SignupCompleteServlet extends HttpServlet {
    EmailService emailService = new EmailService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("mainViewPath", ViewResolver.resolveMainViewPath("signup-complete"));
        request.getRequestDispatcher(ViewResolver.getMainViewPath()).forward(request, response);
    }

    // 이메일 보내기
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Authenticater authenticater = new Authenticater();
        Cookie authCookie = authenticater.getAuthCookie(request);
        Integer id = (Integer)request.getSession().getAttribute(authCookie.getValue());
        emailService.sendEmail(id);
    }

}
