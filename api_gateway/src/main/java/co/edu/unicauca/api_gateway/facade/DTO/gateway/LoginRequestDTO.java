package co.edu.unicauca.api_gateway.facade.DTO.gateway;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDTO {
    @NotBlank
    private String email;

    @NotBlank
    private String password;

}
