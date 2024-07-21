package com.api.investmanager.infra.repository;

import com.api.investmanager.infra.database.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findFirstByEmail(String email);
}
