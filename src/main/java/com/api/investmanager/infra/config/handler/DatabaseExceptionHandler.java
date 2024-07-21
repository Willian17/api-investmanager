package com.api.investmanager.infra.config.handler;

import com.api.investmanager.infra.config.handler.payload.ApiErroTemplate;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DatabaseExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiErroTemplate> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        return UtilException.handleException(HttpStatus.CONFLICT, ex);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiErroTemplate> handleEntityNotFoundException(EntityNotFoundException ex) {
        return UtilException.handleException(HttpStatus.NOT_FOUND, ex);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<ApiErroTemplate> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex) {
        return UtilException.handleException(HttpStatus.NOT_FOUND, ex);
    }

    @ExceptionHandler(ObjectOptimisticLockingFailureException.class)
    public ResponseEntity<ApiErroTemplate> handleOptimisticLockingFailureException(ObjectOptimisticLockingFailureException ex) {
        return UtilException.handleException(HttpStatus.CONFLICT, ex);
    }
}
