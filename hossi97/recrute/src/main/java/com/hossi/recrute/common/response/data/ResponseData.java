package com.hossi.recrute.common.response.data;

import java.util.HashMap;
import java.util.Map;

public class ResponseData {
    private final Map<String, Object> data = new HashMap<>();

    public void set(String key, Object value) {
        data.put(key, value);
    }

    public Map<String, Object> getData() {
        return data;
    }
}
