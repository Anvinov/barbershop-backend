package co.edu.unicauca.api_gateway.controller;

import co.edu.unicauca.api_gateway.facade.DTO.gateway.LoginRequestDTO;
import co.edu.unicauca.api_gateway.facade.DTO.gateway.SignupRequestDTO;
import co.edu.unicauca.api_gateway.facade.DTO.gateway.JwtResponseDTO;
import co.edu.unicauca.api_gateway.facade.DTO.gateway.MessageResponseDTO;
import co.edu.unicauca.api_gateway.facade.service.AuthImpl;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials = "true")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthImpl objAuthImpl;

    public AuthController(AuthImpl objAuthImpl) {
        this.objAuthImpl = objAuthImpl;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequestDTO loginRequest) {
        JwtResponseDTO token = this.objAuthImpl.authenticateUser(loginRequest);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequestDTO signUpRequest) {
        MessageResponseDTO response = objAuthImpl.registerUser(signUpRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
