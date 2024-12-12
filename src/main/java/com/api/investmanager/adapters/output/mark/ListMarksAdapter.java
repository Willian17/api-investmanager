package com.api.investmanager.adapters.output.mark;

import com.api.investmanager.core.application.mapper.MarkMapper;
import com.api.investmanager.core.application.port.output.mark.ListMarksOutput;
import com.api.investmanager.core.domain.model.Mark;
import com.api.investmanager.infra.database.entity.MarkEntity;
import com.api.investmanager.infra.repository.MarkRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class ListMarksAdapter implements ListMarksOutput {
    private final MarkRepository markRepository;

    public ListMarksAdapter(MarkRepository markRepository) {
        this.markRepository = markRepository;
    }

    @Override
    public List<Mark> execute(String idUser) {
        List<MarkEntity> marks = markRepository.findByUser_Id(UUID.fromString(idUser));

        return marks.stream()
                .map(MarkMapper::markEntityToMarkModel)
                .toList();
    }
}
