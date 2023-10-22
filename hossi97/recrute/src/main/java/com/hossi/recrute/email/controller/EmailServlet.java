package com.hossi.recrute.email.controller;

import com.google.gson.Gson;
import com.hossi.recrute.common.Authenticater;
import com.hossi.recrute.common.ResponseData;
import com.hossi.recrute.common.ViewResolver;
import com.hossi.recrute.email.service.EmailDto;
import com.hossi.recrute.email.service.EmailService;
import com.hossi.recrute.member.service.MemberService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.Arrays;

@WebServlet(name = "emailServlet", value = "/auth/email")
public class EmailServlet extends HttpServlet {
    private final MemberService memberService = new MemberService();
    private final Authenticater authenticater = new Authenticater();

    // 인증하기
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (authenticater.isAuthenticated(request)) {
            Integer id = (Integer) request.getSession().getAttribute(authenticater.getAuthCookie(request).getValue());
            memberService.authMail(id);
            response.setStatus(200);
        } else {
            response.setStatus(302);
            response.sendRedirect("/");
        }
    }
}
