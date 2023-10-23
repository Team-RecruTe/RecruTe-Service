package com.hossi.recrute.common.response.data;

import com.hossi.recrute.common.response.service.ServiceCode;
import com.hossi.recrute.common.response.service.ServiceCodeManager;
import com.hossi.recrute.common.response.service.ServicePrefix;

import java.util.Map;

public class MessageCreator {
    private static final MessageCreator INSTANCE = new MessageCreator();

    private MessageCreator(){
    }

    public static MessageCreator getInstance() {
        return INSTANCE;
    }

    public Message<String> create(ServicePrefix servicePrefix, String serviceNumber, boolean success, String message) {
        ServiceCode serviceCode = createServiceCode(servicePrefix, serviceNumber);
        Result result = createResult(success, message);
        return new Message<>(serviceCode, result, "");
    }

    public Message<Map<String, Object>> create(ServicePrefix servicePrefix, String serviceNumber, boolean success, String message, ResponseData responseData) {
        ServiceCode serviceCode = createServiceCode(servicePrefix, serviceNumber);
        Result result = createResult(success, message);
        return new Message<>(serviceCode, result, responseData.getData());
    }

    private ServiceCode createServiceCode(ServicePrefix servicePrefix, String serviceNumber) {
        return ServiceCodeManager.getInstance().getServiceCode(servicePrefix, serviceNumber);
    }

    private Result createResult(boolean success, String message) {
        return new Result(success, message);
    }
}
