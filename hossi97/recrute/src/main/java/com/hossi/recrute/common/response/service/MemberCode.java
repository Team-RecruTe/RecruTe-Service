package com.hossi.recrute.common.response.service;

import java.util.HashMap;
import java.util.Map;

public enum MemberCode implements ServiceCode {
    MBR001("회원가입 페이지 조회"), MBR002("회원 등록"),
    MBR101("로그인 페이지 조회"), MBR102("로그인"), MBR103("로그아웃"),
    MBR201("인증 및 권한 조회"),
    MBR301("이메일 인증 페이지 조회"), MBR302("인증 이메일 전송"), MBR303("이메일 인증");

    private final static Map<String, ServiceCode> codeBox = new HashMap<>();
    private final static ServicePrefix prefix = ServicePrefix.MBR;
    private final String description;

    MemberCode(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    static Map<String, ServiceCode> getCodeBox() {
        ServiceCode.mapCodeBox(prefix, codeBox);
        return codeBox;
    }

    static ServicePrefix getPrefix() {
        return prefix;
    }
}
