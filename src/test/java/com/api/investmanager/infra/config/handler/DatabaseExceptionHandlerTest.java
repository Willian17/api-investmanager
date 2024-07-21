package com.api.investmanager.infra.config.handler;

import com.api.investmanager.infra.config.handler.payload.ApiErroTemplate;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.validation.BindingResult;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class DatabaseExceptionHandlerTest {
    private DatabaseExceptionHandler databaseExceptionHandler;
    @BeforeEach
    public void setUp() {
        databaseExceptionHandler = new DatabaseExceptionHandler();
    }

    @Test
    public void handleDataIntegrityViolationException_ShouldReturnConflictAndExceptionMessage() {
        DataIntegrityViolationException ex = new DataIntegrityViolationException("Duplicate key");

        ResponseEntity<ApiErroTemplate> response = databaseExceptionHandler.handleDataIntegrityViolationException(ex);

        assertNotNull(response);
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("Duplicate key", response.getBody().getMessage());
    }

    @Test
    public void handleEntityNotFoundException_ShouldReturnNotFoundAndExceptionMessage() {
        EntityNotFoundException ex = new EntityNotFoundException("Entity not found");

        ResponseEntity<ApiErroTemplate> response = databaseExceptionHandler.handleEntityNotFoundException(ex);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Entity not found", response.getBody().getMessage());
    }

    @Test
    public void handleEmptyResultDataAccessException_ShouldReturnNotFoundAndExceptionMessage() {
        EmptyResultDataAccessException ex = new EmptyResultDataAccessException(1);

        ResponseEntity<ApiErroTemplate> response = databaseExceptionHandler.handleEmptyResultDataAccessException(ex);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Incorrect result size: expected 1, actual 0", response.getBody().getMessage());
    }

    @Test
    public void handleOptimisticLockingFailureException_ShouldReturnConflictAndExceptionMessage() {
        ObjectOptimisticLockingFailureException ex = new ObjectOptimisticLockingFailureException("Optimistic locking failure", null);

        ResponseEntity<ApiErroTemplate> response = databaseExceptionHandler.handleOptimisticLockingFailureException(ex);

        assertNotNull(response);
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("Optimistic locking failure", response.getBody().getMessage());
    }
}