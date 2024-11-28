package com.api.investmanager.infra.exception;

public class NotFoundException extends BusinessException{

    public NotFoundException(String message) {
        super(message);
    }
}
