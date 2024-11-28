package com.api.investmanager.core.application.dto.question;

public record UpdateQuestionQuery(
         String id,
         String idUser,
         String question,
         String criterion
) {
}
