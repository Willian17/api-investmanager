package com.api.investmanager.core.application.mapper;


import com.api.investmanager.adapters.input.controller.auth.dto.SigninRequestDTO;
import com.api.investmanager.adapters.input.controller.auth.dto.SignupRequestDTO;
import com.api.investmanager.core.domain.model.User;
import com.api.investmanager.infra.database.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User singupRequestDtoToUser(SignupRequestDTO userDto);

    User singninRequestDtoToUser(SigninRequestDTO userDto);

    User userEntityToUserModel(UserEntity userEntity);
}
