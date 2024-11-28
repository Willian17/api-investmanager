package com.api.investmanager.core.application.port.output.question;

import com.api.investmanager.core.domain.model.Question;

import java.util.Optional;

public interface ConsultQuestionOutput {
    Optional<Question> execute(String id);
}
