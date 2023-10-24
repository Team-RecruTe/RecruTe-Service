package com.hossi.recrute.common.util.http.message;

public class Result {
    private final Boolean success;
    private final String message;
    
    public Result(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
