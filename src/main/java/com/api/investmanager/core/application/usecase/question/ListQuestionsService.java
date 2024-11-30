package com.api.investmanager.core.application.usecase.question;

import com.api.investmanager.core.application.dto.question.ListQuestionQuery;
import com.api.investmanager.core.application.port.input.question.ListQuestionsUseCase;
import com.api.investmanager.core.application.port.output.question.ListQuestionOutput;
import com.api.investmanager.core.domain.model.Question;

import java.util.List;

public class ListQuestionsService implements ListQuestionsUseCase {

    private final ListQuestionOutput listQuestionOutput;

    public ListQuestionsService(ListQuestionOutput listQuestionOutput) {
        this.listQuestionOutput = listQuestionOutput;
    }

    @Override
    public List<Question> execute(ListQuestionQuery data) {
        return listQuestionOutput.execute(data);
    }
}
