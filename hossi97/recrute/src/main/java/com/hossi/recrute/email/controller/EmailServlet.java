package com.hossi.recrute.email.controller;

import com.hossi.recrute.common.auth.Authenticator;
import com.hossi.recrute.member.service.MemberService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet(name = "emailServlet", value = "/auth/email")
public class EmailServlet extends HttpServlet {
    private final MemberService memberService = new MemberService();
    private final Authenticator authenticator = new Authenticator();

    // 인증하기
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (authenticator.isAuthenticated(request)) {
            Integer id = (Integer) request.getSession().getAttribute(authenticator.getAuthCookie(request).getValue());
            memberService.authMail(id);
            response.setStatus(200);
        } else {
            response.setStatus(302);
            response.sendRedirect("/");
        }
    }
}
