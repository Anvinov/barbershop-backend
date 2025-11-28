package co.edu.unicauca.api_gateway.facade.service;


import co.edu.unicauca.api_gateway.facade.DTO.auth.*;
import co.edu.unicauca.api_gateway.facade.DTO.client.ClientRequestDTO;

public interface AuthService {
    JwtResponseDTO authenticateUser(LoginRequestDTO loginRequest);
    MessageResponseDTO registerClient(SignupClientRequestDTO signUpRequest);
    MessageResponseDTO registerBarber(SignUpBarberRequestDTO signUpRequest);
    MessageResponseDTO updateUser(Long id, ClientRequestDTO clientRequest);
    MessageResponseDTO updatePassword(Long id, String password);
}
