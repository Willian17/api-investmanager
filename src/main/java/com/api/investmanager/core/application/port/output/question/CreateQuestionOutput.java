package com.api.investmanager.core.application.port.output.question;

import com.api.investmanager.core.application.dto.question.CreateQuestionQuery;

public interface CreateQuestionOutput {
    void execute(CreateQuestionQuery data);
}
