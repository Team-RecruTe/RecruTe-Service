package com.hossi.recrute.common.controller;

import com.google.gson.Gson;
import com.hossi.recrute.common.auth.Authenticator;
import com.hossi.recrute.common.ErrorCode;
import com.hossi.recrute.common.response.ResponseUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static jakarta.servlet.http.HttpServletResponse.SC_OK;
import static jakarta.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;

@WebServlet(name = "authServlet", value = "/auth")
public class AuthServlet extends HttpServlet {
    private static final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Authenticator authenticator = new Authenticator();
        if(authenticator.isAuthenticated(request)) {
            ErrorCode errorCode = new ErrorCode("RCT-02", "Authenticated");
            ResponseUtil.sendJson(SC_OK, gson.toJson(errorCode), response);
        } else {
            ErrorCode errorCode = new ErrorCode("RCT-02", "not Authenticated");
            ResponseUtil.sendJson(SC_UNAUTHORIZED, gson.toJson(errorCode), response);
        }
    }
}