package com.hossi.recrute.common.util.auth.processor;

import com.hossi.recrute.common.util.auth.AuthData;
import com.hossi.recrute.common.util.auth.AuthType;
import com.hossi.recrute.common.util.auth.Authenticator;
import com.hossi.recrute.common.util.auth.util.AuthStream;

import java.util.Optional;

public interface AuthManager {
    static Optional<Authenticator> findAuth(AuthType authType, Object object) {
        return ProxyAuthProcessor.findAuth(authType, object);
    }

    static Authenticator grantAuth(AuthType authType) {
        return ProxyAuthProcessor.grantAuth(authType);
    }

    static void renewAuth(Authenticator authenticator) {
        ProxyAuthProcessor.renewAuth(authenticator);
    }

    static void expireAuth(Authenticator authenticator) {
        ProxyAuthProcessor.expireAuth(authenticator);
    }

    static boolean renewIfAuth(AuthType authType, AuthStream authStream) {
        Optional<Authenticator> authenticator = ProxyAuthProcessor.findAuth(authType, authStream.getRequestData(authType));
        authenticator.ifPresent(auth -> {
            if (auth.isActive() && authStream.checkSession(auth.getValue())) {
                renewAuth(auth);
                authStream.setAuth(authType, auth);
            }
        });

        return authenticator.isPresent();
    }

    static Optional<Authenticator> renewAndGetIfAuth(AuthType authType, AuthStream authStream) {
        Optional<Authenticator> authenticator = ProxyAuthProcessor.findAuth(authType, authStream.getRequestData(authType));
        authenticator.ifPresent(auth -> {
            if (auth.isActive() && authStream.checkSession(auth.getValue())) {
                renewAuth(auth);
                authStream.setAuth(authType, auth);
            }
        });
        return authenticator;
    }

    static Optional<Authenticator> getIfAuth(AuthType authType, AuthStream authStream) {
        return ProxyAuthProcessor.findAuth(authType, authStream.getRequestData(authType));
    }


    static AuthData getAuthData(Authenticator authenticator, AuthStream authStream) {
        return authStream.getAuthData(authenticator);
    }

    static void setAuth(Authenticator authenticator, AuthData authData, AuthStream authStream) {
        authStream.setAuth(authenticator.getType(), authenticator, authData);
    }
}
