package com.example.spring_security.auth.exception;

public class CustomException {
    private int code;
    private String message;

    public CustomException() {
    }

    public CustomException(CustomExceptionType exceptionType, String message){
        this.code = exceptionType.getCode();
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
