package com.hossi.recrute.common.util.auth;

import jakarta.servlet.http.Cookie;

import java.util.UUID;

public interface AuthProcessor {

    static void setAuthCookie(AuthCookie authCookie) {
        String uuid = String.valueOf(UUID.randomUUID());
        Cookie cookie = new Cookie("sid", uuid);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(3600);
    }

    static void renewAuthCookie(AuthCookie authCookie) {
        authCookie.setMaxAge(3600);
    }

    static AuthCookie expireAuthCookie(AuthCookie authCookie) {
        authCookie.setMaxAge(0);
        return authCookie;
    }
}
