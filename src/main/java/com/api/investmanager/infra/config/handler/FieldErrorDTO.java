package com.api.investmanager.infra.config.handler;

import lombok.Getter;

@Getter
public class FieldErrorDTO {
    private final String field;
    private final String message;

    public FieldErrorDTO(String field, String message) {
        this.field = field;
        this.message = message;
    }
}
