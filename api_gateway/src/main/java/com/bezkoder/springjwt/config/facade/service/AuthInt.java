package com.bezkoder.springjwt.config.facade.service;

import com.bezkoder.springjwt.config.facade.DTO.request.LoginRequestDTO;
import com.bezkoder.springjwt.config.facade.DTO.request.SignupRequestDTO;
import com.bezkoder.springjwt.config.facade.DTO.response.JwtResponseDTO;
import com.bezkoder.springjwt.config.facade.DTO.response.MessageResponseDTO;

public interface AuthInt {
    JwtResponseDTO authenticateUser( LoginRequestDTO loginRequest);
    MessageResponseDTO registerUser(SignupRequestDTO signUpRequest);
}
