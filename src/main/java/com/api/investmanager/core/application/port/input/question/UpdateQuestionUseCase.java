package com.api.investmanager.core.application.port.input.question;

import com.api.investmanager.core.application.dto.question.CreateQuestionQuery;
import com.api.investmanager.core.application.dto.question.UpdateQuestionQuery;

public interface UpdateQuestionUseCase {
    void execute(UpdateQuestionQuery data);
}
