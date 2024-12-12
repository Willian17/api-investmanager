package com.api.investmanager.core.application.usecase.user;

import com.api.investmanager.core.application.port.input.user.CreateUserUseCase;
import com.api.investmanager.core.application.port.output.cryptography.HashEncode;
import com.api.investmanager.core.application.port.output.user.ConsultUserOutput;
import com.api.investmanager.core.application.port.output.user.CreateUserOutput;
import com.api.investmanager.core.domain.model.User;
import com.api.investmanager.core.domain.exception.AlreadyExistsException;

import java.util.Objects;

public class CreateUserService implements CreateUserUseCase {
    private final CreateUserOutput createUserOutput;

    private final ConsultUserOutput consultUserOutput;

    private final HashEncode hashEncode;

    public CreateUserService(CreateUserOutput createUserOutput, ConsultUserOutput consultUserOutput, HashEncode hashEncode) {
        this.createUserOutput = createUserOutput;
        this.consultUserOutput = consultUserOutput;
        this.hashEncode = hashEncode;
    }

    @Override
    public void execute(User user) {
        User userExists = consultUserOutput.execute(user.getEmail());
        if(Objects.nonNull(userExists) && Objects.nonNull(userExists.getEmail())) {
            throw new AlreadyExistsException("Este email já está em uso.");
        }
        String passwordEncoded = hashEncode.encode(user.getPassword());
        user.setPassword(passwordEncoded);
        this.createUserOutput.execute(user);
    }
}
