package com.hossi.recrute.common.response;

import jakarta.servlet.http.Cookie;

import java.util.ArrayList;
import java.util.List;

public class CookieContainer {
    private final List<Cookie> cookies = new ArrayList<>();

    public CookieContainer set(Cookie cookie) {
        cookies.add(cookie);
        return this;
    }

    public List<Cookie> getCookies() {
        return cookies;
    }
}
