package co.edu.unicauca.api_gateway.facade.service;


import co.edu.unicauca.api_gateway.facade.DTO.gateway.LoginRequestDTO;
import co.edu.unicauca.api_gateway.facade.DTO.gateway.SignupRequestDTO;
import co.edu.unicauca.api_gateway.facade.DTO.gateway.JwtResponseDTO;
import co.edu.unicauca.api_gateway.facade.DTO.gateway.MessageResponseDTO;

public interface AuthInt {
    JwtResponseDTO authenticateUser(LoginRequestDTO loginRequest);
    MessageResponseDTO registerUser(SignupRequestDTO signUpRequest);
}
