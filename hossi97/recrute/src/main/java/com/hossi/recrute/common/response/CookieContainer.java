package com.hossi.recrute.common.response;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;

public class CookieContainer {
    private final List<Cookie> cookies = new ArrayList<>();

    public CookieContainer() {
    }

    public void setCookies(HttpServletRequest request) {
        this.cookies.addAll(List.of(request.getCookies()));
    }

    public void setCookies(Cookie[] cookies) {
        this.cookies.addAll(List.of(cookies));
    }

    public CookieContainer setCookie(Cookie cookie) {
        cookies.add(cookie);
        return this;
    }

    public List<Cookie> getCookies() {
        return cookies;
    }
}
