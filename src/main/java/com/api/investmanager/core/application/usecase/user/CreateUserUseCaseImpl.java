package com.api.investmanager.core.application.usecase.user;

import com.api.investmanager.core.application.port.input.user.CreateUserUseCase;
import com.api.investmanager.core.application.port.output.user.ConsultUserPort;
import com.api.investmanager.core.application.port.output.user.CreateUserPort;
import com.api.investmanager.core.domain.model.User;
import com.api.investmanager.infra.config.exception.AlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CreateUserUseCaseImpl implements CreateUserUseCase {
    private final CreateUserPort createUserPort;

    private final ConsultUserPort consultUserPort;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CreateUserUseCaseImpl(CreateUserPort createUserPort, ConsultUserPort consultUserPort, PasswordEncoder passwordEncoder) {
        this.createUserPort = createUserPort;
        this.consultUserPort = consultUserPort;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void execute(User user) {
        User userExists = consultUserPort.execute(user.getEmail());
        if(Objects.nonNull(userExists) && Objects.nonNull(userExists.getEmail())) {
            throw new AlreadyExistsException("Este email já está em uso.");
        }
        String passwordEncoded = passwordEncoder.encode(user.getPassword());
        user.setPassword(passwordEncoded);
        this.createUserPort.execute(user);
    }
}
