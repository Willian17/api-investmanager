package com.api.investmanager.core.application.usecase.user;

import com.api.investmanager.core.application.port.input.user.VerifyUserUseCase;
import com.api.investmanager.core.application.port.output.user.UserExistsByIdPort;
import com.api.investmanager.core.domain.exception.NotFoundException;

public class VerifyUserUseCaseImpl implements VerifyUserUseCase {
    private final UserExistsByIdPort userExistsById;

    public VerifyUserUseCaseImpl(UserExistsByIdPort userExistsById) {
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
