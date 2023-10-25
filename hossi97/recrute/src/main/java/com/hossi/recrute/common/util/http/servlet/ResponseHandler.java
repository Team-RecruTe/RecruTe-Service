package com.hossi.recrute.common.util.http.servlet;

import com.google.common.net.MediaType;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface ResponseHandler {
    ServletHandler setStatus(int status, HttpServletResponse response);
    ServletHandler setJson(MediaType mediaType, String json, HttpServletResponse response) throws IOException;
    void sendRedirect(String path, HttpServletResponse response) throws IOException;
    ServletHandler setCookie(Cookie cookie, HttpServletResponse response);
    ServletHandler removeCookie(Cookie cookie, HttpServletResponse response);
}

