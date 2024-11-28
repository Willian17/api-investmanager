package com.api.investmanager.core.application.usecase.question;

import com.api.investmanager.core.application.dto.question.UpdateQuestionQuery;
import com.api.investmanager.core.application.port.output.question.ConsultQuestionOutput;
import com.api.investmanager.core.application.port.output.question.UpdateQuestionOutput;
import com.api.investmanager.core.application.port.output.user.UserExistsByIdPort;
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
import static org.mockito.Mockito.verifyNoInteractions;

@ExtendWith(MockitoExtension.class)
class UpdateQuestionUseCaseImplTest {

    @InjectMocks
    UpdateQuestionUseCaseImpl updateQuestionUseCase;

    @Mock
    UserExistsByIdPort userExistsById;

    @Mock
    ConsultQuestionOutput questionExistsById;

    @Mock
    UpdateQuestionOutput updateQuestionOutput;

    @Test
    void throwExeceptionWhenUserNotExists() {
        UpdateQuestionQuery updateQuestionQuery = new UpdateQuestionQuery("123", "id_user_1234", "Lucro liquido maior que 20%?", "LUCRO");

        when(userExistsById.execute(anyString())).thenReturn(false);

        NotFoundException notFoundException = assertThrows(NotFoundException.class, () -> updateQuestionUseCase.execute(updateQuestionQuery));

        assertEquals(notFoundException.getMessage(), "Usuario não encontrado");
        verify(userExistsById).execute("id_user_1234");
        verifyNoInteractions(questionExistsById);
    }

    @Test
    void throwExeceptionWhenQuestionNotExists() {
        UpdateQuestionQuery updateQuestionQuery = new UpdateQuestionQuery("question_123", "id_user_1234", "Lucro liquido maior que 20%?", "LUCRO");


        when(userExistsById.execute(anyString())).thenReturn(true);
        when(questionExistsById.execute(anyString())).thenReturn(Optional.empty());

        NotFoundException notFoundException = assertThrows(NotFoundException.class, () -> updateQuestionUseCase.execute(updateQuestionQuery));

        assertEquals(notFoundException.getMessage(), "Pergunta não encontrada!");
        verify(userExistsById).execute("id_user_1234");
        verify(questionExistsById).execute("question_123");
    }

    @Test
    void shouldUpdateQuestionSuccessfully() {
        UpdateQuestionQuery updateQuestionQuery = new UpdateQuestionQuery("question_123", "id_user_1234", "Lucro liquido maior que 20%?", "LUCRO");
        Question question = new Question("question_123", "Lucro liquido maior que 30%?", "lucro", CategoryEnum.ACOES_NACIONAIS);

        Question questionUpdated = new Question("question_123","Lucro liquido maior que 20%?", "LUCRO",  CategoryEnum.ACOES_NACIONAIS);

        when(userExistsById.execute(anyString())).thenReturn(true);
        when(questionExistsById.execute(anyString())).thenReturn(Optional.of(question));

        updateQuestionUseCase.execute(updateQuestionQuery);

        verify(userExistsById).execute("id_user_1234");
        verify(questionExistsById).execute("question_123");
        verify(updateQuestionOutput).execute(questionUpdated, updateQuestionQuery.idUser());
    }
}