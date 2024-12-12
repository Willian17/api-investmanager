package com.api.investmanager.core.application.usecase.mark;

import com.api.investmanager.core.application.port.input.mark.AddMarksDefaultUseCase;
import com.api.investmanager.core.application.port.output.mark.CreateMarksOutput;
import com.api.investmanager.core.domain.enuns.CategoryEnum;
import com.api.investmanager.core.domain.model.Mark;

import java.util.List;
import java.util.stream.Stream;

public class AddMarksDefaultService implements AddMarksDefaultUseCase {
    private final CreateMarksOutput createMarksOutput;

    public AddMarksDefaultService(CreateMarksOutput createMarksOutput) {
        this.createMarksOutput = createMarksOutput;
    }

    @Override
    public void execute(String idUser) {
        List<Mark> marks = Stream.of(CategoryEnum.values())
                .map(category -> new Mark(null, 0, category))
                .toList();
        createMarksOutput.execute(marks, idUser);
    }
}
