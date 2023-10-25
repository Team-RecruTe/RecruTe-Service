package com.hossi.recrute.common.util.code;

import java.util.HashMap;
import java.util.Map;

import static com.hossi.recrute.common.util.code.ExamCode.EXM001;
import static com.hossi.recrute.common.util.code.ExamCode.EXM003;
import static com.hossi.recrute.common.util.code.MemberCode.MBR003;
import static com.hossi.recrute.common.util.code.MemberCode.MBR102;

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

    private void mapErrorMessage(ServiceCode code) {
        serviceCodeMap.values().forEach(numberServiceCodeMap -> {
            numberServiceCodeMap.values().forEach(serviceCode -> {
                if (serviceCode.equals(EXM001)) ErrorMessage.setErrorMsg(EXM001, "Invalid Email");
                else if (serviceCode.equals(EXM003)) ErrorMessage.setErrorMsg(EXM003, "Invalid aptId");
                else if (serviceCode.equals(MBR003)) ErrorMessage.setErrorMsg(MBR003, "duplicated username");
                else if (serviceCode.equals(MBR102)) ErrorMessage.setErrorMsg(MBR102, "Invalid ID/PW");
                else ErrorMessage.setErrorMsg(code, "Server Error");
            });
        });
    }
}
