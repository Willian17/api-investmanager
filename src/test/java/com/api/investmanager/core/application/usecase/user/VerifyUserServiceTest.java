package com.api.investmanager.core.application.usecase.user;

import com.api.investmanager.core.application.port.output.user.UserExistsByIdOutput;
import com.api.investmanager.core.domain.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VerifyUserServiceTest {

    @InjectMocks
    VerifyUserService verifyUserUseCase;

    @Mock
    UserExistsByIdOutput userExistsById;

    @Test
    void shouldThrowExceptionWhenUserDoesNotExist() {
        String idUser = "id_user_1234";

        when(userExistsById.execute(anyString())).thenReturn(false);

        NotFoundException notFoundException = assertThrows(NotFoundException.class, () -> verifyUserUseCase.execute(idUser));

        assertEquals(notFoundException.getMessage(), "Usuario não encontrado");
        verify(userExistsById).execute("id_user_1234");
    }

    @Test
    void shouldNotThrowExceptionWhenUserExist() {
        String idUser = "id_user_1234";

        when(userExistsById.execute(anyString())).thenReturn(true);

        verifyUserUseCase.execute(idUser);

        verify(userExistsById).execute("id_user_1234");
    }
}