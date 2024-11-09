package com.api.investmanager.infra.config.exception;

public class ClientException extends RuntimeException {
    public ClientException(String message) {
        super(message);
    }
}
