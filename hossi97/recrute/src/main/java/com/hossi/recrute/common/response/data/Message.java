package com.hossi.recrute.common.response.data;

import com.hossi.recrute.common.response.service.ServiceCode;

import java.util.List;

public class Message<T> {
    private final ServiceCode code;
    private final String description;
    private final Result result;
    private final T data;

    public Message(ServiceCode code, Result result, T data) {
        this.code = code;
        this.description = code.getDescription();
        this.result = result;
        this.data = data;
    }
}
