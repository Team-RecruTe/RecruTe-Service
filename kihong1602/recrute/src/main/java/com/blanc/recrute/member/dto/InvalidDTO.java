package com.blanc.recrute.member.dto;

public class InvalidDTO {
    private final String data;

    public InvalidDTO(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "InvalidDTO{" +
                "data='" + data + '\'' +
                '}';
    }
}
