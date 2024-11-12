package com.api.investmanager.core.application.port.input.mark;


import com.api.investmanager.adapters.input.controller.mark.dto.MarkResponseDTO;

import java.util.List;

public interface MarkFacade {
    List<MarkResponseDTO> listMarks(String idUser);

}
