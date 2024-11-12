package com.api.investmanager.adapters.input.provider;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserProviderAuthTest {

    @InjectMocks
    private UserProviderAuth userProviderAuth;

    private MockedStatic<SecurityContextHolder> securityContextHolderMock;

    @Mock
    private Authentication authentication;

    @Mock
    private SecurityContext securityContext;

    @BeforeEach
    void setUp() {
        securityContextHolderMock = Mockito.mockStatic(SecurityContextHolder.class);
    }

    @AfterEach
    void tearDown() {
        securityContextHolderMock.close();
    }

    @Test
    void getUserId_whenAuthenticated_shouldReturnUserId() {
        when(authentication.isAuthenticated()).thenReturn(true);
        when(authentication.getPrincipal()).thenReturn("expectedUserId");
        when(securityContext.getAuthentication()).thenReturn(authentication);

        securityContextHolderMock.when(SecurityContextHolder::getContext).thenReturn(securityContext);

        String userId = userProviderAuth.getUserId();
        assertEquals("expectedUserId", userId);
        verify(authentication).isAuthenticated();
        verify(authentication).getPrincipal();
        verify(securityContext).getAuthentication();
    }

    @Test
    void getUserId_whenNotAuthenticated_shouldReturnNull() {
        when(authentication.isAuthenticated()).thenReturn(false);
        when(securityContext.getAuthentication()).thenReturn(authentication);

        securityContextHolderMock.when(SecurityContextHolder::getContext).thenReturn(securityContext);

        String userId = userProviderAuth.getUserId();
        assertNull(userId);
        verify(authentication).isAuthenticated();
        verify(securityContext).getAuthentication();
    }

    @Test
    void getUserId_whenAuthenticationIsNull_shouldReturnNull() {
        when(securityContext.getAuthentication()).thenReturn(null);

        securityContextHolderMock.when(SecurityContextHolder::getContext).thenReturn(securityContext);

        String userId = userProviderAuth.getUserId();
        assertNull(userId);
        verify(securityContext).getAuthentication();
        verifyNoInteractions(authentication);
    }
}