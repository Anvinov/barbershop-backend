package co.edu.unicauca.reservation_service.infra.dto.client.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ClientResponseDTO {
    private Long id;
    private String name;
    private String phone;
    private String email;
    private boolean available;
}
