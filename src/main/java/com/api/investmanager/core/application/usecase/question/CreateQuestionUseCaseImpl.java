package com.api.investmanager.core.application.usecase.question;

import com.api.investmanager.core.application.dto.question.CreateQuestionQuery;
import com.api.investmanager.core.application.port.input.question.CreateQuestionUseCase;
import com.api.investmanager.core.application.port.output.question.CreateQuestionOutput;
import com.api.investmanager.core.application.port.output.user.UserExistsByIdPort;
import com.api.investmanager.core.domain.exception.NotFoundException;

public class CreateQuestionUseCaseImpl implements CreateQuestionUseCase {
    private final UserExistsByIdPort userExistsById;

    private final CreateQuestionOutput createQuestionOutput;

    public CreateQuestionUseCaseImpl(UserExistsByIdPort userExistsByIdPort, CreateQuestionOutput createQuestionOutput) {
        this.userExistsById = userExistsByIdPort;
        this.createQuestionOutput = createQuestionOutput;
    }

    @Override
    public void execute(CreateQuestionQuery createQuestionQuery) {
        boolean userExists = userExistsById.execute(createQuestionQuery.idUser());
        if(!userExists) {
            throw new NotFoundException("Usuario não encontrado");
        }

        createQuestionOutput.execute(createQuestionQuery);
    }
}
