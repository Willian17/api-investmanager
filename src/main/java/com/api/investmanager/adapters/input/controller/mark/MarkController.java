package com.api.investmanager.adapters.input.controller.mark;

import com.api.investmanager.adapters.input.controller.mark.dto.MarkResponseDTO;
import com.api.investmanager.core.application.port.input.mark.MarkFacade;
import com.api.investmanager.core.application.port.input.user.AuthenticatedUserProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/marks")
public class MarkController {
    private final MarkFacade markFacade;

    private final AuthenticatedUserProvider userProvider;

    @Autowired
    public MarkController(MarkFacade markFacade, AuthenticatedUserProvider userProvider) {
        this.markFacade = markFacade;
        this.userProvider = userProvider;
    }

    @GetMapping()
    public ResponseEntity<List<MarkResponseDTO>> findAllMarksByUser() {
        String idUser = userProvider.getUserId();
        List<MarkResponseDTO> markResponseDTOS = markFacade.listMarks(idUser);
        return ResponseEntity.ok(markResponseDTOS);
    }
}
