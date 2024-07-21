package com.api.investmanager.adapters.output.user;

import com.api.investmanager.core.application.mapper.UserMapper;
import com.api.investmanager.core.application.port.output.user.ConsultUserPort;
import com.api.investmanager.core.domain.model.User;
import com.api.investmanager.infra.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConsultUserAdapter implements ConsultUserPort {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public ConsultUserAdapter(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public User execute(String email) {
        return userRepository
                .findFirstByEmail(email)
                .map(userMapper::userEntityToUserModel)
                .orElse(null);
    }
}
