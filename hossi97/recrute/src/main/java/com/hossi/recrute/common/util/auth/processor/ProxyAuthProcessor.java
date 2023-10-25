package com.hossi.recrute.common.util.auth.processor;

import com.hossi.recrute.common.util.auth.AuthType;
import com.hossi.recrute.common.util.auth.Authenticator;

import java.util.Optional;

public class ProxyAuthProcessor {
    private static final AuthProcessor.CookieProcessor cookieProcessor = AuthProcessor.CookieProcessor.getINSTANCE();

    private ProxyAuthProcessor() {
    }

    static Optional<Authenticator> findAuth(AuthType authType, Object data) {
        return switch (authType) {
            case COOKIE -> Optional.ofNullable(cookieProcessor.findAuthenticator(data));
            default -> Optional.empty();
        };
    }

    static Authenticator grantAuth(AuthType authType) {
        switch (authType) {
            case COOKIE -> {
                return cookieProcessor.publishAuthenticator();
            }
            default -> {
                return null;
            }
        }
    }

    static void renewAuth(Authenticator authenticator) {
        AuthType type = authenticator.getType();
        switch (type) {
            case COOKIE -> {
                cookieProcessor.renewAuthenticator(authenticator);
            }
        }
    }

    static void expireAuth(Authenticator authenticator) {
        AuthType type = authenticator.getType();
        switch (type) {
            case COOKIE -> {
                cookieProcessor.expireAuthenticator(authenticator);
            }
        }
    }
}
