package com.api.investmanager.infra.exception.handler.payload;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiErroTemplate {
    private final LocalDateTime timestamp;
    private final Integer statusCode;
    private final String message;
    private List<FieldErrorDTO> fieldErrors;

    public ApiErroTemplate(Integer statusCode, String message,  List<FieldErrorDTO> fieldErrors) {
        this.timestamp = LocalDateTime.now();
        this.statusCode = statusCode;
        this.message = message;
        this.fieldErrors = fieldErrors;
    }

    public ApiErroTemplate(Integer statusCode, String message) {
        this.timestamp = LocalDateTime.now();
        this.statusCode = statusCode;
        this.message = message;
    }
}
