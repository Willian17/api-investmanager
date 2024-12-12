package com.api.investmanager.core.application.usecase.user;


import com.api.investmanager.core.application.port.input.user.AuthUserUseCase;
import com.api.investmanager.core.application.port.output.cryptography.HashCompare;
import com.api.investmanager.core.application.port.output.user.AuthUserPort;
import com.api.investmanager.core.application.port.output.user.ConsultUserOutput;
import com.api.investmanager.core.domain.model.User;
import com.api.investmanager.core.domain.exception.ClientException;

public class AuthUserService implements AuthUserUseCase {

    private final ConsultUserOutput consultUserOutput;

    private final HashCompare hashCompare;

    private final AuthUserPort authUserPort;

    public AuthUserService(ConsultUserOutput consultUserOutput, HashCompare hashCompare, AuthUserPort authUserPort) {
        this.consultUserOutput = consultUserOutput;
        this.hashCompare = hashCompare;
        this.authUserPort = authUserPort;
    }

    @Override
    public String execute(User user) {
        User userExists = consultUserOutput.execute(user.getEmail());

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
