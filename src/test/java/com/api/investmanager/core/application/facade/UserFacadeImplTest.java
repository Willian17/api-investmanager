package com.api.investmanager.core.application.facade;

import com.api.investmanager.adapters.input.controller.auth.dto.SigninRequestDTO;
import com.api.investmanager.adapters.input.controller.auth.dto.SignupRequestDTO;
import com.api.investmanager.core.application.port.input.user.AuthUserUseCase;
import com.api.investmanager.core.application.port.input.user.CreateUserUseCase;
import com.api.investmanager.core.domain.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class UserFacadeImplTest {
    @InjectMocks
    private UserFacadeImpl userFacade;

    @Mock
    private CreateUserUseCase createUserUseCase;

    @Mock
    private AuthUserUseCase authUserUseCase;


    @Test
    void createUserFacade() {
        SignupRequestDTO req = new SignupRequestDTO("name", "email", "password");
        User userExpected = new User(null, req.getName(), req.getEmail(), req.getPassword());

        userFacade.createUser(req);

        verify(createUserUseCase).execute(userExpected);
    }

    @Test
    void createUserFacadeWithAuthUser() {
        SigninRequestDTO req = new SigninRequestDTO("email", "password");
        User userExpected = new User(null, null, req.getEmail(), req.getPassword());
        when(authUserUseCase.execute(userExpected)).thenReturn("token_teste");

        String result = userFacade.authUser(req);

        verify(authUserUseCase).execute(userExpected);
        assertEquals("token_teste", result);
    }

}