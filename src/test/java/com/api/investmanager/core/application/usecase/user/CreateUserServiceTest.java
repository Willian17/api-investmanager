package com.api.investmanager.core.application.usecase.user;

import com.api.investmanager.core.application.port.output.cryptography.HashEncode;
import com.api.investmanager.core.application.port.output.user.ConsultUserPort;
import com.api.investmanager.core.application.port.output.user.CreateUserPort;
import com.api.investmanager.core.domain.model.User;
import com.api.investmanager.core.domain.exception.AlreadyExistsException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;


@ExtendWith(MockitoExtension.class)
class CreateUserServiceTest {
    @InjectMocks
    private CreateUserService createUserUseCase;

    @Mock
    private CreateUserPort createUserPort;

    @Mock
    private ConsultUserPort consultUserPort;

    @Mock
    private HashEncode hashEncode;

    @Test
    void shouldCreateUserIfNotExists() {
        User user = getUser();
        User userExpected = getUserExpected();

        when(consultUserPort.execute(anyString())).thenReturn(null);
        when(hashEncode.encode("password")).thenReturn(userExpected.getPassword());
        createUserUseCase.execute(user);

        verify(hashEncode).encode("password");
        verify(consultUserPort).execute(userExpected.getEmail());
        verify(createUserPort).execute(userExpected);
    }

    @Test
    void shouldCreateUserIfNotExistsUserEmpty() {
        User user = getUser();

        User userExpected = getUserExpected();

        when(consultUserPort.execute(anyString())).thenReturn(new User());
        when(hashEncode.encode("password")).thenReturn(userExpected.getPassword());
        createUserUseCase.execute(user);

        verify(hashEncode).encode("password");
        verify(consultUserPort).execute(userExpected.getEmail());
        verify(createUserPort).execute(userExpected);
    }

    @Test
    void shouldExceptionIfExistsUser() {
        User user = getUser();
        User userExpected = getUserExpected();

        User userReturned = new User(new UUID(10, 10).toString(), "name", "email@email.com", "password_encoded");

        when(consultUserPort.execute(anyString())).thenReturn(userReturned);

        AlreadyExistsException alreadyExistsException =  assertThrows(
                AlreadyExistsException.class,
                () -> createUserUseCase.execute(user));

        assertEquals("Este email já está em uso.", alreadyExistsException.getMessage());
        verify(consultUserPort).execute(userExpected.getEmail());
        verifyNoInteractions(hashEncode);
        verifyNoInteractions(createUserPort);
    }

    private static User getUserExpected() {
        return new User(null, "name", "email@email.com", "password_encoded");
    }

    private static User getUser() {
        return new User(null, "name", "email@email.com", "password");
    }

}