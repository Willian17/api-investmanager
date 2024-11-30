package com.api.investmanager.core.application.usecase.question;

import com.api.investmanager.core.application.dto.question.CountQuestionResponse;
import com.api.investmanager.core.application.port.input.question.CountQuestionsUseCase;
import com.api.investmanager.core.application.port.output.question.CountQuestionOutput;
import com.api.investmanager.core.domain.enuns.CategoryEnum;
import com.api.investmanager.core.domain.enuns.CategoryQuestionEnum;

import java.util.Arrays;
import java.util.List;

public class CountQuestionsService implements CountQuestionsUseCase {

    private final CountQuestionOutput countQuestionOutput;

    public CountQuestionsService(CountQuestionOutput countQuestionOutput) {
        this.countQuestionOutput = countQuestionOutput;
    }

    @Override
    public List<CountQuestionResponse> execute(String idUser) {
        List<CountQuestionResponse> response = countQuestionOutput.execute(idUser);

        Arrays.stream(CategoryQuestionEnum.values())
                .filter(categoryEnum -> response.stream().noneMatch(r -> r.category().equals(categoryEnum)))
                    .forEach(categoryEnum -> {
                        response.add(new CountQuestionResponse(categoryEnum, 0));
                    });

        return response;
    }
}
