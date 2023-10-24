package com.hossi.recrute.common.util.service;

import java.util.Arrays;
import java.util.Map;

public interface ServiceCode {
    String getDescription();

    static void mapCodeBox(ServicePrefix prefix, Map<String, ServiceCode> codeBox) {
        switch (prefix) {
            case CMN:
                Arrays.stream(CommonCode.values()).forEach(code -> {
                    codeBox.putIfAbsent(code.name().substring(3), code);
                });
                break;
            case MBR:
                Arrays.stream(MemberCode.values()).forEach(code -> {
                    codeBox.putIfAbsent(code.name().substring(3), code);
                });
                break;
            case EXM:
                Arrays.stream(ExamCode.values()).forEach(code -> {
                    codeBox.putIfAbsent(code.name().substring(3), code);
                });
                break;
            case RCT:
                Arrays.stream(RecruitmentCode.values()).forEach(code -> {
                    codeBox.putIfAbsent(code.name().substring(3), code);
                });
        }
    }

}
