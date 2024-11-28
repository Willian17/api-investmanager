package com.api.investmanager.infra.exception.handler;

import com.api.investmanager.core.domain.exception.AlreadyExistsException;
import com.api.investmanager.core.domain.exception.ClientException;
import com.api.investmanager.infra.exception.UnauthorizedException;
import com.api.investmanager.infra.exception.handler.payload.ApiErroTemplate;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class BusinessExceptionHandler {

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ApiErroTemplate> handleAlreadyExistsException(AlreadyExistsException ex) {
        return UtilException.handleException(HttpStatus.BAD_REQUEST, ex);
    }

    @ExceptionHandler(ClientException.class)
    public ResponseEntity<ApiErroTemplate> handleClientException(ClientException ex) {
        return UtilException.handleException(HttpStatus.BAD_REQUEST, ex);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ApiErroTemplate> handleUnauthorizedException(UnauthorizedException ex) {
        return UtilException.handleException(HttpStatus.UNAUTHORIZED, ex);
    }
}
