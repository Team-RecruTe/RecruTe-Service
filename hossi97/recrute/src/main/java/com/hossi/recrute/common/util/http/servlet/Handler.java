package com.hossi.recrute.common.util.http.servlet;

import com.google.common.net.MediaType;
import com.google.gson.JsonElement;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

public abstract class Handler {
    private static final ServletHandler servletHandler = ServletHandler.getINSTANCE();

    public static Map<String, String[]> getParameters(HttpServletRequest request) {
        return servletHandler.getParameters(request);
    }

    public static Object getSession(String key, HttpServletRequest request) {
        return servletHandler.getSession(key, request);
    }

    public static Map<String, Object> getSessionAll(HttpServletRequest request) {
        return servletHandler.getSessionAll(request);
    }

    public static Cookie[] getCookies(HttpServletRequest request) {
        return servletHandler.getCookies(request);
    }

    public static String getJson(HttpServletRequest request) throws IOException {
        return servletHandler.getJson(request);
    }

    public static class Setter {
        public Setter() {
        }

        public Handler.Setter setSession(String key, Object value, HttpServletRequest request) {
            servletHandler.setSession(key, value, request);
            return this;
        }

        public Handler.Setter removeSession(String key, HttpServletRequest request) {
            request.getSession().removeAttribute(key);
            return this;
        }

        public Handler.Setter setCookie(Cookie cookie, HttpServletResponse response) {
            response.addCookie(cookie);
            return this;
        }

        public Handler.Setter removeCookie(Cookie cookie, HttpServletResponse response) {
            cookie.setMaxAge(0);
            response.addCookie(cookie);
            return this;
        }

        public Handler.Setter setAttribute(String key, Object value, HttpServletRequest request) {
            request.getSession().setAttribute(key, value);
            return this;
        }

        public Handler.Setter setJson(MediaType mediaType, String json, HttpServletResponse response) throws IOException {
            response.setContentType(mediaType.toString());
            response.setContentLength(json.length());
            response.getWriter().write(json);
            return this;
        }

        public Handler.Setter setJson(MediaType mediaType, JsonElement json, HttpServletResponse response) throws IOException {
            response.setContentType(mediaType.toString());
            response.getWriter().write(json.toString());
            return this;
        }

        public Handler.Setter setStatus(int status, HttpServletResponse response) {
            response.setStatus(status);
            return this;
        }
    }

    public static void sendRedirect(String path, HttpServletResponse response) throws IOException {
        servletHandler.sendRedirect(path, response);
    }

    public static void forward(String path, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        servletHandler.forward(path, request, response);
    }

}
