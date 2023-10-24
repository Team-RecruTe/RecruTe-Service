package com.hossi.recrute.member.controller;

import com.hossi.recrute.common.util.auth.AuthCookie;
import com.hossi.recrute.common.util.auth.AuthProcessor;
import com.hossi.recrute.common.util.http.servlet.ServletHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static jakarta.servlet.http.HttpServletResponse.SC_FOUND;

@WebServlet(name = "signoutServlet", value = "/signout")
public class SignoutServlet extends HttpServlet {
    private final ServletHandler servletHandler = ServletHandler.getINSTANCE();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        depriveAuth(request, response);
        servletHandler.sendRedirect(SC_FOUND, "/", response);
    }

    private void depriveAuth(HttpServletRequest request, HttpServletResponse response) {
        AuthCookie authCookie = servletHandler.getAuthCookie(request);
        if(authCookie.isActivate()) {
            AuthProcessor.expireAuthCookie(authCookie);
            servletHandler.removeSession(authCookie, request);
            servletHandler.setAuthCookie(authCookie, response);
        }
    }

}
