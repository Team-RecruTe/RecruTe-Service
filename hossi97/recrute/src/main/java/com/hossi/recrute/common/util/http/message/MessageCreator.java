package com.hossi.recrute.common.util.http.message;

import com.hossi.recrute.common.util.code.ErrorMessage;
import com.hossi.recrute.common.util.code.ServiceCode;
import com.hossi.recrute.common.util.code.ServiceCodeManager;
import com.hossi.recrute.common.util.code.ServicePrefix;
import com.hossi.recrute.common.util.code.anno.CMN;
import com.hossi.recrute.common.util.code.anno.EXM;
import com.hossi.recrute.common.util.code.anno.MBR;
import com.hossi.recrute.common.util.code.anno.RCT;

import java.lang.annotation.Annotation;
import java.util.Map;

public interface MessageCreator {

    static Message<String> create() {
        ServiceCode code = getServiceCode();

        try {
            return new Message<>(code, "");
        } catch (NullPointerException e) {
            throw new RuntimeException(e);
        }
    }

    static ErrorMessage createErrorMessage() {
        ServiceCode code = getServiceCode();

        try {
            return new ErrorMessage(code);
        } catch (NullPointerException e) {
            throw new RuntimeException(e);
        }
    }

    static Message<Map<String, Object>> create(ResponseData responseData) {
        ServiceCode code = getServiceCode();

        try {
            return new Message<>(code, responseData.getData());
        } catch (NullPointerException e) {
            throw new RuntimeException(e);
        }
    }

    private static ServiceCode getServiceCode() {
        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        if (stackTraceElements.length >= 3) {
            StackTraceElement caller = stackTraceElements[2];
            for(Annotation anno: caller.getClass().getAnnotations()) {
                if (anno instanceof CMN) {
                    return createServiceCode(ServicePrefix.CMN, ((CMN)anno).value());
                }else if (anno instanceof MBR) {
                    return createServiceCode(ServicePrefix.MBR, ((MBR)anno).value());
                }else if (anno instanceof RCT) {
                    return createServiceCode(ServicePrefix.RCT, ((RCT)anno).value());
                }else if (anno instanceof EXM) {
                    return createServiceCode(ServicePrefix.EXM, ((EXM)anno).value());
                }
            }
        }
        return null;
    }

    private static ServiceCode createServiceCode(ServicePrefix servicePrefix, String serviceNumber) {
        return ServiceCodeManager.getInstance().getServiceCode(servicePrefix, serviceNumber);
    }
}
