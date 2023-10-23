package com.hossi.recrute.member.controller;

import java.io.*;

import com.google.gson.Gson;
import com.hossi.recrute.common.request.RequestUtil;
import com.hossi.recrute.common.response.data.Message;
import com.hossi.recrute.common.response.data.MessageCreator;
import com.hossi.recrute.common.response.service.ServicePrefix;
import com.hossi.recrute.common.response.util.CookieContainer;
import com.hossi.recrute.common.response.util.ResponseUtil;
import com.hossi.recrute.common.response.util.ViewResolver;
import com.hossi.recrute.common.auth.Authenticator;
import com.hossi.recrute.common.response.data.Result;
import com.hossi.recrute.member.dto.request.SigninDto;
import com.hossi.recrute.member.service.MemberService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import static com.hossi.recrute.common.response.service.ServicePrefix.RCT;
import static jakarta.servlet.http.HttpServletResponse.SC_FOUND;
import static jakarta.servlet.http.HttpServletResponse.SC_NOT_FOUND;

@WebServlet(name = "loginServlet", value = "/signin")
public class SigninServlet extends HttpServlet {
    private static final ServicePrefix servicePrefix = RCT;
    private static final MessageCreator messageCreator = MessageCreator.getInstance();
    private static final CookieContainer cookieContainer = new CookieContainer();
    private static final Gson gson = new Gson();
    private static final MemberService memberService = new MemberService();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("mainViewPath", ViewResolver.resolveMainViewPath("signin"));
        request.getRequestDispatcher(ViewResolver.getMainViewPath()).forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Authenticator authenticator = new Authenticator();
        if(authenticator.isAuthenticated(request)) {
            Cookie renewedAuthCookie = authenticator.getAuthCookie(request);
            cookieContainer.set(renewedAuthCookie);
            ResponseUtil.setCookies(cookieContainer, response);
            ResponseUtil.sendRedirect(SC_FOUND, "/", response);
        } else {
            SigninDto signinDto = gson.fromJson(RequestUtil.parseJson(request), SigninDto.class);
            Integer id = memberService.signin(signinDto);
            if(id != null) {
                authenticator.setAuthCookie(request, id);
                Cookie authCookie = authenticator.getAuthCookie(request);
                cookieContainer.set(authCookie);
                ResponseUtil.setCookies(cookieContainer, response);
                ResponseUtil.sendRedirect(SC_FOUND, "/", response);
            } else {
                Message<String> message = messageCreator.create(servicePrefix, "004", false, "Invalid ID/PW");
                ResponseUtil.sendJson(SC_NOT_FOUND, gson.toJson(message), response);
            }
        }
    }
}