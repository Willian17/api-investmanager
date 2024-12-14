package com.api.investmanager.core.application.usecase.mark;

import com.api.investmanager.core.application.dto.mark.MarkDTO;
import com.api.investmanager.core.application.dto.mark.UpdateMarkQuery;
import com.api.investmanager.core.application.port.output.mark.UpdateMarksOutput;
import com.api.investmanager.core.domain.enuns.CategoryEnum;
import com.api.investmanager.core.domain.exception.ClientException;
import com.api.investmanager.core.domain.model.Mark;
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
    UpdateMarksOutput updateMarksOutput;

    @Test
    void testExecute_ThrowException_WhenPercentageGreaterThan100() {
        String idUser = "user1234";
        List<MarkDTO> marks = List.of(
                new MarkDTO(null, 16, CategoryEnum.ACOES_NACIONAIS),
                new MarkDTO(null, 16, CategoryEnum.ACOES_INTERNACIONAIS),
                new MarkDTO(null, 16, CategoryEnum.CRIPTOMOEDAS),
                new MarkDTO(null, 16, CategoryEnum.REITS),
                new MarkDTO(null, 16, CategoryEnum.FUNDOS_IMOBILIARIOS),
                new MarkDTO(null, 21, CategoryEnum.RENDA_FIXA)
        );

        UpdateMarkQuery updateMarkQuery = new UpdateMarkQuery(idUser, marks);

        ClientException clientException = assertThrows(ClientException.class, () -> updateMarksUseCase.execute(updateMarkQuery));

        verifyNoInteractions(updateMarksOutput);
        assertEquals("A soma dos percentuais excede o limite permitido de 100%.", clientException.getMessage());
    }

    @Test
    void testExecute_ThrowException_WhenPercentageLess0() {
        String idUser = "user1234";
        List<MarkDTO> marks = List.of(
                new MarkDTO(null, 16, CategoryEnum.ACOES_NACIONAIS),
                new MarkDTO(null, 16, CategoryEnum.ACOES_INTERNACIONAIS),
                new MarkDTO(null, 16, CategoryEnum.CRIPTOMOEDAS),
                new MarkDTO(null, 16, CategoryEnum.REITS),
                new MarkDTO(null, 16, CategoryEnum.FUNDOS_IMOBILIARIOS),
                new MarkDTO(null, -1, CategoryEnum.RENDA_FIXA)
        );
        UpdateMarkQuery updateMarkQuery = new UpdateMarkQuery(idUser, marks);

        ClientException clientException = assertThrows(ClientException.class, () -> updateMarksUseCase.execute(updateMarkQuery));

        verifyNoInteractions(updateMarksOutput);
        assertEquals("A porcentagem não pode ser inferior a 0%.", clientException.getMessage());
    }

    @Test
    void testExecute_ThrowException_WhenPercentagePlus100() {
        String idUser = "user1234";
        List<MarkDTO> marks = List.of(
                new MarkDTO(null, 0, CategoryEnum.ACOES_NACIONAIS),
                new MarkDTO(null, 101, CategoryEnum.ACOES_INTERNACIONAIS),
                new MarkDTO(null, 16, CategoryEnum.CRIPTOMOEDAS),
                new MarkDTO(null, 16, CategoryEnum.REITS),
                new MarkDTO(null, 16, CategoryEnum.FUNDOS_IMOBILIARIOS),
                new MarkDTO(null, 12, CategoryEnum.RENDA_FIXA)
        );
        UpdateMarkQuery updateMarkQuery = new UpdateMarkQuery(idUser, marks);

        ClientException clientException = assertThrows(ClientException.class, () -> updateMarksUseCase.execute(updateMarkQuery));

        verifyNoInteractions(updateMarksOutput);
        assertEquals("A porcentagem não pode ser superior a 100%.", clientException.getMessage());
    }

    @Test
    void testExecute_ThrowException_WhenCategoryInvalid() {
        String idUser = "user1234";
        List<MarkDTO> marks = List.of(
                new MarkDTO(null, 16, CategoryEnum.ACOES_NACIONAIS),
                new MarkDTO(null, 16, CategoryEnum.ACOES_INTERNACIONAIS),
                new MarkDTO(null, 16, CategoryEnum.CRIPTOMOEDAS),
                new MarkDTO(null, 16, CategoryEnum.REITS),
                new MarkDTO(null, 16, CategoryEnum.RENDA_FIXA)
        );
        UpdateMarkQuery updateMarkQuery = new UpdateMarkQuery(idUser, marks);

        ClientException clientException = assertThrows(ClientException.class, () -> updateMarksUseCase.execute(updateMarkQuery));

        verifyNoInteractions(updateMarksOutput);
        assertEquals("Para completar a operação, todas as categorias exigidas devem ser informadas.", clientException.getMessage());
    }

    @Test
    void testExecute_UpdateSuccess() {
        String idUser = "user1234";
        List<MarkDTO> marks = List.of(
                new MarkDTO(null, 16, CategoryEnum.ACOES_NACIONAIS),
                new MarkDTO(null, 16, CategoryEnum.ACOES_INTERNACIONAIS),
                new MarkDTO(null, 16, CategoryEnum.CRIPTOMOEDAS),
                new MarkDTO(null, 16, CategoryEnum.REITS),
                new MarkDTO(null, 16, CategoryEnum.FUNDOS_IMOBILIARIOS),
                new MarkDTO(null, 16, CategoryEnum.RENDA_FIXA)
        );
        List<Mark> marksExpected = List.of(
                new Mark(null, 16, CategoryEnum.ACOES_NACIONAIS),
                new Mark(null, 16, CategoryEnum.ACOES_INTERNACIONAIS),
                new Mark(null, 16, CategoryEnum.CRIPTOMOEDAS),
                new Mark(null, 16, CategoryEnum.REITS),
                new Mark(null, 16, CategoryEnum.FUNDOS_IMOBILIARIOS),
                new Mark(null, 16, CategoryEnum.RENDA_FIXA)
        );

        UpdateMarkQuery updateMarkQuery = new UpdateMarkQuery(idUser, marks);

        updateMarksUseCase.execute(updateMarkQuery);

        verify(updateMarksOutput).execute(marksExpected, idUser);
    }
}