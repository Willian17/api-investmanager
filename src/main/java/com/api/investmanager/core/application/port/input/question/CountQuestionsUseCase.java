package com.api.investmanager.core.application.port.input.question;

import com.api.investmanager.core.application.dto.question.CountQuestionResponse;

import java.util.List;

public interface CountQuestionsUseCase {
    List<CountQuestionResponse> execute(String idUser);
}
