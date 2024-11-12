package com.api.investmanager.infra.exception;

public class ClientException extends RuntimeException {
    public ClientException(String message) {
        super(message);
    }
}
