package com.hossi.recrute.common.request;

import jakarta.servlet.http.HttpServletRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.stream.Collectors;

public interface RequestUtil {
    static String parseJson(HttpServletRequest request) throws IOException {
        return request.getReader().lines().collect(Collectors.joining());
    }
}
