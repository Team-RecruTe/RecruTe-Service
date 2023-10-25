package com.hossi.recrute.common.util.http;

import com.google.gson.Gson;

public class JsonManager {
    private static final Gson gson = new Gson();

    public static String toJson(Object message) {
        return gson.toJson(message);
    }

    public static <T> T fromJson(String json, Class<T> tClass) {
        return gson.fromJson(json, tClass);
    }
}
