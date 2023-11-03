package com.hossi.recrute.member.vo;

public enum Certification {
    NON_CERTIFIED("미인증"), CERTIFIED("인증");

    private final String description;

    Certification(String description) {
        this.description = description;
    }
}
