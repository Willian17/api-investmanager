package com.api.investmanager.core.application.usecase.user;

import com.api.investmanager.core.application.port.output.user.AuthUserPort;
import com.api.investmanager.core.application.port.output.user.ConsultUserPort;
import com.api.investmanager.core.domain.model.User;
import com.api.investmanager.infra.exception.ClientException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoInteractions;

@ExtendWith(MockitoExtension.class)
class AuthUserUseCaseImplTest {
    @InjectMocks
    private AuthUserUseCaseImpl authUserUseCase;

    @Mock
    private ConsultUserPort consultUserPort;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthUserPort authUserPort;

    @Test
    @DisplayName("Deve lançar invalid Credentials se usuario não existe")
    void shouldExceptionIfNotExistsUser() {
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

        ClientException alreadyExistsException =  assertThrows(
                ClientException.class,
                () -> authUserUseCase.execute(user));

        assertEquals("Credenciais inválidas, tente novamente!", alreadyExistsException.getMessage());
        verify(consultUserPort).execute(userExpected.getEmail());
        verifyNoInteractions(passwordEncoder);
    }

    @Test
    @DisplayName("Deve lançar invalid Credentials se senha inválida")
    void shouldExceptionIfPasswordInvalid() {
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
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(false);

        ClientException alreadyExistsException =  assertThrows(
                ClientException.class,
                () -> authUserUseCase.execute(user));

        assertEquals("Credenciais inválidas, tente novamente!", alreadyExistsException.getMessage());
        verify(consultUserPort).execute(userExpected.getEmail());
        verify(passwordEncoder).matches(user.getPassword(), userExpected.getPassword());
        verify(authUserPort).execute(userReturned);
    }

    @Test
    @DisplayName("Deve autenticar o usuario se dados corretos")
    void shouldAuthUserSuccess() {
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
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(true);
        when(authUserPort.execute(any(User.class))).thenReturn("token_etc");


        String authentication = authUserUseCase.execute(user);

        assertEquals("token_etc", authentication);
        verify(consultUserPort).execute(userExpected.getEmail());
        verify(passwordEncoder).matches(user.getPassword(), userExpected.getPassword());
        verify(authUserPort).execute(userReturned);
    }


}