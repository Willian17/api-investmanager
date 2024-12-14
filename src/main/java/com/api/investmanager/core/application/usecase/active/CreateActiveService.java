package com.api.investmanager.core.application.usecase.active;

import com.api.investmanager.core.application.dto.active.CreateActiveQuery;
import com.api.investmanager.core.application.port.input.active.CreateActiveUseCase;
import com.api.investmanager.core.application.port.output.active.CreateActiveOutput;
import com.api.investmanager.core.application.port.output.active.VerifyNameExistsOutput;
import com.api.investmanager.core.domain.exception.ClientException;
import com.api.investmanager.core.domain.model.Active;


public class CreateActiveService implements CreateActiveUseCase {

    private final VerifyNameExistsOutput verifyNameExists;

    private final CreateActiveOutput createActiveOutput;

    public CreateActiveService(VerifyNameExistsOutput verifyNameExists, CreateActiveOutput createUserOutput) {
        this.verifyNameExists = verifyNameExists;
        this.createActiveOutput = createUserOutput;
    }

    @Override
    public void execute(CreateActiveQuery createActiveQuery) {
        Active active = new Active(createActiveQuery.category(), createActiveQuery.name());
        active.setAmount(createActiveQuery.amount());

        if(verifyNameExists.execute(active, createActiveQuery.idUser())) {
            throw new ClientException("Ativo já cadastrado!");
        }

        createActiveOutput.execute(active, createActiveQuery.idUser());
    }
}
