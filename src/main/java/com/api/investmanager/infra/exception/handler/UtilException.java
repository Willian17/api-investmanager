package com.api.investmanager.infra.exception.handler;

import com.api.investmanager.infra.exception.handler.payload.ApiErroTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public final class UtilException {

    public static ResponseEntity<ApiErroTemplate> handleException(HttpStatus httpStatus, Exception exception) {
        ApiErroTemplate apiError = new ApiErroTemplate(httpStatus.value(), exception.getMessage());
        return new ResponseEntity<>(apiError, httpStatus);
    }

    private UtilException() {

    }

}
