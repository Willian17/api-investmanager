package com.api.investmanager.adapters.output.mark;

import com.api.investmanager.core.domain.enuns.CategoryEnum;
import com.api.investmanager.core.domain.model.Mark;
import com.api.investmanager.infra.database.entity.MarkEntity;
import com.api.investmanager.infra.repository.MarkRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListMarksAdapterTest {

    @InjectMocks
    private ListMarksAdapter listMarksAdapter;

    @Mock
    private MarkRepository markRepository;


    @Test
    void returnListMarks() {
        String idUser = UUID.randomUUID().toString();
        String uuid1 = UUID.randomUUID().toString();
        String uuid2 = UUID.randomUUID().toString();
        List<Mark> marksExpected = List.of(
                new Mark(uuid1, 50, CategoryEnum.ACOES_NACIONAIS),
                new Mark(uuid2, 50, CategoryEnum.ACOES_INTERNACIONAIS)
        );
        List<MarkEntity> marksReturned = List.of(
                MarkEntity.builder().id(UUID.fromString(uuid1))
                        .category(CategoryEnum.ACOES_NACIONAIS)
                        .percentage(50)
                        .build(),
                MarkEntity.builder().id(UUID.fromString(uuid2))
                        .category(CategoryEnum.ACOES_INTERNACIONAIS)
                        .percentage(50)
                        .build()
        );
        when(markRepository.findByUser_Id(UUID.fromString(idUser))).thenReturn(marksReturned);

        List<Mark> marks = listMarksAdapter.execute(idUser);

        assertEquals(marksExpected, marks);
        verify(markRepository).findByUser_Id(UUID.fromString(idUser));

    }
}