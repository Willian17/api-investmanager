package com.api.investmanager.adapters.output.user;

import com.api.investmanager.infra.config.properties.AuthProperties;
import io.jsonwebtoken.Jwts;
import com.api.investmanager.core.application.port.output.user.AuthUserPort;
import com.api.investmanager.core.domain.model.User;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class AuthUserAdapter implements AuthUserPort {

    private final AuthProperties authProperties;

    public AuthUserAdapter(AuthProperties authProperties) {
        this.authProperties = authProperties;
    }

    @Override
    public String execute(User user) {
        Date now = new Date();
        long expirationTime = convertHoursToMilliseconds(authProperties.getExpirationHours());
        Date expiryDate = new Date(now.getTime() + expirationTime);

        SecretKey secretKey = Keys.hmacShaKeyFor(authProperties.getPrivateSecretKey().getBytes());

        return Jwts.builder()
                .subject(String.valueOf(user.getId()))
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(secretKey)
                .compact();
    }

    private long convertHoursToMilliseconds(Long hours) {
        return  hours * 60 * 60 * 1000;
    }
}
