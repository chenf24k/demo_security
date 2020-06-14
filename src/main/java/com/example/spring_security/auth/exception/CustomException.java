package com.example.spring_security.auth.exception;

public class CustomException {
    private int code;
    private String message;

    public CustomException() {
    }

    public CustomException(CustomExceptionType exceptionType) {
        this.code = exceptionType.getCode();
        this.message = exceptionType.getTypeDesc();
    }

    public CustomException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
