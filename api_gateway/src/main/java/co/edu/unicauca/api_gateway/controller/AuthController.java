package co.edu.unicauca.api_gateway.controller;

import co.edu.unicauca.api_gateway.facade.DTO.auth.request.SignUpBarberRequestDTO;
import co.edu.unicauca.api_gateway.facade.DTO.auth.request.SignupClientRequestDTO;
import co.edu.unicauca.api_gateway.facade.DTO.auth.response.JwtResponseDTO;
import co.edu.unicauca.api_gateway.facade.DTO.auth.request.LoginRequestDTO;
import co.edu.unicauca.api_gateway.facade.DTO.auth.response.MessageResponseDTO;
import co.edu.unicauca.api_gateway.facade.DTO.client.request.ClientRequestDTO;
import co.edu.unicauca.api_gateway.facade.service.AuthService;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials = "true")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequestDTO loginRequest) {
        JwtResponseDTO token = this.authService.authenticateUser(loginRequest);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/signup/client")
    public ResponseEntity<?> registerClient(@Valid @RequestBody SignupClientRequestDTO signUpRequest) {
        MessageResponseDTO response = authService.registerClient(signUpRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/signup/barber")
    public ResponseEntity<?> registerBarber(@Valid @RequestBody SignUpBarberRequestDTO signUpRequest) {
        MessageResponseDTO response = authService.registerBarber(signUpRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/update/{id}")
    @PreAuthorize("hasRole('CLIENT') or hasRole('BARBER') or hasRole('ADMIN')")
    public ResponseEntity<?> updateUser(
            @Valid @PathVariable Long id,
            @Valid @RequestBody ClientRequestDTO request) {
        MessageResponseDTO response = authService.updateUser(id, request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/password/{id}")
    @PreAuthorize("hasRole('CLIENT') or hasRole('BARBER') or hasRole('ADMIN')")
    public ResponseEntity<?> changePassword(
            @Valid @PathVariable Long id,
            @Valid @RequestParam String pass) {
        MessageResponseDTO response = authService.updatePassword(id, pass);
        return ResponseEntity.ok(response);
    }
}
