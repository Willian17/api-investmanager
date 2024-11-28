package com.api.investmanager.core.application.usecase.user;

import com.api.investmanager.core.application.port.input.user.CreateUserUseCase;
import com.api.investmanager.core.application.port.output.cryptography.HashEncode;
import com.api.investmanager.core.application.port.output.user.ConsultUserPort;
import com.api.investmanager.core.application.port.output.user.CreateUserPort;
import com.api.investmanager.core.domain.model.User;
import com.api.investmanager.core.domain.exception.AlreadyExistsException;

import java.util.Objects;

public class CreateUserUseCaseImpl implements CreateUserUseCase {
    private final CreateUserPort createUserPort;

    private final ConsultUserPort consultUserPort;

    private final HashEncode hashEncode;

    public CreateUserUseCaseImpl(CreateUserPort createUserPort, ConsultUserPort consultUserPort, HashEncode hashEncode) {
        this.createUserPort = createUserPort;
        this.consultUserPort = consultUserPort;
        this.hashEncode = hashEncode;
    }

    @Override
    public void execute(User user) {
        User userExists = consultUserPort.execute(user.getEmail());
        if(Objects.nonNull(userExists) && Objects.nonNull(userExists.getEmail())) {
            throw new AlreadyExistsException("Este email já está em uso.");
        }
        String passwordEncoded = hashEncode.encode(user.getPassword());
        user.setPassword(passwordEncoded);
        this.createUserPort.execute(user);
    }
}
