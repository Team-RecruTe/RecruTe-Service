package com.hossi.recrute.common.util.http;

import jakarta.servlet.http.Cookie;

import java.util.List;

public class CookieContainer {
    private final List<Cookie> cookies;

    public CookieContainer(Cookie[] cookies) {
        this.cookies = List.of(cookies);
    }

    public CookieContainer setCookie(Cookie cookie) {
        cookies.add(cookie);
        return this;
    }

    public List<Cookie> getCookies() {
        return cookies;
    }
}
