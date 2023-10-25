package com.hossi.recrute.member.controller;

import com.hossi.recrute.common.util.auth.AuthCookie;
import com.hossi.recrute.common.util.auth.AuthProcessor;
import com.hossi.recrute.common.util.http.servlet.ServletHandler;
import com.hossi.recrute.common.util.http.servlet.ViewResolver;
import com.hossi.recrute.common.util.code.anno.MBR;
import com.hossi.recrute.member.service.EmailService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static jakarta.servlet.http.HttpServletResponse.SC_OK;

@WebServlet(name = "signupCompleteServlet", value = "/signup/complete")
public class EmailAuthServlet extends HttpServlet {
    private final ServletHandler servletHandler = ServletHandler.getINSTANCE();
    private final EmailService emailService = new EmailService();

    @Override @MBR("301")
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        servletHandler
            .setAttribute("mainViewPath", ViewResolver.resolveMainViewPath("signup-complete"), request)
            .setStatus(SC_OK, response)
            .forward(ViewResolver.getMainViewPath(), request, response);
    }

    @Override @MBR("302")
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AuthCookie authCookie = AuthProcessor.getAuthCookie(servletHandler.getCookies(request));
        Integer id = (Integer)request.getSession().getAttribute(authCookie.getValue());
        emailService.sendEmail(id);
    }

}
