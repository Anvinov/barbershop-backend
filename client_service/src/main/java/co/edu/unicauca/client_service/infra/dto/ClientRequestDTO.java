package co.edu.unicauca.client_service.infra.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class ClientRequestDTO {
    @NotBlank
    private String name;

    @NotBlank
    private String phone;

    @NotBlank
    private String email;
}
