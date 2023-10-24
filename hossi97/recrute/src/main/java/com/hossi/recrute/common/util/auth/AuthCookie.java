package com.hossi.recrute.common.util.auth;

import jakarta.servlet.http.Cookie;

public class AuthCookie implements Authenticator {
    private Cookie cookie;

    public AuthCookie() {
        cookie = null;
    }

    public AuthCookie(Cookie cookie) {
        this.cookie = cookie;
    }

    public boolean isActivate() {
        return (cookie != null && cookie.getMaxAge() > 0);
    }

    public void setMaxAge(int age) {
        cookie.setMaxAge(age);
    }

    public Cookie getCookie() {
        return cookie;
    }

    public String getValue() {
        return cookie.getValue();
    }

    public String getKey() {
        return cookie.getName();
    }
}
