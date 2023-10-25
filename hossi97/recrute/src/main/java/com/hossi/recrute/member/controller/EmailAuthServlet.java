package com.hossi.recrute.member.controller;

import com.hossi.recrute.common.util.auth.AuthData;
import com.hossi.recrute.common.util.auth.Authenticator;
import com.hossi.recrute.common.util.auth.processor.AuthManager;
import com.hossi.recrute.common.util.auth.util.AuthStream;
import com.hossi.recrute.common.util.code.anno.MBR;
import com.hossi.recrute.common.util.http.servlet.ServletHandler;
import com.hossi.recrute.common.util.http.servlet.ViewResolver;
import com.hossi.recrute.member.service.EmailService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

import static com.hossi.recrute.common.util.auth.AuthType.COOKIE;
import static jakarta.servlet.http.HttpServletResponse.SC_OK;

@WebServlet(name = "signupCompleteServlet", value = "/member/email")
public class EmailAuthServlet extends HttpServlet {
    private final ServletHandler servletHandler = ServletHandler.getINSTANCE();
    private final EmailService emailService = new EmailService();

    @Override @MBR("301")
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AuthManager.renewIfAuth(COOKIE, new AuthStream(request, response));
        // 이메일 인증 했다면
        servletHandler
            .sendRedirect("", response);
        // 이메일 인증 안했다면
        servletHandler
            .setAttribute("mainViewPath", ViewResolver.resolveMainViewPath("email-auth"), request)
            .setStatus(SC_OK, response)
            .forward(ViewResolver.getMainViewPath(), request, response);
    }

    @Override @MBR("302")
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        Optional<Authenticator> authenticator = AuthManager.renewAndGetIfAuth(COOKIE, new AuthStream(request, response));
        if (authenticator.isPresent()) {
            AuthData authData = AuthManager.getAuthData(authenticator.get(), new AuthStream(request, response));
            emailService.sendEmail(authData.getId());
        }
    }

}
