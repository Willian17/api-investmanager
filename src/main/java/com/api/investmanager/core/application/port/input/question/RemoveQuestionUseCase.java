package com.api.investmanager.core.application.port.input.question;

import com.api.investmanager.core.application.dto.question.RemoveQuestionQuery;

public interface RemoveQuestionUseCase {
    void execute(RemoveQuestionQuery data);
}
