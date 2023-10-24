package com.hossi.recrute.common.util.http.servlet;

import com.google.common.net.MediaType;
import com.hossi.recrute.common.util.auth.AuthCookie;
import com.hossi.recrute.common.util.http.AttributeContainer;
import com.hossi.recrute.common.util.http.CookieContainer;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class ServletHandler implements RequestHandler, ResponseHandler, ViewHandler {

    private static final ServletHandler INSTANCE = new ServletHandler();
    private ServletHandler() {
    }

    public static ServletHandler getINSTANCE() {
        return INSTANCE;
    }

    public String parseJson(HttpServletRequest request) throws IOException {
        return request.getReader().lines().collect(Collectors.joining());
    }

    public Map<String, String[]> getParameters(HttpServletRequest request) {
        return request.getParameterMap();
    }

    public HttpSession getSession(HttpServletRequest request) {
        return request.getSession();
    }

    public CookieContainer getCookies(HttpServletRequest request) {
        return new CookieContainer(request.getCookies());
    }

    public AuthCookie getAuthCookie(HttpServletRequest request) {
        Optional<Cookie> foundedCookie = Arrays.stream(request.getCookies())
            .filter(cookie -> cookie.getName().equals("sid"))
            .findFirst();

        return foundedCookie.map(AuthCookie::new).orElse(new AuthCookie());
    }

    public void setAttributes(AttributeContainer attributeContainer, HttpServletRequest request) {
        Map<String, Object> attributes = attributeContainer.getAttributes();
        attributes.forEach(request::setAttribute);
        attributes.clear();
    }

    public void setSession(String key, Object value, HttpServletRequest request) {
        request.getSession().setAttribute(key, value);
    }

    public void removeSession(AuthCookie authCookie, HttpServletRequest request) {
        request.getSession().removeAttribute(authCookie.getValue());
    }


    public void sendJson(int status, String json, HttpServletResponse response) throws IOException {
        response.setStatus(status);
        response.setContentType(MediaType.JSON_UTF_8.toString());
        response.setContentLength(json.length());
        response.getWriter().write(json);
    }

    public void sendRedirect(int status, String path, HttpServletResponse response) throws IOException {
        response.setStatus(status);
        response.sendRedirect(path);
    }

    public void forward(int status, String path, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setStatus(status);
        request.setAttribute("errorViewPath", ViewResolver.resolveErrorViewPath("err404"));
        request.getRequestDispatcher(path).forward(request, response);
    }

    public void setCookies(CookieContainer cookieContainer, HttpServletResponse response) {
        List<Cookie> cookies = cookieContainer.getCookies();
        cookies.forEach(response::addCookie);
        cookies.clear();
    }

    public void setAuthCookie(AuthCookie authCookie, HttpServletResponse response) {
        response.addCookie(authCookie.getCookie());
    }



}
