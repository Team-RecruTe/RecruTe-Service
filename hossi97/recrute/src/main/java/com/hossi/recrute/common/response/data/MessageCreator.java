package com.hossi.recrute.common.response.data;

import com.hossi.recrute.common.response.service.ServiceCode;
import com.hossi.recrute.common.response.service.ServiceCodeManager;
import com.hossi.recrute.common.response.service.ServicePrefix;

import java.util.Map;

public interface MessageCreator {
    static Message<String> create(ServicePrefix servicePrefix, String serviceNumber, boolean success, String message) {
        ServiceCode serviceCode = createServiceCode(servicePrefix, serviceNumber);
        Result result = createResult(success, message);
        return new Message<>(serviceCode, result, "");
    }

    static Message<Map<String, Object>> create(ServicePrefix servicePrefix, String serviceNumber, boolean success, String message, ResponseData responseData) {
        ServiceCode serviceCode = createServiceCode(servicePrefix, serviceNumber);
        Result result = createResult(success, message);
        return new Message<>(serviceCode, result, responseData.getData());
    }

    private static ServiceCode createServiceCode(ServicePrefix servicePrefix, String serviceNumber) {
        return ServiceCodeManager.getInstance().getServiceCode(servicePrefix, serviceNumber);
    }

    private static Result createResult(boolean success, String message) {
        return new Result(success, message);
    }
}
