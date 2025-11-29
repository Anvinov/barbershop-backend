package co.edu.unicauca.client_service.infra.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class ClientResponseDTO {
    private Long id;
    private String name;
    private String phone;
    private String email;
    private boolean available;
}
