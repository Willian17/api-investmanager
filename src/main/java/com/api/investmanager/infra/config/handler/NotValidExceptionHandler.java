package com.api.investmanager.infra.config.handler;
import com.api.investmanager.infra.config.handler.payload.ApiErroTemplate;
import com.api.investmanager.infra.config.handler.payload.FieldErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.*;

@RestControllerAdvice
public class NotValidExceptionHandler {

    private static final String[] PRIORITY_CODES = {"NotNull", "NotBlank"};

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErroTemplate> handleValidationExceptions(MethodArgumentNotValidException ex) {
        List<FieldErrorDTO> fieldErrorsDTO = getFieldErrorDTOS(ex);

        HttpStatus status = HttpStatus.BAD_REQUEST;
        ApiErroTemplate apiErroTemplate = new ApiErroTemplate(
                status.value(),
                "Erro de validação: Por favor, corrija os campos destacados e tente novamente.",
                fieldErrorsDTO
        );

        return new ResponseEntity<>(apiErroTemplate, status);
    }

    private static List<FieldErrorDTO> getFieldErrorDTOS(MethodArgumentNotValidException ex) {
        Map<String, List<FieldError>> fieldErrorsMap = getFieldErrosMap(ex);

        return getFieldErrorDTOS(fieldErrorsMap);
    }


    private static Map<String, List<FieldError>> getFieldErrosMap(MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        Map<String, List<FieldError>> fieldErrorsMap = new HashMap<>();

        for (FieldError fieldError : fieldErrors) {
            String field = fieldError.getField();

            fieldErrorsMap.computeIfAbsent(field, key -> new ArrayList<>()).add(fieldError);
        }
        return fieldErrorsMap;
    }

    private static List<FieldErrorDTO> getFieldErrorDTOS(Map<String, List<FieldError>> fieldErrorsMap) {
        List<FieldErrorDTO> fieldErrorsDTO = new ArrayList<>();
        for (Map.Entry<String, List<FieldError>> entry : fieldErrorsMap.entrySet()) {
            String field = entry.getKey();
            List<FieldError> fieldErrors = entry.getValue();

            FieldError firstFieldError = prioritizeFieldError(fieldErrors);
            fieldErrorsDTO.add(new FieldErrorDTO(field, firstFieldError.getDefaultMessage()));
        }
        return fieldErrorsDTO;
    }

    private static FieldError prioritizeFieldError(List<FieldError> errors) {
        return errors.stream()
                .filter(error -> {
                    String[] codes = error.getCodes();
                    return codes != null && codes.length > 0 && containsPriorityCode(codes);
                })
                .findFirst()
                .orElse(errors.getFirst());
    }
    private static boolean containsPriorityCode(String[] codes) {
        return Arrays.stream(codes)
                .anyMatch(code -> Arrays.asList(PRIORITY_CODES).contains(code));
    }

}