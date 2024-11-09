package com.api.investmanager.adapters.input.controller.auth;

import com.api.investmanager.adapters.input.controller.auth.dto.SigninRequestDTO;
import com.api.investmanager.adapters.input.controller.auth.dto.SigninResponseDTO;
import com.api.investmanager.adapters.input.controller.auth.dto.SignupRequestDTO;
import com.api.investmanager.core.application.port.input.user.UserFacade;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final UserFacade userFacade;

    @Autowired
    public AuthController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @PostMapping("/signup")
    public ResponseEntity<Void> signupUser(@Valid @RequestBody SignupRequestDTO requestDTO) {
        userFacade.createUser(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/signin")
    public ResponseEntity<SigninResponseDTO> signinUser(@Valid @RequestBody SigninRequestDTO requestDTO) {
        String authorization = userFacade.authUser(requestDTO);
        return ResponseEntity.ok(new SigninResponseDTO(authorization));
    }
}
