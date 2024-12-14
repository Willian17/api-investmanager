package com.api.investmanager.core.application.dto.active;

import com.api.investmanager.core.domain.enuns.CategoryEnum;

import java.math.BigDecimal;

public record UpdateActiveQuery(
        String idUser,
        String id,
        String name,
        CategoryEnum category,
        BigDecimal amount
) {

}
