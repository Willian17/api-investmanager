package com.api.investmanager.core.application.port.output.question;

import com.api.investmanager.core.application.dto.question.UpdateQuestionQuery;
import com.api.investmanager.core.domain.model.Question;

public interface UpdateQuestionOutput {
    void execute(Question question, String idUser);
}
