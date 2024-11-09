package com.api.investmanager.infra.config.handler;

import com.api.investmanager.infra.config.exception.AlreadyExistsException;
import com.api.investmanager.infra.config.exception.ClientException;
import com.api.investmanager.infra.config.handler.payload.ApiErroTemplate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

class BusinessExceptionHandlerTest {

    private BusinessExceptionHandler businessExceptionHandler;

    @BeforeEach
    public void setUp() {
        businessExceptionHandler = new BusinessExceptionHandler();
    }

    @Test
    public void handleAlreadyExistsException_ShouldReturnBadRequestAndExceptionMessage() {
        AlreadyExistsException ex = new AlreadyExistsException("Email já existente");

        ResponseEntity<ApiErroTemplate> response = businessExceptionHandler.handleAlreadyExistsException(ex);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Email já existente", response.getBody().getMessage());
    }

    @Test
    public void handleClientException_ShouldReturnBadRequestAndExceptionMessage() {
        ClientException ex = new ClientException("Email invalido");

        ResponseEntity<ApiErroTemplate> response = businessExceptionHandler.handleClientException(ex);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Email invalido", response.getBody().getMessage());
    }


}