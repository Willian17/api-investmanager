package com.api.investmanager.core.application.port.output.user;


import com.api.investmanager.core.domain.model.User;

public interface ConsultUserPort {
    User execute(String email);
}
