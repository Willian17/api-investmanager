package com.api.investmanager.adapters.input.controller.auth;

import com.api.investmanager.adapters.input.controller.auth.dto.SigninRequestDTO;
import com.api.investmanager.adapters.input.controller.auth.dto.SigninResponseDTO;
import com.api.investmanager.adapters.input.controller.auth.dto.SignupRequestDTO;
import com.api.investmanager.core.application.port.input.user.UserFacade;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {
    @InjectMocks
    private AuthController authController;

    @Mock
    private UserFacade userFacade;

    @Test
    void signupUser() {
        SignupRequestDTO req = new SignupRequestDTO("nome", "email", "senha");

        authController.signupUser(req);

        verify(userFacade).createUser(req);
    }

    @Test
    void signinUser() {
        SigninRequestDTO req = new SigninRequestDTO("email", "senha");
        SigninResponseDTO responseDtoExpected = new SigninResponseDTO("user_token_teste");

        when(userFacade.authUser(req)).thenReturn("user_token_teste");

        ResponseEntity<SigninResponseDTO> response = authController.signinUser(req);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDtoExpected, response.getBody());
        verify(userFacade).authUser(req);
    }

}