package com.api.investmanager.core.application.facade;

import com.api.investmanager.adapters.input.controller.mark.dto.MarkResponseDTO;
import com.api.investmanager.core.application.mapper.MarkMapper;
import com.api.investmanager.core.application.port.input.mark.ListMarksUseCase;
import com.api.investmanager.core.application.port.input.mark.MarkFacade;
import com.api.investmanager.core.domain.model.Mark;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarkFacadeImpl implements MarkFacade {

    private final ListMarksUseCase listMarksUseCase;

    public MarkFacadeImpl(ListMarksUseCase listMarksUseCase) {
        this.listMarksUseCase = listMarksUseCase;
    }


    @Override
    public List<MarkResponseDTO> listMarks(String idUser) {
        List<Mark> marks = listMarksUseCase.execute(idUser);
        return MarkMapper.INSTANCE.markModelToMarkResponseDTO(marks);
    }
}
