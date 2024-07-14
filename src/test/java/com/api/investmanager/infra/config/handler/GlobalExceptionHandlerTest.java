package com.api.investmanager.infra.config.handler;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GlobalExceptionHandlerTest {


    private GlobalExceptionHandler globalExceptionHandler;
    private MethodArgumentNotValidException methodArgumentNotValidException;
    private BindingResult bindingResult;

    @BeforeEach
    public void setUp() {
        globalExceptionHandler = new GlobalExceptionHandler();
        bindingResult = mock(BindingResult.class);
        methodArgumentNotValidException = new MethodArgumentNotValidException(null, bindingResult);
    }

    @Test
    public void handleValidationExceptions_ShouldReturnBadRequestAndErrorMessages() {
        FieldError fieldError1 = new FieldError("objectName", "username", "Username is mandatory");
        FieldError fieldError2 = new FieldError("objectName", "email", "Email should be valid");
        FieldError fieldError3 = new FieldError("objectName", "password", "Password should have at least 8 characters");
        when(bindingResult.getFieldErrors()).thenReturn(List.of(fieldError1, fieldError2, fieldError3));

        ResponseEntity<Map<String, String>> response = globalExceptionHandler.handleValidationExceptions(methodArgumentNotValidException);

        Map<String, String> expectedErrors = new HashMap<>();
        expectedErrors.put("username", "Username is mandatory");
        expectedErrors.put("email", "Email should be valid");
        expectedErrors.put("password", "Password should have at least 8 characters");

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(expectedErrors, response.getBody());
    }

}