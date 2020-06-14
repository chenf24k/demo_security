package com.example.spring_security.auth;

import com.example.spring_security.auth.exception.CustomException;
import com.example.spring_security.auth.exception.CustomExceptionType;
import com.example.spring_security.model.AjaxResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * session 实现超时策略
 */
public class ExpiredSessionStrategy implements SessionInformationExpiredStrategy {

    private static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent sessionInformationExpiredEvent) throws IOException, ServletException {
        objectMapper.writeValueAsString(AjaxResponse.error(new CustomException(CustomExceptionType.SESSION_ERROR, CustomExceptionType.SESSION_ERROR.getTypeDesc())));
        sessionInformationExpiredEvent.getResponse().setContentType("application/json;charset=UTF-8");
        sessionInformationExpiredEvent.getResponse().getWriter().write(
                objectMapper.writeValueAsString(
                        new CustomException(
                                CustomExceptionType.SESSION_ERROR, CustomExceptionType.SESSION_ERROR.getTypeDesc()
                        )
                )
        );
    }
}
