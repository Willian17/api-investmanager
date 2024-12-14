package com.api.investmanager.core.application.port.input.mark;

import com.api.investmanager.core.application.dto.mark.UpdateMarkQuery;
import com.api.investmanager.core.domain.model.Mark;

import java.util.List;

public interface UpdateMarksUseCase {
    void execute(UpdateMarkQuery updateMarkQuery);
}
