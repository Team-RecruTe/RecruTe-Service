package com.hossi.recrute.member.controller;

import com.hossi.recrute.common.util.auth.AuthCookie;
import com.hossi.recrute.common.util.auth.AuthProcessor;
import com.hossi.recrute.common.util.code.ErrorMessage;
import com.hossi.recrute.common.util.code.anno.MBR;
import com.hossi.recrute.common.util.http.JsonManager;
import com.hossi.recrute.common.util.http.message.MessageCreator;
import com.hossi.recrute.common.util.http.servlet.ServletHandler;
import com.hossi.recrute.common.util.http.servlet.ViewResolver;
import com.hossi.recrute.member.dto.request.SigninDto;
import com.hossi.recrute.member.service.MemberService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.google.common.net.MediaType.JSON_UTF_8;
import static jakarta.servlet.http.HttpServletResponse.SC_FOUND;
import static jakarta.servlet.http.HttpServletResponse.SC_NOT_FOUND;

@WebServlet(name = "loginServlet", value = "/signin")
public class SigninServlet extends HttpServlet {
    private final MemberService memberService = new MemberService();
    private final ServletHandler servletHandler = ServletHandler.getINSTANCE();

    @Override @MBR("101")
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("mainViewPath", ViewResolver.resolveMainViewPath("signin"));
        request.getRequestDispatcher(ViewResolver.getMainViewPath()).forward(request, response);
    }

    @Override @MBR("102")
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        AuthCookie authCookie = AuthProcessor.getAuthCookie(servletHandler.getCookies(request));
        if(authCookie.isActivate()) {
            AuthProcessor.renewAuthCookie(authCookie);
            servletHandler.setCookie(authCookie.getCookie(), response)
                .setStatus(SC_FOUND, response)
                .sendRedirect("/", response);
        } else {
            SigninDto signinDto = JsonManager.fromJson(servletHandler.getJson(request), SigninDto.class);
            Integer id = memberService.signin(signinDto);
            if(id != null) {
                AuthProcessor.setAuthCookie(authCookie);
                servletHandler.setSession(authCookie.getValue(), id, request)
                    .setCookie(authCookie.getCookie(), response)
                    .setStatus(SC_FOUND, response)
                    .sendRedirect("/", response);
            } else {
                ErrorMessage errorMessage = MessageCreator.createErrorMessage();
                servletHandler
                    .setStatus(SC_NOT_FOUND, response)
                    .setJson(JSON_UTF_8, JsonManager.toJson(errorMessage), response);
            }
        }
    }
}