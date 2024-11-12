package com.api.investmanager.core.application.facade;

import com.api.investmanager.adapters.input.controller.mark.dto.MarkResponseDTO;
import com.api.investmanager.core.application.port.input.mark.ListMarksUseCase;
import com.api.investmanager.core.domain.enuns.CategoryEnum;
import com.api.investmanager.core.domain.model.Mark;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MarkFacadeImplTest {

    @InjectMocks
    private MarkFacadeImpl markFacade;

    @Mock
    private ListMarksUseCase listMarksUseCase;

    @Test
    void shouldListMarks() {
        String idUser = "dhfhsdgfhds";
        List<Mark> marksReturned = List.of(
                new Mark("id", 50, CategoryEnum.ACOES_NACIONAIS),
                new Mark("id2", 50, CategoryEnum.ACOES_INTERNACIONAIS)
        );

        List<MarkResponseDTO> marksExpected = List.of(
                new MarkResponseDTO("id", CategoryEnum.ACOES_NACIONAIS,50),
                new MarkResponseDTO("id2", CategoryEnum.ACOES_INTERNACIONAIS, 50)
        );
        when(listMarksUseCase.execute(idUser)).thenReturn(marksReturned);

        List<MarkResponseDTO> marks = markFacade.listMarks(idUser);

        assertEquals(marksExpected, marks);
        verify(listMarksUseCase).execute(idUser);
    }
}