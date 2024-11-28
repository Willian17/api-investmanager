package com.api.investmanager.core.application.port.input.question;

import com.api.investmanager.core.application.dto.question.CreateQuestionQuery;

public interface CreateQuestionUseCase {
    void execute(CreateQuestionQuery createQuestionQuery);
}
