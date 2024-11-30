package com.api.investmanager.core.application.usecase.question;

import com.api.investmanager.core.application.dto.question.ListQuestionQuery;
import com.api.investmanager.core.application.port.output.question.ListQuestionOutput;
import com.api.investmanager.core.domain.enuns.CategoryEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ListQuestionsServiceTest {

    @InjectMocks
    ListQuestionsService listQuestionsUseCase;

    @Mock
    ListQuestionOutput listQuestionOutput;

    @Test
    void shouldReturnListQuestions() {
        ListQuestionQuery data = new ListQuestionQuery("idUser_1234", CategoryEnum.RENDA_FIXA);
        listQuestionsUseCase.execute(data);

        verify(listQuestionOutput).execute(data);
    }
}