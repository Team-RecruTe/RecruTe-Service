package com.hossi.recrute.common.util.http.message;

import com.hossi.recrute.common.util.code.ServiceCode;

public class Message<T> {
    private final ServiceCode code;
    private final String description;
    private final T data;

    public Message(ServiceCode code, T data) {
        this.code = code;
        this.description = code.getDescription();
        this.data = data;
    }
}
