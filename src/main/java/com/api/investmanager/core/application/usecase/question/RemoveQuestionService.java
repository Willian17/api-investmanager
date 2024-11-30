package com.api.investmanager.core.application.usecase.question;

import com.api.investmanager.core.application.dto.question.RemoveQuestionQuery;
import com.api.investmanager.core.application.port.input.question.RemoveQuestionUseCase;
import com.api.investmanager.core.application.port.output.question.ConsultQuestionOutput;
import com.api.investmanager.core.application.port.output.question.DeleteQuestionOutput;
import com.api.investmanager.core.domain.exception.NotFoundException;
import com.api.investmanager.core.domain.model.Question;

import java.util.Optional;

public class RemoveQuestionService implements RemoveQuestionUseCase {

    private final ConsultQuestionOutput consultQuestionOutput;
    private final DeleteQuestionOutput deleteQuestionOutput;

    public RemoveQuestionService(ConsultQuestionOutput consultQuestionOutput, DeleteQuestionOutput deleteQuestionOutput) {
        this.consultQuestionOutput = consultQuestionOutput;
        this.deleteQuestionOutput = deleteQuestionOutput;
    }

    @Override
    public void execute(RemoveQuestionQuery data) {
        Optional<Question> question = consultQuestionOutput.execute(data.id());
        if(question.isEmpty()) {
            throw new NotFoundException("Pergunta não encontrada!");
        }

        deleteQuestionOutput.execute(data);
    }
}
