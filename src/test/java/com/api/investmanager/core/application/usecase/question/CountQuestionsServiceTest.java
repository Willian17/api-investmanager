package com.api.investmanager.core.application.usecase.question;

import com.api.investmanager.core.application.dto.question.CountQuestionResponse;
import com.api.investmanager.core.application.port.output.question.CountQuestionOutput;
import com.api.investmanager.core.domain.enuns.CategoryQuestionEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CountQuestionsServiceTest {

    @InjectMocks
    CountQuestionsService countQuestionsService;

    @Mock
    CountQuestionOutput countQuestionOutput;


    @Test
    void shouldReturnListCountQuestions() {
        String idUser = "idUser_123";
        List<CountQuestionResponse> returned = List.of(
                new CountQuestionResponse(CategoryQuestionEnum.ACOES_NACIONAIS, 10),
                new CountQuestionResponse(CategoryQuestionEnum.FUNDOS_IMOBILIARIOS, 11),
                new CountQuestionResponse(CategoryQuestionEnum.REITS, 9),
                new CountQuestionResponse(CategoryQuestionEnum.ACOES_INTERNACIONAIS, 9)
        );

        when(countQuestionOutput.execute(anyString())).thenReturn(returned);

        List<CountQuestionResponse> response = countQuestionsService.execute(idUser);

        verify(countQuestionOutput).execute(idUser);
        assertNotNull(response);
        assertEquals(returned, response);
    }

    @Test
    void shouldReturnListCountQuestionsComplete() {
        String idUser = "idUser_123";
        List<CountQuestionResponse> returned = new ArrayList<>(
                List.of(
                        new CountQuestionResponse(CategoryQuestionEnum.ACOES_NACIONAIS, 10),
                        new CountQuestionResponse(CategoryQuestionEnum.FUNDOS_IMOBILIARIOS, 10),
                        new CountQuestionResponse(CategoryQuestionEnum.ACOES_INTERNACIONAIS, 10)
                )
        );

        List<CountQuestionResponse> expected = List.of(
                new CountQuestionResponse(CategoryQuestionEnum.ACOES_NACIONAIS, 10),
                new CountQuestionResponse(CategoryQuestionEnum.FUNDOS_IMOBILIARIOS, 10),
                new CountQuestionResponse(CategoryQuestionEnum.ACOES_INTERNACIONAIS, 10),
                new CountQuestionResponse(CategoryQuestionEnum.REITS, 0)
        );

        when(countQuestionOutput.execute(anyString())).thenReturn(returned);

        List<CountQuestionResponse> response = countQuestionsService.execute(idUser);

        verify(countQuestionOutput).execute(idUser);
        assertNotNull(response);
        assertEquals(expected, response);
    }
}