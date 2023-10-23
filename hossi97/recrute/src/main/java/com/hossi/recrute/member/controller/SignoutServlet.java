package com.hossi.recrute.member.controller;

import com.hossi.recrute.common.request.Authenticator;
import com.hossi.recrute.common.request.RequestUtil;
import com.hossi.recrute.common.response.CookieContainer;
import com.hossi.recrute.common.response.ResponseUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static jakarta.servlet.http.HttpServletResponse.SC_FOUND;

@WebServlet(name = "signoutServlet", value = "/signout")
public class SignoutServlet extends HttpServlet {

    private static final CookieContainer cookieContainer = new CookieContainer();
    private static final Authenticator AUTHENTICATOR = new Authenticator();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        depriveAuth(request, response);
        ResponseUtil.sendRedirect(SC_FOUND, "/", response);
    }

    private static void depriveAuth(HttpServletRequest request, HttpServletResponse response) {
        cookieContainer.setCookies(request);
        if(AUTHENTICATOR.isAuthenticated(cookieContainer)) {
            Cookie expiredAuthCookie = AUTHENTICATOR.expireAuthCookie();
            cookieContainer.setCookie(expiredAuthCookie);
            ResponseUtil.setCookies(cookieContainer, response);
        }
    }

}
