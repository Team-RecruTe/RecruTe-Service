package com.hossi.recrute.member.controller;

import com.hossi.recrute.common.auth.Authenticator;
import com.hossi.recrute.common.response.util.CookieContainer;
import com.hossi.recrute.common.response.util.ResponseUtil;
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
        if(AUTHENTICATOR.isAuthenticated(request)) {
            Cookie expiredAuthCookie = AUTHENTICATOR.expireAuthCookie();
            cookieContainer.set(expiredAuthCookie);
            ResponseUtil.setCookies(cookieContainer, response);
        }
    }

}
