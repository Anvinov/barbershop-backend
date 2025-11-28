package co.edu.unicauca.api_gateway.facade.DTO.client;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ClientRequestDTO {
    private String name;
    private String phone;
    private String email;
}
