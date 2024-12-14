package com.api.investmanager.core.application.usecase.active;

import com.api.investmanager.core.application.dto.active.UpdateActiveQuery;
import com.api.investmanager.core.application.port.output.active.ConsultActiveById;
import com.api.investmanager.core.application.port.output.active.UpdateActiveOutput;
import com.api.investmanager.core.application.port.output.active.VerifyNameExistsOutput;
import com.api.investmanager.core.domain.enuns.CategoryEnum;
import com.api.investmanager.core.domain.exception.ClientException;
import com.api.investmanager.core.domain.exception.NotFoundException;
import com.api.investmanager.core.domain.model.Active;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdateActiveServiceTest {

    @InjectMocks
    private UpdateActiveService updateActiveService;

    @Mock
    private ConsultActiveById consultActiveById;

    @Mock
    private VerifyNameExistsOutput verifyNameExistsOutput;

    @Mock
    private UpdateActiveOutput updateActiveOutput;

    @Test
    void shouldThrowExceptionQuantityNegative() {
        UpdateActiveQuery updateActiveQuery = new UpdateActiveQuery("id_user", "idActive_123","BBAS3", CategoryEnum.ACOES_NACIONAIS, new BigDecimal(-6));
        Active activeExist = new Active("idActive_123", "BBAS3", CategoryEnum.ACOES_NACIONAIS, BigDecimal.TEN);

        when(consultActiveById.execute(any())).thenReturn(Optional.of(activeExist));
        ClientException ex = assertThrows(ClientException.class, () -> updateActiveService.execute(updateActiveQuery));

        assertEquals("Quantidade não pode ser negativa", ex.getMessage());
        verify(consultActiveById).execute("idActive_123");
        verifyNoInteractions(updateActiveOutput);
        verifyNoInteractions(updateActiveOutput);
    }

    @Test
    void shouldThrowExceptionWhenActiveNotExists() {
        UpdateActiveQuery updateActiveQuery = new UpdateActiveQuery("id_user", "idActive_123","BBAS3", CategoryEnum.ACOES_NACIONAIS, BigDecimal.TEN);
        when(consultActiveById.execute(any())).thenReturn(Optional.empty());

        NotFoundException ex = assertThrows(NotFoundException.class, () -> updateActiveService.execute(updateActiveQuery));

        assertEquals("Ativo não encontrado!", ex.getMessage());
        verify(consultActiveById).execute("idActive_123");
        verifyNoInteractions(updateActiveOutput);
    }

    @Test
    void shouldThrowExceptionWhenUpdatedNameExists() {
        UpdateActiveQuery updateActiveQuery = new UpdateActiveQuery("id_user", "idActive_123","BBAS3", CategoryEnum.ACOES_NACIONAIS, BigDecimal.TEN);
        Active activeExist = new Active("idActive_123", "VALE3", CategoryEnum.ACOES_NACIONAIS, BigDecimal.TEN);

        when(consultActiveById.execute(any())).thenReturn(Optional.of(activeExist));
        when(verifyNameExistsOutput.execute(any(), any())).thenReturn(true);

        ClientException ex = assertThrows(ClientException.class, () -> updateActiveService.execute(updateActiveQuery));

        assertEquals("Nome atualizado não permitido, já existente em outro ativo!", ex.getMessage());
        verify(consultActiveById).execute("idActive_123");
        Active activeExpected = new Active("idActive_123", "BBAS3", CategoryEnum.ACOES_NACIONAIS, BigDecimal.TEN);
        verify(verifyNameExistsOutput).execute(activeExpected, "id_user");
        verifyNoInteractions(updateActiveOutput);
    }

    @Test
    void shouldThrowExceptionWhenUpdateSameEqual() {
        UpdateActiveQuery updateActiveQuery = new UpdateActiveQuery("id_user", "idActive_123","BBAS3", CategoryEnum.ACOES_NACIONAIS, BigDecimal.TEN);
        Active activeExist = new Active("idActive_123", "BBAS3", CategoryEnum.ACOES_NACIONAIS, BigDecimal.TEN);

        when(consultActiveById.execute(any())).thenReturn(Optional.of(activeExist));

        ClientException ex = assertThrows(ClientException.class, () -> updateActiveService.execute(updateActiveQuery));

        assertEquals("Ativo atualizado sem mudanças em relação ao ativo cadastrado!", ex.getMessage());
        verify(consultActiveById).execute("idActive_123");
        verifyNoInteractions(verifyNameExistsOutput);
        verifyNoInteractions(updateActiveOutput);
    }

    @Test
    void shouldUpdateActiveSuccessfully() {
        UpdateActiveQuery updateActiveQuery = new UpdateActiveQuery("id_user", "idActive_123","BBAS3", CategoryEnum.ACOES_NACIONAIS, BigDecimal.TEN);
        Active activeExist = new Active("idActive_123", "BBAS3", CategoryEnum.ACOES_NACIONAIS, BigDecimal.TWO);

        when(consultActiveById.execute(any())).thenReturn(Optional.of(activeExist));

        updateActiveService.execute(updateActiveQuery);

        verify(consultActiveById).execute("idActive_123");
        verifyNoInteractions(verifyNameExistsOutput);
        Active activeExpected = new Active("idActive_123", "BBAS3", CategoryEnum.ACOES_NACIONAIS, BigDecimal.TEN);
        verify(updateActiveOutput).execute(activeExpected, "id_user");
    }
}