package com.blanc.recrute.member.controller;

import com.blanc.recrute.member.util.Authenticater;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "signout", value = "/signout")
public class SignOutController extends HttpServlet {
    private static final Authenticater authenticater = new Authenticater();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        depriveAuth(request, response);
        response.setStatus(302);
        response.sendRedirect("/");
    }

    private static void depriveAuth(HttpServletRequest request, HttpServletResponse response) {
        if (SignOutController.authenticater.isAuthenticated(request)) {
            Cookie cookie = SignOutController.authenticater.expireAuthCookie();
            response.addCookie(cookie);
        }
    }
}
