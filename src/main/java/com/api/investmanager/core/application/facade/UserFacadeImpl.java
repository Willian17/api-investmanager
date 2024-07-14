package com.api.investmanager.core.application.facade;

import com.api.investmanager.adapters.input.controller.auth.dto.SignupRequestDTO;
import com.api.investmanager.core.application.mapper.UserMapper;
import com.api.investmanager.core.application.port.input.user.CreateUserUseCase;
import com.api.investmanager.core.application.port.input.user.UserFacade;
import com.api.investmanager.core.domain.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserFacadeImpl implements UserFacade {

    private final CreateUserUseCase createUserUseCase;

    public UserFacadeImpl( CreateUserUseCase createUserUseCase) {
        this.createUserUseCase = createUserUseCase;
    }

    @Override
    public void createUser(SignupRequestDTO userDto) {
        User user = UserMapper.INSTANCE.singupRequestDtoToUser(userDto);

        createUserUseCase.execute(user);
    }
}
