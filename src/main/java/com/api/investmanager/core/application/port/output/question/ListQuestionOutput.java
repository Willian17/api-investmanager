package com.api.investmanager.core.application.port.output.question;

import com.api.investmanager.core.application.dto.question.ListQuestionQuery;
import com.api.investmanager.core.domain.model.Question;

import java.util.List;

public interface ListQuestionOutput {
    List<Question> execute(ListQuestionQuery data);
}
