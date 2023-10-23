package com.hossi.recrute.common.response.data;

public class Result {
    private final Boolean success;
    private final String message;
    
    public Result(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
