package com.hossi.recrute.common.util.auth.util;

import com.hossi.recrute.common.util.auth.AuthData;
import com.hossi.recrute.common.util.auth.AuthType;
import com.hossi.recrute.common.util.auth.Authenticator;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthStream {
    private final HttpServletRequest request;
    private final HttpServletResponse response;

    public AuthStream(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public void setAuth(AuthType authType, Authenticator authenticator, AuthData authData) {
        switch (authType) {
            case COOKIE -> {
                response.addCookie((Cookie) authenticator.get());
                request.getSession().setAttribute(authenticator.getValue(), authType);
            }
        }
    }

    public void setAuth(AuthType authType, Authenticator authenticator) {
        switch (authType) {
            case COOKIE -> {
                response.addCookie((Cookie) authenticator.get());
                request.getSession().setAttribute(authenticator.getValue(), authType);
            }
        }
    }

    public Object getRequestData(AuthType authType) {
        return switch (authType) {
            case COOKIE -> request.getCookies();
            default -> null;
        };
    }

    public boolean checkSession(String key) {
        return request.getSession().getAttribute(key) != null;
    }

    public AuthData getAuthData(Authenticator authenticator) {
        return (AuthData) request.getSession().getAttribute(authenticator.getValue());
    }
}
