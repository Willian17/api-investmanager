package com.api.investmanager.adapters.output.user;

import com.api.investmanager.core.domain.model.User;
import com.api.investmanager.infra.database.entity.UserEntity;
import com.api.investmanager.infra.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CreateUserEntityAdapterTest {
    @InjectMocks
    private CreateUserAdapter createUserAdapter;

    @Mock
    private UserRepository userRepository;

    @Test
    void createUserSuccess() {
        User user = getUser();
        UserEntity userEntityExpected = getUserEntity(user);

        createUserAdapter.execute(user);
        verify(userRepository).save(userEntityExpected);
    }

    private static UserEntity getUserEntity(User user) {
        return UserEntity
                .builder()
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }

    private static User getUser() {
        return new User(null, "name", "email@email.com", "password");
    }
}