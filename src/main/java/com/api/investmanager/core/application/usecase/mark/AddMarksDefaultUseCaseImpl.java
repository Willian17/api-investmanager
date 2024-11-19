package com.api.investmanager.core.application.usecase.mark;

import com.api.investmanager.core.application.port.input.mark.AddMarksDefaultUseCase;
import com.api.investmanager.core.application.port.output.mark.CreateMarksPort;
import com.api.investmanager.core.domain.enuns.CategoryEnum;
import com.api.investmanager.core.domain.model.Mark;

import java.util.List;
import java.util.stream.Stream;

public class AddMarksDefaultUseCaseImpl implements AddMarksDefaultUseCase {
    private final CreateMarksPort createMarksPort;

    public AddMarksDefaultUseCaseImpl(CreateMarksPort createMarksPort) {
        this.createMarksPort = createMarksPort;
    }

    @Override
    public void execute(String idUser) {
        List<Mark> marks = Stream.of(CategoryEnum.values())
                .map(category -> new Mark(null, 0, category))
                .toList();
        createMarksPort.execute(marks, idUser);
    }
}
