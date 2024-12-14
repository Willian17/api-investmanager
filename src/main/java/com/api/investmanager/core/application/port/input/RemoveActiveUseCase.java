package com.api.investmanager.core.application.port.input;

import com.api.investmanager.core.application.dto.active.RemoveActiveQuery;

public interface RemoveActiveUseCase {
    void execute(RemoveActiveQuery removeActiveQuery);
}
