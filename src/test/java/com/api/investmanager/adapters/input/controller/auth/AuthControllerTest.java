package com.api.investmanager.adapters.input.controller.auth;

import com.api.investmanager.adapters.input.controller.auth.dto.SignupRequestDTO;
import com.api.investmanager.core.application.port.input.user.UserFacade;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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

}