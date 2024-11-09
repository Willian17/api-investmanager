package com.api.investmanager.core.application.port.input.user;

import com.api.investmanager.core.domain.model.User;

public interface AuthUserUseCase {
    String execute(User user);
}
