package com.api.investmanager.infra.config.handler;

import com.api.investmanager.infra.config.handler.payload.ApiErroTemplate;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErroTemplate> handleException(Exception ex) {
        return UtilException.handleException(HttpStatus.INTERNAL_SERVER_ERROR, ex);
    }
}
