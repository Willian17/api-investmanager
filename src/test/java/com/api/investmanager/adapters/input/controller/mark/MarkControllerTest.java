package com.api.investmanager.adapters.input.controller.mark;

import com.api.investmanager.adapters.input.controller.mark.dto.MarkResponseDTO;
import com.api.investmanager.core.application.port.input.mark.MarkFacade;
import com.api.investmanager.core.application.port.input.user.AuthenticatedUserProvider;
import com.api.investmanager.core.domain.enuns.CategoryEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MarkControllerTest {
    @InjectMocks
    private MarkController markController;

    @Mock
    private AuthenticatedUserProvider authenticatedUserProvider;

    @Mock
    private MarkFacade markFacade;


    @Test
    void findAllMarksByUser() {
        String idUser = "dsjsfhsjdhfsdf";

        List<MarkResponseDTO> marksExpected = List.of(
                new MarkResponseDTO("id", CategoryEnum.ACOES_NACIONAIS, 50),
                new MarkResponseDTO("id2", CategoryEnum.ACOES_NACIONAIS, 50)
        );

        when(authenticatedUserProvider.getUserId()).thenReturn(idUser);
        when(markFacade.listMarks(idUser)).thenReturn(marksExpected);

        ResponseEntity<List<MarkResponseDTO>> response = markController.findAllMarksByUser();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(marksExpected, response.getBody());
        verify(markFacade).listMarks(idUser);
        verify(authenticatedUserProvider).getUserId();
    }
}