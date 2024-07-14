package com.api.investmanager.adapters.input.controller.auth;

import com.api.investmanager.adapters.input.controller.auth.dto.SignupRequestDTO;
import com.api.investmanager.core.application.port.input.user.UserFacade;
import com.api.investmanager.infra.config.AuthConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthController.class)
@Import(AuthConfig.class)
public class AuthControllerIntTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserFacade userFacade;

    @Test
    public void whenValidInput_thenReturns200() throws Exception {
        SignupRequestDTO validRequest = new SignupRequestDTO("name", "email@email.com", "12345678");

        mockMvc.perform(post("/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(validRequest)))
                .andExpect(status().isCreated());
    }

    @Test
    public void whenInvalidInput_thenReturns400AndValidationErrors() throws Exception {
        SignupRequestDTO invalidRequest = new SignupRequestDTO();
        invalidRequest.setName("");
        invalidRequest.setEmail("invalidemail");
        invalidRequest.setPassword("short");

        mockMvc.perform(post("/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.name").value("Nome é obrigatório"))
                .andExpect(jsonPath("$.email").value("Email deve ser válido"))
                .andExpect(jsonPath("$.password").value("Senha deve ter no minimo 8 caracteres"));
    }


    @Test
    public void whenInvalidInput_NotBlank_thenReturns400AndValidationErrors() throws Exception {
        SignupRequestDTO invalidRequest = new SignupRequestDTO();
        invalidRequest.setName("");
        invalidRequest.setEmail("");
        invalidRequest.setPassword("");

        mockMvc.perform(post("/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.name").value("Nome é obrigatório"))
                .andExpect(jsonPath("$.email").value("Email é obrigatório"))
                .andExpect(jsonPath("$.password").value("Senha é obrigatório"));
    }

    @Test
    public void whenInvalidInput_NotNull_thenReturns400AndValidationErrors() throws Exception {
        SignupRequestDTO invalidRequest = new SignupRequestDTO();

        mockMvc.perform(post("/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.name").value("Nome é obrigatório"))
                .andExpect(jsonPath("$.email").value("Email é obrigatório"))
                .andExpect(jsonPath("$.password").value("Senha é obrigatório"));
    }
}
