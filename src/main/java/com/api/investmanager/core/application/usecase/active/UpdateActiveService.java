package com.api.investmanager.core.application.usecase.active;

import com.api.investmanager.core.application.dto.active.UpdateActiveQuery;
import com.api.investmanager.core.application.port.input.active.UpdateActiveUseCase;
import com.api.investmanager.core.application.port.output.active.ConsultActiveById;
import com.api.investmanager.core.application.port.output.active.UpdateActiveOutput;
import com.api.investmanager.core.application.port.output.active.VerifyNameExistsOutput;
import com.api.investmanager.core.domain.exception.ClientException;
import com.api.investmanager.core.domain.exception.NotFoundException;
import com.api.investmanager.core.domain.model.Active;

import java.util.Optional;

public class UpdateActiveService implements UpdateActiveUseCase {
    private final ConsultActiveById consultActiveById;
    private final VerifyNameExistsOutput verifyNameExistsOutput;

    private final UpdateActiveOutput updateActiveOutput;

    public UpdateActiveService(ConsultActiveById consultActiveById, VerifyNameExistsOutput verifyNameExistsOutput, UpdateActiveOutput updateActiveOutput) {
        this.consultActiveById = consultActiveById;
        this.verifyNameExistsOutput = verifyNameExistsOutput;
        this.updateActiveOutput = updateActiveOutput;
    }

    @Override
    public void execute(UpdateActiveQuery updateActiveQuery) {
        Active active = getActiveOrThrow(updateActiveQuery);
        validIfNotUpdated(updateActiveQuery, active);
        active.setAmount(updateActiveQuery.amount());

        validNameActive(updateActiveQuery, active);
        active.setName(updateActiveQuery.name());

        updateActiveOutput.execute(active, updateActiveQuery.idUser());
    }


    private Active getActiveOrThrow(UpdateActiveQuery updateActiveQuery) {
        Optional<Active> optionalActive = consultActiveById.execute(updateActiveQuery.id());
        return optionalActive.orElseThrow(() -> new NotFoundException("Ativo não encontrado!"));
    }

    private static void validIfNotUpdated(UpdateActiveQuery updateActiveQuery, Active active) {
        if(isEqualsAmount(updateActiveQuery, active) && isEqualsName(updateActiveQuery, active)) {
            throw new ClientException("Ativo atualizado sem mudanças em relação ao ativo cadastrado!");
        }
    }

    private static boolean isEqualsName(UpdateActiveQuery updateActiveQuery, Active active) {
        return active.getName().equals(updateActiveQuery.name());
    }

    private static boolean isEqualsAmount(UpdateActiveQuery updateActiveQuery, Active active) {
        return active.getAmount().equals(updateActiveQuery.amount());
    }

    private void validNameActive(UpdateActiveQuery updateActiveQuery, Active active) {
        if(!isEqualsName(updateActiveQuery, active)) {
            Active activeVerify = new Active(active.getId(), updateActiveQuery.name(), active.getCategory(), active.getAmount());
            if(verifyNameExistsOutput.execute(activeVerify, updateActiveQuery.idUser())) {
                throw new ClientException("Nome atualizado não permitido, já existente em outro ativo!");
            }
        }
    }

}
