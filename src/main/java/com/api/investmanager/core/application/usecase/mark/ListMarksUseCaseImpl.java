package com.api.investmanager.core.application.usecase.mark;

import com.api.investmanager.core.application.port.input.mark.ListMarksUseCase;
import com.api.investmanager.core.application.port.output.mark.ListMarksPort;
import com.api.investmanager.core.domain.model.Mark;

import java.util.List;

public class ListMarksUseCaseImpl implements ListMarksUseCase {
    private final ListMarksPort listMarksPort;

    public ListMarksUseCaseImpl(ListMarksPort listMarksPort) {
        this.listMarksPort = listMarksPort;
    }

    @Override
    public List<Mark> execute(String idUser) {
        return this.listMarksPort.execute(idUser);
    }
}
