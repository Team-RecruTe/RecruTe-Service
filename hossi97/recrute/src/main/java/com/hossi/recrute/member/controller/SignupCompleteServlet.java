package com.hossi.recrute.member.controller;

import com.hossi.recrute.common.auth.Authenticator;
import com.hossi.recrute.common.response.AttributeContainer;
import com.hossi.recrute.common.response.ResponseUtil;
import com.hossi.recrute.common.response.ViewResolver;
import com.hossi.recrute.email.service.EmailService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static jakarta.servlet.http.HttpServletResponse.SC_OK;

@WebServlet(name = "signupCompleteServlet", value = "/signup/complete")
public class SignupCompleteServlet extends HttpServlet {

    private static final AttributeContainer attributeContainer = new AttributeContainer();
    EmailService emailService = new EmailService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        attributeContainer.set("mainViewPath", ViewResolver.resolveMainViewPath("signup-complete"));
        ResponseUtil.setAttributes(attributeContainer, request);
        ResponseUtil.forward(SC_OK, ViewResolver.getMainViewPath(), request, response);
    }

    // 이메일 보내기
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Authenticator authenticator = new Authenticator();
        Cookie authCookie = authenticator.getAuthCookie(request);
        Integer id = (Integer)request.getSession().getAttribute(authCookie.getValue());
        emailService.sendEmail(id);
    }

}
