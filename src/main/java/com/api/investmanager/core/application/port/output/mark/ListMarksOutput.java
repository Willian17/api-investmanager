package com.api.investmanager.core.application.port.output.mark;

import com.api.investmanager.core.domain.model.Mark;

import java.util.List;

public interface ListMarksOutput {
    List<Mark> execute(String idUser);
}
