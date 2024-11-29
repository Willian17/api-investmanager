package com.api.investmanager.core.application.dto.question;

import com.api.investmanager.core.domain.enuns.CategoryEnum;

public record ListQuestionQuery(
        String idUser,
        CategoryEnum category
) {

}
