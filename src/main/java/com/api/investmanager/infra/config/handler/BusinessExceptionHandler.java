package com.api.investmanager.infra.config.handler;

import com.api.investmanager.infra.config.exception.AlreadyExistsException;
import com.api.investmanager.infra.config.handler.payload.ApiErroTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BusinessExceptionHandler {

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ApiErroTemplate> handleAlreadyExistsException(AlreadyExistsException ex) {
        return UtilException.handleException(HttpStatus.BAD_REQUEST, ex);
    }
}
