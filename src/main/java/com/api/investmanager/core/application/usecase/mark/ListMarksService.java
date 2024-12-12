package com.api.investmanager.core.application.usecase.mark;

import com.api.investmanager.core.application.port.input.mark.ListMarksUseCase;
import com.api.investmanager.core.application.port.output.mark.ListMarksOutput;
import com.api.investmanager.core.domain.model.Mark;

import java.util.List;

public class ListMarksService implements ListMarksUseCase {
    private final ListMarksOutput listMarksOutput;

    public ListMarksService(ListMarksOutput listMarksOutput) {
        this.listMarksOutput = listMarksOutput;
    }

    @Override
    public List<Mark> execute(String idUser) {
        return this.listMarksOutput.execute(idUser);
    }
}
