package com.api.investmanager.infra.config.handler;

import com.api.investmanager.infra.exception.handler.GlobalExceptionHandler;
import com.api.investmanager.infra.exception.handler.payload.ApiErroTemplate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class GlobalExceptionHandlerTest {
    private GlobalExceptionHandler globalExceptionHandler;

    @BeforeEach
    public void setUp() {
        globalExceptionHandler = new GlobalExceptionHandler();
    }

    @Test
    public void handleException_ShouldReturnInternalServerErrorAndExceptionMessage() {
        Exception ex = new Exception("Erro ao split[0] string name");

        ResponseEntity<ApiErroTemplate> response = globalExceptionHandler.handleException(ex);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Erro ao split[0] string name", response.getBody().getMessage());
    }
}