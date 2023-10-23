package com.hossi.recrute.common.request;

import com.hossi.recrute.common.response.CookieContainer;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class Authenticator {
    private Cookie authCookie;

    public boolean isAuthenticated(CookieContainer cookieContainer){
        findAuthCookie(cookieContainer);
        return authCookie != null;
    }

    private void renewAuthCookie(Cookie cookie) {
        cookie.setMaxAge(3600);
        authCookie = cookie;
    }

    public void setAuthCookie(HttpSession session, Integer id) {
        String uuid = String.valueOf(UUID.randomUUID());
        Cookie cookie = new Cookie("sid", uuid);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(3600);
        authCookie = cookie;
        session.setAttribute(uuid, id);
    }

    public Cookie expireAuthCookie() {
        authCookie.setMaxAge(0);
        return authCookie;
    }

    public Cookie getAuthCookie(CookieContainer cookieContainer) {
        if(authCookie == null) {
            findAuthCookie(cookieContainer);
        }
        return authCookie;
    }

    private void findAuthCookie(CookieContainer cookieContainer) {
        Optional<Cookie> authCookie = cookieContainer.getCookies().stream()
            .filter(cookie -> cookie.getName().equals("sid"))
            .findFirst();

        authCookie.ifPresent(this::renewAuthCookie);
    }
}
