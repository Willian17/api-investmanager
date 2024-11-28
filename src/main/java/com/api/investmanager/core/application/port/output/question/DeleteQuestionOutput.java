package com.api.investmanager.core.application.port.output.question;

import com.api.investmanager.core.application.dto.question.RemoveQuestionQuery;
import com.api.investmanager.core.domain.model.Question;

public interface DeleteQuestionOutput {
    void execute(RemoveQuestionQuery data);
}
