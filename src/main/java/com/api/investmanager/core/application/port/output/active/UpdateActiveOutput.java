package com.api.investmanager.core.application.port.output.active;

import com.api.investmanager.core.domain.model.Active;

public interface UpdateActiveOutput {
    void execute(Active active, String idUser);
}
