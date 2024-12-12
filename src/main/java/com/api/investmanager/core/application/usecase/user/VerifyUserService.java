package com.api.investmanager.core.application.usecase.user;

import com.api.investmanager.core.application.port.input.user.VerifyUserUseCase;
import com.api.investmanager.core.application.port.output.user.UserExistsByIdOutput;
import com.api.investmanager.core.domain.exception.NotFoundException;

public class VerifyUserService implements VerifyUserUseCase {
    private final UserExistsByIdOutput userExistsById;

    public VerifyUserService(UserExistsByIdOutput userExistsById) {
        this.userExistsById = userExistsById;
    }

    @Override
    public void execute(String idUser) {
        boolean userExists = userExistsById.execute(idUser);
        if(!userExists) {
            throw new NotFoundException("Usuario não encontrado");
        }
    }
}
