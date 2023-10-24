package com.hossi.recrute.common.util.http.servlet;

import com.google.common.net.MediaType;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ServletHandler extends RequestResponseHandler implements RequestHandler, ResponseHandler, ViewHandler {
    private static final ServletHandler INSTANCE = new ServletHandler();

    private ServletHandler() {
    }

    public static ServletHandler getINSTANCE() {
        return INSTANCE;
    }

    @Override
    public Map<String, String[]> getParameters(HttpServletRequest request) {
        return request.getParameterMap();
    }

    @Override
    public Object getSession(String key, HttpServletRequest request) {
        return request.getSession().getAttribute(key);
    }

    @Override
    public Map<String, Object> getSessionAll(HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        Map<String, Object> map = new HashMap<>();
        httpSession.getAttributeNames().asIterator().forEachRemaining(key -> {
            map.put(key, httpSession.getAttribute(key));
        });
        return map;
    }

    @Override
    public Cookie[] getCookies(HttpServletRequest request) {
        return request.getCookies();
    }

    @Override
    public String getJson(HttpServletRequest request) throws IOException {
        return request.getReader().lines().collect(Collectors.joining());
    }

    @Override
    public ServletHandler setSession(String key, Object value, HttpServletRequest request) {
        request.getSession().setAttribute(key, value);
        return this;
    }

    @Override
    public ServletHandler removeSession(String key, HttpServletRequest request) {
        request.getSession().removeAttribute(key);
        return this;
    }

    @Override
    public ServletHandler setCookie(Cookie cookie, HttpServletResponse response) {
        response.addCookie(cookie);
        return this;
    }

    @Override
    public ServletHandler removeCookie(Cookie cookie, HttpServletResponse response) {
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return this;
    }

    @Override
    public ServletHandler setAttribute(String key, Object value, HttpServletRequest request) {
        request.getSession().setAttribute(key, value);
        return this;
    }

    @Override
    public ServletHandler setJson(MediaType mediaType, String json, HttpServletResponse response) throws IOException {
        response.setContentType(mediaType.toString());
        response.setContentLength(json.length());
        response.getWriter().write(json);
        return this;
    }

    @Override
    public ServletHandler setStatus(int status, HttpServletResponse response) {
        response.setStatus(status);
        return this;
    }

    @Override
    public void sendRedirect(String path, HttpServletResponse response) throws IOException {
        response.sendRedirect(path);
    }

    @Override
    public void forward(String path, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher(path).forward(request, response);
    }
}
