package com.api.investmanager.adapters.output.user;

import com.api.investmanager.core.domain.model.User;
import com.api.investmanager.infra.config.properties.AuthProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.crypto.SecretKey;

import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AuthUserAdapterTest {

    @InjectMocks
    private AuthUserAdapter authUserAdapter;

    @Mock
    private AuthProperties authProperties;

    private SecretKey secretKey;

    @BeforeEach
    void setUp() {
        secretKey = Keys.hmacShaKeyFor("minha_chave_secreta_teste_mockdhfuishdfuisdfusdufiysduifysudify".getBytes());
    }

    @Test
    void shouldGenerateValidJwtToken() {
        User user = new User();
        user.setId(String.valueOf(new UUID(3, 3)));
        long horasExpire = 240L;

        when(authProperties.getPrivateSecretKey()).thenReturn("minha_chave_secreta_teste_mockdhfuishdfuisdfusdufiysduifysudify");
        when(authProperties.getExpirationHours()).thenReturn(horasExpire);


        String token = authUserAdapter.execute(user);
        assertNotNull(token, "O token JWT não deve ser nulo");

        Claims claims = (Claims) Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parse(token)
                .getPayload();


        assertEquals(String.valueOf(user.getId()), claims.getSubject(), "O ID do usuário não corresponde ao 'subject' no token");
        assertNotNull(claims.getIssuedAt(), "O campo 'issuedAt' deve estar presente no token");
        assertNotNull(claims.getExpiration(), "O campo 'expiration' deve estar presente no token");


        Date now = new Date();
        long expirationTimeExpected = horasExpire * 60 * 60 * 1000;
        Date expectedExpiryDate = new Date(now.getTime() + expirationTimeExpected);

        long delta = 10000;
        assertTrue(Math.abs(claims.getExpiration().getTime() - expectedExpiryDate.getTime()) < delta,
                "A data de expiração não corresponde ao esperado (10 dias a partir da data de emissão)");
    }

}