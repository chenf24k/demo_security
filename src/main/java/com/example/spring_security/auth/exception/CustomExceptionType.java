package com.example.spring_security.auth.exception;

public enum CustomExceptionType {
    USER_INPUT_ERROR(400, "用户或密码输入错误"),
    SYSTEM_ERROR(500, "系统服务异常"),
    SESSION_ERROR(600, "您在其它设备设备登录"),
    OTHER_ERROR(999, "其它未知异常");

    CustomExceptionType(int code, String typeDesc) {
        this.code = code;
        this.typeDesc = typeDesc;
    }

    private int code;
    private String typeDesc;

    public int getCode() {
        return code;
    }

    public String getTypeDesc() {
        return typeDesc;
    }
}
