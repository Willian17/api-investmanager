package com.api.investmanager.core.application.dto.mark;

import com.api.investmanager.core.domain.enuns.CategoryEnum;

public record MarkDTO (
        String id,
        Integer percentage,
        CategoryEnum category
) {

}
