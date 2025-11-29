package co.edu.unicauca.api_gateway.facade.service;


import co.edu.unicauca.api_gateway.facade.DTO.auth.request.SignUpBarberRequestDTO;
import co.edu.unicauca.api_gateway.facade.DTO.auth.request.SignupClientRequestDTO;
import co.edu.unicauca.api_gateway.facade.DTO.auth.response.JwtResponseDTO;
import co.edu.unicauca.api_gateway.facade.DTO.auth.request.LoginRequestDTO;
import co.edu.unicauca.api_gateway.facade.DTO.auth.response.MessageResponseDTO;
import co.edu.unicauca.api_gateway.facade.DTO.client.request.ClientRequestDTO;

public interface AuthService {
    JwtResponseDTO authenticateUser(LoginRequestDTO loginRequest);
    MessageResponseDTO registerClient(SignupClientRequestDTO signUpRequest);
    MessageResponseDTO registerBarber(SignUpBarberRequestDTO signUpRequest);
    MessageResponseDTO updateUser(Long id, ClientRequestDTO clientRequest);
    MessageResponseDTO updatePassword(Long id, String password);
}
