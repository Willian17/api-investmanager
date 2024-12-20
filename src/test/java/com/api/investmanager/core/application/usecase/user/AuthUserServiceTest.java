package com.api.investmanager.core.application.usecase.user;

import com.api.investmanager.core.application.port.output.cryptography.HashCompare;
import com.api.investmanager.core.application.port.output.user.AuthUserPort;
import com.api.investmanager.core.application.port.output.user.ConsultUserOutput;
import com.api.investmanager.core.domain.model.User;
import com.api.investmanager.core.domain.exception.ClientException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoInteractions;

@ExtendWith(MockitoExtension.class)
class AuthUserServiceTest {
    @InjectMocks
    private AuthUserService authUserUseCase;

    @Mock
    private ConsultUserOutput consultUserOutput;

    @Mock
    private HashCompare hashCompare;

    @Mock
    private AuthUserPort authUserPort;

    @Test
    @DisplayName("Deve lançar invalid Credentials se usuario não existe")
    void shouldExceptionIfNotExistsUser() {
        User user = getUser();
        User userExpected = getUserExpected();

        when(consultUserOutput.execute(anyString())).thenReturn(null);

        ClientException alreadyExistsException =  assertThrows(
                ClientException.class,
                () -> authUserUseCase.execute(user));

        assertEquals("Credenciais inválidas, tente novamente!", alreadyExistsException.getMessage());
        verify(consultUserOutput).execute(userExpected.getEmail());
        verifyNoInteractions(hashCompare);
    }

    @Test
    @DisplayName("Deve lançar invalid Credentials se senha inválida")
    void shouldExceptionIfPasswordInvalid() {
        User user = getUser();
        User userExpected = getUserExpected();
        User userReturned = getUserExpected();
        userReturned.setId(String.valueOf(UUID.randomUUID()));

        when(consultUserOutput.execute(anyString())).thenReturn(userReturned);
        when(hashCompare.compare(anyString(), anyString())).thenReturn(false);

        ClientException alreadyExistsException =  assertThrows(
                ClientException.class,
                () -> authUserUseCase.execute(user));

        assertEquals("Credenciais inválidas, tente novamente!", alreadyExistsException.getMessage());
        verify(consultUserOutput).execute(userExpected.getEmail());
        verify(hashCompare).compare(user.getPassword(), userExpected.getPassword());
        verifyNoInteractions(authUserPort);
    }

    @Test
    @DisplayName("Deve autenticar o usuario se dados corretos")
    void shouldAuthUserSuccess() {
        User user = getUser();
        User userExpected = getUserExpected();
        User userReturned = getUserExpected();
        userReturned.setId(String.valueOf(UUID.randomUUID()));

        when(consultUserOutput.execute(anyString())).thenReturn(userReturned);
        when(hashCompare.compare(anyString(), anyString())).thenReturn(true);
        when(authUserPort.execute(any(User.class))).thenReturn("token_etc");


        String authentication = authUserUseCase.execute(user);

        assertEquals("token_etc", authentication);
        verify(consultUserOutput).execute(userExpected.getEmail());
        verify(hashCompare).compare(user.getPassword(), userExpected.getPassword());
        verify(authUserPort).execute(userReturned);
    }

    private static User getUserExpected() {
        return new User(null, "name", "email@email.com", "password_encoded");
    }

    private static User getUser() {
        return new User(null, "name", "email@email.com", "password");
    }


}