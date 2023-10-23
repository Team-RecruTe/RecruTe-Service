package com.hossi.recrute.common.response.data;

public class ResponseData<V> {
    private final String key;
    private final V value;

    public ResponseData(String key, V value) {
        this.key = key;
        this.value = value;
    }
}
