package com.api.investmanager.core.application.usecase.user;


import com.api.investmanager.core.application.port.input.user.AuthUserUseCase;
import com.api.investmanager.core.application.port.output.cryptography.HashCompare;
import com.api.investmanager.core.application.port.output.user.AuthUserPort;
import com.api.investmanager.core.application.port.output.user.ConsultUserPort;
import com.api.investmanager.core.domain.model.User;
import com.api.investmanager.infra.exception.ClientException;

public class AuthUserUseCaseImpl implements AuthUserUseCase {

    private final ConsultUserPort consultUserPort;

    private final HashCompare hashCompare;

    private final AuthUserPort authUserPort;

    public AuthUserUseCaseImpl(ConsultUserPort consultUserPort, HashCompare hashCompare, AuthUserPort authUserPort) {
        this.consultUserPort = consultUserPort;
        this.hashCompare = hashCompare;
        this.authUserPort = authUserPort;
    }

    @Override
    public String execute(User user) {
        User userExists = consultUserPort.execute(user.getEmail());

        if(userExists == null) {
            throw new ClientException("Credenciais inválidas, tente novamente!");
        }

        boolean passwordMatch = hashCompare.compare(user.getPassword(), userExists.getPassword());
        if(!passwordMatch) {
            throw new ClientException("Credenciais inválidas, tente novamente!");
        }

        return this.authUserPort.execute(userExists);
    }
}
