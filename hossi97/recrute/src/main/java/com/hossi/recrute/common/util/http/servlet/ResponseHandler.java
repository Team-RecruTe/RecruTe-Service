package com.hossi.recrute.common.util.http.servlet;

import com.hossi.recrute.common.util.auth.AuthCookie;
import com.hossi.recrute.common.util.http.CookieContainer;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface ResponseHandler {
    void sendJson(int status, String json, HttpServletResponse response) throws IOException;
    void sendRedirect(int status, String path, HttpServletResponse response) throws IOException;
    void setCookies(CookieContainer cookieContainer, HttpServletResponse response);
    void setAuthCookie(AuthCookie authCookie, HttpServletResponse response);
}
