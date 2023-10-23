package com.hossi.recrute.common.controller;

import com.google.gson.Gson;
import com.hossi.recrute.common.request.Authenticator;
import com.hossi.recrute.common.request.RequestUtil;
import com.hossi.recrute.common.response.CookieContainer;
import com.hossi.recrute.common.response.data.Message;
import com.hossi.recrute.common.response.data.MessageCreator;
import com.hossi.recrute.common.response.service.ServicePrefix;
import com.hossi.recrute.common.response.ResponseUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.hossi.recrute.common.response.service.ServicePrefix.RCT;
import static jakarta.servlet.http.HttpServletResponse.SC_OK;
import static jakarta.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;

@WebServlet(name = "authServlet", value = "/auth")
public class AuthServlet extends HttpServlet {
    private static final Gson gson = new Gson();
    private static final ServicePrefix servicePrefix = RCT;
    private static final CookieContainer cookieContainer = new CookieContainer();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Authenticator authenticator = new Authenticator();
        cookieContainer.setCookies(request);
        if(authenticator.isAuthenticated(cookieContainer)) {
            Message<String> message = MessageCreator.create(servicePrefix, "002", true, "Authenticated");
            ResponseUtil.sendJson(SC_OK, gson.toJson(message), response);
        } else {
            Message<String> message = MessageCreator.create(servicePrefix, "002", false, "Not Authenticated");
            ResponseUtil.sendJson(SC_UNAUTHORIZED, gson.toJson(message), response);
        }
    }
}