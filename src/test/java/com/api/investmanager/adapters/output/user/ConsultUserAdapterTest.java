package com.api.investmanager.adapters.output.user;

import com.api.investmanager.core.application.mapper.UserMapper;
import com.api.investmanager.core.domain.model.User;
import com.api.investmanager.infra.database.entity.UserEntity;
import com.api.investmanager.infra.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ConsultUserAdapterTest {
    @InjectMocks
    private ConsultUserAdapter consultUserAdapter;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;


    @Test
    void returnUserIfExists() {
        String email = "email@email.com";

        User userExpected = getUserExpected();

        UserEntity userEntityReturn = UserEntity
                .builder()
                .name(userExpected.getName())
                .email(userExpected.getEmail())
                .password(userExpected.getPassword())
                .build();

        when(userRepository.findFirstByEmail(anyString())).thenReturn(Optional.of(userEntityReturn));
        when(userMapper.userEntityToUserModel(any())).thenReturn(userExpected);

        User userActual = consultUserAdapter.execute(email);

        assertEquals(userExpected, userActual);
        verify(userRepository).findFirstByEmail(email);
        verify(userMapper).userEntityToUserModel(userEntityReturn);
    }



    @Test
    void returnNullIfNotExists() {
        String email = "email@email.com";

        when(userRepository.findFirstByEmail(anyString())).thenReturn(Optional.empty());

        User userActual = consultUserAdapter.execute(email);

        assertNull(userActual);
        verify(userRepository).findFirstByEmail(email);
        verifyNoInteractions(userMapper);
    }

    private static User getUserExpected() {
        return new User(null, "name", "email@email.com", "password");
    }


}