package com.api.investmanager.core.application.usecase.mark;

import com.api.investmanager.core.application.port.output.mark.UpdateMarksPort;
import com.api.investmanager.core.domain.enuns.CategoryEnum;
import com.api.investmanager.core.domain.model.Mark;
import com.api.investmanager.core.domain.exception.ClientException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoInteractions;


@ExtendWith(MockitoExtension.class)
class UpdateMarksServiceTest {
    @InjectMocks
    UpdateMarksService updateMarksUseCase;

    @Mock
    UpdateMarksPort updateMarksPort;

    @Test
    void testExecute_ThrowException_WhenPercentageGreaterThan100() {
        String idUser = "user1234";
        List<Mark> marks = List.of(
                new Mark(null, 16, CategoryEnum.ACOES_NACIONAIS),
                new Mark(null, 16, CategoryEnum.ACOES_INTERNACIONAIS),
                new Mark(null, 16, CategoryEnum.CRIPTOMOEDAS),
                new Mark(null, 16, CategoryEnum.REITS),
                new Mark(null, 16, CategoryEnum.FUNDOS_IMOBILIARIOS),
                new Mark(null, 21, CategoryEnum.RENDA_FIXA)
        );

        ClientException clientException = assertThrows(ClientException.class, () -> updateMarksUseCase.execute(marks, idUser));

        verifyNoInteractions(updateMarksPort);
        assertEquals("A soma dos percentuais excede o limite permitido de 100%.", clientException.getMessage());
    }

    @Test
    void testExecute_ThrowException_WhenPercentageLess0() {
        String idUser = "user1234";
        List<Mark> marks = List.of(
                new Mark(null, 16, CategoryEnum.ACOES_NACIONAIS),
                new Mark(null, 16, CategoryEnum.ACOES_INTERNACIONAIS),
                new Mark(null, 16, CategoryEnum.CRIPTOMOEDAS),
                new Mark(null, 16, CategoryEnum.REITS),
                new Mark(null, 16, CategoryEnum.FUNDOS_IMOBILIARIOS),
                new Mark(null, -1, CategoryEnum.RENDA_FIXA)
        );

        ClientException clientException = assertThrows(ClientException.class, () -> updateMarksUseCase.execute(marks, idUser));

        verifyNoInteractions(updateMarksPort);
        assertEquals("A porcentagem não pode ser inferior a 0%.", clientException.getMessage());
    }

    @Test
    void testExecute_ThrowException_WhenCategoryInvalid() {
        String idUser = "user1234";
        List<Mark> marks = List.of(
                new Mark(null, 16, CategoryEnum.ACOES_NACIONAIS),
                new Mark(null, 16, CategoryEnum.ACOES_INTERNACIONAIS),
                new Mark(null, 16, CategoryEnum.CRIPTOMOEDAS),
                new Mark(null, 16, CategoryEnum.REITS),
                new Mark(null, 16, CategoryEnum.RENDA_FIXA)
        );

        ClientException clientException = assertThrows(ClientException.class, () -> updateMarksUseCase.execute(marks, idUser));

        verifyNoInteractions(updateMarksPort);
        assertEquals("Para completar a operação, todas as categorias exigidas devem ser informadas.", clientException.getMessage());
    }

    @Test
    void testExecute_UpdateSuccess() {
        String idUser = "user1234";
        List<Mark> marks = List.of(
                new Mark(null, 16, CategoryEnum.ACOES_NACIONAIS),
                new Mark(null, 16, CategoryEnum.ACOES_INTERNACIONAIS),
                new Mark(null, 16, CategoryEnum.CRIPTOMOEDAS),
                new Mark(null, 16, CategoryEnum.REITS),
                new Mark(null, 16, CategoryEnum.FUNDOS_IMOBILIARIOS),
                new Mark(null, 16, CategoryEnum.RENDA_FIXA)
        );

        updateMarksUseCase.execute(marks, idUser);

        verify(updateMarksPort).execute(marks, idUser);
    }
}