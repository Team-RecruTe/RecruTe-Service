package com.hossi.recrute.common.util.http.servlet;

import com.hossi.recrute.common.util.auth.AuthCookie;
import com.hossi.recrute.common.util.http.AttributeContainer;
import com.hossi.recrute.common.util.http.CookieContainer;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Map;

public interface RequestHandler {
    String parseJson(HttpServletRequest request) throws IOException;
    Map<String, String[]> getParameters(HttpServletRequest request);
    HttpSession getSession(HttpServletRequest request);
    CookieContainer getCookies(HttpServletRequest request);
    AuthCookie getAuthCookie(HttpServletRequest request);
    void setAttributes(AttributeContainer attributeContainer, HttpServletRequest request);
    void setSession(String key, Object value, HttpServletRequest request);
    void removeSession(AuthCookie authCookie, HttpServletRequest request);
}
