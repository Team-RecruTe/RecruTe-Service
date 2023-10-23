package com.hossi.recrute.common.response;

import com.google.common.net.MediaType;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface ResponseUtil {
    static void sendJson(int status, String json, HttpServletResponse response) throws IOException {
        response.setStatus(status);
        response.setContentType(MediaType.JSON_UTF_8.toString());
        response.setContentLength(json.length());
        response.getWriter().write(json);
    }

    static void sendRedirect(int status, String path, HttpServletResponse response) throws IOException {
        response.setStatus(status);
        response.sendRedirect(path);
    }

    static void forward(int status, String path, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setStatus(status);
        request.setAttribute("errorViewPath", ViewResolver.resolveErrorViewPath("err404"));
        request.getRequestDispatcher(path).forward(request, response);
    }

    static void setCookies(CookieContainer cookieContainer, HttpServletResponse response) {
        List<Cookie> cookies = cookieContainer.getCookies();
        cookies.forEach(response::addCookie);
        cookies.clear();
    }

    static void setAttributes(AttributeContainer attributeContainer, HttpServletRequest request) {
        Map<String, Object> attributes = attributeContainer.getAttributes();
        attributes.forEach(request::setAttribute);
        attributes.clear();
    }

}
