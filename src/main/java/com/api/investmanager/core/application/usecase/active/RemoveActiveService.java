package com.api.investmanager.core.application.usecase.active;

import com.api.investmanager.core.application.dto.active.RemoveActiveQuery;
import com.api.investmanager.core.application.port.input.RemoveActiveUseCase;
import com.api.investmanager.core.application.port.output.active.DeleteActiveOutput;
import com.api.investmanager.core.application.port.output.active.VerifyExistsActiveOutput;
import com.api.investmanager.core.domain.exception.NotFoundException;

public class RemoveActiveService implements RemoveActiveUseCase {
    private final VerifyExistsActiveOutput verifyExistsActive;

    private final DeleteActiveOutput deleteActive;

    public RemoveActiveService(VerifyExistsActiveOutput verifyExistsActive, DeleteActiveOutput deleteActive) {
        this.verifyExistsActive = verifyExistsActive;
        this.deleteActive = deleteActive;
    }

    @Override
    public void execute(RemoveActiveQuery removeActiveQuery) {

        boolean exists = verifyExistsActive.execute(removeActiveQuery);
        if(!exists) {
            throw new NotFoundException("Ativo não encontrado");
        }

        deleteActive.execute(removeActiveQuery);
    }
}
