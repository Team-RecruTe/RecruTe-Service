package com.hossi.recrute.member.controller;

import com.hossi.recrute.common.util.auth.Authenticator;
import com.hossi.recrute.common.util.auth.processor.AuthManager;
import com.hossi.recrute.common.util.auth.util.AuthStream;
import com.hossi.recrute.common.util.code.anno.MBR;
import com.hossi.recrute.common.util.http.servlet.ServletHandler;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

import static com.hossi.recrute.common.util.auth.AuthType.COOKIE;
import static jakarta.servlet.http.HttpServletResponse.SC_OK;

@WebServlet(name = "signoutServlet", value = "/signout")
public class SignoutServlet extends HttpServlet {
    private final ServletHandler servletHandler = ServletHandler.getINSTANCE();

    @Override @MBR("103")
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Optional<Authenticator> authenticator = AuthManager.getIfAuth(COOKIE, new AuthStream(request, response));
        authenticator.ifPresent(AuthManager::expireAuth);
        servletHandler
            .setStatus(SC_OK, response)
            .sendRedirect("", response);
    }
}
