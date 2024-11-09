package com.api.investmanager.core.application.usecase.user;

import com.api.investmanager.core.application.port.output.user.ConsultUserPort;
import com.api.investmanager.core.application.port.output.user.CreateUserPort;
import com.api.investmanager.core.domain.model.User;
import com.api.investmanager.infra.config.exception.AlreadyExistsException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;


@ExtendWith(MockitoExtension.class)
class CreateUserUseCaseImplTest {
    @InjectMocks
    private CreateUserUseCaseImpl createUserUseCase;

    @Mock
    private CreateUserPort createUserPort;

    @Mock
    private ConsultUserPort consultUserPort;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    void shouldCreateUserIfNotExists() {
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

        when(consultUserPort.execute(anyString())).thenReturn(null);
        when(passwordEncoder.encode("password")).thenReturn(userExpected.getPassword());
        createUserUseCase.execute(user);

        verify(passwordEncoder).encode("password");
        verify(consultUserPort).execute(userExpected.getEmail());
        verify(createUserPort).execute(userExpected);
    }

    @Test
    void shouldCreateUserIfNotExistsUserEmpty() {
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

        when(consultUserPort.execute(anyString())).thenReturn(new User());
        when(passwordEncoder.encode("password")).thenReturn(userExpected.getPassword());
        createUserUseCase.execute(user);

        verify(passwordEncoder).encode("password");
        verify(consultUserPort).execute(userExpected.getEmail());
        verify(createUserPort).execute(userExpected);
    }

    @Test
    void shouldExceptionIfExistsUser() {
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

        User userReturned = User
                .builder()
                .id(new UUID(100, 100))
                .name("name")
                .email("email@email.com")
                .password("password_encoded")
                .build();

        when(consultUserPort.execute(anyString())).thenReturn(userReturned);

        AlreadyExistsException alreadyExistsException =  assertThrows(
                AlreadyExistsException.class,
                () -> createUserUseCase.execute(user));

        assertEquals("Este email já está em uso.", alreadyExistsException.getMessage());
        verify(consultUserPort).execute(userExpected.getEmail());
        verifyNoInteractions(passwordEncoder);
        verifyNoInteractions(createUserPort);
    }

}