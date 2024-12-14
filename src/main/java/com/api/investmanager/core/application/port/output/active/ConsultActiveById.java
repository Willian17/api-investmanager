package com.api.investmanager.core.application.port.output.active;

import com.api.investmanager.core.domain.model.Active;

import java.util.Optional;

public interface ConsultActiveById {
    Optional<Active> execute(String id);
}
