package com.blanc.recrute.member.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import java.util.UUID;

public class Authenticater {

  private Cookie authCookie;

  public boolean isAuthenticated(HttpServletRequest request) {
    if (request.getCookies() != null) {
      for (Cookie cookie : request.getCookies()) {
        if (cookie != null && cookie.getName().equals("sid")) {
          renewAuthCookie(cookie);
          return true;
        }
      }

    }
    return false;
  }

  private void renewAuthCookie(Cookie cookie) {
    cookie.setMaxAge(3600);
    authCookie = cookie;
  }

  public Cookie expireAuthCookie() {
    authCookie.setMaxAge(0);
    return authCookie;
  }

  public void setAuthCookie(HttpServletRequest request, String id) {
    String uuid = String.valueOf(UUID.randomUUID());
    Cookie cookie = new Cookie("sid", uuid);
    request.getSession().setAttribute(uuid, id);
    cookie.setHttpOnly(true);
    cookie.setMaxAge(3600);
    authCookie = cookie;
  }

  public Cookie getAuthCookie() {
    return authCookie;
  }
}
