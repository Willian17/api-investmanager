package com.api.investmanager.core.application.facade;

import com.api.investmanager.adapters.input.controller.auth.dto.SignupRequestDTO;
import com.api.investmanager.core.application.port.input.user.CreateUserUseCase;
import com.api.investmanager.core.domain.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class UserFacadeImplTest {
    @InjectMocks
    private UserFacadeImpl userFacade;

    @Mock
    private CreateUserUseCase createUserUseCase;


    @Test
    void createUserFacade() {
        SignupRequestDTO req = new SignupRequestDTO("name", "email", "password");
        User userExpected = User
                .builder()
                .name(req.getName())
                .email(req.getEmail())
                .password(req.getPassword())
                .build();

        userFacade.createUser(req);

        verify(createUserUseCase).execute(userExpected);
    }

}