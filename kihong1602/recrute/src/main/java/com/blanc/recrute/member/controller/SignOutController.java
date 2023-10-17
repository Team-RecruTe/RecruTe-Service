package com.blanc.recrute.member.controller;

import com.blanc.recrute.member.util.Authenticater;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "signout", value = "/signout")
public class SignOutController extends HttpServlet {
    private static Authenticater authenticater = new Authenticater();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        depriveAuth(request, response, authenticater);
        response.setStatus(302);
        response.sendRedirect("/");
    }

    private static void depriveAuth(HttpServletRequest request, HttpServletResponse response, Authenticater authenticater) {
        if (authenticater.isAuthenticated(request)) {
            Cookie cookie = authenticater.expireAuthCookie();
            response.addCookie(cookie);
        }
    }
}
