package com.hossi.recrute.common.response;

import java.util.HashMap;
import java.util.Map;

public class AttributeContainer {
    private final Map<String, Object> attributes = new HashMap<>();

    public AttributeContainer set(String key, Object value) {
        attributes.put(key, value);
        return this;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }
}
