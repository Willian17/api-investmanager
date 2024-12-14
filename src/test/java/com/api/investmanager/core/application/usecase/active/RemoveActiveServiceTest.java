package com.api.investmanager.core.application.usecase.active;

import com.api.investmanager.core.application.dto.active.RemoveActiveQuery;
import com.api.investmanager.core.application.port.output.active.DeleteActiveOutput;
import com.api.investmanager.core.application.port.output.active.VerifyExistsActiveOutput;
import com.api.investmanager.core.domain.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RemoveActiveServiceTest {

    @InjectMocks
    private RemoveActiveService removeActiveService;

    @Mock
    private VerifyExistsActiveOutput verifyExistsActiveOutput;

    @Mock
    private DeleteActiveOutput deleteActiveOutput;

    @Test
    void shouldThrowWhenActiveNotFound() {
        RemoveActiveQuery removeActiveQuery = new RemoveActiveQuery("id_user_123", "id_active_123");
        when(verifyExistsActiveOutput.execute(any())).thenReturn(false);

        NotFoundException ex = assertThrows(NotFoundException.class, ()-> removeActiveService.execute(removeActiveQuery));

        assertEquals("Ativo não encontrado", ex.getMessage());
        verify(verifyExistsActiveOutput).execute(removeActiveQuery);
        verifyNoInteractions(deleteActiveOutput);
    }

    @Test
    void shouldDeleteActiveSuccessfully() {
        RemoveActiveQuery removeActiveQuery = new RemoveActiveQuery("id_user_123", "id_active_123");
        when(verifyExistsActiveOutput.execute(any())).thenReturn(true);

        removeActiveService.execute(removeActiveQuery);

        verify(verifyExistsActiveOutput).execute(removeActiveQuery);
        verify(deleteActiveOutput).execute(removeActiveQuery);
    }
}