package com.hossi.recrute.member.controller;

import com.hossi.recrute.common.util.auth.processor.AuthManager;
import com.hossi.recrute.common.util.auth.util.AuthStream;
import com.hossi.recrute.common.util.code.ErrorMessage;
import com.hossi.recrute.common.util.code.anno.MBR;
import com.hossi.recrute.common.util.http.JsonManager;
import com.hossi.recrute.common.util.http.message.Message;
import com.hossi.recrute.common.util.http.message.MessageCreator;
import com.hossi.recrute.common.util.http.servlet.ServletHandler;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.google.common.net.MediaType.JSON_UTF_8;
import static com.hossi.recrute.common.util.auth.AuthType.COOKIE;
import static jakarta.servlet.http.HttpServletResponse.SC_OK;
import static jakarta.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;

@WebServlet(name = "authServlet", value = "/auth")
public class AuthServlet extends HttpServlet {
    private final ServletHandler servletHandler = ServletHandler.getINSTANCE();

    @Override @MBR("201")
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(AuthManager.renewIfAuth(COOKIE, new AuthStream(request, response))) {
            Message<String> message = MessageCreator.create();
            servletHandler
                .setStatus(SC_OK, response)
                .setJson(JSON_UTF_8, JsonManager.toJson(message), response);
        } else {
            ErrorMessage errorMessage = MessageCreator.createErrorMessage();
            servletHandler
                .setStatus(SC_UNAUTHORIZED, response)
                .setJson(JSON_UTF_8, JsonManager.toJson(errorMessage), response);
        }
    }
}