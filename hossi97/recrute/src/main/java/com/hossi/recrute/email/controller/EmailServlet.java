package com.hossi.recrute.email.controller;

import com.hossi.recrute.common.util.auth.AuthCookie;
import com.hossi.recrute.common.util.auth.AuthProcessor;
import com.hossi.recrute.common.util.http.servlet.ServletHandler;
import com.hossi.recrute.member.service.MemberService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "emailServlet", value = "/auth/email")
public class EmailServlet extends HttpServlet {
    private final MemberService memberService = new MemberService();
    private final ServletHandler servletHandler = ServletHandler.getINSTANCE();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AuthCookie authCookie = AuthProcessor.getAuthCookie(servletHandler.getCookies(request));
        if (authCookie.isActivate()) {
            Integer id = (Integer) request.getSession().getAttribute(authCookie.getValue());
            memberService.authMail(id);
            response.setStatus(200);
        } else {
            response.setStatus(302);
            response.sendRedirect("/");
        }
    }
}
