package com.hossi.recrute.member.controller;

import com.hossi.recrute.common.util.auth.AuthData;
import com.hossi.recrute.common.util.auth.Authenticator;
import com.hossi.recrute.common.util.auth.processor.AuthManager;
import com.hossi.recrute.common.util.auth.util.AuthStream;
import com.hossi.recrute.common.util.code.anno.MBR;
import com.hossi.recrute.common.util.http.servlet.ServletHandler;
import com.hossi.recrute.member.service.MemberService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

import static com.hossi.recrute.common.util.auth.AuthType.COOKIE;

@WebServlet(name = "emailServlet", value = "/member/email/*")
public class EmailServlet extends HttpServlet {
    private final MemberService memberService = new MemberService();
    private final ServletHandler servletHandler = ServletHandler.getINSTANCE();

    @Override @MBR("303")
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Optional<Authenticator> authenticator = AuthManager.renewAndGetIfAuth(COOKIE, new AuthStream(request, response));
        if(authenticator.isPresent()) {
            AuthData authData = AuthManager.getAuthData(authenticator.get(), new AuthStream(request, response));
            memberService.authMail(authData.getId());
            response.setStatus(200);
        } else {
            response.setStatus(302);
            response.sendRedirect("/");
        }
    }
}
