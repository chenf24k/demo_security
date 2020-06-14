package com.example.spring_security.model;

import com.example.spring_security.auth.exception.CustomException;
import com.example.spring_security.auth.exception.CustomExceptionType;
import lombok.Data;

@Data
public class AjaxResponse {
    private int code;
    private String message;
    private Object data;

    public AjaxResponse() {
    }

    public static AjaxResponse error(CustomException e) {
        AjaxResponse response = new AjaxResponse();
        response.setCode(e.getCode());
        if (e.getCode() == CustomExceptionType.USER_INPUT_ERROR.getCode())
            response.setMessage(e.getMessage());
        else if (e.getCode() == CustomExceptionType.SYSTEM_ERROR.getCode())
            response.setMessage(e.getMessage());
        else
            response.setMessage("系统出现未知异常，请联系管理员。");
        return response;
    }

    public static AjaxResponse success() {
        AjaxResponse response = new AjaxResponse();
        response.setCode(200);
        response.setMessage("success");
        return response;
    }

    public static AjaxResponse success(Object data) {
        AjaxResponse response = new AjaxResponse();
        response.setCode(200);
        response.setMessage("success");
        response.setData(data);
        return response;
    }
}
