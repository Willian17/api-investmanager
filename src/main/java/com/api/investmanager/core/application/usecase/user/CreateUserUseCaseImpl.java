package com.api.investmanager.core.application.usecase.user;

import com.api.investmanager.core.application.port.input.user.CreateUserUseCase;
import com.api.investmanager.core.application.port.output.user.CreateUserPort;
import com.api.investmanager.core.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateUserUseCaseImpl implements CreateUserUseCase {
    private final CreateUserPort createUserPort;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CreateUserUseCaseImpl(CreateUserPort createUserPort, PasswordEncoder passwordEncoder) {
        this.createUserPort = createUserPort;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void execute(User user) {
        String passwordEncoded = passwordEncoder.encode(user.getPassword());
        user.setPassword(passwordEncoded);
        this.createUserPort.execute(user);
    }
}
