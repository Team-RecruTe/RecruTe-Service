package com.hossi.recrute.common.request;

import com.hossi.recrute.common.response.CookieContainer;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

public interface RequestUtil {
    static String parseJson(HttpServletRequest request) throws IOException {
        return request.getReader().lines().collect(Collectors.joining());
    }

    static Map<String, String[]> getParameters(HttpServletRequest request) {
        return request.getParameterMap();
    }


    static HttpSession getSession(HttpServletRequest request) {
        return request.getSession();
    }

}
