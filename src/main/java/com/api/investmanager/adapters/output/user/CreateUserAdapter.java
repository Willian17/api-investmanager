package com.api.investmanager.adapters.output.user;

import com.api.investmanager.adapters.input.controller.auth.dto.SignupRequestDTO;
import com.api.investmanager.core.application.port.output.user.CreateUserPort;
import com.api.investmanager.core.domain.model.User;
import com.api.investmanager.infra.database.entity.UserEntity;
import com.api.investmanager.infra.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CreateUserAdapter implements CreateUserPort {

    private final UserRepository createRepository;


    @Autowired
    public CreateUserAdapter(UserRepository createRepository) {
        this.createRepository = createRepository;
    }

    @Override
    public void execute(User user) {
        UserEntity userEntity = UserEntity
                .builder()
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
        createRepository.save(userEntity);
    }
}
