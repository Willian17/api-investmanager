package com.api.investmanager.core.application.usecase;

import com.api.investmanager.core.application.port.output.user.CreateUserPort;
import com.api.investmanager.core.application.usecase.user.CreateUserUseCaseImpl;
import com.api.investmanager.core.domain.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;


@ExtendWith(MockitoExtension.class)
class CreateUserUseCaseImplTest {
    @InjectMocks
    private CreateUserUseCaseImpl createUserUseCase;

    @Mock
    private CreateUserPort createUserPort;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    void shouldExecute() {
        User user = User
                .builder()
                .name("name")
                .email("email@email.com")
                .password("password")
                .build();

        User userExpected = User
                .builder()
                .name("name")
                .email("email@email.com")
                .password("password_encoded")
                .build();


        when(passwordEncoder.encode("password")).thenReturn(userExpected.getPassword());
        createUserUseCase.execute(user);

        verify(passwordEncoder).encode("password");
        verify(createUserPort).execute(userExpected);
    }

}