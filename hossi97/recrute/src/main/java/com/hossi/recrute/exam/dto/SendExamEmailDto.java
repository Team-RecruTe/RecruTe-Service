package com.hossi.recrute.exam.dto;

public class SendExamEmailDto {
    private final String userEmail;
    private final String aptId;
    private final String companyName;

    SendExamEmailDto(String userEmail, String aptId, String companyName) {
        this.userEmail = userEmail;
        this.aptId = aptId;
        this.companyName = companyName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getAptId() {
        return aptId;
    }

    public String getCompanyName() {
        return companyName;
    }
}
