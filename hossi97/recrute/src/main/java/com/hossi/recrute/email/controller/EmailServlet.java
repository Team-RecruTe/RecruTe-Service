package com.hossi.recrute.email.controller;

import com.hossi.recrute.common.request.Authenticator;
import com.hossi.recrute.common.request.RequestUtil;
import com.hossi.recrute.common.response.CookieContainer;
import com.hossi.recrute.member.service.MemberService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet(name = "emailServlet", value = "/auth/email")
public class EmailServlet extends HttpServlet {
    private final MemberService memberService = new MemberService();
    private final Authenticator authenticator = new Authenticator();
    private final CookieContainer cookieContainer = new CookieContainer();

    // 인증하기
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        cookieContainer.setCookies(request);
        if (authenticator.isAuthenticated(cookieContainer)) {
            Integer id = (Integer) request.getSession().getAttribute(authenticator.getAuthCookie(cookieContainer).getValue());
            memberService.authMail(id);
            response.setStatus(200);
        } else {
            response.setStatus(302);
            response.sendRedirect("/");
        }
    }
}
