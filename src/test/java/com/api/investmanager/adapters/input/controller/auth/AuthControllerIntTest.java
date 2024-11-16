package com.api.investmanager.adapters.input.controller.auth;

import com.api.investmanager.adapters.input.controller.auth.dto.SignupRequestDTO;
import com.api.investmanager.core.application.port.input.user.UserFacade;
import com.api.investmanager.infra.auth.JwtAuthenticationFilter;
import com.api.investmanager.infra.config.SecurityConfig;
import com.api.investmanager.infra.config.WebConfig;
import com.api.investmanager.infra.config.properties.AuthProperties;
import com.api.investmanager.infra.config.properties.CorsProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthController.class)
@Import({SecurityConfig.class, JwtAuthenticationFilter.class, CorsProperties.class, AuthProperties.class, WebConfig.class})
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

        performSignupTest(
                invalidRequest,
                status().isBadRequest(),
                new String[]{"name", "email", "password"},
                new String[]{"Nome é obrigatório", "Email deve ser válido", "Senha deve ter no minimo 8 caracteres"}
        );
    }

    @Test
    public void whenInvalidInput_passwordNumber_thenReturns400AndValidationErrors() throws Exception {
        SignupRequestDTO invalidRequest = new SignupRequestDTO();
        invalidRequest.setName("");
        invalidRequest.setEmail("invalidemail");
        invalidRequest.setPassword("57");

        performSignupTest(
                invalidRequest,
                status().isBadRequest(),
                new String[]{"password", "name", "email"},
                new String[]{"Senha deve ter no minimo 8 caracteres", "Nome é obrigatório", "Email deve ser válido"}
        );
    }

    @Test
    public void whenInvalidInput_NotBlank_thenReturns400AndValidationErrors() throws Exception {
        SignupRequestDTO invalidRequest = new SignupRequestDTO();
        invalidRequest.setName("");
        invalidRequest.setEmail("");
        invalidRequest.setPassword("");

        performSignupTest(
                invalidRequest,
                status().isBadRequest(),
                new String[]{"password", "name", "email"},
                new String[]{"Senha é obrigatório", "Nome é obrigatório", "Email é obrigatório"}
        );
    }

    @Test
    public void whenInvalidInput_NotNull_thenReturns400AndValidationErrors() throws Exception {
        SignupRequestDTO invalidRequest = new SignupRequestDTO();

        performSignupTest(
                invalidRequest,
                status().isBadRequest(),
                new String[]{"name", "email", "password"},
                new String[]{"Nome é obrigatório", "Email é obrigatório", "Senha é obrigatório"}
        );
    }

    private void performSignupTest(SignupRequestDTO requestDTO, ResultMatcher statusMatcher, String[] fields, String[] messages) throws Exception {
        var resultActions = mockMvc.perform(post("/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(statusMatcher);

        for (int i = 0; i < fields.length; i++) {
            String fieldMatch = "$.fieldErrors[?(@.field == '" + fields[i];
            String getMessage = "')].message";
            resultActions.andExpect(jsonPath( fieldMatch + getMessage ).value(messages[i]));
        }
    }
}
