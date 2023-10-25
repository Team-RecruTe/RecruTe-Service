package com.hossi.recrute.member.controller;

import com.hossi.recrute.common.util.auth.AuthData;
import com.hossi.recrute.common.util.auth.Authenticator;
import com.hossi.recrute.common.util.auth.processor.AuthManager;
import com.hossi.recrute.common.util.auth.util.AuthStream;
import com.hossi.recrute.common.util.code.ErrorMessage;
import com.hossi.recrute.common.util.code.anno.MBR;
import com.hossi.recrute.common.util.http.JsonManager;
import com.hossi.recrute.common.util.http.message.MessageCreator;
import com.hossi.recrute.common.util.http.servlet.ServletHandler;
import com.hossi.recrute.common.util.http.servlet.ViewResolver;
import com.hossi.recrute.member.dto.request.SigninReqDto;
import com.hossi.recrute.member.dto.response.SigninResDto;
import com.hossi.recrute.member.service.MemberService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.google.common.net.MediaType.JSON_UTF_8;
import static com.hossi.recrute.common.util.auth.AuthType.COOKIE;
import static jakarta.servlet.http.HttpServletResponse.SC_NOT_FOUND;
import static jakarta.servlet.http.HttpServletResponse.SC_OK;

@WebServlet(name = "loginServlet", value = "/signin")
public class SigninServlet extends HttpServlet {
    private final MemberService memberService = new MemberService();
    private final ServletHandler servletHandler = ServletHandler.getINSTANCE();

    @Override @MBR("101")
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if(AuthManager.renewIfAuth(COOKIE, new AuthStream(request, response))) {
            servletHandler
                .sendRedirect("", response);
        } else {
            servletHandler
                .setAttribute("mainViewPath", ViewResolver.resolveMainViewPath("signin"), request)
                .forward(ViewResolver.getMainViewPath(), request, response);
        }
    }

    @Override @MBR("102")
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SigninReqDto signinReqDto = JsonManager.fromJson(servletHandler.getJson(request), SigninReqDto.class);
        SigninResDto signinResDto = memberService.signin(signinReqDto);
        AuthData authData = new AuthData.Builder()
            .id(signinResDto.getId())
            .certification(signinResDto.getCertification())
            .build();
        if(!authData.isEmpty()) {
            Authenticator authenticator = AuthManager.grantAuth(COOKIE);
            AuthManager.setAuth(authenticator, authData, new AuthStream(request, response));
            servletHandler
                .setStatus(SC_OK, response)
                .sendRedirect("", response);
        } else {
            ErrorMessage errorMessage = MessageCreator.createErrorMessage();
            servletHandler
                .setStatus(SC_NOT_FOUND, response)
                .setJson(JSON_UTF_8, JsonManager.toJson(errorMessage), response);
        }
    }
}