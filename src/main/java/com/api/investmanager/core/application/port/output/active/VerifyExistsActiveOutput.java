package com.api.investmanager.core.application.port.output.active;

import com.api.investmanager.core.application.dto.active.RemoveActiveQuery;

public interface VerifyExistsActiveOutput {
    boolean execute(RemoveActiveQuery removeActiveQuery);
}
