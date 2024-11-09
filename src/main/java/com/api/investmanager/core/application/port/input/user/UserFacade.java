package com.api.investmanager.core.application.port.input.user;

import com.api.investmanager.adapters.input.controller.auth.dto.SigninRequestDTO;
import com.api.investmanager.adapters.input.controller.auth.dto.SignupRequestDTO;

public interface UserFacade {
    void createUser(SignupRequestDTO user);
    String authUser(SigninRequestDTO user);

}
