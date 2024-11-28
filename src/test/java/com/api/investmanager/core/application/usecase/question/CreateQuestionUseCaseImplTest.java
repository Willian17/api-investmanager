package com.api.investmanager.core.application.usecase.question;

import com.api.investmanager.core.application.dto.question.CreateQuestionQuery;
import com.api.investmanager.core.application.port.output.question.CreateQuestionOutput;
import com.api.investmanager.core.application.port.output.user.UserExistsByIdPort;
import com.api.investmanager.core.domain.enuns.CategoryEnum;
import com.api.investmanager.core.domain.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoInteractions;

@ExtendWith(MockitoExtension.class)
class CreateQuestionUseCaseImplTest {
    @InjectMocks
    CreateQuestionUseCaseImpl createQuestionUseCase;

    @Mock
    UserExistsByIdPort userExistsById;

    @Mock
    CreateQuestionOutput createQuestionOutput;


    @Test
    void throwExeceptionWhenUserNotExists() {
        CreateQuestionQuery createQuestionQuery = new CreateQuestionQuery("123", "lucro liquido valido?", "LUCRO", CategoryEnum.ACOES_NACIONAIS);

        when(userExistsById.execute(anyString())).thenReturn(false);

        NotFoundException notFoundException = assertThrows(NotFoundException.class, () -> createQuestionUseCase.execute(createQuestionQuery));

        assertEquals(notFoundException.getMessage(), "Usuario não encontrado");
        verify(userExistsById).execute("123");
        verifyNoInteractions(createQuestionOutput);
    }

    @Test
    void shouldCreateQuestionSuccessfully() {
        String idUser = "iduser123";
        CreateQuestionQuery createQuestionQuery = new CreateQuestionQuery(idUser, "lucro liquido valido?", "LUCRO", CategoryEnum.ACOES_NACIONAIS);

        when(userExistsById.execute(anyString())).thenReturn(true);

        createQuestionUseCase.execute(createQuestionQuery);

        verify(userExistsById).execute(idUser);
        verify(createQuestionOutput).execute(createQuestionQuery);
    }
}