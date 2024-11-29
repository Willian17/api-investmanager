package com.api.investmanager.core.application.usecase.question;

import com.api.investmanager.core.application.dto.question.UpdateQuestionQuery;
import com.api.investmanager.core.application.port.input.question.UpdateQuestionUseCase;
import com.api.investmanager.core.application.port.output.question.ConsultQuestionOutput;
import com.api.investmanager.core.application.port.output.question.UpdateQuestionOutput;
import com.api.investmanager.core.application.port.output.user.UserExistsByIdPort;
import com.api.investmanager.core.domain.exception.NotFoundException;
import com.api.investmanager.core.domain.model.Question;

import java.util.Optional;

public class UpdateQuestionUseCaseImpl  implements UpdateQuestionUseCase {

    private final ConsultQuestionOutput questionExistsByIdOutput;

    private final UpdateQuestionOutput updateQuestionOutput;

    public UpdateQuestionUseCaseImpl(ConsultQuestionOutput questionExistsByIdPort, ConsultQuestionOutput questionExistsByIdPort1, UpdateQuestionOutput updateQuestionOutput) {
        this.questionExistsByIdOutput = questionExistsByIdPort;
        this.updateQuestionOutput = updateQuestionOutput;
    }

    @Override
    public void execute(UpdateQuestionQuery data) {
        Optional<Question> questionOptional = questionExistsByIdOutput.execute(data.id());

        updateQuestion(data, questionOptional.orElseThrow(() -> new NotFoundException("Pergunta não encontrada!")));
    }

    private void updateQuestion(UpdateQuestionQuery data, Question question) {
        question.setQuestion(data.question());
        question.setCriterion(data.criterion());
        updateQuestionOutput.execute(question, data.idUser());
    }
}
