package com.api.investmanager.core.application.usecase.question;

import com.api.investmanager.core.application.dto.question.CreateQuestionQuery;
import com.api.investmanager.core.application.port.output.question.CreateQuestionOutput;
import com.api.investmanager.core.domain.enuns.CategoryEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateQuestionServiceTest {
    @InjectMocks
    CreateQuestionService createQuestionUseCase;

    @Mock
    CreateQuestionOutput createQuestionOutput;


    @Test
    void shouldCreateQuestionSuccessfully() {
        String idUser = "iduser123";
        CreateQuestionQuery createQuestionQuery = new CreateQuestionQuery(idUser, "lucro liquido valido?", "LUCRO", CategoryEnum.ACOES_NACIONAIS);

        createQuestionUseCase.execute(createQuestionQuery);

        verify(createQuestionOutput).execute(createQuestionQuery);
    }
}