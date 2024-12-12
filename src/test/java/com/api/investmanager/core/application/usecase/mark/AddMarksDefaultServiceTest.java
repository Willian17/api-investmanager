package com.api.investmanager.core.application.usecase.mark;

import com.api.investmanager.core.application.port.output.mark.CreateMarksOutput;
import com.api.investmanager.core.domain.enuns.CategoryEnum;
import com.api.investmanager.core.domain.model.Mark;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class AddMarksDefaultServiceTest {

    @InjectMocks
    AddMarksDefaultService addMarksDefaultUseCase;

    @Mock
    CreateMarksOutput createMarksOutput;

    @Test
    void testExecute_CreateDefaultSuccess() {
        String idUser = "user123";
        List<Mark> marks = Stream.of(CategoryEnum.values())
                .map(category -> new Mark(null, 0, category))
                .toList();

        addMarksDefaultUseCase.execute(idUser);

        verify(createMarksOutput).execute(marks, idUser);
    }

}