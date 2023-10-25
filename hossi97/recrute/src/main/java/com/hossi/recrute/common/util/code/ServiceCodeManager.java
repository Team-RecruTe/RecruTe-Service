package com.hossi.recrute.common.util.code;

import java.util.HashMap;
import java.util.Map;

public class ServiceCodeManager {
    private static final ServiceCodeManager INSTANCE = new ServiceCodeManager();
    private final Map<ServicePrefix, Map<String, ServiceCode>> serviceCodeMap = new HashMap<>();

    private ServiceCodeManager() {
        mapServiceCode(CommonCode.getPrefix(), CommonCode.getCodes());
        mapServiceCode(ExamCode.getPrefix(), ExamCode.getCodes());
        mapServiceCode(MemberCode.getPrefix(), MemberCode.getCodes());
        mapServiceCode(RecruitmentCode.getPrefix(), RecruitmentCode.getCodes());
    }

    public static ServiceCodeManager getInstance() {
        return INSTANCE;
    }

    public ServiceCode getServiceCode(ServicePrefix prefix, String number) {
        return serviceCodeMap.get(prefix).get(number);
    }

    private void mapServiceCode(ServicePrefix prefix, Map<String, ServiceCode> codeBox) {
        serviceCodeMap.putIfAbsent(prefix, codeBox);
    }
}
