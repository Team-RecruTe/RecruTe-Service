package com.hossi.recrute.member.controller;

import com.hossi.recrute.common.util.auth.AuthCookie;
import com.hossi.recrute.common.util.http.AttributeContainer;
import com.hossi.recrute.common.util.http.servlet.ServletHandler;
import com.hossi.recrute.common.util.http.servlet.ViewResolver;
import com.hossi.recrute.email.service.EmailService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static jakarta.servlet.http.HttpServletResponse.SC_OK;

@WebServlet(name = "signupCompleteServlet", value = "/signup/complete")
public class SignupCompleteServlet extends HttpServlet {
    private final AttributeContainer attributeContainer = new AttributeContainer();
    private final ServletHandler servletHandler = ServletHandler.getINSTANCE();

    EmailService emailService = new EmailService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        attributeContainer.set("mainViewPath", ViewResolver.resolveMainViewPath("signup-complete"));
        servletHandler.setAttributes(attributeContainer, request);
        servletHandler.forward(SC_OK, ViewResolver.getMainViewPath(), request, response);
    }

    // 이메일 보내기
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AuthCookie authCookie = servletHandler.getAuthCookie(request);
        Integer id = (Integer)request.getSession().getAttribute(authCookie.getValue());
        emailService.sendEmail(id);
    }

}
