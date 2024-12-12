package com.api.investmanager.core.application.usecase.mark;

import com.api.investmanager.core.application.port.input.mark.UpdateMarksUseCase;
import com.api.investmanager.core.application.port.output.mark.UpdateMarksOutput;
import com.api.investmanager.core.domain.enuns.CategoryEnum;
import com.api.investmanager.core.domain.model.Mark;
import com.api.investmanager.core.domain.exception.ClientException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UpdateMarksService implements UpdateMarksUseCase {

    private final UpdateMarksOutput updateMarksOutput;

    public UpdateMarksService(UpdateMarksOutput updateMarksOutput) {
        this.updateMarksOutput = updateMarksOutput;
    }

    @Override
    public void execute(List<Mark> marks, String idUser) {
        validMarks(marks);

        updateMarksOutput.execute(marks, idUser);
    }

    private static void validMarks(List<Mark> marks) {
        Set<CategoryEnum> categoriesRequired = Set.of(CategoryEnum.values());

        Set<CategoryEnum> categoriesPresent = new HashSet<>();
        int totalPercentage = 0;

        for (Mark mark : marks) {
            if (mark.getPercentage() < 0) {
                throw new ClientException("A porcentagem não pode ser inferior a 0%.");
            }
            categoriesPresent.add(mark.getCategory());
            totalPercentage += mark.getPercentage();
        }

        if (!categoriesPresent.containsAll(categoriesRequired)) {
            throw new ClientException("Para completar a operação, todas as categorias exigidas devem ser informadas.");
        }

        if (totalPercentage > 100) {
            throw new ClientException("A soma dos percentuais excede o limite permitido de 100%.");
        }
    }
}
