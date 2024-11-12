package com.api.investmanager.infra.database.entity;

import com.api.investmanager.core.domain.enuns.CategoryEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity(name = "marks")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MarkEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private CategoryEnum category;

    private Integer percentage;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", nullable = false)
    private UserEntity user;

}
