package com.api.investmanager.core.application.usecase.active;

import com.api.investmanager.core.application.dto.active.CreateActiveQuery;
import com.api.investmanager.core.application.port.output.active.CreateActiveOutput;
import com.api.investmanager.core.application.port.output.active.VerifyNameExistsOutput;
import com.api.investmanager.core.domain.enuns.CategoryEnum;
import com.api.investmanager.core.domain.exception.ClientException;
import com.api.investmanager.core.domain.model.Active;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateActiveServiceTest {

    @InjectMocks
    private CreateActiveService createActiveService;

    @Mock
    private VerifyNameExistsOutput verifyNameExistsOutput;

    @Mock
    private CreateActiveOutput createActiveOutput;


    @Test
    void shouldThrowExceptionQuantityNegative() {
        CreateActiveQuery createActiveQuery = new CreateActiveQuery("id_user", "BBAS3", CategoryEnum.ACOES_NACIONAIS, new BigDecimal(-6));
        ClientException ex = assertThrows(ClientException.class, () -> createActiveService.execute(createActiveQuery));

        assertEquals("Quantidade não pode ser negativa", ex.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenNameDuplicatedInSameCategory() {
        CreateActiveQuery createActiveQuery = new CreateActiveQuery("id_user", "BBAS3", CategoryEnum.ACOES_NACIONAIS, new BigDecimal(10));
        Active active = new Active(CategoryEnum.ACOES_NACIONAIS, "BBAS3");
        active.setAmount(BigDecimal.TEN);

        when(verifyNameExistsOutput.execute(any(Active.class), any())).thenReturn(true);

        ClientException ex = assertThrows(ClientException.class, () -> createActiveService.execute(createActiveQuery));

        assertEquals("Ativo já cadastrado!", ex.getMessage());

        verify(verifyNameExistsOutput).execute(active, "id_user");
    }

    @Test
    void shouldCreateActiveSuccessfully() {
        CreateActiveQuery createActiveQuery = new CreateActiveQuery("id_user", "BBAS3", CategoryEnum.ACOES_NACIONAIS, new BigDecimal(10));
        Active active = new Active(CategoryEnum.ACOES_NACIONAIS, "BBAS3");
        active.setAmount(BigDecimal.TEN);

        when(verifyNameExistsOutput.execute(any(Active.class), any())).thenReturn(false);

        createActiveService.execute(createActiveQuery);

        verify(verifyNameExistsOutput).execute(active, "id_user");
        verify(createActiveOutput).execute(active, "id_user");
    }

}