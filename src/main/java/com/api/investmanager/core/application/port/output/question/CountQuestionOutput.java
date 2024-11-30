package com.api.investmanager.core.application.port.output.question;

import com.api.investmanager.core.application.dto.question.CountQuestionResponse;

import java.util.List;

public interface CountQuestionOutput {
    List<CountQuestionResponse> execute(String idUser);
}
