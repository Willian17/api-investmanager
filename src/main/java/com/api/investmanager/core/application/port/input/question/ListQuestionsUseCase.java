package com.api.investmanager.core.application.port.input.question;

import com.api.investmanager.core.application.dto.question.ListQuestionQuery;
import com.api.investmanager.core.domain.model.Question;

import java.util.List;

public interface ListQuestionsUseCase {
    List<Question> execute(ListQuestionQuery data);
}
