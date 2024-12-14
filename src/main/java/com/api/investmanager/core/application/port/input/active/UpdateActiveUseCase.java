package com.api.investmanager.core.application.port.input.active;

import com.api.investmanager.core.application.dto.active.UpdateActiveQuery;

public interface UpdateActiveUseCase {
    void execute(UpdateActiveQuery updateActiveQuery);
}
