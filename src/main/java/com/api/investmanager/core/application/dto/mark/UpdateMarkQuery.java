package com.api.investmanager.core.application.dto.mark;

import com.api.investmanager.core.domain.enuns.CategoryEnum;

import java.util.List;

public record UpdateMarkQuery(
        String idUser,
        List<MarkDTO> marks
) {
}
