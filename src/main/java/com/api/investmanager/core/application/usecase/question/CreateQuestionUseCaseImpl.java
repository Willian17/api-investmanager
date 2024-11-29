package com.api.investmanager.core.application.usecase.question;

import com.api.investmanager.core.application.dto.question.CreateQuestionQuery;
import com.api.investmanager.core.application.port.input.question.CreateQuestionUseCase;
import com.api.investmanager.core.application.port.output.question.CreateQuestionOutput;

public class CreateQuestionUseCaseImpl implements CreateQuestionUseCase {

    private final CreateQuestionOutput createQuestionOutput;

    public CreateQuestionUseCaseImpl(CreateQuestionOutput createQuestionOutput) {
        this.createQuestionOutput = createQuestionOutput;
    }

    @Override
    public void execute(CreateQuestionQuery createQuestionQuery) {
        createQuestionOutput.execute(createQuestionQuery);
    }
}
