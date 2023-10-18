package com.blanc.recrute.member.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.stream.Collectors;

public class JsonUtil {

    public static String jsonParsing(HttpServletRequest request) throws IOException {
        /*StringBuilder jsonBuilder = new StringBuilder();
        try (BufferedReader br = request.getReader()) {
            String json;
            while ((json = br.readLine()) != null) {
                jsonBuilder.append(json);
            }
        }*/

        BufferedReader requestReader = request.getReader();

//        return jsonBuilder.toString();
        return requestReader.lines().collect(Collectors.joining());
    }

    public static void sendJSON(HttpServletResponse response, String json) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
}
