package com.hossi.recrute.common.controller;

import com.google.gson.Gson;
import com.hossi.recrute.common.auth.Authenticator;
import com.hossi.recrute.common.response.data.Message;
import com.hossi.recrute.common.response.data.MessageCreator;
import com.hossi.recrute.common.response.data.Result;
import com.hossi.recrute.common.response.service.ServiceCode;
import com.hossi.recrute.common.response.service.ServiceCodeManager;
import com.hossi.recrute.common.response.service.ServicePrefix;
import com.hossi.recrute.common.response.util.ResponseUtil;
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
    private static final MessageCreator messageCreator = MessageCreator.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Authenticator authenticator = new Authenticator();
        if(authenticator.isAuthenticated(request)) {
            Message<String> message = messageCreator.create(servicePrefix, "002", true, "Authenticated");
            ResponseUtil.sendJson(SC_OK, gson.toJson(message), response);
        } else {
            Message<String> message = messageCreator.create(servicePrefix, "002", false, "Not Authenticated");
            ResponseUtil.sendJson(SC_UNAUTHORIZED, gson.toJson(message), response);
        }
    }
}