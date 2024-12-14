package com.api.investmanager.core.application.port.input.active;

import com.api.investmanager.core.application.dto.active.CreateActiveQuery;

public interface CreateActiveUseCase {
    void execute (CreateActiveQuery createActiveQuery);
}
