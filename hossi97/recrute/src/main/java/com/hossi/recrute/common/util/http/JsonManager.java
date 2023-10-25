package com.hossi.recrute.common.util.http;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

public class JsonManager {
    private static final Gson gson = new Gson();

    public static JsonElement toJson(Object message) {
        return gson.toJsonTree(message);
    }

    public static <T> T fromJson(String json, Class<T> tClass) {
        return gson.fromJson(json, tClass);
    }
}
