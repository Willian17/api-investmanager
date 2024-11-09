package com.api.investmanager.core.application.usecase.user;


import com.api.investmanager.core.application.port.input.user.AuthUserUseCase;
import com.api.investmanager.core.application.port.output.user.AuthUserPort;
import com.api.investmanager.core.application.port.output.user.ConsultUserPort;
import com.api.investmanager.core.domain.model.User;
import com.api.investmanager.infra.config.exception.ClientException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthUserUseCaseImpl implements AuthUserUseCase {

    private final ConsultUserPort consultUserPort;

    private final PasswordEncoder passwordEncoder;

    private final AuthUserPort authUserPort;

    public AuthUserUseCaseImpl(ConsultUserPort consultUserPort, PasswordEncoder passwordEncoder, AuthUserPort authUserPort) {
        this.consultUserPort = consultUserPort;
        this.passwordEncoder = passwordEncoder;
        this.authUserPort = authUserPort;
    }

    @Override
    public String execute(User user) {
        User userExists = consultUserPort.execute(user.getEmail());

        if(userExists == null) {
            throw new ClientException("Credenciais inválidas, tente novamente!");
        }

        boolean passwordMatch = passwordEncoder.matches(user.getPassword(), userExists.getPassword());
        if(!passwordMatch) {
            throw new ClientException("Credenciais inválidas, tente novamente!");
        }

        return this.authUserPort.execute(userExists);


    }
}
