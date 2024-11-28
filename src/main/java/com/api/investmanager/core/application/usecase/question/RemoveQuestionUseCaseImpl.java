package com.api.investmanager.core.application.usecase.question;

import com.api.investmanager.core.application.dto.question.RemoveQuestionQuery;
import com.api.investmanager.core.application.port.input.question.RemoveQuestionUseCase;
import com.api.investmanager.core.application.port.output.question.ConsultQuestionOutput;
import com.api.investmanager.core.application.port.output.question.DeleteQuestionOutput;
import com.api.investmanager.core.application.port.output.user.UserExistsByIdPort;
import com.api.investmanager.core.domain.exception.NotFoundException;
import com.api.investmanager.core.domain.model.Question;

import java.util.Optional;

public class RemoveQuestionUseCaseImpl implements RemoveQuestionUseCase {

    private final UserExistsByIdPort userExistsById;
    private final ConsultQuestionOutput consultQuestionOutput;
    private final DeleteQuestionOutput deleteQuestionOutput;

    public RemoveQuestionUseCaseImpl(UserExistsByIdPort userExistsById, ConsultQuestionOutput consultQuestionOutput, DeleteQuestionOutput deleteQuestionOutput) {
        this.userExistsById = userExistsById;
        this.consultQuestionOutput = consultQuestionOutput;
        this.deleteQuestionOutput = deleteQuestionOutput;
    }

    @Override
    public void execute(RemoveQuestionQuery data) {
        boolean userExists = userExistsById.execute(data.idUser());
        if(!userExists) {
            throw new NotFoundException("Usuario não encontrado");
        }

        Optional<Question> question = consultQuestionOutput.execute(data.id());
        if(question.isEmpty()) {
            throw new NotFoundException("Pergunta não encontrada!");
        }

        deleteQuestionOutput.execute(data);
    }
}
