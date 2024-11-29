package com.api.investmanager.core.application.usecase.question;

import com.api.investmanager.core.application.dto.question.RemoveQuestionQuery;
import com.api.investmanager.core.application.port.output.question.ConsultQuestionOutput;
import com.api.investmanager.core.application.port.output.question.DeleteQuestionOutput;
import com.api.investmanager.core.domain.enuns.CategoryEnum;
import com.api.investmanager.core.domain.exception.NotFoundException;
import com.api.investmanager.core.domain.model.Question;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RemoveQuestionUseCaseImplTest {
    @InjectMocks
    RemoveQuestionUseCaseImpl removeQuestionUseCase;

    @Mock
    ConsultQuestionOutput consultQuestionOutput;

    @Mock
    DeleteQuestionOutput deleteQuestionOutput;


    @Test
    void throwExeceptionWhenQuestionNotExists() {
        RemoveQuestionQuery removeQuestionQuery = new RemoveQuestionQuery("id_question_id3", "id_user_1234");

        when(consultQuestionOutput.execute(anyString())).thenReturn(Optional.empty());

        NotFoundException notFoundException = assertThrows(NotFoundException.class, () -> removeQuestionUseCase.execute(removeQuestionQuery));

        assertEquals(notFoundException.getMessage(), "Pergunta não encontrada!");

        verify(consultQuestionOutput).execute("id_question_id3");
    }

    @Test
    void shouldDeleteQuestionSuccessfully() {
        RemoveQuestionQuery removeQuestionQuery = new RemoveQuestionQuery("id_question_id3", "id_user_1234");
        Question question = new Question("id_question_id3", "Lucro liquido maior que 30%?", "lucro", CategoryEnum.ACOES_NACIONAIS);

        when(consultQuestionOutput.execute(anyString())).thenReturn(Optional.of(question));

        removeQuestionUseCase.execute(removeQuestionQuery);

        verify(consultQuestionOutput).execute("id_question_id3");
        verify(deleteQuestionOutput).execute(removeQuestionQuery);
    }
}