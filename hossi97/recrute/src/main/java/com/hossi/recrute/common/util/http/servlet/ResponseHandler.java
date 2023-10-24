package com.hossi.recrute.common.util.http.servlet;

import com.google.common.net.MediaType;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface ResponseHandler {
    <T extends RequestResponseHandler> T setStatus(int status, HttpServletResponse response);
    <T extends RequestResponseHandler> T setJson(MediaType mediaType, String json, HttpServletResponse response) throws IOException;
    <T extends RequestResponseHandler> void sendRedirect(String path, HttpServletResponse response) throws IOException;
    <T extends RequestResponseHandler> T setCookie(Cookie cookie, HttpServletResponse response);
    <T extends RequestResponseHandler> T removeCookie(Cookie cookie, HttpServletResponse response);
}

