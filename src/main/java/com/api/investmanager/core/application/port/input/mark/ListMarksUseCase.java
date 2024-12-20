package com.api.investmanager.core.application.port.input.mark;

import com.api.investmanager.core.domain.model.Mark;

import java.util.List;

public interface ListMarksUseCase {
    List<Mark> execute(String idUser);
}
