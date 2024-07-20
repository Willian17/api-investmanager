package com.api.investmanager.infra.config.handler;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ApiErroTemplate {
    private final LocalDateTime timestamp;
    private final Integer statusCode;
    private final String message;
    private final List<FieldErrorDTO> fieldErrors;

    public ApiErroTemplate(Integer statusCode, String message,  List<FieldErrorDTO> fieldErrors) {
        this.timestamp = LocalDateTime.now();
        this.statusCode = statusCode;
        this.message = message;
        this.fieldErrors = fieldErrors;
    }
}
