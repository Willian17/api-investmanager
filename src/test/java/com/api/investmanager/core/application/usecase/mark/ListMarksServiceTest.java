package com.api.investmanager.core.application.usecase.mark;

import com.api.investmanager.core.application.port.output.mark.ListMarksPort;
import com.api.investmanager.core.domain.enuns.CategoryEnum;
import com.api.investmanager.core.domain.model.Mark;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ListMarksServiceTest {

    @InjectMocks
    ListMarksService listMarksUseCase;

    @Mock
    ListMarksPort listMarksPort;

    @Test
    void testExecute_ReturnsEmptyList_WhenNoMarksFound() {
        when(listMarksPort.execute("user123")).thenReturn(List.of());

        List<Mark> marks = listMarksUseCase.execute("user123");

        assertNotNull(marks, "Marks list should not be null");
        assertTrue(marks.isEmpty(), "Marks list should be empty");
        verify(listMarksPort).execute("user123");
    }

    @Test
    void testExecute_ReturnsList_Marks() {
        List<Mark> marksExpected = List.of(
                new Mark("id", 50, CategoryEnum.ACOES_NACIONAIS),
                new Mark("id2", 50, CategoryEnum.ACOES_INTERNACIONAIS)
        );
        when(listMarksPort.execute("user123")).thenReturn(marksExpected);

        List<Mark> marks = listMarksUseCase.execute("user123");

        assertNotNull(marks, "Marks list should not be null");
        assertFalse(marks.isEmpty(), "Marks list should be empty");
        verify(listMarksPort).execute("user123");
        assertEquals(marks, marksExpected);
    }



}