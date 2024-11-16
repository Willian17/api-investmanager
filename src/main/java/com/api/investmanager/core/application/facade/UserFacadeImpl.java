package com.api.investmanager.core.application.facade;

import com.api.investmanager.adapters.input.controller.auth.dto.SigninRequestDTO;
import com.api.investmanager.adapters.input.controller.auth.dto.SignupRequestDTO;
import com.api.investmanager.core.application.mapper.UserMapper;
import com.api.investmanager.core.application.port.input.user.AuthUserUseCase;
import com.api.investmanager.core.application.port.input.user.CreateUserUseCase;
import com.api.investmanager.core.application.port.input.user.UserFacade;
import com.api.investmanager.core.domain.model.User;

public class UserFacadeImpl implements UserFacade {

    private final CreateUserUseCase createUserUseCase;

    private final AuthUserUseCase authUserUseCase;


    public UserFacadeImpl(CreateUserUseCase createUserUseCase, AuthUserUseCase authUserUseCase) {
        this.createUserUseCase = createUserUseCase;
        this.authUserUseCase = authUserUseCase;
    }

    @Override
    public void createUser(SignupRequestDTO userDto) {
        User user = UserMapper.INSTANCE.singupRequestDtoToUser(userDto);

        createUserUseCase.execute(user);
    }

    @Override
    public String authUser(SigninRequestDTO userDto) {
        User user = UserMapper.INSTANCE.singninRequestDtoToUser(userDto);;

       return authUserUseCase.execute(user);
    }
}
