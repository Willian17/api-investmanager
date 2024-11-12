package com.api.investmanager.infra.repository;

import com.api.investmanager.infra.database.entity.MarkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface MarkRepository extends JpaRepository<MarkEntity, UUID> {
    List<MarkEntity> findByUser_Id(UUID userId);
}
