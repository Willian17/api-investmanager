package com.api.investmanager.infra.config.handler;

import com.api.investmanager.infra.exception.handler.NotValidExceptionHandler;
import com.api.investmanager.infra.exception.handler.payload.ApiErroTemplate;
import com.api.investmanager.infra.exception.handler.payload.FieldErrorDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class NotValidExceptionHandlerTest {

    private NotValidExceptionHandler notValidExceptionHandler;
    private MethodArgumentNotValidException methodArgumentNotValidException;
    private BindingResult bindingResult;

    @BeforeEach
    public void setUp() {
        notValidExceptionHandler = new NotValidExceptionHandler();
        bindingResult = mock(BindingResult.class);
        methodArgumentNotValidException = new MethodArgumentNotValidException(null, bindingResult);
    }

    @Test
    public void handleValidationExceptions_ShouldReturnBadRequestAndErrorMessages() {
        FieldError fieldError1 = new FieldError("objectName", "username", "Username is mandatory");
        FieldError fieldError2 = new FieldError("objectName", "email", "Email should be valid");
        FieldError fieldError3 = new FieldError("objectName", "password", "Password is mandatory");
        FieldError fieldError4 = new FieldError("objectName", "password", "Password should have at least 8 characters");
        when(bindingResult.getFieldErrors()).thenReturn(List.of(fieldError1, fieldError2, fieldError3, fieldError4));

        ResponseEntity<ApiErroTemplate> response = notValidExceptionHandler.handleValidationExceptions(methodArgumentNotValidException);

        List<FieldErrorDTO> expectedErrors = List.of(
                new FieldErrorDTO("password", "Password is mandatory"),
                new FieldErrorDTO("email", "Email should be valid"),
                new FieldErrorDTO("username", "Username is mandatory")

        );

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        ApiErroTemplate apiErroTemplate = response.getBody();
        assertNotNull(apiErroTemplate);
        assertEquals(HttpStatus.BAD_REQUEST.value(), apiErroTemplate.getStatusCode());
        assertEquals(expectedErrors.size(), apiErroTemplate.getFieldErrors().size());

        for (int i = 0; i < expectedErrors.size(); i++) {
            FieldErrorDTO expectedError = expectedErrors.get(i);
            FieldErrorDTO actualError = apiErroTemplate.getFieldErrors().get(i);
            assertEquals(expectedError.getField(), actualError.getField());
            assertEquals(expectedError.getMessage(), actualError.getMessage());
        }

        assertNotNull(apiErroTemplate.getTimestamp());
        assertEquals("Erro de validação: Por favor, corrija os campos destacados e tente novamente.", apiErroTemplate.getMessage());
    }

    @Test
    public void handleValidationExceptions_ShouldOrdemPriorityCodes() {
        FieldError fieldErrorNameSize = new FieldError("objectName", "username", null, false, new String[]{"Size"}, null, "Nome deve ter 14 caracteres");
        FieldError fieldErrorNameNotNull = new FieldError("objectName", "username", null, false, new String[]{"NotNull"}, null, "Nome é obrigatório");
        FieldError fieldErrorEmailInvalid = new FieldError("objectName", "email", null, false, new String[]{"Email"}, null, "Email deve ser válido");
        FieldError fieldErrorEmailNotBlank = new FieldError("objectName", "email", null, false, new String[]{"NotBlank"}, null, "Email deve ser obrigatório");
        FieldError fieldErrorPasswordSize = new FieldError("objectName", "password", null, false, new String[]{"Size"}, null, "Senha deve ter no minimo 8 caracteres");
        FieldError fieldErrorPasswordPattern = new FieldError("objectName", "password", null, false, new String[]{"Pattern"}, null, "Senha deve ter caracteres especiais");
        when(bindingResult.getFieldErrors()).thenReturn(List.of(fieldErrorNameSize, fieldErrorNameNotNull, fieldErrorEmailInvalid, fieldErrorEmailNotBlank, fieldErrorPasswordSize, fieldErrorPasswordPattern));

        ResponseEntity<ApiErroTemplate> response = notValidExceptionHandler.handleValidationExceptions(methodArgumentNotValidException);

        List<FieldErrorDTO> expectedErrors = List.of(
                new FieldErrorDTO("password", "Senha deve ter no minimo 8 caracteres"),
                new FieldErrorDTO("email", "Email deve ser obrigatório"),
                new FieldErrorDTO("username", "Nome é obrigatório")
        );

        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        ApiErroTemplate apiErroTemplate = response.getBody();
        assertNotNull(apiErroTemplate);
        assertEquals(HttpStatus.BAD_REQUEST.value(), apiErroTemplate.getStatusCode());
        assertEquals(expectedErrors.size(), apiErroTemplate.getFieldErrors().size());

        for (int i = 0; i < expectedErrors.size(); i++) {
            FieldErrorDTO expectedError = expectedErrors.get(i);
            FieldErrorDTO actualError = apiErroTemplate.getFieldErrors().get(i);
            assertEquals(expectedError.getField(), actualError.getField());
            assertEquals(expectedError.getMessage(), actualError.getMessage());
        }

        assertNotNull(apiErroTemplate.getTimestamp());
        assertEquals("Erro de validação: Por favor, corrija os campos destacados e tente novamente.", apiErroTemplate.getMessage());
    }

}