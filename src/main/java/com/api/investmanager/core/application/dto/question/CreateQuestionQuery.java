package com.api.investmanager.core.application.dto.question;

import com.api.investmanager.core.domain.enuns.CategoryEnum;

public record CreateQuestionQuery(
         String idUser,
         String question,
         String criterion,
         CategoryEnum category
) {
}
