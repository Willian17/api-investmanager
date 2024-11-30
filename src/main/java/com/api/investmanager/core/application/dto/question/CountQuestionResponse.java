package com.api.investmanager.core.application.dto.question;

import com.api.investmanager.core.domain.enuns.CategoryQuestionEnum;

public record CountQuestionResponse(
        CategoryQuestionEnum category,
        Integer quantity
) {

}
