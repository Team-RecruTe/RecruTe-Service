package com.hossi.recrute.common.util.http.servlet;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.Map;

public interface RequestHandler {
    String getJson(HttpServletRequest request) throws IOException;
    Map<String, String[]> getParameters(HttpServletRequest request);
    Map<String, Object> getSessionAll(HttpServletRequest request);
    Object getSession(String key, HttpServletRequest request);
    Cookie[] getCookies(HttpServletRequest request);
    <T extends RequestResponseHandler> T setAttribute(String key, Object value, HttpServletRequest request);
    <T extends RequestResponseHandler> T setSession(String key, Object value, HttpServletRequest request);
    <T extends RequestResponseHandler> T removeSession(String key, HttpServletRequest request);
}
